package com.rtsim.engine.graphics.raytracing;

import java.util.Collection;
import com.rtsim.engine.IntersectionTester;
import com.rtsim.engine.graphics.view.Viewport;
import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;
import com.rtsim.engine.settings.Settings;

public abstract class Raytracer {
    private RayPool pool;
    private Viewport projection;

    protected Raytracer(Viewport projection) {
        this.projection = projection;
    }

    public void setViewport(Viewport viewport) {
        projection = viewport;
    }

    private void buildRayPool(int rayCount, int rayBounces) {
        pool = new RayPool();
        for (int r = 0; r < rayCount; r++) {
            pool.pushRay(projection.createRay(rayBounces), true);
        }
    }
    
    private void makeUpdates(RayPool pool, Ray ray, Body body, BodyIntersection intersection,
            Collection<Body> bodies, Collection<Light> lights) {
        // Create rays for reflection, refraction, etc. 
        for (BodyBehavior behavior : body.getBehaviors()) {
            for (Ray childRay : behavior.onIntersection(ray, body, intersection)) {
                pool.pushRay(childRay, true);
            }
        }
        // Check if the intersection can reach lights for illumination.
        for (Light light : lights) {
            if (IntersectionTester.canReach(bodies, intersection.getIntersectionLocation(), light.getLocation())
                    && projection.canReach(bodies, intersection.getIntersectionLocation())) {
                projection.updateView(intersection.getIntersectionLocation(), body.getMaterial().interact(light));
            }
        }

    }

    protected final void traceRay(Collection<Body> bodies, Collection<Light> lights, RayPool pool, Ray ray) {
        BodyIntersection closestIntersection = IntersectionTester.findClosestIntersection(bodies, ray);
        if (closestIntersection != null) {
            makeUpdates(pool, ray, closestIntersection.getBody(), closestIntersection, bodies, lights);
        }
    }

    protected abstract void renderScene(Collection<Body> bodies, Collection<Light> lights, RayPool pool);

    public void render(Collection<Body> bodies, Collection<Light> lights, Settings settings) {
        buildRayPool((Integer) settings.getSetting(Settings.RAY_COUNT_SETTING), (Integer) settings.getSetting(Settings.RAY_BOUNCES_SETTING));
        renderScene(bodies, lights, pool);
    }
}

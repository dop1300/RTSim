package com.rtsim.engine.graphics.raytracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.rtsim.engine.IntersectionTester;
import com.rtsim.engine.graphics.SceneSettings;
import com.rtsim.engine.graphics.ViewUpdate;
import com.rtsim.engine.graphics.Viewport;
import com.rtsim.engine.graphics.camera.Projection;
import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.graphics.light.strategy.LightingStrategy;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;
import com.rtsim.engine.graphics.raytracing.behavior.RaytraceStep;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public abstract class Raytracer {
    private LightingStrategy[] lightingStrategies;
    private Viewport viewport;
    protected RayPool pool;
    private Projection projection;

    protected Raytracer(Viewport viewport, Projection projection) {
        this.viewport = viewport;
        this.projection = projection;
    }

    private void buildRayPool(int rayCount) {
        pool = new RayPool();
        for (int r = 0; r < rayCount; r++) {
            pool.pushRay(projection.createRay(), true);
        }
    }
    

    private void update(RayPool pool, RaytraceStep raytraceStep) {
        viewport.updateView(raytraceStep.viewUpdates);
        for (Ray addedRay : raytraceStep.createdRays) {
            if (addedRay.hasBouncesRemaining()) {
                pool.pushRay(addedRay, true);
            }
        }
    }

    private RaytraceStep makeUpdates(Ray ray, Body body, BodyIntersection intersection,
            Collection<Body> bodies, Collection<Light> lights) {
        // Create rays for reflection, refraction, etc.
        ArrayList<Ray> rays = new ArrayList<>();
        for (BodyBehavior behavior : body.getBehaviors()) {
            rays.addAll(Arrays.asList(behavior.onIntersection(ray, body, intersection)));
        }
        // Check if the intersection can reach lights for illumination.
        for (Light light : lights) {
            if (IntersectionTester.canReach(bodies, light.getLocation())
                    && perspective) {
                // TODO illuminate
            }
        }
        return new Ray

    }

    protected final void traceRay(Collection<Body> bodies, RayPool pool, Ray ray) {
        for (Body body : bodies) {
            BodyIntersection intersection = body.intersection(ray);
            if (intersection != null) {
                update(pool, makeUpdates(pool, ray, body, intersection));
            }
        }
    }

    protected abstract void renderScene(Collection<Body> bodies);

    public void render(Collection<Body> bodies, Collection<Light> lights, SceneSettings settings) {
        buildRayPool(settings.getRaysPerLight());
        renderScene(bodies);
    }
}

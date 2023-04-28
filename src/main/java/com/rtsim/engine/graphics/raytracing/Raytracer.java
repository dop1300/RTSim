package com.rtsim.engine.graphics.raytracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.rtsim.engine.graphics.SceneSettings;
import com.rtsim.engine.graphics.ViewUpdate;
import com.rtsim.engine.graphics.Viewport;
import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.graphics.light.strategy.LightingStrategy;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;
import com.rtsim.engine.graphics.raytracing.behavior.RaytraceStep;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public abstract class Raytracer {
    private Viewport viewport;
    private List<RayPool> pools;
    private Object lock;

    protected Raytracer(Viewport viewport) {
        this.viewport = viewport;
        lock = new Object();
    }

    private void buildScene(Collection<Light> lights, int raysPerLight) {
        pools = new ArrayList<>();
        for (Light light : lights) {
            pools.add(buildRayPool(light, raysPerLight));
        }
    }

    protected RayPool getRayPool() {
        synchronized(lock) {
            while(pools.isEmpty()) {
                RayPool pool = pools.get(0);
                if (pool.hasRaysRemaining())
                    return pool;
                else
                    pools.remove(0);
                
            }
            return null;
        }
    }

    private RayPool buildRayPool(Light light, int raysPerLight) {
        RayPool pool = new RayPool(light.getLightingStrategies(), light.getRayBehaviors(), light.getIntensity(), light.getColor());
        for (int r = 0; r < raysPerLight; r++) {
            pool.pushRay(light.createRay(), true);
        }
        return pool;
    }
    

    private void update(RayPool pool, RaytraceStep raytraceStep) {
        viewport.updateView(raytraceStep.viewUpdates);
        for (Ray addedRay : raytraceStep.createdRays) {
            if (addedRay.hasBouncesRemaining()) {
                pool.pushRay(addedRay, true);
            }
        }
    }

    private RaytraceStep makeUpdates(RayPool pool, Ray ray, Body body, BodyIntersection intersection) {
        List<ViewUpdate> updates = new LinkedList<>();
        for (LightingStrategy strategy : pool.getLightingStrategies()) {
            updates.addAll(Arrays.asList(strategy.update(ray, body, intersection, pool.getIntensity(), pool.getColor())));
        }
        List<Ray> rays = new LinkedList<>();
        for (BodyBehavior behavior : pool.getRayBehaviors()) {
            rays.addAll(Arrays.asList(behavior.onIntersection(ray, body, intersection)));
        }
        return new RaytraceStep(updates.toArray(new ViewUpdate[0]), rays.toArray(new Ray[0]));
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
        buildScene(lights, settings.raysPerLight);
        renderScene(bodies);
    }
}

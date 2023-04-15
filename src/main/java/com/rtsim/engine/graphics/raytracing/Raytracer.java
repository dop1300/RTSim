package com.rtsim.engine.graphics.raytracing;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.rtsim.engine.graphics.ViewUpdate;
import com.rtsim.engine.graphics.Viewport;
import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.graphics.light.strategy.LightingStrategy;
import com.rtsim.engine.graphics.raytracing.behavior.RayBehavior;
import com.rtsim.engine.graphics.raytracing.behavior.RaytraceStep;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public abstract class Raytracer {
    public static final long POOL_WAIT = 50;
    private Object lock;
    private LinkedList<Ray> rayPool;
    private int activeRays;
    private Viewport viewport;

    private LightingStrategy[] lightingStrategies;
    private RayBehavior[] rayBehaviors;

    protected Raytracer(Viewport viewport) {
        lock = new Object();
        rayPool = null;
        activeRays = 0;
        this.viewport = viewport;
    }

    public void buildRayPool(Light[] lights, int raysPerLight) {
        rayPool = new LinkedList<>();
        activeRays = lights.length * raysPerLight;
        for (Light light : lights) {
            for (int r = 0; r < raysPerLight; r++) {
                rayPool.add(light.createRay());
            }
        }
    }
    
    protected Ray popRay() {
        if (activeRays > 0) {
            while(rayPool.isEmpty()) {
                try {
                    Thread.sleep(POOL_WAIT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized(lock) {
                return rayPool.remove();
            }
        } else
            return null;
    }

    protected void pushRay(Ray ray) {
        synchronized(lock) {
            rayPool.add(ray);
        }
    }

    private void update(RaytraceStep raytraceStep) {
        viewport.updateView(raytraceStep.viewUpdates);
        for (Ray addedRay : raytraceStep.createdRays) {
            if (addedRay.hasBouncesRemaining()) {
                pushRay(addedRay);
            }
        }
    }

    private RaytraceStep makeUpdates(Ray ray, Body body, BodyIntersection intersection) {
        List<ViewUpdate> updates = new LinkedList<>();
        for (LightingStrategy strategy : lightingStrategies) {
            updates.addAll(Arrays.asList(strategy.update(ray, body, intersection)));
        }
        List<Ray> rays = new LinkedList<>();
        for (RayBehavior behavior : rayBehaviors) {
            rays.addAll(Arrays.asList(behavior.onIntersection(ray, body, intersection)));
        }
        return new RaytraceStep(updates.toArray(new ViewUpdate[0]), rays.toArray(new Ray[0]));
    }

    protected void traceRay(Collection<Body> bodies, Ray ray) {
        for (Body body : bodies) {
            BodyIntersection intersection = body.intersection(ray);
            if (intersection != null) {
                update(makeUpdates(ray, body, intersection));
            }
        }
    }

    public abstract void buildScene(Collection<Body> bodies);

}

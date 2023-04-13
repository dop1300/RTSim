package com.rtsim.engine.graphics.raytracing;

import java.util.LinkedList;
import java.util.List;

import com.rtsim.engine.graphics.Viewport;
import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.graphics.raytracing.behavior.RaytraceStep;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public abstract class Raytracer {
    public static final long POOL_WAIT = 50;
    private Object lock;
    private LinkedList<Ray> rayPool;
    private int activeRays;
    private Viewport viewport;

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

    protected void traceRay(List<Body> bodies, Ray ray) {
        for (Body body : bodies) {
            BodyIntersection intersection = body.intersection(ray);
            if (intersection != null) {
                update(ray.makeUpdates(body, intersection));
            }
        }
    }

    public abstract void buildScene(List<Body> bodies);

}

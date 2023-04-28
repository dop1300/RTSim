package com.rtsim.engine.graphics.raytracing;

import java.util.Collection;
import java.util.List;

import com.rtsim.engine.graphics.Viewport;
import com.rtsim.engine.physics.body.Body;

public class SimpleRaytracer extends Raytracer {

    public SimpleRaytracer(Viewport viewport) {
        super(viewport);
    }


    @Override
    public void renderScene(Collection<Body> bodies) {
        try {
            RayPool pool = getRayPool();
            while(pool != null) {
                while(pool.hasRaysRemaining()) {
                    traceRay(bodies, pool, pool.popRay());
                }
                pool = getRayPool();
            }
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
}
package com.rtsim.engine.graphics.raytracing;

import java.util.Collection;
import com.rtsim.engine.graphics.view.Viewport;
import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.physics.body.Body;

public class SimpleRaytracer extends Raytracer {

    public SimpleRaytracer(Viewport projection) {
        super(projection);
    }


    @Override
    public void renderScene(Collection<Body> bodies, Collection<Light> lights, RayPool pool) {
        try {
            while(pool.hasRaysRemaining()) {
                traceRay(bodies, lights, pool, pool.popRay());
            }
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
}
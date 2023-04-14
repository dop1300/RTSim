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
    public void buildScene(Collection<Body> bodies) {
        Ray ray;
        while ((ray = popRay()) != null) {
            traceRay(bodies, ray);
        }
    }
    
}
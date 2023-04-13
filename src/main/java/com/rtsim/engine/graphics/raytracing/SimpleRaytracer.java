package com.rtsim.engine.graphics.raytracing;

import java.util.List;

import com.rtsim.engine.graphics.Viewport;
import com.rtsim.engine.physics.body.Body;

public class SimpleRaytracer extends Raytracer {

    protected SimpleRaytracer(Viewport viewport) {
        super(viewport);
    }

    @Override
    public void buildScene(List<Body> bodies) {
        Ray ray;
        while ((ray = popRay()) != null) {
            traceRay(bodies, ray);
        }
    }
    
}
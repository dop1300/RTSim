package com.rtsim.engine.graphics.light.strategy;

import com.rtsim.engine.graphics.ViewUpdate;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public class ForegroundLightStrategy implements LightingStrategy {
    
    public ViewUpdate[] update(Ray ray, Body body, BodyIntersection intersection) {
        return new ViewUpdate[] {
            new ViewUpdate(intersection.getIntersectionLocation(), ray.getIntensity(), body.getColor(intersection.getIntersectionLocation()), true)
        };
    }
}

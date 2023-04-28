package com.rtsim.engine.graphics.light.strategy;

import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.ViewUpdate;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public class ForegroundLightStrategy implements LightingStrategy {
    public static final float INTERSECTION_MIX_FACTOR = 0.5f;
    
    public ViewUpdate[] update(Ray ray, Body body, BodyIntersection intersection, float intensity, Color color) {
        return new ViewUpdate[] {
            new ViewUpdate(intersection.getIntersectionLocation(), intensity,
                body.getColor(intersection.getIntersectionLocation()).mix(color, INTERSECTION_MIX_FACTOR), true)
        };
    }
}

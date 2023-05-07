package com.rtsim.engine.graphics.light.strategy;

import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.LightingUpdate;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public class SpecularLightStrategy implements LightingStrategy{
    private float mixFactor;
    
    public SpecularLightStrategy(float mixFactor) {
        this.mixFactor = mixFactor;
    }

    public LightingUpdate[] update(Ray ray, Body body, BodyIntersection intersection, float intensity, Color color) {
        return new LightingUpdate[] {
            new LightingUpdate(intersection.getIntersectionLocation(), intensity, 
                body.getMaterial().getSpecularColor().mix(color, mixFactor))
        };
    }

}

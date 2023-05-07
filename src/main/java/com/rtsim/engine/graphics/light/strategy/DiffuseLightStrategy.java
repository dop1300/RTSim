package com.rtsim.engine.graphics.light.strategy;

import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.LightingUpdate;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

/**
 * Adds diffuse light to the lit location.
 */
public class DiffuseLightStrategy implements LightingStrategy {

    public LightingUpdate[] update(Ray ray, Body body, BodyIntersection intersection, float intensity, Color color) {
        return new LightingUpdate[] {
            new LightingUpdate(intersection.getIntersectionLocation(), intensity, body.getMaterial().getDiffuseColor())
        };
    }
}

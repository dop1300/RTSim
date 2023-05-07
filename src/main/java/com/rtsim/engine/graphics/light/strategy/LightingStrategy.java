package com.rtsim.engine.graphics.light.strategy;

import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.LightingUpdate;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public interface LightingStrategy {
    
    LightingUpdate[] update(Ray ray, Body body, BodyIntersection intersection, float intensity, Color color);

}

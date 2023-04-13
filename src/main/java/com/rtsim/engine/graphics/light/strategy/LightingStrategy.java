package com.rtsim.engine.graphics.light.strategy;

import com.rtsim.engine.graphics.ViewUpdate;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public interface LightingStrategy {
    
    ViewUpdate[] update(Ray ray, Body body, BodyIntersection intersection);

}

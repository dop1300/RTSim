package com.rtsim.engine.graphics.raytracing.behavior;

import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public interface BodyBehavior {
    
    Ray[] onIntersection(Ray ray, Body body, BodyIntersection intersection);
    
}

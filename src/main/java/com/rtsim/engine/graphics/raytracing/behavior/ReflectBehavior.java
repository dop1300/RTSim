package com.rtsim.engine.graphics.raytracing.behavior;

import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public class ReflectBehavior implements BodyBehavior {

    @Override
    public Ray[] onIntersection(Ray ray, Body body, BodyIntersection intersection) {
        ray.setDirection(ray.getDirection().subtract(intersection.getIntersectionNormal().scale(2 * intersection.getIntersectionNormal().dot(ray.getDirection()))));
        ray.setLocation(intersection.getIntersectionLocation());
        ray.decreaseBounces();  
        return new Ray[] {ray};
    }

}

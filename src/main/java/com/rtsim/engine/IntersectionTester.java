package com.rtsim.engine;

import java.util.Collection;

import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public class IntersectionTester {
    
    public static BodyIntersection intersects(Collection<Body> bodies, Ray ray) {
        for (Body body : bodies) {
            BodyIntersection intersection = body.intersection(ray);
            if (intersection != null)
                return intersection;
        }
        return null;
    }

    public static boolean canReach(Collection<Body> bodies, VectorD location) {
        return false;
    }
}

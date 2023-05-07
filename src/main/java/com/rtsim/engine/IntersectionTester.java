package com.rtsim.engine;

import java.util.Collection;

import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public class IntersectionTester {

    private IntersectionTester() { }
    
    public static BodyIntersection intersects(Collection<Body> bodies, Ray ray) {
        for (Body body : bodies) {
            BodyIntersection intersection = body.intersection(ray);
            if (intersection != null)
                return intersection;
        }
        return null;
    }

    public static boolean canReach(Collection<Body> bodies, VectorD location, VectorD vectorD) {
        return false;
    }

    public static BodyIntersection findClosestIntersection(Collection<Body> bodies, Ray ray) {
        double closestDistance = Double.POSITIVE_INFINITY;
        BodyIntersection closestIntersection = null;
        // find the closest body, as that's what the ray hits first
        for (Body body : bodies) {
            BodyIntersection intersection = body.intersection(ray);
            if (intersection != null) {
                double distance = intersection.getIntersectionLocation().distance(ray.getLocation());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestIntersection = intersection; 
                }
            }
        }
        return closestIntersection;
    }
}

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

    public static boolean canReach(Collection<Body> bodies, VectorD location, VectorD destination) {
        Ray ray = new Ray(location, destination.subtract(location), 0);
        for (Body body : bodies) {
            if (body.intersection(ray) != null)
                return false;
        }
        return true;
    }

    public static BodyIntersection findClosestIntersection(Collection<Body> bodies, Ray ray) {
        double closestDistance = Double.POSITIVE_INFINITY;
        BodyIntersection closestIntersection = null;
        // find the closest body, as that's what the ray hits first
        for (Body body : bodies) {
            BodyIntersection intersection = body.intersection(ray);
            if (intersection != null) {
                double distance = intersection.getIntersectionLocation().distance(ray.getStart());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestIntersection = intersection; 
                }
            }
        }
        // System.out.println(ray + "\t" + closestDistance);
        return closestIntersection;
    }
}

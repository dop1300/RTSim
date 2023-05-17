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

    public static VectorD calculatePlaneIntersection(VectorD corner, VectorD horizontal, VectorD vertical, VectorD normal, Ray ray) {
        return calculatePlaneIntersection(corner, horizontal, vertical, normal, ray.getStart(), ray.getDirection());
    }
    
    public static VectorD calculatePlaneIntersection(VectorD corner, VectorD horizontal, VectorD vertical, VectorD normal, VectorD rayStart, VectorD rayDirection) {
        double t = corner.subtract(rayStart).dot(normal) / rayDirection.dot(normal);
        if (t != 0) {
            double aDet = new MatrixD(new VectorD[] {horizontal, vertical, normal}).determinant();
            if (aDet != 0) {
                VectorD b = rayStart.add(rayDirection.scale(t));
                double ut = new MatrixD(new VectorD[] {b, vertical, normal}).determinant() / aDet;
                double vt = new MatrixD(new VectorD[] {horizontal, b, normal}).determinant() / aDet;
                double nt = new MatrixD(new VectorD[] {horizontal, vertical, b}).determinant() / aDet;
                return new VectorD(new double[] {ut, vt, nt});
            }
        }
        return null;
        
    }
}

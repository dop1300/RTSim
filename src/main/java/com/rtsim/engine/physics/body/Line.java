package com.rtsim.engine.physics.body;

import com.rtsim.engine.VectorD;

public class Line {
    public static final float INTERSECTION_TOLERANCE = (float) 1E-3;
    private VectorD a, b;

    public Line(VectorD a, VectorD b) {
        this.a = a;
        this.b = b;
    }

    // public boolean intersects(Line other) {
    //     double epsilon = (float) 1e-9;
    //     float x = (float) ((a.getY()-other.a.getX()*(other.b.getY() - other.a.getY())/(other.b.getX() - other.a.getX() + epsilon) + a.getX()*(b.getY() - a.getY())/(b.getX() - a.getX() + epsilon))/((b.getY() - a.getY())/(b.getX() - a.getX() + epsilon) - (other.b.getY() - other.a.getY())/(other.b.getX() - other.a.getX() + epsilon)));
    //     float y = (float) ((b.getY() - a.getY())/(b.getX() - a.getX() + epsilon)*(x - a.getX()) + a.getY());
    //     VectorD intersection = new VectorD(x, y);
    //     return validateIntersection(intersection, a, b, INTERSECTION_TOLERANCE) && validateIntersection(intersection, other.a, other.b, INTERSECTION_TOLERANCE);
    // }

    // private boolean validateIntersection(VectorD intersection, VectorD pointOne, VectorD pointTwo, float tolerance) {
    //     float intersectionSum = intersection.distance(pointOne) + intersection.distance(pointTwo);
    //     float segmentLength = pointOne.distance(pointTwo);
    //     return Math.abs(segmentLength - intersectionSum) < tolerance;
    // }



}

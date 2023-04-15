package com.rtsim.engine.physics.body;

import com.rtsim.engine.Vector2F;

public class Line {
    public static final float INTERSECTION_TOLERANCE = (float) 1E-3;
    private Vector2F a, b;

    public Line(Vector2F a, Vector2F b) {
        this.a = a;
        this.b = b;
    }

    public boolean intersects(Line other) {
        double epsilon = (float) 1e-9;
        float x = (float) ((a.getY()-other.a.getX()*(other.b.getY() - other.a.getY())/(other.b.getX() - other.a.getX() + epsilon) + a.getX()*(b.getY() - a.getY())/(b.getX() - a.getX() + epsilon))/((b.getY() - a.getY())/(b.getX() - a.getX() + epsilon) - (other.b.getY() - other.a.getY())/(other.b.getX() - other.a.getX() + epsilon)));
        float y = (float) ((b.getY() - a.getY())/(b.getX() - a.getX() + epsilon)*(x - a.getX()) + a.getY());
        Vector2F intersection = new Vector2F(x, y);
        return validateIntersection(intersection, a, b, INTERSECTION_TOLERANCE) && validateIntersection(intersection, other.a, other.b, INTERSECTION_TOLERANCE);
    }

    private boolean validateIntersection(Vector2F intersection, Vector2F pointOne, Vector2F pointTwo, float tolerance) {
        float intersectionSum = intersection.distance(pointOne) + intersection.distance(pointTwo);
        float segmentLength = pointOne.distance(pointTwo);
        return Math.abs(segmentLength - intersectionSum) < tolerance;
    }



}

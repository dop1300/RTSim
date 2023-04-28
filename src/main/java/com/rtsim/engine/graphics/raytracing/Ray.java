package com.rtsim.engine.graphics.raytracing;


import com.rtsim.engine.Vector2F;
import com.rtsim.engine.graphics.Color;

public class Ray {
    private Vector2F start, direction;
    private int remainingBounces;

    public Ray(Vector2F start, Vector2F direction, int bounces) {
        this.start = start;
        this.direction = direction;
        remainingBounces = bounces;
    }

    public boolean hasBouncesRemaining() {
        return remainingBounces > 0;
    }

    public Vector2F getDirection() {
        return direction;
    }

    public void setDirection(Vector2F direction) {
        this.direction = direction;
    }

    public void decreaseBounces() {
        remainingBounces--;
    }

    public void setLocation(Vector2F location) {
        start = location;
    }

    public Vector2F getStart() {
        return start;
    }

    public Vector2F getLocation() {
        return null;
    }
    
}

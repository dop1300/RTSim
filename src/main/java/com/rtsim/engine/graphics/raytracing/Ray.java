package com.rtsim.engine.graphics.raytracing;


import com.rtsim.engine.Vector2F;
import com.rtsim.engine.graphics.Color;

public class Ray {
    private Vector2F start, direction;
    private int remainingBounces;
    private float intensity;

    public Ray(Vector2F start, Vector2F direction, float intensity, int bounces) {
        this.start = start;
        this.direction = direction;
        this.intensity = intensity;
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
    
    public float getIntensity() {
        return intensity;
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

    public Color getColor() {
        return null;
    }



    
}

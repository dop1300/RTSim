package com.rtsim.engine.graphics.raytracing;


import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;

public class Ray {
    private VectorD start, direction;
    private int remainingBounces;

    public Ray(VectorD start, VectorD direction, int bounces) {
        this.start = start;
        this.direction = direction;
        remainingBounces = bounces;
    }

    public boolean hasBouncesRemaining() {
        return remainingBounces > 0;
    }

    public VectorD getDirection() {
        return direction;
    }

    public void setDirection(VectorD direction) {
        this.direction = direction;
    }

    public void decreaseBounces() {
        remainingBounces--;
    }

    public void setLocation(VectorD location) {
        start = location;
    }

    public VectorD getStart() {
        return start;
    }

    public VectorD getLocation() {
        return null;
    }
    
}

package com.rtsim.engine.graphics;

import java.util.Vector;

import com.rtsim.engine.Vector2F;

public class Ray {
    private Vector2F start, direction;
    private float[] color;

    public Ray(Vector2F start, Vector2F direction) {
        this.start = start;
        this.direction = direction;
    }

    public boolean hasBouncesRemaining() {
        return false;
    }

    
}

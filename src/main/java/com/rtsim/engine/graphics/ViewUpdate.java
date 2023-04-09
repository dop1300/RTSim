package com.rtsim.engine.graphics;

import com.rtsim.engine.Vector2F;

public class ViewUpdate {
    private Vector2F location;
    private float intensity;
    private Color color;
    private boolean foreground;

    public ViewUpdate(Vector2F location, float intensity, Color color, boolean foreground) {
        this.location = location;
        this.intensity = intensity;
        this.color = color;
        this.foreground = foreground;
    }

    public Vector2F getLocation() {
        return location;
    }

    public float getIntensity() {
        return intensity;
    }

    public boolean inForeground() {
        return foreground;
    }

    public Color getColor() {
        return color;
    }
    
}

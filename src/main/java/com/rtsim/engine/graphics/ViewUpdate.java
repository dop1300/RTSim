package com.rtsim.engine.graphics;

import com.rtsim.engine.VectorD;

public class ViewUpdate {
    private VectorD location;
    private float intensity;
    private Color color;
    private boolean foreground;

    public ViewUpdate(VectorD location, float intensity, Color color, boolean foreground) {
        this.location = location;
        this.intensity = intensity;
        this.color = color;
        this.foreground = foreground;
    }

    public VectorD getLocation() {
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

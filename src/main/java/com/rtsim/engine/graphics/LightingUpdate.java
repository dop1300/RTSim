package com.rtsim.engine.graphics;

import com.rtsim.engine.VectorD;

public class LightingUpdate {
    private VectorD location;
    private float intensity;
    private Color color;

    public LightingUpdate(VectorD location, float intensity, Color color) {
        this.location = location;
        this.intensity = intensity;
        this.color = color;
    }

    public VectorD getLocation() {
        return location;
    }

    public float getIntensity() {
        return intensity;
    }

    public Color getColor() {
        return color;
    }
    
}

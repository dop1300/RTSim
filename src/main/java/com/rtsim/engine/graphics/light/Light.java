package com.rtsim.engine.graphics.light;


import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.raytracing.Ray;

public abstract class Light {
    protected VectorD location;
    protected Color color;
    protected float intensity;

    protected Light(VectorD location, Color color, float intensity) {
        this.color = color;
        this.location = location;
        this.intensity = intensity;
    }

    public abstract Ray createRay();
    

    public float getIntensity() {
        return intensity;
    }

    public Color getColor() {
        return color;
    }

    public VectorD getLocation() {
        return location;
    }
    
}

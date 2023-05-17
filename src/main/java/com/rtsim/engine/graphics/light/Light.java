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

    public Color getColor(VectorD other) {
        return color.scale((float) Math.min(0, 1 - intensity/other.distance(location)));
    }

    public VectorD getLocation() {
        return location;
    }
    
}

package com.rtsim.engine.graphics.light;

import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.raytracing.Ray;

public class LightRay extends Ray {
    private float intensity;
    private Color color;

    public LightRay(VectorD start, VectorD direction, int bounces, float intensity, Color color) {
        super(start, direction, bounces);
        this.intensity = intensity;
        this.color = color;
    }
    
}

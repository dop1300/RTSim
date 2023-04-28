package com.rtsim.engine.graphics.light;

import com.rtsim.engine.Vector2F;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.raytracing.Ray;

public class LightRay extends Ray {
    private float intensity;
    private Color color;

    public LightRay(Vector2F start, Vector2F direction, int bounces, float intensity, Color color) {
        super(start, direction, bounces);
        this.intensity = intensity;
        this.color = color;
    }
    
}

package com.rtsim.engine.graphics.light;


import com.rtsim.engine.Vector2F;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.raytracing.Ray;

public abstract class Light {
    private Vector2F location;
    private Color color;
    private float intensity;

    public abstract Ray createRay();
    
}

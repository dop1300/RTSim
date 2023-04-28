package com.rtsim.engine.graphics.light;


import com.rtsim.engine.Vector2F;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.light.strategy.LightingStrategy;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;

public abstract class Light {
    private LightingStrategy[] lightingStrategies;
    private BodyBehavior[] bodyBehaviors;
    protected Vector2F location;
    protected Color color;
    protected float intensity;

    protected Light(Vector2F location, Color color, float intensity, LightingStrategy[] lightingStrategies, BodyBehavior[] bodyBehaviors) {
        this.color = color;
        this.location = location;
        this.intensity = intensity;
        this.bodyBehaviors = bodyBehaviors;
        this.lightingStrategies = lightingStrategies;
    }

    public abstract Ray createRay();

    public LightingStrategy[] getLightingStrategies() {
        return lightingStrategies;
    }

    public BodyBehavior[] getRayBehaviors() {
        return bodyBehaviors;
    }

    public float getIntensity() {
        return intensity;
    }

    public Color getColor() {
        return color;
    }
    
}

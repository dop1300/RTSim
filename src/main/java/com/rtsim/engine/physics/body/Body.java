package com.rtsim.engine.physics.body;

import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.material.Material;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;

public abstract class Body {
    private float percentLightAbsorbed;
    private BodyBehavior[] behaviors;
    private Material material;

    
    protected Body(float percentLightAbsorbed, Material material, BodyBehavior[] behaviors) {
        this.percentLightAbsorbed = percentLightAbsorbed;
        this.behaviors = behaviors;
        this.material = material;
    }

    public float getPercentLightAbsorbed() {
        return percentLightAbsorbed;
    }


    
    public abstract BodyIntersection intersection(Ray ray);

    public Material getMaterial() {
        return material;
    }

    public BodyBehavior[] getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(BodyBehavior[] behaviors) {
        this.behaviors = behaviors;
    }

    public Color getColor(VectorD intersectionLocation) {
        return null;
    }

}

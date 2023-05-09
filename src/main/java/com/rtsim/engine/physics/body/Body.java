package com.rtsim.engine.physics.body;

import com.rtsim.engine.graphics.material.Material;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;

public abstract class Body {
    private BodyBehavior[] behaviors;
    private Material material;

    
    protected Body(Material material, BodyBehavior[] behaviors) {
        this.behaviors = behaviors;
        this.material = material;
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

}

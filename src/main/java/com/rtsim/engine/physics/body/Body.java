package com.rtsim.engine.physics.body;

import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;
import com.rtsim.engine.graphics.texturing.Texture;

public abstract class Body {
    private float percentLightAbsorbed;
    private BodyBehavior[] behaviors;

    
    protected Body(float percentLightAbsorbed, VectorD location, BodyBehavior[] behaviors) {
        this.percentLightAbsorbed = percentLightAbsorbed;
        this.behaviors = behaviors;
    }

    public float getPercentLightAbsorbed() {
        return percentLightAbsorbed;
    }


    
    public abstract BodyIntersection intersection(Ray ray);
    public abstract Color getColor(VectorD location, Texture texture);
    public VectorD getLocation() {
        return null; // TODO transforms
    }

    public BodyBehavior[] getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(BodyBehavior behaviors) {
        this.behaviors = behaviors;
    }

}

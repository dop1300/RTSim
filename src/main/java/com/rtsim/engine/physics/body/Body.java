package com.rtsim.engine.physics.body;

import com.rtsim.engine.Vector2F;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.raytracing.Ray;

public abstract class Body {
    private float percentLightAbsorbed;

    protected Body(float percentLightAbsorbed, Vector2F location) {
        this.percentLightAbsorbed = percentLightAbsorbed;
    }

    public float getPercentLightAbsorbed() {
        return percentLightAbsorbed;
    }
    
    public abstract BodyIntersection intersection(Ray ray);
    public abstract Color getColor(Vector2F location);
    public Vector2F getLocation() {
        return null; // TODO transforms
    }

}

package com.rtsim.engine.physics.body;

import com.rtsim.engine.Vector2F;
import com.rtsim.engine.graphics.Ray;

public abstract class Body {
    private float percentLightAbsorbed;

    protected Body(float percentLightAbsorbed) {
        this.percentLightAbsorbed = percentLightAbsorbed;
    }

    public float getPercentLightAbsorbed() {
        return percentLightAbsorbed;
    }
    public abstract boolean intersects(Ray ray);
    public abstract Vector2F getReflection(Ray ray);
    public abstract Vector2F getRefraction(Ray ray);

}

package com.rtsim.engine.graphics.material;

import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.light.Light;

public class SimpleMaterial implements Material {
    private Color diffuseColor;
    private Color specularColor;
    private float specularMix;

    public SimpleMaterial(Color diffuseColor, Color specularColor, float specularMix) {
        this.diffuseColor = diffuseColor;
        this.specularColor = specularColor;
        this.specularMix = specularMix;
    }

    @Override
    public Color getDiffuseColor() {
        return diffuseColor;
    }

    @Override
    public Color getSpecularColor() {
        return specularColor;
    }

    @Override
    public Color interact(Light light, VectorD location) {
        return light.getColor(location).scale(light.getIntensity()).mix(specularColor, specularMix).add(diffuseColor);
    }
    
}

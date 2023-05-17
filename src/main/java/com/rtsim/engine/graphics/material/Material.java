package com.rtsim.engine.graphics.material;

import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.light.Light;

public interface Material {

    Color getDiffuseColor();
    Color getSpecularColor();
    Color interact(Light light, VectorD location);

}

package com.rtsim.engine.graphics.light;

import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.raytracing.Ray;

public class RadialLight extends Light {

    public RadialLight(VectorD location, Color color, float intensity) {
        super(location, color, intensity);
    }

    @Override
    public Ray createRay() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRay'");
    }
    
}

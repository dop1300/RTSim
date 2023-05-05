package com.rtsim.engine.physics.body;

import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.raytracing.Ray;

public class Polygon extends Body {
    
    protected Polygon(float percentLightAbsorbed, VectorD location, VectorD[] points) {
        super(percentLightAbsorbed, location);
    }

    @Override
    public BodyIntersection intersection(Ray ray) {
        throw new UnsupportedOperationException("Unimplemented method 'intersection'");
    }

    @Override
    public Color getColor(VectorD location) {
        throw new UnsupportedOperationException("Unimplemented method 'getColor'");
    }
    
}

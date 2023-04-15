package com.rtsim.engine.physics.body;

import com.rtsim.engine.Vector2F;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.raytracing.Ray;

public class Polygon extends Body {
    
    protected Polygon(float percentLightAbsorbed, Vector2F location, Vector2F[] points) {
        super(percentLightAbsorbed, location);
    }

    @Override
    public BodyIntersection intersection(Ray ray) {
        throw new UnsupportedOperationException("Unimplemented method 'intersection'");
    }

    @Override
    public Color getColor(Vector2F location) {
        throw new UnsupportedOperationException("Unimplemented method 'getColor'");
    }
    
}

package com.rtsim.engine.physics.body;

import com.rtsim.engine.VectorD;

public class BodyIntersection {
    private VectorD intersectionLocation, intersectionNormal;

    public VectorD getIntersectionLocation() {
        return intersectionLocation;
    }

    public VectorD getIntersectionNormal() {
        return intersectionNormal;
    }

    public Body getBody() {
        return null;
    }
    
}

package com.rtsim.engine.physics.body;

import com.rtsim.engine.VectorD;

public record BodyIntersection(VectorD intersectionLocation, VectorD intersectionNormal) {

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

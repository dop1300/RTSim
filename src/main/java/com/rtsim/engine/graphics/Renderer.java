package com.rtsim.engine.graphics;

import java.util.Collection;
import java.util.List;

import com.rtsim.engine.physics.body.Body;

public class Renderer {
    private Viewport viewport;
    private int rayCount;

    public Renderer(Viewport viewport, int rayCount, float backgroundPenalty) {
        this.viewport = viewport;
        this.rayCount = rayCount;
    }

    // public Color[][] render(List<Body> background, List<Body> foreground) {

        
    // }
}

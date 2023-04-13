package com.rtsim.engine.graphics;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;

public class Renderer {
    private Viewport viewport;
    private Stack<Ray> rayPool;
    private int threadCount;
    private int rayCount;

    public Renderer(Viewport viewport, int rayCount, float backgroundPenalty) {
        this.viewport = viewport;
        this.rayCount = rayCount;
    }


    public Color[][] render(List<Body> background, List<Body> foreground, Light[] lights) {
        final int raysPerThread = rayCount / threadCount;
        return null;
    }
}

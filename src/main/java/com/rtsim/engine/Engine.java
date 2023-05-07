package com.rtsim.engine;

import java.util.HashMap;
import java.util.Map;

import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.SceneSettings;
import com.rtsim.engine.graphics.view.Viewport;
import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.graphics.raytracing.Raytracer;
import com.rtsim.engine.graphics.raytracing.SimpleRaytracer;
import com.rtsim.engine.physics.body.Body;

public class Engine {
    
    public static final double DEFAULT_X_LOCATION = 0,
                                DEFAULT_Y_LOCATION = 0,
                                DEFAULT_X_SIZE = 10,
                                DEFAULT_Y_SIZE = 10,
                                DEFAULT_BACKGROUND_PENALTY = 0.5;
    private Map<String,Light> lights;
    private Map<String,Body> bodies;
    private Raytracer raytracer;
    private SceneSettings settings;
    private Viewport viewport;

    public Engine(Viewport viewport) {
        bodies = new HashMap<>();
        lights = new HashMap<>();
        raytracer = new SimpleRaytracer(viewport);
    }


    public Color[][] createImage() {
        viewport.clearViewport();
        raytracer.render(bodies.values(), lights.values(), settings);
        return viewport.getRender();
    }

    public Map<String, Body> getBodyMap() {
        return bodies;
    }

    public Map<String, Light> getLightMap() {
        return lights;
    }

    public int getXResolution() {
        return viewport.getXResolution();
    }

    public int getYResolution() {
        return viewport.getYResolution();
    }
}

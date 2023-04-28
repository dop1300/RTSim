package com.rtsim.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.SceneSettings;
import com.rtsim.engine.graphics.Viewport;
import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.graphics.raytracing.Raytracer;
import com.rtsim.engine.graphics.raytracing.SimpleRaytracer;
import com.rtsim.engine.physics.body.Body;

public class Engine {
    
    public static final float DEFAULT_X_LOCATION = 0,
                                DEFAULT_Y_LOCATION = 0,
                                DEFAULT_X_SIZE = 10,
                                DEFAULT_Y_SIZE = 10,
                                DEFAULT_BACKGROUND_PENALTY = 0.5f;
    private Map<String,Light> lights;
    private Map<String,Body> bodies;
    private Raytracer raytracer;
    private Viewport viewport;
    private SceneSettings settings;
    private int raysPerLight;

    public Engine(int xResolution, int yResolution, int raysPerLight) {
        bodies = new HashMap<>();
        lights = new HashMap<>();
        viewport = new Viewport(xResolution, yResolution, new Vector2F(DEFAULT_X_LOCATION,
                                    DEFAULT_Y_LOCATION), new Vector2F(DEFAULT_X_SIZE, DEFAULT_Y_SIZE),
                                    DEFAULT_BACKGROUND_PENALTY);
        raytracer = new SimpleRaytracer(viewport);
        this.raysPerLight = raysPerLight;
    }

    public void updateViewport(int xResolution, int yResolution, float xLocation, float yLocation,
                                float xSize, float ySize, float backgroundPenalty) {
        viewport = new Viewport(xResolution, yResolution, new Vector2F(xLocation, yLocation), 
                                    new Vector2F(xSize, ySize), backgroundPenalty);
            
    }

    public Color[][] createImage() {
        viewport.clearViewport();
        raytracer.render(bodies.values(), lights.values(), settings);
        return viewport.getImage();
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

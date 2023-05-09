package com.rtsim;

import java.util.Map;

import com.rtsim.engine.Engine;
import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.settings.Settings;

public class Scene {
    private Engine engine;
    private Settings settings;
    private Map<String, Body> bodies;
    private Map<String, Light> lights;

    public Scene(Engine engine, Settings settings, Map<String, Body> bodies, Map<String, Light> lights) {
        this.engine = engine;
        this.settings = settings;
        this.bodies = bodies;
        this.lights = lights;
    }
    public Engine getEngine() {
        return engine;
    }

    public Settings getSettings() {
        return settings;
    }

    public Map<String, Body> getBodies() {
        return bodies;
    }

    public Map<String, Light> getLights() {
        return lights;
    }
}

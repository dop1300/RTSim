package com.rtsim.engine;

import java.util.Collection;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.view.Viewport;
import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.graphics.raytracing.Raytracer;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.settings.Settings;

public class Engine {
    private Raytracer raytracer;
    private Viewport viewport;

    public Engine(Viewport viewport, Raytracer raytracer) {
        this.raytracer = raytracer;
        this.viewport = viewport;
    }


    public Color[][] createImage(Settings settings, Collection<Body> bodies, Collection<Light> lights) {
        viewport.clearViewport();
        raytracer.render(bodies, lights, settings);
        return viewport.getRender();
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
        raytracer.setViewport(viewport);
    }

    public int getXResolution() {
        return viewport.getXResolution();
    }

    public int getYResolution() {
        return viewport.getYResolution();
    }
}

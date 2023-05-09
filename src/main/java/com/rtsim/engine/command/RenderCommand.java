package com.rtsim.engine.command;

import java.util.Map;

import com.rtsim.Scene;

public class RenderCommand implements Command {

    @Override
    public Object execute(Scene scene, Map<String, Object> inputs) {
        return scene.getEngine().createImage(scene.getSettings(), scene.getBodies().values(), scene.getLights().values());
    }
}

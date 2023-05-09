package com.rtsim.engine.command;

import java.util.Map;

import com.rtsim.Scene;

public class RemoveLightCommand implements Command {

    @Override
    public Object execute(Scene scene, Map<String, Object> inputs) {
        scene.getLights().remove(inputs.get(Command.NAME_KEY));
        return null;
    }

}
package com.rtsim.engine.command;

import java.util.Map;

import com.rtsim.Scene;
import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.light.RadialLight;

public class AddRadialLightCommand implements Command {

    @Override
    public Object execute(Scene scene, Map<String, Object> inputs) {
        scene.getLights().put((String) inputs.get(Command.NAME_KEY), new RadialLight((VectorD) inputs.get(Command.LOCATION_KEY),
            (Color) inputs.get(Command.COLOR_KEY), (Float) inputs.get(Command.INTENSITY)));
        return null;
    }
    
}

package com.rtsim.engine.command;

import java.util.Map;

import com.rtsim.Scene;
import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.view.RectangularProjection;


public class CreateRectangularCameraCommand implements Command {
    @Override
    public Object execute(Scene scene, Map<String, Object> inputs) {
        scene.getEngine().setViewport(new RectangularProjection((Integer) inputs.get(Command.X_RESOLUTION_KEY),
            (Integer) inputs.get(Command.Y_RESOLUTION_KEY),
            (Float) inputs.get(Command.MIX_FACTOR_KEY),
            (Double) inputs.get(Command.FOV_KEY),
            (Double) inputs.get(Command.DISTANCE_KEY),
            (VectorD) inputs.get(Command.LOCATION_KEY),
            (VectorD) inputs.get(Command.ROTATION_KEY)));
        return null;
    }
}
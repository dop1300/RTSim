package com.rtsim.engine.command;

import java.util.Map;

import com.rtsim.Scene;
import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.material.Material;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;
import com.rtsim.engine.physics.body.Triangle;


public class AddTriangleCommand implements Command {

    @Override
    public Object execute(Scene scene, Map<String, Object> inputs) {
        scene.getBodies().put((String) inputs.get(Command.NAME_KEY),
            new Triangle((VectorD[]) inputs.get(Command.LOCATION_KEY),
                (Material) inputs.get(Command.MATERIAL_KEY),
                (BodyBehavior[]) inputs.get(Command.BEHAVIOR_KEY)));
        return null;
    }

}

package com.rtsim.engine.command;

import java.util.Map;

import com.rtsim.Scene;

public interface Command {
    public static final String INTENSITY = "intensity";
    public static final String LOCATION_KEY = "location",
                            COLOR_KEY = "color";
    public static final String NAME_KEY = "name";
    public static final String MATERIAL_KEY = "material";
    public static final String BEHAVIOR_KEY = "behavior";
    public static final String X_RESOLUTION_KEY = "x_res";
    public static final String Y_RESOLUTION_KEY = "y_res";
    public static final String MIX_FACTOR_KEY = "mix_factor";
    public static final String FOV_KEY = "fov";
    public static final String DISTANCE_KEY = "distance";
    public static final String ROTATION_KEY = "rotation";

    Object execute(Scene scene, Map<String, Object> inputs);
}

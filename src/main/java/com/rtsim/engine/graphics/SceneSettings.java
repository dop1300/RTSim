package com.rtsim.engine.graphics;

public class SceneSettings {
    private int raysPerLight;
    
    public SceneSettings(int raysPerLight) {
        this.raysPerLight = raysPerLight;
    }
    
    public int getRaysPerLight() {
        return raysPerLight;
    }

    public void setRaysPerLight(int raysPerLight) {
        this.raysPerLight = raysPerLight;
    }
}

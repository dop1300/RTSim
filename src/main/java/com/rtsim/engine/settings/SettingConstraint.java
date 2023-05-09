package com.rtsim.engine.settings;

public interface SettingConstraint {

    boolean accept(Object old, Object value);
    
}

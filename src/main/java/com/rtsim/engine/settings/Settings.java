package com.rtsim.engine.settings;

import java.util.HashMap;

public class Settings {
    public static final String RAY_COUNT_SETTING = "ray_count",
        RAY_BOUNCES_SETTING = "ray_bounces";
    private HashMap<String, SettingValue> settings;
    
    public Settings() {
        settings = new HashMap<>();
        settings.put(RAY_COUNT_SETTING, new SettingValue(1000000));
        settings.put(RAY_BOUNCES_SETTING, new SettingValue(3));
    }
    
    public Object getSetting(String name) {
        return settings.get(name).getValue();
    }

    public boolean setSetting(String name, Object value) {
        return settings.get(name).setValue(value);
    }

}

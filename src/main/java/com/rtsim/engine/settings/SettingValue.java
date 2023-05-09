package com.rtsim.engine.settings;

public class SettingValue {
    private Object value;
    private SettingConstraint constraint;

    public SettingValue(Object value, SettingConstraint constraint) {
        this.value = value;
        this.constraint = constraint;
    }

    public SettingValue(Object value) {
        this(value, null);
    }
    
    public Object getValue() {
        return value;
    }
    
    public boolean setValue(Object value) {
        if (constraint == null || constraint.accept(this.value, value)) {
            this.value = value;
            return true;
        }
        return false;
    }

}

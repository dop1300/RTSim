package com.rtsim.engine.graphics;

public class Color {
    private float r, g, b;

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color scale(float percent) {
        return new Color(r * percent, g * percent, b * percent);
    }

    public void add(Color other) {
        r = (r + other.r) / 2;
        g = (g + other.g) / 2;
        b = (b + other.b) / 2;
        
    }

    public float getRed() {
        return r;
    }

    public float getGreen() {
        return g;
    }

    public float getBlue() {
        return b;
    }
}

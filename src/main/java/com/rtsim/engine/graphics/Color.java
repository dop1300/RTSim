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

    public Color add(Color other) {
        return new Color((r + other.r) / 2, (g + other.g) / 2, (b + other.b) / 2);
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

    public Color mix(Color color, float factor) {
        float complement = 1 - factor;
        return new Color(r * complement + color.r * factor, g * complement + color.g * factor, b * complement + color.b * factor);
    }

    public String toString() {
        return "Color(r=" + r + ", g=" + g + ", b=" + b + ")";
    }
}

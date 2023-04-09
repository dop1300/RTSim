package com.rtsim.engine;

public class Vector2F {
    private float x, y;

    public Vector2F(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    Vector2F normalize() {
        return scale(1/magnitude());
    }

    Vector2F add(Vector2F other) {
        return new Vector2F(x + other.x, y + other.y);
    }

    Vector2F scale(float scalar) {
        return new Vector2F(x * scalar, y * scalar);
    }

    public Vector2F subtract(Vector2F other) {
        return new Vector2F(x - other.x, y - other.y);
    }

    float dot(Vector2F other) {
        return x * other.x + y * other.y;
    }

    float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }
 
}
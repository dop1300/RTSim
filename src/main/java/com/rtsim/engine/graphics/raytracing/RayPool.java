package com.rtsim.engine.graphics.raytracing;

import java.util.LinkedList;

import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.light.strategy.LightingStrategy;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;

public class RayPool {
    public static final long POOL_WAIT = 50;
    private LightingStrategy[] lightingStrategies;
    private BodyBehavior[] rayBehaviors;
    private LinkedList<Ray> rays;
    private float intensity;
    private Color color;
    private Object lock;
    private int activeRays;

    public RayPool(LightingStrategy[] lightingStrategies, BodyBehavior[] rayBehaviors,
            float intensity, Color color) {
        this.lightingStrategies = lightingStrategies;
        this.rayBehaviors = rayBehaviors;
        this.intensity = intensity;
        this.color = color;
        rays = new LinkedList<>();
        activeRays = 0;
    }

    public Ray popRay() throws InterruptedException {
        if (activeRays > 0) {
            while(rays.isEmpty()) {
                Thread.sleep(POOL_WAIT);
            }
            synchronized(lock) {
                return rays.removeFirst();
            }
        } else {
            return null;
        }
    }

    public void pushRay(Ray ray, boolean changeActiveCount) {
        if (ray.hasBouncesRemaining()) {
            synchronized(lock) {
                if(changeActiveCount)
                    activeRays++;
                rays.addLast(ray);
            }
        } else if (changeActiveCount) {
            activeRays--;
        }
    }


    public float getIntensity() {
        return intensity;
    }

    public Color getColor() {
        return color;
    }

	public BodyBehavior[] getRayBehaviors() {
		return rayBehaviors;
	}

	public LightingStrategy[] getLightingStrategies() {
		return lightingStrategies;
	}

	public boolean hasRaysRemaining() {
		synchronized(lock) {
            return !rays.isEmpty();
        }
	}

    

    
}

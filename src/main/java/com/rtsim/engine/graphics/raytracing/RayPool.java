package com.rtsim.engine.graphics.raytracing;

import java.util.LinkedList;

public class RayPool {
    public static final long POOL_WAIT = 50;
    private LinkedList<Ray> rays;
    private Object lock;
    private int activeRays;

    public RayPool() {
        rays = new LinkedList<>();
        activeRays = 0;
        lock = new Object();
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

	public boolean hasRaysRemaining() {
		synchronized(lock) {
            return !rays.isEmpty();
        }
	}

    

    
}

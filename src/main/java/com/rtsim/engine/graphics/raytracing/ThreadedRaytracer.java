package com.rtsim.engine.graphics.raytracing;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.graphics.view.Viewport;
import com.rtsim.engine.physics.body.Body;


public class ThreadedRaytracer extends Raytracer {
    private int threads; 

    public ThreadedRaytracer(Viewport projection, int threads) {
        super(projection);
        this.threads = threads;
    }

    @Override
    protected void renderScene(Collection<Body> bodies, Collection<Light> lights, RayPool pool) {
        ExecutorService threadPool = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            Runnable t = () -> {
                try {
                    while(pool.hasRaysRemaining()) {
                        traceRay(bodies, lights, pool, pool.popRay());
                    }
                } catch(InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
            threadPool.execute(t);
        }
        threadPool.shutdown();
        // while(threadPool.isShutdown()) {
        //     try {
        //         Thread.sleep(100);
        //     } catch (InterruptedException e) {
        //         Thread.currentThread().interrupt();
        //     }
        // }
    }
    
}

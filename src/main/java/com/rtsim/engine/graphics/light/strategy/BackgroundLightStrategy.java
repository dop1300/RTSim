package com.rtsim.engine.graphics.light.strategy;

import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.LightingUpdate;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public class BackgroundLightStrategy implements LightingStrategy {
    private int updateCount;

    public BackgroundLightStrategy(int updateCount) {
        this.updateCount = updateCount;
    }
    
    public LightingUpdate[] update(Ray ray, Body body, BodyIntersection intersection, float intensity, Color color) {
        LightingUpdate[] updates = new LightingUpdate[updateCount];
        VectorD inverval = intersection.getIntersectionLocation().subtract(ray.getLocation()).scale(1.0 / updateCount);
        for (int step = 0; step < updateCount; step++) {
            updates[step] = new LightingUpdate(ray.getLocation().add(inverval.scale(step)), intensity, color);
        }
        return updates;
    }

}

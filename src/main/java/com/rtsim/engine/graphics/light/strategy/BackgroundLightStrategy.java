package com.rtsim.engine.graphics.light.strategy;

import com.rtsim.engine.Vector2F;
import com.rtsim.engine.graphics.ViewUpdate;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public class BackgroundLightStrategy implements LightingStrategy {
    private int updateCount;

    public BackgroundLightStrategy(int updateCount) {
        this.updateCount = updateCount;
    }
    
    public ViewUpdate[] update(Ray ray, Body body, BodyIntersection intersection) {
        ViewUpdate[] updates = new ViewUpdate[updateCount];
        float dx = (intersection.getIntersectionLocation().getX() - ray.getLocation().getX()) / updateCount;
        float dy = (intersection.getIntersectionLocation().getY() - ray.getLocation().getY()) / updateCount;
        for (int step = 0; step < updateCount; step++) {
            updates[step] = new ViewUpdate(ray.getLocation().add(new Vector2F(dx * step, dy * step)), ray.getLightIntensity(), ray.getColor(), false);
        }
        return updates;
    }

}
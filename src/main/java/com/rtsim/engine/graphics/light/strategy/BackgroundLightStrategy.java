package com.rtsim.engine.graphics.light.strategy;

import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.ViewUpdate;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public class BackgroundLightStrategy implements LightingStrategy {
    private int updateCount;

    public BackgroundLightStrategy(int updateCount) {
        this.updateCount = updateCount;
    }
    
    public ViewUpdate[] update(Ray ray, Body body, BodyIntersection intersection, float intensity, Color color) {
        ViewUpdate[] updates = new ViewUpdate[updateCount];
        float dx = (intersection.getIntersectionLocation().getX() - ray.getLocation().getX()) / updateCount;
        float dy = (intersection.getIntersectionLocation().getY() - ray.getLocation().getY()) / updateCount;
        for (int step = 0; step < updateCount; step++) {
            updates[step] = new ViewUpdate(ray.getLocation().add(new VectorD(dx * step, dy * step)), intensity, color, false);
        }
        return updates;
    }

}

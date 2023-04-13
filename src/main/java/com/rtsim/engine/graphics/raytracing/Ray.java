package com.rtsim.engine.graphics.raytracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.rtsim.engine.Vector2F;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.ViewUpdate;
import com.rtsim.engine.graphics.light.strategy.LightingStrategy;
import com.rtsim.engine.graphics.raytracing.behavior.RayBehavior;
import com.rtsim.engine.graphics.raytracing.behavior.RaytraceStep;
import com.rtsim.engine.physics.body.Body;
import com.rtsim.engine.physics.body.BodyIntersection;

public class Ray {
    private Vector2F start, direction;
    private int remainingBounces;
    private Color color;
    private float lightIntensity;
    private LightingStrategy[] lightingStrategies;
    private RayBehavior[] rayBehaviors;

    public Ray(Vector2F start, Vector2F direction, Color color, float lightIntensity, int bounces) {
        this.start = start;
        this.color = color;
        this.direction = direction;
        this.lightIntensity = lightIntensity;
        remainingBounces = bounces;
    }

    public boolean hasBouncesRemaining() {
        return remainingBounces > 0;
    }

    public ViewUpdate[] backgroundUpdates(Vector2F intersectionLocation, int updateCount) {
        ViewUpdate[] updates = new ViewUpdate[updateCount];
        float dx = (intersectionLocation.getX() - start.getX()) / updateCount;
        float dy = (intersectionLocation.getY() - start.getY()) / updateCount;
        for (int step = 0; step < updateCount; step++) {
            updates[step] = new ViewUpdate(start.add(new Vector2F(dx * step, dy * step)), lightIntensity, color, false);
        }
        return updates;
    }


    public Vector2F getDirection() {
        return direction;
    }

    public void setDirection(Vector2F direction) {
        this.direction = direction;
    }
    
    public RaytraceStep makeUpdates(Body body, BodyIntersection intersection) {
        List<ViewUpdate> updates = new LinkedList<>();
        for (LightingStrategy strategy : lightingStrategies) {
            updates.addAll(Arrays.asList(strategy.update(this, body, intersection)));
        }
        List<Ray> rays = new LinkedList<>();
        for (RayBehavior behavior : rayBehaviors) {
            rays.addAll(Arrays.asList(behavior.onIntersection(null, body, intersection)));
        }
        return new RaytraceStep(updates.toArray(new ViewUpdate[0]), rays.toArray(new Ray[0]));
    }

    public void decreaseBounces() {
        remainingBounces--;
    }
    
    public float getLightIntensity() {
        return lightIntensity;
    }

    public void setLocation(Vector2F location) {
        start = location;
    }

    public Vector2F getLocation() {
        return null;
    }

    public Color getColor() {
        return null;
    }



    
}

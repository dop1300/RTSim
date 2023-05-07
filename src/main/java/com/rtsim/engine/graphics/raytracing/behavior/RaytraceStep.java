package com.rtsim.engine.graphics.raytracing.behavior;

import com.rtsim.engine.graphics.LightingUpdate;
import com.rtsim.engine.graphics.raytracing.Ray;

public class RaytraceStep {
    public final LightingUpdate[] viewUpdates;
    public final Ray[] createdRays;

    public RaytraceStep(LightingUpdate[] updates, Ray[] rays) {
        this.viewUpdates = updates;
        createdRays = rays;

    }

}

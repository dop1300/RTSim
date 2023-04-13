package com.rtsim.engine.graphics.raytracing.behavior;

import com.rtsim.engine.graphics.ViewUpdate;
import com.rtsim.engine.graphics.raytracing.Ray;

public class RaytraceStep {
    public final ViewUpdate[] viewUpdates;
    public final Ray[] createdRays;

    public RaytraceStep(ViewUpdate[] updates, Ray[] rays) {
        this.viewUpdates = updates;
        createdRays = rays;

    }

}

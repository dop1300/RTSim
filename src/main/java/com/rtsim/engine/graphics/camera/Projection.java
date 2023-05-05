package com.rtsim.engine.graphics.camera;

import java.util.Collection;

import com.rtsim.engine.Point2I;
import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.raytracing.Ray;

public interface Projection {

    Point2I getViewablePixel(VectorD location, Collection<Body> bodies);
    Ray createRay();
}

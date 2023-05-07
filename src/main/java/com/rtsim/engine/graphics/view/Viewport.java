package com.rtsim.engine.graphics.view;

import java.util.Collection;

import com.rtsim.engine.Point2I;
import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.Color;
import com.rtsim.engine.graphics.light.Light;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;

public abstract class Viewport {
    private Color[][] render;
    private float rayMixFactor;

    protected Viewport(int xResolution, int yResolution, float rayMixFactor) {
        this.rayMixFactor = rayMixFactor;
        render = new Color[xResolution][yResolution];
        clearViewport();
    }

    public void clearViewport() {
        for(int x = 0; x < render.length; x++) {
            for (int y = 0; y < render[x].length; y++) {
                render[x][y] = new Color(0, 0, 0);
            }
        }
    }


    public void updateView(VectorD intersectionLocation, Light light) {
        Point2I viewLocation = getViewablePixel(intersectionLocation);
        render[viewLocation.getX()][viewLocation.getY()].mix(light.getColor(), light.getIntensity() * rayMixFactor);
    }

    public abstract Ray createRay(int bounces);
    public abstract Point2I getViewablePixel(VectorD location);
    public abstract boolean canReach(Collection<Body> bodies, VectorD intersectionLocation);

    public int getXResolution() {
        return render.length;
    }

    public int getYResolution() {
        return render[0].length;
    }

    public Color[][] getRender() {
        return render;
    }
}

package com.rtsim.engine.graphics;

import com.rtsim.engine.Point2I;
import com.rtsim.engine.VectorD;

public class Viewport {
    private int xResolution, yResolution;
    private VectorD location, size;
    private float backgroundPenalty;
    private Color[][] render;
 
    public Viewport(int xResolution, int yResolution, VectorD location, VectorD size, float backgroundPenalty) {
        this.xResolution = xResolution;
        this.yResolution = yResolution;
        this.location = location;
        this.size = size;
        this.backgroundPenalty = backgroundPenalty;
        render = new Color[xResolution][yResolution];
        clearViewport();
    }

    public void clearViewport() {
        for(int x = 0; x < xResolution; x++) {
            for (int y = 0; y < yResolution; y++) {
                render[x][y] = new Color(0, 0, 0);
            }
        }
    }

    public boolean containsVector(VectorD viewLocation) {
        return true;
        // return viewLocation.getX() < location.getX() && viewLocation.getY() < location.getY()
                // && viewLocation.getX() + size.getX() > location.getX() && viewLocation.getY() + size.getY() > location.getY();
    }

    public Point2I mapViewPoint(VectorD viewLocation) {
        // if (containsVector(viewLocation)) {
        //     VectorD inside = location.subtract(viewLocation);
        //     return new Point2I((int) (xResolution * (inside.getX() / size.getX())), 
        //                         (int) (yResolution * (inside.getY() / size.getY())));
        // } else
            return null;
    }

    public synchronized void updateView(ViewUpdate[] updateLocations) {
        for(ViewUpdate update : updateLocations) {
            Point2I mappedPoint = mapViewPoint(update.getLocation());
            float intensity = update.getIntensity();
            if (!update.inForeground()) // reduce lighting to background
                intensity *= backgroundPenalty;
            // change color according to lights
            render[mappedPoint.getX()][mappedPoint.getY()].add(update.getColor().scale(intensity));
        }
    }

    public Color[][] getImage() {
        return render;
    }

    public int getXResolution() {
        return xResolution;
    }

    public int getYResolution() {
        return yResolution;
    }

}

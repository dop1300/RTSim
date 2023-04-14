package com.rtsim.engine.graphics;

import com.rtsim.engine.Point2I;
import com.rtsim.engine.Vector2F;

public class Viewport {
    private int xResolution, yResolution;
    private Vector2F location, size;
    private float backgroundPenalty;
    private Color[][] render;
 
    public Viewport(int xResolution, int yResolution, Vector2F location, Vector2F size, float backgroundPenalty) {
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

    public boolean containsVector(Vector2F viewLocation) {
        return viewLocation.getX() < location.getX() && viewLocation.getY() < location.getY()
                && viewLocation.getX() + size.getX() > location.getX() && viewLocation.getY() + size.getY() > location.getY();
    }

    public Point2I mapViewPoint(Vector2F viewLocation) {
        if (containsVector(viewLocation)) {
            Vector2F inside = location.subtract(viewLocation);
            return new Point2I((int) (xResolution * (inside.getX() / size.getX())), 
                                (int) (yResolution * (inside.getY() / size.getY())));
        } else
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

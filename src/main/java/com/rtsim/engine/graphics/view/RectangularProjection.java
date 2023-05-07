package com.rtsim.engine.graphics.view;

import java.util.Collection;

import java.util.Random;

import com.rtsim.engine.IntersectionTester;
import com.rtsim.engine.MatrixD;
import com.rtsim.engine.Point2I;
import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.Body;


public class RectangularProjection extends Viewport {
    private VectorD position, planeNormal, planeLocation, horizontal, vertical;
    private double inclusionThreshold;
    private int xResolution, yResolution;
    private VectorD[][] buckets;
    private double fov, size;
    private Random random;
    /**
     * @param xResolution Camera's x resolution and the number of buckets along the horizontal axis.
     * @param yResolution Camera's y resolution and the number of buckets along the vertical axis.
     * @param fov Camera's field of view.
     * @param width Width of the projected rectangle.
     * @param height Height of the projected rectangle.
     * @param position Position of the projection.
     * @param vertical Vertical axis of the projection.
     * @param horizontal Horizontal axis of the projection.
     */
    public RectangularProjection(int xResolution, int yResolution, float rayMixFactor, double fov, double focalDistance, VectorD position, VectorD rotation) {
        super(xResolution, yResolution, rayMixFactor);
        this.fov = fov;
        this.position = position;
        random = new Random();
        buckets = new VectorD[xResolution][yResolution];
        createBuckets(rotation, focalDistance);
    }


    private void createBuckets(VectorD rotation, double focalDistance) {
        // Find the location of the plane the view is on.
        MatrixD rotationTransform = MatrixD.createXRotationMatrix(rotation.get(0))
            .multiply(MatrixD.createYRotationmatrix(rotation.get(1)))
            .multiply(MatrixD.createZRotationMatrix(rotation.get(2)));
        horizontal = rotationTransform.multiply(new VectorD(new double[]{1, 0, 0})).getColumn(0).normalize();
        vertical = rotationTransform.multiply(new VectorD(new double[]{0, 1, 0})).getColumn(0).normalize();
        VectorD perpendicular = horizontal.cross(vertical);
        // Save the plane location for later intersection testing.
        planeNormal = perpendicular;
        planeLocation = position.add(perpendicular.scale(focalDistance));
        // Create buckets along the plane.
        size = 2 * Math.cos((Math.PI - fov) / 2);
        inclusionThreshold = size / Math.min(xResolution, yResolution);
        VectorD bottomLeft = planeLocation.subtract(horizontal.scale(size / 2)).subtract(vertical.scale(size / 2));
        VectorD horizontalInterval = horizontal.scale(1 / size);
        VectorD verticalInterval = vertical.scale(1 / size);
        for (int x = 0; x < xResolution; x++) {
            VectorD xLocation = bottomLeft.add(horizontalInterval.scale(x));
            for (int y = 0; y < yResolution; y++) {
                buckets[x][y] = xLocation.add(verticalInterval.scale(y));
            }
        }
    }

    @Override
    public Ray createRay(int bounces) {
        double drift = Math.random() * inclusionThreshold;
        VectorD direction = buckets[random.nextInt(0, xResolution)][random.nextInt(yResolution)]
            .add(horizontal.scale(drift))
            .add(vertical.scale(drift))
            .subtract(position);
        return new Ray(position, direction, bounces);
    }

    @Override
    public Point2I getViewablePixel(VectorD location) {
        VectorD cameraDirection = position.subtract(location).normalize();
        double t = planeLocation.subtract(location).dot(planeNormal) / cameraDirection.dot(planeNormal);
        if (t == 0)
            return null; // Does not intersect.
        VectorD intersection = cameraDirection.scale(t).add(location);
        Point2I closestPixel = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        for(int x = 0; x < buckets.length; x++) {
            for (int y = 0; y < buckets[0].length; y++) {
                double distance = buckets[x][y].distance(intersection);
                if (distance < closestDistance) {
                    closestPixel = new Point2I(x, y);
                    closestDistance = distance;
                }
            }
        }
        if (closestDistance < inclusionThreshold) // avoid things on the plane, but far off
            return closestPixel;
        else
            return null;

    }

    @Override
    public boolean canReach(Collection<Body> bodies, VectorD intersectionLocation) {
        return IntersectionTester.canReach(bodies, intersectionLocation, position) && getViewablePixel(intersectionLocation) != null;
    }
    
}

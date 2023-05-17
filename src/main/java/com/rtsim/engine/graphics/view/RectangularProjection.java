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
    private VectorD position, planeNormal, planeLocation, horizontal, vertical, bottomLeft;
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
        size = 2 * Math.abs(Math.cos((Math.PI - fov) / 2));
        bottomLeft = planeLocation.subtract(horizontal.scale(size / 2)).subtract(vertical.scale(size / 2));
    }

    @Override
    public Ray createRay(int bounces) {
        VectorD direction = planeLocation.add(horizontal.scale(size * (Math.random() - 0.5))).add(vertical.scale(size * (Math.random() - 0.5))).subtract(position);
        return new Ray(position, direction, bounces);
    }

    @Override
    public Point2I getViewablePixel(VectorD location) {
        VectorD cameraDirection = position.subtract(location).normalize();
        VectorD viewIntersectionScalars = IntersectionTester.calculatePlaneIntersection(bottomLeft, horizontal, vertical, cameraDirection, position, cameraDirection);
        if (viewIntersectionScalars != null) {
            double x = -viewIntersectionScalars.get(0) / size * getXResolution();
            double y = -viewIntersectionScalars.get(1) / size * getYResolution();
            if (x > 0 && y > 0 && x < getXResolution() && y < getYResolution())
                return new Point2I((int) x, (int) y);
        }
        return null;
    }

    @Override
    public boolean canReach(Collection<Body> bodies, VectorD intersectionLocation) {
        return IntersectionTester.canReach(bodies, intersectionLocation, position) && getViewablePixel(intersectionLocation) != null;
    }
    
}

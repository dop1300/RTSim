package com.rt;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.physics.body.BodyIntersection;
import com.rtsim.engine.physics.body.Triangle;

public class TriangleRayIntersectionTest {

    @Test
    public void testIntersectingTriangle() {
        Triangle triangle1 = new Triangle(new VectorD[] {
            new VectorD(new double[] {0, 0d, 0d}),
            new VectorD(new double[] {0d, 1d, 0d}),
            new VectorD(new double[] {1d, 1d, 0d}) 
        }, null, null);
        Ray ray1 = new Ray(new VectorD(new double[] {0d, 0d, -1d}), new VectorD(new double[] {0.025, 0.025, 1d}), 0);
        BodyIntersection intersection = triangle1.intersection(ray1);
        assertNotNull(intersection);
        assertArrayEquals(intersection.getIntersectionLocation().getValues(), new double[] {0.025, 0.025, 0}, 1E-5);
    }

    @Test
    public void testNonIntersectingTriangle() {
        Triangle triangle1 = new Triangle(new VectorD[] {
            new VectorD(new double[] {0, 0d, 0d}),
            new VectorD(new double[] {0d, 1d, 0d}),
            new VectorD(new double[] {1d, 1d, 0d}) 
        }, null, null);
        Ray ray1 = new Ray(new VectorD(new double[] {0d, 0d, -1d}), new VectorD(new double[] {10, 0.025, 1d}), 0);
        BodyIntersection intersection = triangle1.intersection(ray1);
        assertNull(intersection);
        Ray ray2 = new Ray(new VectorD(new double[] {0, 0, -1}), new VectorD(new double[] {0.75, 0.25, 1}), 0);
        assertNull(triangle1.intersection(ray2));
    }
}

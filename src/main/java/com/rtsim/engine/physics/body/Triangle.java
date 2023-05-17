package com.rtsim.engine.physics.body;

import com.rtsim.engine.IntersectionTester;
import com.rtsim.engine.MatrixD;
import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.material.Material;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;

public class Triangle extends Body {
    public static final double NT_THRESHOLD = 1E-4;
    private VectorD[] points;

    public Triangle(VectorD[] points, Material material, BodyBehavior[] behaviors) {
        super(material, behaviors);
        this.points = points;
    }
    
    @Override
    public BodyIntersection intersection(Ray ray) { 
        VectorD u = points[1].subtract(points[0]);
        VectorD v = points[2].subtract(points[0]);
        VectorD n = u.cross(v);

        VectorD solution = IntersectionTester.calculatePlaneIntersection(points[0], u, v, n, ray);
        if (solution != null) {
            double ut = solution.get(0);
            double vt = solution.get(1);
            if (solution.get(0) >= 0 && ut <= 1 && vt >= 0 && vt <= 1 && vt <= 1 - ut && ut <= 1 - vt) {
                return new BodyIntersection(this, u.scale(ut).add(v.scale(vt)), n);
            }
        }
        return null;

    }

    public static void main(String[] args) {
        Triangle triangle1 = new Triangle(new VectorD[] {
            new VectorD(new double[] {0, 0d, 0d}),
            new VectorD(new double[] {0d, 1d, 0d}),
            new VectorD(new double[] {1d, 1d, 0d}) 
        }, null, null);
        Ray ray1 = new Ray(new VectorD(new double[] {0d, 0d, -1d}), new VectorD(new double[] {0.025, 0.025, 1d}), 0);
        BodyIntersection intersection = triangle1.intersection(ray1);
    }
    
}

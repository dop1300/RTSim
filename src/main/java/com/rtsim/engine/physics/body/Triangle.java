package com.rtsim.engine.physics.body;

import com.rtsim.engine.MatrixD;
import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.material.Material;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;

public class Triangle extends Body {
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
        double t = points[0].subtract(ray.getStart()).dot(n) / ray.getDirection().dot(n);
        if (t != 0) {
            double aDet = new MatrixD(new VectorD[] {u, v, n}).determinant();
            VectorD b = ray.getStart().add(ray.getDirection().scale(t));
            double ut = new MatrixD(new VectorD[] {b, v, n}).determinant() / aDet;
            double vt = new MatrixD(new VectorD[] {u, b, n}).determinant() / aDet;
            // double nt = new MatrixD(new VectorD[] {u, v, b}).determinant() / aDet;
            if (ut >= 0 && ut <= 1 && vt >= 0 && vt <= 1) {
                // System.out.println("Ow!");
                return new BodyIntersection(this, b, n);
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

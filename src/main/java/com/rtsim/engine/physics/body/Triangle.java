package com.rtsim.engine.physics.body;

import com.rtsim.engine.MatrixD;
import com.rtsim.engine.VectorD;
import com.rtsim.engine.graphics.material.Material;
import com.rtsim.engine.graphics.raytracing.Ray;
import com.rtsim.engine.graphics.raytracing.behavior.BodyBehavior;

public class Triangle extends Body {
    private VectorD[] points;

    protected Triangle(float percentLightAbsorbed, VectorD[] points, Material material, BodyBehavior[] behaviors) {
        super(percentLightAbsorbed, material, behaviors);
        this.points = points;
    }
    

    // https://en.wikipedia.org/wiki/M%C3%B6ller%E2%80%93Trumbore_intersection_algorithm
    @Override
    public BodyIntersection intersection(Ray ray) {
        double error = 1E-5;
        VectorD edge0 = points[1].subtract(points[0]);
        VectorD edge1 = points[2].subtract(points[0]);
        VectorD planeNormal = edge0.cross(edge1);
        double p = planeNormal.dot(ray.getDirection());
        if (p < error && p > -error) {
            return null;
        } else {
            VectorD b = points[0].subtract(ray.getLocation());
            float aDet = new MatrixD(new VectorD[] {ray.getDirection(), edge0, edge1}).determinant();
            if (aDet == 0) {
                return null;
            }
            VectorD ab = points[0].subtract(points[1]);
            VectorD ac = points[0].subtract(points[2]);
            // double t = new MatrixD(new VectorD[]{b, ab, ac}).determinant() / aDet;
            double u = new MatrixD(new VectorD[]{ray.getDirection(), b, ac}).determinant() / aDet;
            double v = new MatrixD(new VectorD[]{ray.getDirection(), ab, b}).determinant() / aDet;
            if (u < 1 && u > 0 && v < 1 && v > 0) {
                VectorD intersection = ac.scale(u).add(ab.scale(v));
                // VectorD intersection = ray.getLocation().add(ray.getDirection().scale(t));
                return new BodyIntersection(intersection, planeNormal);
            }
            return null;
        }
    }

    
}

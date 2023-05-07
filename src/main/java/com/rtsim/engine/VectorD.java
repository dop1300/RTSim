package com.rtsim.engine;

import java.util.Arrays;
import java.util.function.Function;

public class VectorD {
    private double[] values;

    public VectorD(double[] values) {
        this.values = values;
    }

    public VectorD normalize() {
        return scale(1/magnitude());
    }

    private VectorD map(Function<Integer, Double> function) {
        double[] result = new double[values.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = function.apply(i);
        }
        return new VectorD(result);
    }
    public VectorD add(VectorD other) {
        return map(i -> values[i] + other.values[i]);
    }

    public VectorD scale(double scalar) {
        return map(i -> values[i] * scalar);
    }

    public VectorD subtract(VectorD other) {
        return map(i -> values[i] - other.values[i]);
    }

    public double dot(VectorD other) {
        return map(i -> values[i] * other.values[i]).sum();
    }

    public double magnitude() {
        return Math.sqrt(dot(this));
    }

    public double distance(VectorD other) {
        return Math.sqrt(map(i -> (double) Math.pow(values[i] - other.values[i], 2)).sum());
    }

    public double get(int dimension) {
        return values[dimension];
    }

    public double sum() {
        double sum = 0;
        for (double f : values)
            sum += f;
        return sum;
    }

    public MatrixD asMatrixD() {
        return new MatrixD(new double[][] {Arrays.copyOf(values, values.length)});
    }

    public VectorD cross(VectorD vertical) {
        return null; // TODO finish
    }
 
}
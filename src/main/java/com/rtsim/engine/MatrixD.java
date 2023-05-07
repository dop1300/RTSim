package com.rtsim.engine;

import java.util.Arrays;

public class MatrixD {
    private double[][] values;

    public MatrixD(double[][] values) {
        this.values = values;
    }

    public VectorD getColumn(int index) {
        return new VectorD(Arrays.copyOf(values[index], values[index].length));
    }

    public VectorD getRow(int index) {
        double[] row = new double[values[0].length];
        for (int i = 0; i < row.length; i++)
            row[i] = values[index][i];
        return new VectorD(row);
    }

    public MatrixD multiply(VectorD vector) {
        return multiply(vector.asMatrixD());
    }

    public MatrixD multiply(MatrixD other) {
        double[][] result = new double[getWidth()][other.getHeight()];
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < other.getHeight(); j++) {
                result[i][j] = getRow(i).dot(other.getColumn(j));
            }
        }
        return new MatrixD(result);
    }

    public int getWidth() {
        return values.length;
    }

    public int getHeight() {
        return values[0].length;
    }

    public static MatrixD createXRotationMatrix(double theta) {
        return new MatrixD(new double[][]{
            new double[]{1, 0, 0},
            new double[]{0, Math.cos(theta), Math.sin(theta)},
            new double[]{0, -Math.sin(theta), Math.cos(theta)}
        });
    }

    public static MatrixD createYRotationmatrix(double theta) {
        return new MatrixD(new double[][]{
            new double[]{Math.cos(theta), 0, Math.sin(theta)},
            new double[]{0, 1, 0},
            new double[]{-Math.sin(theta), 0, Math.cos(theta)}
        });
    }

    public static MatrixD createZRotationMatrix(double theta) {
        return new MatrixD(new double[][]{
            new double[]{Math.cos(theta), Math.sin(theta), 0},
            new double[]{-Math.sin(theta), Math.cos(theta), 0},
            new double[]{0, 0, 1}
        });
    }
}

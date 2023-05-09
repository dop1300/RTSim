package com.rtsim.engine;

import java.util.Arrays;

public class MatrixD {
    private double[][] values;

    public MatrixD(int width, int height) {
        this(new double[width][height]);
    }

    public MatrixD(double[][] values) {
        this.values = values;
    }

    public MatrixD(VectorD[] columns) {
        values = new double[columns.length][columns[0].getSize()];
        for (int column = 0; column < columns.length; column++) {
            values[column] = columns[column].getValues();
        }
    }

    public VectorD getColumn(int index) {
        return new VectorD(Arrays.copyOf(values[index], values[index].length));
    }

    public void setColumn(int index, VectorD column) {
        values[index] = column.getValues();
    }

    public VectorD getRow(int index) {
        double[] row = new double[values[0].length];
        for (int i = 0; i < row.length; i++)
            row[i] = values[i][index];
        return new VectorD(row);
    }

    public void setRow(int index, VectorD row) {
        for (int column = 0; column < values.length; column++) {
            values[column][index] = row.get(column);
        }
    }
    public MatrixD multiply(VectorD vector) {
        return multiply(vector.asMatrixD());
    }

    public MatrixD multiply(MatrixD other) {
        double[][] result = new double[other.getWidth()][getHeight()];
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < other.getWidth(); x++) {
                result[x][y] = getRow(y).dot(other.getColumn(x));
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

    public MatrixD exclude(int row, int column) {
        double[][] sub = new double[getWidth() - 1][getHeight() - 1];
        for (int c = 0; c < getWidth() - 1; c++) {
            for (int r = 0; r < getHeight() - 1; r++) {
                sub[c][r] = values[c + (c >= column ? 1 : 0)][r + (r >= row ? 1 : 0)];
            }
        }
        return new MatrixD(sub);
    }

    public double cofactor(int row, int column) {
        return Math.pow(-1, row + column) * exclude(row, column).determinant();
    }

    public double determinant() {
        if (getWidth() == 2 && getHeight() == 2) {
            return values[0][0] * values[1][1] - values[1][0] * values[0][1];
        } else {
            double sum = 0;
            for (int c = 0; c < getWidth(); c++) {
                sum += values[c][0] * cofactor(0, c);
            }
            return sum;
        }
    }
}

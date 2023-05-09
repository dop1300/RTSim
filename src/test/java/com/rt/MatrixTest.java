package com.rt;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rtsim.engine.MatrixD;
import com.rtsim.engine.VectorD;

public class MatrixTest {

    private MatrixD identity() {
        return new MatrixD(new double[][]{new double[] {1, 0, 0}, new double[] {0, 1, 0}, new double[] {0, 0, 1}});
    }

    @Test
    public void columnTest() {
        assertEquals(identity().getColumn(0), new VectorD(new double[] {1, 0, 0}));
        assertEquals(identity().getColumn(1), new VectorD(new double[] {0, 1, 0}));
        assertEquals(identity().getColumn(2), new VectorD(new double[] {0, 0, 1}));
        MatrixD test = new MatrixD(new double[][]{new double[] {1, 2, 3}, new double[] {4, 5, 6}, new double[] {7, 8, 9}});
        assertEquals(test.getColumn(0), new VectorD(new double[] {1, 2, 3}));
        assertEquals(test.getColumn(1), new VectorD(new double[] {4, 5, 6}));
        assertEquals(test.getColumn(2), new VectorD(new double[] {7, 8, 9}));
    }

    @Test
    public void rowTest() {
        assertEquals(identity().getRow(0), new VectorD(new double[] {1, 0, 0}));
        assertEquals(identity().getRow(1), new VectorD(new double[] {0, 1, 0}));
        assertEquals(identity().getRow(2), new VectorD(new double[] {0, 0, 1}));
        MatrixD test = new MatrixD(new double[][]{new double[] {1, 2, 3}, new double[] {4, 5, 6}, new double[] {7, 8, 9}});
        assertEquals(test.getRow(0), new VectorD(new double[] {1, 4, 7}));
        assertEquals(test.getRow(1), new VectorD(new double[] {2, 5, 8}));
        assertEquals(test.getRow(2), new VectorD(new double[] {3, 6, 9}));
    }
}

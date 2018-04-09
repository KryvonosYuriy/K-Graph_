package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Matrix;

public class MatrixTest {
	Matrix matrix = null;
	TestMatrix testmatrix = new TestMatrix();

	@Before
	public void setUp() throws Exception {

		matrix = new Matrix(3, 3);
	}

	@Test
	public void komplementaerMatrixTrue() throws Exception {

		int[][] arrayKomp3Mal3 = { { 0, 0, 1 }, { 0, 0, 0 }, { 1, 0, 0 } };

		int[][] result = matrix.komplementaer(testmatrix.generate3Mal3Matrix());
		assertArrayEquals(result, arrayKomp3Mal3);
	}

	@Test
	public void komplementaerMatrixFalse() throws Exception {

		int[][] arrayKomp3Mal3 = { 
				{ 0, 1, 1 }, 
				{ 1, 0, 0 }, 
				{ 1, 0, 0 } 
		};

		int[][] result = matrix.komplementaer(testmatrix.generate3x3Matrix());
		assertArrayEquals(result, arrayKomp3Mal3);
	}

	@Test
	public void wegmatrix() throws Exception {
		int[][] arrayWeg = { 
				{ 1, 1, 1, 1, 1 }, 
				{ 1, 1, 1, 1, 1 }, 
				{ 1, 1, 1, 1, 1 }, 
				{ 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1 } 
		};
		int[][] result = matrix.wegmatrix1(arrayWeg);
		assertArrayEquals(result, arrayWeg);
	}

	// @Test
	public void distanz() throws Exception {
		int[][] arrayDistanz = { 
				{ 0, 2, 2, 1, 1 }, 
				{ 2, 0, 1, 2, 1 }, 
				{ 2, 1, 0, 1, 2 }, 
				{ 1, 2, 1, 0, 2 },
				{ 1, 1, 2, 2, 0 } 
		};
		int[][] result = matrix.distanzmatrix(arrayDistanz);

		assertArrayEquals(result, arrayDistanz);
	}

}

package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Matrix;

public class MatrixTestErgebnis {
	Matrix matrix = null;
	TestMatrix testmatrix = new TestMatrix();
	MatrixTest test = new MatrixTest();

	@Before
	public void setUp() throws Exception {
		matrix = new Matrix(3,3);
		matrix = new Matrix(4,4);
		matrix = new Matrix(6,6);
	}
	//------------------ Komplementaer Matrix ----------------------------//
	//-------------------------- 3x3 -------------------------------------//
	public int[][] matrixErgebnisKomplementaer3x3True(){
		int [][] matrixErgebnis = {
				{0, 0, 1},
				{0, 0, 0},
				{1, 0, 0}
		};
		return matrixErgebnis;
	}
	public int[][] matrixErgebnisKomplementaer3x3False(){
		int [][] matrixErgebnis = {
				{0, 1, 1},
				{1, 0, 0},
				{1, 0, 0}
		};
		return matrixErgebnis;
	}
	//-------------------------- 4x4 -------------------------------------//
	public int[][] matrixErgebnisKomplementaer4x4True(){
		int [][] matrixErgebnis = {
				{0, 1, 1, 0},
				{1, 0, 0, 1},
				{1, 0, 0, 0},
				{0, 1, 0, 0}
		};
		return matrixErgebnis;
	}
	public int[][] matrixErgebnisKomplementaer4x4False(){
		int [][] matrixErgebnis = {
				{0, 1, 1, 0},
				{1, 0, 1, 1},
				{1, 1, 0, 0},
				{0, 1, 0, 0}
		};
		return matrixErgebnis;
	}
	//-------------------------- 5x5 -------------------------------------//
	public int[][] matrixErgebnisKomplementaer5x5True(){
		int [][] matrixErgebnis = {
				{0, 1, 1, 0, 1},
				{1, 0, 0, 1, 0},
				{1, 0, 0, 0, 1},
				{0, 1, 0, 0, 1},
				{1, 0, 1, 1, 0}
		};
		return matrixErgebnis;
	}
	public int[][] matrixErgebnisKomplementaer5x5False(){
		int [][] matrixErgebnis = {
				{0, 1, 1, 0, 1},
				{1, 0, 0, 1, 1},
				{1, 0, 0, 0, 1},
				{0, 1, 0, 0, 1},
				{1, 1, 1, 1, 0}
		};
		return matrixErgebnis;
	}	
	//------------------------------------  Distanz Matrizen  ------------------------------------//
	//------------------------------------------- 3x3 --------------------------------------------//
	public int[][] matrixErgebnisDistanzMatrix3x3True(){
		int [][] matrixErgebnis = {
				{0, 1, 2},
				{1, 0, 1},
				{2, 1, 0}
		};
		return matrixErgebnis;
	}
	public int[][] matrixErgebnisDistanzMatrix3x3False(){
		int [][] matrixErgebnis = {
				{0, 2147483647, 2147483647},
				{2147483647, 0, 1},
				{2147483647, 1, 0}
		};
		return matrixErgebnis;
	}
	//---------------------------- Weg Matrix ----------------------------//
	//------------------------------- 3x3 --------------------------------//
	public int[][] matrixErgebnisWegMatrix3x3True(){
		int [][] matrixErgebnis = {
				{1, 1, 1},
				{1, 1, 1},
				{1, 1, 1}
		};
		return matrixErgebnis;
	}
	public int[][] matrixErgebnisWegMatrix3x3False(){
		int [][] matrixErgebnis = {
				{1, 0, 0},
				{0, 1, 1},
				{0, 1, 1}
		};
		return matrixErgebnis;
	}
	
	public int[][] matrix6x6(){
		int[][] m = {
			{0, 1, 1, 0, 0, 0},	
			{1, 0, 1, 0, 0, 0},	
			{1, 1, 0, 1, 0, 0},
			{0, 0, 1, 0, 1, 1},
			{0, 0, 0, 1, 0, 1},
			{0, 0, 0, 1, 1, 0},	
		};
		
		return m;
	}
	@Test
	public void wegmatrixfuer6x6() {
		int[][] res= matrix.wegmatrix1(matrix6x6());
		System.out.println("----------------- Wegmatrix ------------------");
		for(int i = 0; i < res.length; i++) {
			for(int j = 0; j < res.length; j++) {
				System.out.print(res[i][j]);
			}
			System.out.println();
		}
		
		int [][]ergebnis = {
				{1,1,1,1,1,1},
				{1,1,1,1,1,1},
				{1,1,1,1,1,1},
				{1,1,1,1,1,1},
				{1,1,1,1,1,1},
				{1,1,1,1,1,1}
		};
		
		
		
		assertArrayEquals(res, ergebnis);
	}
	
	//--------------------------------------------------------------------------------------------//
	//-------------------------------- Ergebnis KomplementärMatrizen -----------------------------//
	@Test
	public void komplementaerMatrixTrue() throws Exception{
		int[][] result1 = matrix.komplementaer(testmatrix.generate3Mal3Matrix());
		assertArrayEquals(result1, matrixErgebnisKomplementaer3x3True());

		int[][] result2 = matrix.komplementaer(testmatrix.generate4Mal4Matrix());
		assertArrayEquals(result2, matrixErgebnisKomplementaer4x4True());

		int[][] result3 = matrix.komplementaer(testmatrix.generate5Mal5Matrix());
		assertArrayEquals(result3, matrixErgebnisKomplementaer5x5True());
	}
	@Test
	public void komplementaerMatrixFalse() throws Exception{
		int[][] result1 = matrix.komplementaer(testmatrix.generate3x3Matrix());
		assertArrayEquals(result1, matrixErgebnisKomplementaer3x3False());

		int[][] result2 = matrix.komplementaer(testmatrix.generate4x4Matrix());
		assertArrayEquals(result2, matrixErgebnisKomplementaer4x4False());

		int[][] result3 = matrix.komplementaer(testmatrix.generate5x5Matrix());
		assertArrayEquals(result3, matrixErgebnisKomplementaer5x5False());
	}

	//-------------------------------- Ergebnis DistanzMatrizen -----------------------------//
	@Test
	public void distanzmatrixTrue() throws Exception{
		int[][] result1 = matrix.distanzmatrix(testmatrix.generate3Mal3Matrix());
		assertArrayEquals(result1, matrixErgebnisDistanzMatrix3x3True());
	}
	@Test
	public void distanzmatrixFalse() throws Exception{
		int[][] result1 = matrix.distanzmatrix(testmatrix.generate3x3Matrix());
		assertArrayEquals(result1, matrixErgebnisDistanzMatrix3x3False());
	}
	
	//-------------------------------- Ergebnis WegMatrizen -----------------------------//
	@Test
	public void wegmatrixTrue() throws Exception{
		int[][] result1 = matrix.wegmatrix1(testmatrix.generate3Mal3Matrix());
		assertArrayEquals(result1, matrixErgebnisWegMatrix3x3True());
	}
	@Test
	public void wegmatrixFalse() throws Exception{
		int[][] result1 = matrix.wegmatrix1(testmatrix.generate3x3Matrix());
		assertArrayEquals(result1, matrixErgebnisWegMatrix3x3False());
	}
}

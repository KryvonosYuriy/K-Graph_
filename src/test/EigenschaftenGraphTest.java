package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.EigenschaftenGraph;
import model.EigenschaftenGraphImpl;
import model.Matrix;

public class EigenschaftenGraphTest
{
	Matrix matrix1 = null;
	Matrix matrix2 = null;
	Matrix matrix10x10t = null; 
	Matrix matrix10x10f = null; 
	Matrix matrixArt1 = null;
	Matrix matrixArt2 = null;
	
	TestMatrix testmatrix = new TestMatrix();
		
	@Before
	public void setUp() throws Exception
	{
		int [][] array10 = {
				{0, 1, 1, 0, 0}, //K1
				{1, 0, 1, 0, 0}, //K2
				{1, 1, 0, 1, 1}, //K3 -> knoten 3
				{0, 0, 1, 0, 1}, //K4
				{0, 0, 1, 1, 0}  //K5
		};
		matrixArt1 = new Matrix(5, 5);
		matrixArt1.setAdjazentMatrix(array10);
		int [][] array11 = {
				{0, 0, 1, 0, 0}, //K1
				{0, 0, 1, 0, 0}, //K2
				{1, 1, 0, 1, 1}, //K3 -> knoten 3
				{0, 0, 1, 0, 1}, //K4
				{0, 0, 1, 1, 0}  //K5
		};
		matrixArt2 = new Matrix(5, 5);
		matrixArt2.setAdjazentMatrix(array11);
		
		// diese Matrix ist ZUSAMMENHÄNGEND
		int[][] array = {
				{0, 1, 0, 0, 1},
				{1, 0, 1, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 0, 1, 0, 1},
				{1, 0, 0, 1, 0}
		};
		matrix1 = new Matrix(5, 5);
		matrix1.setAdjazentMatrix(array);
	
	// diese Matrix ist NICHT ZUSAMMENHР”NGEND
			int[][] array1 = {
					{0, 1, 0, 1, 0},
					{1, 0, 1, 0, 0},
					{0, 1, 0, 1, 0},
					{1, 0, 1, 0, 0},
					{0, 0, 0, 0, 0}
			};
			matrix2 = new Matrix(5, 5);
			matrix2.setAdjazentMatrix(array1);
		
	//-------------------------- 10x10 ---------------------------------
	// -------------MIT ZUSAMMENHÄNGEND---------------
		int[][] array3 = {
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, 
			{1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
			{0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
			{0, 0, 0, 1, 0, 1, 0, 1, 0, 0},
			{0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
			{0, 0, 0, 0, 1, 0, 1, 0, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
			{1, 0, 1, 0, 0, 0, 0, 0, 1, 0}
		};
		matrix10x10t = new Matrix(10, 10);
		matrix10x10t.setAdjazentMatrix(array3);
		
	// -------------NICHT ZUSAMMENHР”NGEND---------------
		int[][] array4 = {
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, 
			{1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
			{0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
			{0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 1, 0, 0, 0, 0, 0, 1, 0}
		};
			matrix10x10f = new Matrix(10, 10);
			matrix10x10f.setAdjazentMatrix(array4);
		
		
	//-------------------------- 10x10 ---------------------------------
	}



	@Test
	public void zusammenhaengendTestTrue(){
		EigenschaftenGraph eigenschaftenGraph = new EigenschaftenGraphImpl(matrix1);
		boolean result1 = eigenschaftenGraph.isZusammenhaengend(matrix1.getAdjazentMatrix());
		assertTrue(result1);
		
		EigenschaftenGraph eigenschaftenGraph1 = new EigenschaftenGraphImpl(matrix10x10t);
		boolean result2 = eigenschaftenGraph1.isZusammenhaengend(matrix10x10t.getAdjazentMatrix());
		
		assertTrue(result2);
	}
	
	@Test
	public void zusammenhaengendTestFalse(){
		
		EigenschaftenGraph eigenschaftenGraph = new EigenschaftenGraphImpl(matrix2);
		boolean result1 = eigenschaftenGraph.isZusammenhaengend(matrix2.getAdjazentMatrix());
		assertFalse(result1);
		
		
		EigenschaftenGraph eigenschaftenGraph1 = new EigenschaftenGraphImpl(matrix10x10f);
		boolean result2 = eigenschaftenGraph1.isZusammenhaengend(matrix10x10f.getAdjazentMatrix());
		assertFalse(result2); 
	}
	
	@Test
	public void komponentenAnzahl(){
		
		int[][] array2 = {
				{0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, 
				{1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
				{0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
				{0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 1, 0, 0, 0, 0, 0, 1, 0}
		};
		EigenschaftenGraph eigenschafGraph = new EigenschaftenGraphImpl(matrix10x10f);
		int ergebnis1 = eigenschafGraph.anzKomponenten(array2);
		int ergebnis = eigenschafGraph.anzKomponenten(matrix10x10f.getAdjazentMatrix());
		assertTrue(ergebnis == 4);
		assertEquals(ergebnis1, 4);
	}
	

}

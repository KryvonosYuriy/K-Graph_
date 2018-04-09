package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.EigenschaftenGraph;
import model.EigenschaftenGraphImpl;
import model.Matrix;
import model.Tupel;

import java.util.List;
import java.util.Set;

public class EigenschaftenGraphTestErgebnis {
	Matrix matrix3 = null;
	Matrix matrix4 = null;
	Matrix matrix5 = null;
	Matrix matrix6 = null;
	Matrix matrix8 = null;
	Matrix matrix13 = null;
	TestMatrix testmatrix = new TestMatrix();

	//3 x 3 bis 15 x 15 -> Anzahl von den Komponenten, extentrizitäten, radius, durchmesser 
	//und zentrum, zusammenhängend
	@Before
	public void setUp() throws Exception {
		matrix3 = new Matrix(3, 3);
		matrix4 = new Matrix(4, 4);
		matrix5 = new Matrix(5, 5);
		matrix6 = new Matrix(6, 6);
		matrix8 = new Matrix(8, 8);
		matrix13 = new Matrix(13, 13);
	}

	@Test       // Korrekt
	public void anzahlVonKomponentenTrue(){
		EigenschaftenGraph eigenschafGraph1 = new EigenschaftenGraphImpl(matrix3);
		//matrix3.setAdjazentMatrix(testmatrix.generate3Mal3Matrix());
		int result1 = eigenschafGraph1.anzKomponenten(testmatrix.generate3Mal3Matrix());
		assertTrue(result1 == 1);  //<-- richtiges Ergebnis
		
		EigenschaftenGraph eigenschafGraph2 = new EigenschaftenGraphImpl(matrix4);
		matrix4.setAdjazentMatrix(testmatrix.generate4Mal4Matrix());
		int result2 = eigenschafGraph2.anzKomponenten(testmatrix.generate4Mal4Matrix());
		assertTrue(result2 == 1);
		assertEquals(result2, 1);  //<-- richtiges Ergebnis
		
		EigenschaftenGraph eigenschafGraph3 = new EigenschaftenGraphImpl(matrix5);
		matrix5.setAdjazentMatrix(testmatrix.generate5Mal5Matrix());
		int result3 = eigenschafGraph3.anzKomponenten(testmatrix.generate5Mal5Matrix());
		assertTrue(result3 == 1);
		assertEquals(result3, 1);  //<-- richtiges Ergebnis
		
		
	}
	@Test   // Korrekt
	public void anzahlVonKomponentenFalse(){
		
		EigenschaftenGraph eigenschafGraph1 = new EigenschaftenGraphImpl(matrix3);
		matrix3.setAdjazentMatrix(testmatrix.generate3x3Matrix());
		int result1 = eigenschafGraph1.anzKomponenten(testmatrix.generate3x3Matrix());
		assertTrue(result1 == 2);
		
		EigenschaftenGraph eigenschafGraph2 = new EigenschaftenGraphImpl(matrix4);
		matrix4.setAdjazentMatrix(testmatrix.generate4x4Matrix());
		int result2 = eigenschafGraph2.anzKomponenten(testmatrix.generate4x4Matrix());
		assertTrue(result2 == 2);
		
		EigenschaftenGraph eigenschafGraph3 = new EigenschaftenGraphImpl(matrix5);
		matrix5.setAdjazentMatrix(testmatrix.generate5x5Matrix());
		int result3 = eigenschafGraph3.anzKomponenten(testmatrix.generate5x5Matrix());
		assertEquals(result3, 3); 
	}
	
	@Test  // Korrekt
	public void extentrizitätenTrue() {
		EigenschaftenGraph eg1 = new EigenschaftenGraphImpl(matrix3);
		matrix3.setAdjazentMatrix(testmatrix.generate3Mal3Matrix());
		int[][] distanz = matrix3.distanzmatrix(matrix3.getAdjazentMatrix()); 
		int[] result1 = eg1.exzentriziteaten(distanz);
		assertEquals(result1.length, 3);
		boolean erg = eg1.isZusammenhaengend(testmatrix.generate3Mal3Matrix());
		assertTrue(erg);
		
		System.out.println("extentrizitätenTrue 3x3:");
		for(int i=0; i<result1.length; i++) {
			System.out.print(" Knoten " + (i+1) + " - " + result1[i] + "\n");
		}
	}
	@Test   // korrekt
	public void extentrizitätenFalse() {
		EigenschaftenGraph eg1 = new EigenschaftenGraphImpl(matrix3);
		matrix3.setAdjazentMatrix(testmatrix.generate3x3Matrix());
		int[][] distanz = matrix3.distanzmatrix(matrix3.getAdjazentMatrix()); 
		int[] extentrit = eg1.exzentriziteaten(distanz);
		boolean zusammenhaenged = eg1.isZusammenhaengend(matrix3.getAdjazentMatrix());
		assertFalse(zusammenhaenged);
		assertNull(extentrit);
		System.out.println("extentrizitätenFalse 3x3: " + extentrit);
	}
	
	@Test   // Korrekt
	public void radiusTrue() {
		EigenschaftenGraph eg1 = new EigenschaftenGraphImpl(matrix3);
		matrix3.setAdjazentMatrix(testmatrix.generate3Mal3Matrix());
		int[][] distanz =  matrix3.distanzmatrix(matrix3.getAdjazentMatrix());
		int[] ex = eg1.exzentriziteaten(distanz);
		assertNotNull(ex);
		
		int radius = eg1.radius(testmatrix.generate3Mal3Matrix());
		assertEquals(radius, 1);
	}
	
	@Test   // Korrekt
	public void durchmesserTrue() {
		EigenschaftenGraph eg1 = new EigenschaftenGraphImpl(matrix3);
		matrix3.setAdjazentMatrix(testmatrix.generate3Mal3Matrix());
		int[][] distanz =  matrix3.distanzmatrix(matrix3.getAdjazentMatrix());
		int[] ex = eg1.exzentriziteaten(distanz);
		assertNotNull(ex);

		int durchmesser = eg1.durchmesser(testmatrix.generate3Mal3Matrix());
		assertEquals(durchmesser, 2);
	}
	@Test  
	public void durchmesserFalse() {
		EigenschaftenGraph eg1 = new EigenschaftenGraphImpl(matrix3);
		matrix3.setAdjazentMatrix(testmatrix.generate3x3Matrix());
		boolean zusammenhaengend = eg1.isZusammenhaengend(matrix3.getAdjazentMatrix());
		assertFalse(zusammenhaengend);
		
		int durchmesser = eg1.durchmesser(matrix3.getAdjazentMatrix());
		assertEquals(durchmesser, Integer.MIN_VALUE);
	}
	//-----------------   Zentrum  True  ---------------------
	@Test 
	public void zentrumTrue(){
		EigenschaftenGraph eg1 = new EigenschaftenGraphImpl(matrix5);
		matrix5.setAdjazentMatrix(testmatrix.generate5Mal5Matrix());
		
		int[][] distanz =  matrix5.distanzmatrix(matrix5.getAdjazentMatrix());
		int[] ex = eg1.exzentriziteaten(distanz);
		assertNotNull(ex);
		
		boolean zusammenhaenged = eg1.isZusammenhaengend(testmatrix.generate5Mal5Matrix());
		assertTrue(zusammenhaenged);
		
		int[] zentrum = eg1.zentrum(testmatrix.generate5Mal5Matrix());
		assertNotNull(zentrum);
		System.out.println("zentrumTrue 5x5:");
		for(int i = 0; i < zentrum.length; i++ ) {
			System.out.print(zentrum[i]);
			if(i < zentrum.length -1) {
				System.out.print(",");
			}
		}
	}
	//-----------------   Zentrum  FALSE ---------------------
	@Test 
	public void zentrumFalse(){
		EigenschaftenGraph eg1 = new EigenschaftenGraphImpl(matrix3);
		matrix3.setAdjazentMatrix(testmatrix.generate3x3Matrix());

		boolean zusammenhaenged = eg1.isZusammenhaengend(testmatrix.generate3x3Matrix());
		assertFalse(zusammenhaenged);

	}
	//-----------------   Zusammenhängend ---------------------
	@Test   // Korrekt
	public void zusammenhängendTrue() {
		EigenschaftenGraph eigenschaftenGraph1 = new EigenschaftenGraphImpl(matrix3);
		matrix3.setAdjazentMatrix(testmatrix.generate3Mal3Matrix());
		boolean result1 = eigenschaftenGraph1.isZusammenhaengend(testmatrix.generate3Mal3Matrix());
		assertTrue(result1);  // richtiges Ergebnis
		
		EigenschaftenGraph eigenschaftenGraph2 = new EigenschaftenGraphImpl(matrix4);
		matrix4.setAdjazentMatrix(testmatrix.generate4Mal4Matrix());
		boolean result2 = eigenschaftenGraph2.isZusammenhaengend(testmatrix.generate4Mal4Matrix());
		assertTrue(result2);  // richtiges Ergebnis
		
		EigenschaftenGraph eigenschaftenGraph3 = new EigenschaftenGraphImpl(matrix5);
		matrix5.setAdjazentMatrix(testmatrix.generate5Mal5Matrix());
		boolean result3 = eigenschaftenGraph3.isZusammenhaengend(testmatrix.generate5Mal5Matrix());
		assertTrue(result3);  // richtiges Ergebnis
		
		EigenschaftenGraph eigenschaftenGraph4 = new EigenschaftenGraphImpl(matrix8);
		matrix8.setAdjazentMatrix(testmatrix.generate8Mal8Matrix());
		boolean result4 = eigenschaftenGraph4.isZusammenhaengend(testmatrix.generate8Mal8Matrix());
		assertTrue(result4);  // richtiges Ergebnis
	}
	
	@Test     // Korrekt
	public void zusammenhängendFalse() {
		EigenschaftenGraph eigenschaftenGraph1 = new EigenschaftenGraphImpl(matrix3);
		matrix3.setAdjazentMatrix(testmatrix.generate3x3Matrix());
		boolean result1 = eigenschaftenGraph1.isZusammenhaengend(testmatrix.generate3x3Matrix());
		assertFalse(result1);  // richtiges Ergebnis
		
		EigenschaftenGraph eigenschaftenGraph2 = new EigenschaftenGraphImpl(matrix4);
		matrix4.setAdjazentMatrix(testmatrix.generate4x4Matrix());
		boolean result2 = eigenschaftenGraph2.isZusammenhaengend(testmatrix.generate4x4Matrix());
		assertFalse(result2);  // richtiges Ergebnis
		
		EigenschaftenGraph eigenschaftenGraph3 = new EigenschaftenGraphImpl(matrix5);
		matrix5.setAdjazentMatrix(testmatrix.generate5x5Matrix());
		boolean result3 = eigenschaftenGraph3.isZusammenhaengend(testmatrix.generate5x5Matrix());
		assertFalse(result3);  // richtiges Ergebnis
	}
	
	@Test      // Fast korrekt 
	public void artikulationTrue() {
		EigenschaftenGraph eg1 = new EigenschaftenGraphImpl(matrix13);
		matrix13.setAdjazentMatrix(testmatrix.generate13Mal13Matrix());

		List<Integer> artikulation = eg1.artikulation(matrix13.getAdjazentMatrix());
		assertEquals(artikulation.size(), 6);
	}
	
	@Test  // Falsche Berechnung bei der Artikulation
	public void artikulationFalse() {
		EigenschaftenGraph eg1 = new EigenschaftenGraphImpl(matrix8);

		eg1.print(testmatrix.generate8x8Matrix());

		matrix8.setAdjazentMatrix(testmatrix.generate8x8Matrix());

		List<Integer> artikulation = eg1.artikulation(matrix8.getAdjazentMatrix());
		assertEquals(artikulation.size(), 2);
	}
	//----------------  Brücken --------------------//
	@Test
	public void brueckenFalse() {
		EigenschaftenGraph eg1 = new EigenschaftenGraphImpl(matrix6);
		matrix6.setAdjazentMatrix(testmatrix.generate6x6Matrix());

		Set<Tupel> bruecken = eg1.bruecken(matrix6.getAdjazentMatrix());
		for(Tupel tupel : bruecken) {
			System.out.println("------- brueckenFalse -------------");
			System.out.println(tupel.getKey() + " " + tupel.getValue());
		}
		assertEquals(bruecken.size(), 1);
	}
	

	@Test      // Korrekt 
	public void brueckenTrue() {
		EigenschaftenGraph eg1 = new EigenschaftenGraphImpl(matrix13);
		matrix13.setAdjazentMatrix(testmatrix.generate13Mal13Matrix());
		System.out.println("----- 13x13---------");
		print(testmatrix.generate13Mal13Matrix());
		
		Set<Tupel> bruecken = eg1.bruecken(testmatrix.generate13Mal13Matrix());
		for(Tupel tupel : bruecken) {
			System.out.println("------- brueckenTrue -------------");
			System.out.println(tupel.getKey() + " " + tupel.getValue());
		}
		
		assertTrue(bruecken.contains(new Tupel(2,1)));
		assertTrue(bruecken.contains(new Tupel(6,5)));
		assertTrue(bruecken.contains(new Tupel(8,7)));
		assertTrue(bruecken.contains(new Tupel(3,1)));
		assertTrue(bruecken.contains(new Tupel(12,10)));

		assertEquals(bruecken.size(),5);
	}
	
	public void print (int [][] matrix){
		for(int i = 0; i < matrix.length; ++i){
			for(int j=0; j < matrix.length; ++j){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	

}

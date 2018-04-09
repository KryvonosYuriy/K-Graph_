package model;

public class BerechnungImpl implements Berechnungen
{
	@Override
	public Matrix copyMatrixTo(Matrix a)
	{
		Matrix copy = new Matrix(a.getZeile(), a.getSpalte());
		copy.setAdjazentMatrix(a.getAdjazentMatrix());
		copy.setKantenAnzahl(a.getKnotenAnzahl());
		copy.setKnotenAnzahl(a.getKnotenAnzahl());
	
		return copy;	
	}

	@Override
	public int[][] multiplikation(int[][] a, int[][] b)
	{
		int[][] ergebnismatrix = null;

		if (a[0].length == b.length) {
			int zeilenm1 = a.length;
			int spaltenm1 = a[0].length;
			int spalenm2 = b[0].length;
			
			ergebnismatrix = new int[zeilenm1][spalenm2];
					

			for (int i = 0; i < zeilenm1; i++) {
				for (int j = 0; j < spalenm2; j++) {
					ergebnismatrix[i][j] = 0;
					for (int k = 0; k < spaltenm1; k++) {
					  ergebnismatrix[i][j] += a[i][k] * b[k][j];
					}
				}
			}
		} else {
			int zeilen = a.length;
			int spalten = a[0].length;

			ergebnismatrix = new int[zeilen][spalten];
				
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[0].length; j++) {
					ergebnismatrix[i][j] = 0;
				}
			}
		}
		return ergebnismatrix;
		
	}

}

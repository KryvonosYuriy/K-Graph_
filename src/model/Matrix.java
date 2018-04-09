package model;

public class Matrix
{
	// Eigenschaften der Matrizen
	private int knotenAnzahl;
	private int kantenAnzahl;
	private int [][] adjazentMatrix, wegMatrix;
	private int zeile, spalte;
	private Berechnungen berechnung;
	
	//******************** Konstruktor mit dem Parameterwert von der Zeile und der Spalte *************************
	public Matrix(int zeile, int spalte){
		adjazentMatrix = new int[zeile][spalte];
		wegMatrix = new int[zeile][spalte];
	
		this.zeile = zeile;
		this.spalte = spalte;
		knotenAnzahl = zeile;
		this.berechnung = new BerechnungImpl();
	} 
	//******************** 2. Konstruktor mit Parameterwert von knotenanzahl *************************
	public Matrix(int knotenanzahl){
		this.knotenAnzahl = knotenanzahl;
		adjazentMatrix = new int[knotenanzahl][knotenanzahl];
		this.berechnung = new BerechnungImpl();
	}
	//******************** getter-Methoden **************************
	public int[][] getAdjazentMatrix(){
		return adjazentMatrix;
	}
	public int[][] getWegMatrix(){
		return wegMatrix;
	}
	public int getKnotenAnzahl(){
		return knotenAnzahl;
	}
	public int getKantenAnzahl(){
		return kantenAnzahl;
	}
	public int getZeile(){
		return zeile;
	}
	public int getSpalte(){
		return spalte;
	}
	//******************** setter-Methoden *************************
	public void setAdjazentMatrix(int[][]matrix){
		this.adjazentMatrix = matrix;
	}
	public void setKnotenAnzahl(int knotenAnzahl){
		this.knotenAnzahl = knotenAnzahl;
	}
	public void setKantenAnzahl(int kantenAnzahl){
		this.kantenAnzahl = kantenAnzahl;
	}
	public void setZeile(int zeile)
	{
		this.zeile = zeile;
	}
	public void setSpalte(int spalte)
	{
		this.spalte = spalte;
	}
	
	public void setWegmatrix(int[][] wegmatrix){
		this.wegMatrix = wegmatrix;
	}
	
	//******************** Adjazenz Matrix *************************
	public int[][] adjazenzMatrix1(){
		int[][] m = new int[zeile][spalte];
		
		for(int z = 0; z < zeile; z++){
			for(int s = 0; s < spalte; s++){
				m[z][s] = adjazentMatrix[z][s];
			}
			
		}
		return m;
	}
	
	//******************** WegMatrix *************************
	public int[][] wegmatrix1(int[][] adjazenzmatrix){
		
		
		int [][] wegmatrix = new int[adjazenzmatrix.length][adjazenzmatrix[0].length];
		
		for(int z = 0; z < adjazenzmatrix.length; z++){
			for(int s = 0; s < adjazenzmatrix[0].length; s++){
				if(z != s){
					//Schritt 2: Alle elemente der Adjanzmatrix kopieren
					wegmatrix[z][s] = adjazenzmatrix[z][s];
					
				}else
				{
					//Schritt 1 : Diagonale auf 1 gesetzt
					wegmatrix[z][s] = 1;
					
				}
			}
			
		}
		
		boolean repeat = true;
		int numberOfrepeat = 0;
		
		int [][] multiplikatator1 = adjazenzmatrix;
		int [][] multiplikatator2 = adjazenzmatrix;
	
		
		while(repeat && numberOfrepeat < adjazenzmatrix.length-1) {
		
		//Schritt 3 : Adjanzmatrix quadrieren
		int[][] adjanzmatrix_2 = berechnung.multiplikation(multiplikatator1, multiplikatator2);
		
		for(int i = 0; i < adjanzmatrix_2.length; i++){
			for(int j = 0; j < adjanzmatrix_2.length; j++){
				if(adjanzmatrix_2[i][j] != 0){
					wegmatrix[i][j] = 1;
				}
			}
		}
		repeat = checkIfMatrixContains0(wegmatrix);
		multiplikatator1 = adjanzmatrix_2;
		numberOfrepeat++;
		}
		
		return wegmatrix;
	}
	
	public boolean checkIfMatrixContains0(int [][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				if(matrix[i][j] != 0) {
					return true;
				}
			}
		}
		return false;
	}
	//******************** Komplementaermatrix *************************
	public int[][] komplementaer(int[][] adjazenzmatrix){
		int[][] komplementaer = new int[adjazenzmatrix.length][adjazenzmatrix.length];
		
		for(int z = 0; z < adjazenzmatrix.length; z++){
			for(int s = 0; s < adjazenzmatrix.length; s++){
				if(z!=s && adjazenzmatrix[z][s] == 1){
					komplementaer[z][s] = 0;
				}
				else if(z!=s && adjazenzmatrix[z][s] == 0){
					komplementaer[z][s] = 1;
				}
			}
		}
		return komplementaer;
	}
	//******************** Distanzmatrix *************************
	public int[][] distanzmatrix(int[][] adjazenzmatrix){
		// Initialisieren der Distanzmatrix
		int [][] distanz = new int[adjazenzmatrix.length][adjazenzmatrix.length];
		
		for(int z=0; z<adjazenzmatrix.length; z++){ //jede Zeile
			for(int s = 0; s < adjazenzmatrix.length; s++){  // jede Spalte
				// schritt 1: Die Distanzmatrix wird mit allem Werten aus der Adjazenzmatrix
				// kopiert.
				if(z != s){
					//max Wert wird fьr unendlich verwendet
					int max = Integer.MAX_VALUE;
					//wenn in der Adjazenzmatrix der Wert 0 steht
					if(adjazenzmatrix[z][s] == 0){
						//wird in der Distanzmatrix der Wert unendlich (Integer.MAX_VALUE) gesetzt
						distanz[z][s] = max;
					}else{
						//ansonsten wir der Wert von der Adjazenzmatrix kopiert
						distanz[z][s] = adjazenzmatrix[z][s];
					}
					
				}else{
				// schritt 1: Die Diagonale der DM wird mit dem Wert 0 gesetzt.	
					distanz[z][s] = 0;
				}
			}
		}
		
	   //Integer.MAX_VALUE = unendlich
		boolean repeat = true;
		
		//schritt2: die nächste Potenz der Adjazenzmatrix berechnen.
		int[][] potenzmatrix = berechnung.multiplikation(adjazenzmatrix, adjazenzmatrix);
		
		int value = 2;
		while(repeat && value < distanz.length){

			for(int i=0; i<distanz.length; i++){
				for(int j=0; j<distanz.length; j++){
					//wir ьberprьfen wo der Wert unendlich (Interger.MAX_VALUE) in der Distanzmatrix steht
					if(distanz[i][j] == Integer.MAX_VALUE){
						//falls in der Potenzmatrix an dieser Stelle ein Wert grцЯer als 0 steht
						if(potenzmatrix[i][j] > 0){
							//tragen wir diesen Wert in die Distanzmatrix ein
							distanz[i][j] = value;
						}	
					}
				}
			}
			//Überprьfung ob es noch wert mit unendlich gibt 
			repeat = checkIfRepeatIsNecessary(distanz);
			if(repeat){
				potenzmatrix =  berechnung.multiplikation(potenzmatrix, adjazenzmatrix);
				++value;
			}
		}
		return distanz;
	}

	// ******************** repeat *************************
	/**
	 * Methoden für die Überprüfung der Wert mit unendlich...
	 * 
	 * @param matrix
	 * @return
	 */
	public boolean checkIfRepeatIsNecessary(int[][] matrix) {

		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix.length; ++j) {
				if (matrix[i][j] == Integer.MAX_VALUE) {
					return true;
				}
			}
		}

		return false;
	}

}

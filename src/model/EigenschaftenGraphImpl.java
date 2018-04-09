package model;

import java.util.*;

public class EigenschaftenGraphImpl implements EigenschaftenGraph {
	private Matrix matrix;

	public EigenschaftenGraphImpl() {
	}

	public EigenschaftenGraphImpl(Matrix matrix) {
		this.matrix = matrix;
	}

	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}
    //***************** Anzahl Kanten und Knoten *******************
	@Override
	public int anzKanten(int [][] adjazenzmatrix) {
		int kanten = 0;
		for(int z=0; z<adjazenzmatrix.length; z++) {
			for(int s=0; s<adjazenzmatrix.length; s++) {
				if(adjazenzmatrix[z][s] == 1) {
					kanten += 1;
				}
			}
		}
		
		return (kanten/2);
	}
	@Override
	public int anzKnoten(int [][] adjazenzmatrix) {
		
		int anz = adjazenzmatrix.length;
		return anz;
	}
	
	// *********************** zusammenhängender Graph *************************
	@Override
	public boolean isZusammenhaengend(int[][] adjazenzmatrix) {
		// Kopie der Adjanzmatrix um ursprüngliche Adjazenzmatrix nicht zu verändern
		int[][] copyAdjazenzmatrix = copyMatrix(adjazenzmatrix);

		// Diagonalelemente auf 1 gesetzt
		for (int i = 0; i < copyAdjazenzmatrix.length; i++)
			copyAdjazenzmatrix[i][i] = 1;
		// durch die copyMatrix wird iteriert
		// d.h - > jedes Elemente wird besucht
		for (int j = 0; j < copyAdjazenzmatrix.length; j++)
			for (int i = 0; i < copyAdjazenzmatrix[j].length; i++)
				// wird ein element gefunden das ungleich 0 ist
				if (copyAdjazenzmatrix[i][j] != 0)
					// wird die zeile erneut durchlaufen
					for (int k = 0; k < copyAdjazenzmatrix.length; k++)
						// falls ein elemnt gefunden wird das nicht 0 ist
						if (copyAdjazenzmatrix[j][k] != 0)
							// wird in der gespiegelten zeile (spalte i wird zur zeile) und der aktuellen
							// spalte der wert auf 1 gesetzt
							copyAdjazenzmatrix[i][k] = 1;

		// falls eine Zeile nur aus Einsen besteht -> true
		for (int i = 0; i < copyAdjazenzmatrix.length; i++) {
			boolean temp = true;
			for (int j = 0; j < copyAdjazenzmatrix[i].length; j++)
				if (copyAdjazenzmatrix[i][j] == 0)
					temp = false;

			if (temp)
				return true;
		}
		return false;
	}

	public int[][] copyMatrix(int[][] matrix) {
		int[][] copy = new int[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				copy[i][j] = matrix[i][j];
			}
		}
		return copy;
	}

	// ******************* Anzahl der Komponenten *********************
	@Override
	public int anzKomponenten(int[][] adjazenzmatrix) {
		if (isZusammenhaengend(adjazenzmatrix) == true) {
			return 1;
		} else {
			// Falls nein, wird für jeden knoten in der matrix eine tiefensuche ausgeführt
			Set<List<Integer>> set = new HashSet<>();
			for (int i = 0; i < adjazenzmatrix.length; i++) {
				// tiefensuche aufrufen
				List<Integer> list = depthSearch(i, adjazenzmatrix);
				Collections.sort(list);
				set.add(list);
			}
			return set.size();
		}
	}
	
	public List<Integer> depthSearch(Integer node, int[][] adjazenzmatrix) {

		List<Integer> unvisitedNodes = new ArrayList<>();
		List<Integer> visitedNodes = new ArrayList<>();

		for (int i = 0; i < unvisitedNodes.size(); i++) {
			unvisitedNodes.add(i);
		}

		Stack<Integer> stack = new Stack<>();
		stack.push(node);

		unvisitedNodes.remove(node);
		visitedNodes.add(node);

		while (!stack.isEmpty()) {
			
			Integer current = stack.pop();
			
			List<Integer> neighbors = findeNachbarKnoten(current, adjazenzmatrix);
			// System.out.println("Anzahl der Nachbarn " + neighbors.size());

			// liste von den Nachbarknoten wird durchlaufen
			for (Integer i : neighbors) {
				Integer currentNode = i;

				if (currentNode != null && !visitedNodes.contains(i)) {
					
					stack.push(i);
					unvisitedNodes.remove(i);
					visitedNodes.add(i);
					// System.out.println("Aktueller Knoten " + i);
				}
			}
		}
		return visitedNodes;
	}

	/**
	 * findet alle Nachbarknoten zu einem bestimmtne Knoten.
	 * 
	 * @param knoten
	 * @param adjazenzmatrix
	 * @return
	 */
	public List<Integer> findeNachbarKnoten(int knoten, int[][] adjazenzmatrix) {
		List<Integer> nachbarknoten = new ArrayList<>();

		int[][] adjazenz = adjazenzmatrix;

		// falls zwischen knoten und einem anderen Knoten eine Kante vorhanden ist
		// ist der Eintrag in der Matrix nicht 0 und der andere Knoten wird zur Liste
		// hinzugefuegt
		for (int i = 0; i < adjazenz[knoten].length; i++) {
			if (adjazenz[knoten][i] != 0) {
				nachbarknoten.add(i);
			}
		}
		return nachbarknoten;
	}

	// ******************** Exzentrizitäten ***********************
	/**
	 * Die Exzentrizität eines Knoten im zusammenhängenden Graph ist die Distanz zum
	 * entferntesten Knoten.
	 */
	@Override
	public int[] exzentriziteaten(int[][] distanzmatrix) {
		if (isZusammenhaengend(this.matrix.getAdjazentMatrix()) == false) {
			return null;
		}
		int[] exzentrizitaeten = new int[distanzmatrix.length];
		int max = 0;

		for (int z = 0; z < distanzmatrix.length; z++) {
			for (int s = 0; s < distanzmatrix.length; s++) {
				if (distanzmatrix[z][s] > max) {
					max = distanzmatrix[z][s];
				}
			}
			exzentrizitaeten[z] = max;
			max = 0;
		}
		return exzentrizitaeten;

	}

	// ************************* Radius *************************
	/**
	 * Der Radius ist der niederste Wert aller Exzentrizitäten.
	 */
	@Override
	public int radius(int[][] adjazenzmatrix) {
		int[] exze = exzentriziteaten(matrix.distanzmatrix(adjazenzmatrix));
		int radius = Integer.MAX_VALUE;

		if (exze == null) {
			return radius; // Radius: 0
		} else {
			for (int z = 0; z < exze.length; z++) {
				if (exze[z] < radius) {
					radius = exze[z];
				}
			}
		}
		return radius;
	}

	// ********************** Durchmesser *************************
	/**
	 * Durchmesser ist der höchste Wert aller Exzentrizitäten.
	 */
	@Override
	public int durchmesser(int[][] adjazenzmatrix) {
		int[] exze = exzentriziteaten(matrix.distanzmatrix(adjazenzmatrix));
		int durchmesser = Integer.MIN_VALUE;

		if (exze == null) {
			return durchmesser;
		} else {
			for (int z = 0; z < exze.length; z++) {
				if (exze[z] > durchmesser) {
					durchmesser = exze[z];
				}
			}
		}
		return durchmesser;
	}
	
	public int[][] generateMatrixFromList(List<Integer> list, int size) {
		int[][] matrix = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = 0;
			}
		}
		for (int i = 0; i < list.size() - 1; i++) {
			matrix[list.get(i)][list.get(i + 1)] = 1;
			matrix[list.get(i + 1)][list.get(i)] = 1;
		}

		return matrix;

	}

	public int[][] matrixKopie(int[][] matrix) {
		int[][] copie = new int[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				copie[i][j] = matrix[i][j];
			}

		}
		return copie;
	}
    //********************** Zentrum **********************
	/**
	 * Alle Knoten deren Exzentrizität dem Radius entspricht, befindet sich
	 * im Zentrum des Graphen und werden als zentrale Knoten bezeichnet. 
	 */
	
	@Override
	public int[] zentrum(int[][] adjazenzmatrix) {
		int[] ex = exzentriziteaten(matrix.distanzmatrix(adjazenzmatrix));
		if (ex == null) {
			return null;
		}
		// int ex = [2, 3, 4, 5, 6, 7, 2]
		int r = radius(adjazenzmatrix); // r = 2
		int counter = 0; // Anzahl der Zentrumselemente

		for (int i = 0; i < ex.length; i++) {
			if (r == ex[i]) {
				counter++;
			}
		}

		int[] pos = new int[counter]; // [1][6] -> [2][2]
		int index = 0;

		// i = 0
		// r == ex[0]
		// 2 == 2
		// pos[0] = 1
		// i = 1
		// r == ex[1]
		// 2 == 3
		// i = 2
		// r = ex[2]
		// 2 == 4
		// i = 3
		// r = ex[3]
		// 2 == 5
		// i = 4
		// r = ex[4]
		// 2 == 6
		// i = 5
		// r = ex[5]
		// 2 == 7

		for (int i = 0; i < ex.length; i++) {
			if (r == ex[i]) { // if (2 == 2)
				pos[index] = i + 1;
				index++;
			}
		}
		return pos;
	}
	
	// ******************** Artikulation *************************
	@Override
	public List<Integer> artikulation(int[][] adjazenzmatrix) {
		List<Integer> result = new ArrayList<>();
		this.matrix.setAdjazentMatrix(adjazenzmatrix);
		int anzahlBefore = anzKomponenten(adjazenzmatrix);
		int anzahlAfter = 0;

		for (int z = 0; z < adjazenzmatrix.length; z++) {
			int[][] copie = matrixKopie(adjazenzmatrix);

			for (int i = 0; i < copie.length; i++) {
				for (int j = 0; j < copie.length; j++) {
					if (z == i && copie[i][j] != 0) {
						copie[i][j] = 0;
						copie[j][i] = 0;
					}
				}
			}
			anzahlAfter = anzKomponenten(copie);

			if (anzahlAfter-1 > anzahlBefore) {
				if (!result.contains(z+1)) {
					result.add(z+1);
				}
			}
		}
		return result;
	}
	
	// ******************** Brücken *************************
	@Override
	public Set<Tupel> bruecken(int[][] adjazenzmatrix) {

		Set<Tupel> erg = new HashSet<>();

		int anzahlBefore = anzKomponenten(adjazenzmatrix);
		int anzahlAfter = 0;

		for (int z = 0; z < adjazenzmatrix.length; ++z) {
			for (int i = 0; i < adjazenzmatrix.length; i++) {
				int[][] copie = matrixKopie(adjazenzmatrix);
				if (copie[z][i] != 0) {
					copie[z][i] = 0;
					copie[i][z] = 0;

					anzahlAfter = anzKomponenten(copie);

					if (anzahlAfter > anzahlBefore) {
						Tupel tupel = null;
						if (z > i) {
							tupel = new Tupel(z + 1, i + 1);
						} else {
							tupel = new Tupel(i + 1, z + 1);
						}
						if (!erg.contains(tupel)) {
							erg.add(tupel);
						}
					}

				}
			}
		}
		return erg;
	}
	
	public void print(int[][] matrix) {
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix.length; ++j) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public String komponentenToString(List<Integer> komp) {
		String res = "{ ";
		for(int i = 0; i < komp.size(); i++) {
			res = res + (komp.get(i)+1);
			if(i < komp.size()-1) {
				res =  res + ", ";
			}		
		}
		res = res + " }";
		return res;
	}
	
	@Override
	public String eigenschaften1Print() {
		
		StringBuilder string = new StringBuilder();
		int[][] aktuelleMatrix = this.matrix.getAdjazentMatrix();
		
		Set<Tupel> bruecken = bruecken(aktuelleMatrix);
		List<Integer> artikulation = artikulation(aktuelleMatrix);
		boolean zusammen = isZusammenhaengend(aktuelleMatrix);
		
		if (zusammen == true) {
			string.append(" Radius: " + radius(aktuelleMatrix) + "\n");
			string.append(" Durchmesser: " + durchmesser(aktuelleMatrix) + "\n");
			string.append(" Zentrum: \n");
			string.append(" { ");

			int[] zentrum = zentrum(aktuelleMatrix);
			for (int i = 0; i < zentrum.length; i++) {
				string.append(zentrum[i]);
				if (i < zentrum.length - 1) {
					string.append(",");
				}
			}
			string.append(" } \n");

		}
		string.append(" Anzahl der Artikulation: " + artikulation.size() + "\n");
		string.append(" { ");

		for (int i = 0; i < artikulation.size(); i++) {
			string.append(artikulation.get(i));
			if (i < artikulation.size() - 1) {
				string.append(", ");
			}
		}
		string.append(" } \n");
		
		string.append(" Anzahl der Brücken: " + bruecken.size() + "\n");
		string.append(" { ");

		for (Tupel tupel : bruecken) {
			string.append("[");
			string.append(tupel.getKey() + "," + tupel.getValue());
			string.append("] ");
		}
		string.append("}");
		
		return string.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		int[][] aktuelleMatrix = this.matrix.getAdjazentMatrix();

		String ausgabe = "Nein";
		boolean zusammen = isZusammenhaengend(aktuelleMatrix);
		if (zusammen == true) {
			ausgabe = "Ja";

		}

		//Set<Tupel> bruecken = bruecken(aktuelleMatrix);
		//List<Integer> artikulation = artikulation(aktuelleMatrix);
		string.append(" Anzahl Kanten: " + anzKanten(aktuelleMatrix)+ "\n");
		string.append(" Anzahl Knoten: " + anzKnoten(aktuelleMatrix)+ "\n");
		string.append(" Anzahl der Komponenten: " + anzKomponenten(aktuelleMatrix) + "\n");

		Set<List<Integer>> set = new HashSet<>();
		for (int i = 0; i < aktuelleMatrix.length; i++) {
			List<Integer> list = depthSearch(i, aktuelleMatrix);
			Collections.sort(list);
			set.add(list);
		}

		int k = 1;
		for (List<Integer> list : set) {
			string.append("  K(" + k + ") = " + komponentenToString(list) + "\n");
			k++;
		}
		
		string.append(" Zusammenhägend: " + ausgabe + "\n");

		if (zusammen == true) {
			string.append(" Exzentrizitäten:" + "\n");

			int[] ex = exzentriziteaten(matrix.distanzmatrix(aktuelleMatrix));
			

			int knoten = 1;
			if (ex != null) {
				for (int i : ex) {
					string.append(" Knoten " + knoten++ + " - " + i + "\n");
				}

			}
		}

		return string.toString();
	}
}

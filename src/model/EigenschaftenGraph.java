package model;

import java.util.List;
import java.util.Set;

public interface EigenschaftenGraph {
	boolean isZusammenhaengend(int[][] adjazenzmatrix);

	int anzKomponenten(int[][] adjazenzmatrix);

	int radius(int[][] adjazenzmatrix);

	int durchmesser(int[][] adjazenzmatrix);

	int[] exzentriziteaten(int[][] distanzmatrix);

	int[] zentrum(int[][] adjazenzmatrix);

	List<Integer> artikulation(int[][] adjazenzmatrix);

	Set<Tupel> bruecken(int[][] adjazenzmatrix);

	List<Integer> depthSearch(Integer node, int[][] adjazenzmatrix);

	String toString();

	String eigenschaften1Print();

	void setMatrix(Matrix matrix);

	void print(int[][] matrix);
	
	int anzKanten(int [][] adjazenzmatrix);
	
	int anzKnoten(int [][] adjazenzmatrix);
}

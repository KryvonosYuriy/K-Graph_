
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.EigenschaftenGraph;
import model.EigenschaftenGraphImpl;
import model.Matrix;

public class MainWindows extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel obenPanel;
	private JButton startButton, resetButton;
	private DialogWindows dialog;
	private JPanel fenstername, adjazentMatrixFenster, komplementaerMatrixFenster, distanzMatrixFenster, wegMatrixFenster, eigeschaftenMatrixFenster1, eigeschaftenMatrixFenster2;
	private Matrix matrix;
	private EigenschaftenGraph eigenschaftGraph;
	private JToggleButton [][] adjazenzMatrix, komplemtaermatrix, wegmatrix, distanzmatrix;
	private JTextArea text1, text2;
	
	public MainWindows(){
		initBasics();
		initComponents();
		generateGrid();
		addComponents();
		addListeners();
		setVisible(true);
	}
	
	public void initBasics(){
		setTitle("\"K-Graph\"");
		setSize(900, 700);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initComponents(){
		obenPanel = new JPanel();
		obenPanel.setLayout(new FlowLayout());
		fenstername = new JPanel(new GridLayout(3,2));
		startButton = new JButton("Start");
		resetButton = new JButton("Rücksetzen");
		dialog = new DialogWindows();	
	}
	
	public void generateGrid() {

		adjazentMatrixFenster = new JPanel(new BorderLayout());
		JLabel label1 = new JLabel("Adjazenzmatrix");
		label1.setHorizontalAlignment(JLabel.CENTER);
		adjazentMatrixFenster.add(label1, BorderLayout.NORTH);
		adjazentMatrixFenster.setBackground(Color.YELLOW);

		komplementaerMatrixFenster = new JPanel(new BorderLayout());
		JLabel label2 = new JLabel("Komplementärmatrix");
		label2.setHorizontalAlignment(JLabel.CENTER);
		komplementaerMatrixFenster.add(label2, BorderLayout.NORTH);
		komplementaerMatrixFenster.setBackground(Color.GREEN);

		distanzMatrixFenster = new JPanel(new BorderLayout());
		JLabel label3 = new JLabel("Distanzmatrix");
		label3.setHorizontalAlignment(JLabel.CENTER);
		distanzMatrixFenster.add(label3, BorderLayout.NORTH);
		distanzMatrixFenster.setBackground(Color.CYAN);

		wegMatrixFenster = new JPanel(new BorderLayout());
		JLabel label4 = new JLabel("Wegmatrix");
		label4.setHorizontalAlignment(JLabel.CENTER);
		wegMatrixFenster.add(label4, BorderLayout.NORTH);
		wegMatrixFenster.setBackground(Color.ORANGE);

		eigeschaftenMatrixFenster1 = new JPanel(new BorderLayout());
		JLabel label5 = new JLabel("Eingeschaften des Matrix");
		label5.setHorizontalAlignment(JLabel.CENTER);
		text1 = new JTextArea();
		JScrollPane textpanel = new JScrollPane(text1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		eigeschaftenMatrixFenster1.add(textpanel);
		eigeschaftenMatrixFenster1.add(label5, BorderLayout.NORTH);
		eigeschaftenMatrixFenster1.setBackground(new Color(200, 164, 92));

		eigeschaftenMatrixFenster2 = new JPanel(new BorderLayout());
		JLabel label6 = new JLabel("Eingeschaften des Matrix");
		label6.setHorizontalAlignment(JLabel.CENTER);
		text2 = new JTextArea();
		JScrollPane textpanel1 = new JScrollPane(text2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		eigeschaftenMatrixFenster2.add(textpanel1);
		eigeschaftenMatrixFenster2.add(label6, BorderLayout.NORTH);
		eigeschaftenMatrixFenster2.setBackground(new Color(200, 164, 92));

		adjazentMatrixFenster.setVisible(true);

		fenstername.add(adjazentMatrixFenster);
		fenstername.add(komplementaerMatrixFenster);
		fenstername.add(distanzMatrixFenster);
		fenstername.add(wegMatrixFenster);
		fenstername.add(eigeschaftenMatrixFenster1);
		fenstername.add(eigeschaftenMatrixFenster2);

		enableComponents(false);
	}
	
	public void addComponents(){
		obenPanel.add(startButton);
		obenPanel.add(resetButton);
		
		add(obenPanel, BorderLayout.NORTH);
		add(fenstername);
	}
	
	private void enableComponents(boolean enabled){
		fenstername.setVisible(enabled);
	}
	
	private void addListeners(){
		startButton.addActionListener(e -> startButton());
		resetButton.addActionListener(e -> resetButton());
	}
	//**************************** sonstige Methodem ***************************
	
	// Das Button "Start" wird das Dialogfenster mit der Größer der Martix (wert x wert) zeigen.
	public void startButton(){

		int matrixDimension = dialog.updateAndShow();
		
		this.matrix = new Matrix(matrixDimension, matrixDimension);
		this.adjazenzMatrix = new JToggleButton[matrixDimension][matrixDimension];
		this.komplemtaermatrix = new JToggleButton[matrixDimension][matrixDimension];
		this.distanzmatrix = new JToggleButton[matrixDimension][matrixDimension];
		this.wegmatrix = new JToggleButton[matrixDimension][matrixDimension];

		adjazentMatrixFenster.add(generateMatrixGrid(matrixDimension, this.adjazenzMatrix, true));
		komplementaerMatrixFenster.add(generateMatrixGrid(matrixDimension, this.komplemtaermatrix, false));
		distanzMatrixFenster.add(generateMatrixGrid(matrixDimension, this.distanzmatrix, false));
		wegMatrixFenster.add(generateMatrixGrid(matrixDimension, this.wegmatrix, false));
		
		startButton.setEnabled(false);
		enableComponents(true);
	}	
	
	// wird alles zurueckgesezt
	public void resetButton() {
		adjazentMatrixFenster.remove(1);
		adjazentMatrixFenster.repaint();
		adjazentMatrixFenster.revalidate();
		
		komplementaerMatrixFenster.remove(1);
		komplementaerMatrixFenster.repaint();
		komplementaerMatrixFenster.revalidate();
		
		distanzMatrixFenster.remove(1);
		distanzMatrixFenster.repaint();
		distanzMatrixFenster.revalidate();
		
		wegMatrixFenster.remove(1);
		wegMatrixFenster.repaint();
		wegMatrixFenster.revalidate();
		
		fenstername.setVisible(false);
		text1.setText("");
		text2.setText("");
		startButton.setEnabled(true);
	}
	
	public void erstellenMatrix(int dimension) {
		fenstername.removeAll();
		matrix = new Matrix(dimension);
		dimension += 1;
		adjazentMatrixFenster.setLayout(new GridLayout(dimension, dimension));
	}
	
	// hier wird alle Berechnungen-Ergebnisse gezeigt.
	public void ergebnisInfoDaten() {
		
		eigenschaftGraph = new EigenschaftenGraphImpl(matrix);
	
		int [][] komplementaer = this.matrix.komplementaer(this.matrix.getAdjazentMatrix());
		int [][] distanzmatrix =  this.matrix.distanzmatrix(this.matrix.getAdjazentMatrix());
		int [][] wegmatrix =  this.matrix.wegmatrix1(this.matrix.getAdjazentMatrix());
		
		updateMatrix(komplementaer, this.komplemtaermatrix, komplementaerMatrixFenster);
		updateMatrix(distanzmatrix, this.distanzmatrix, distanzMatrixFenster);
		updateMatrix(wegmatrix, this.wegmatrix, wegMatrixFenster);
		
		text1.setText("");
		text1.setText(eigenschaftGraph.toString());
		
		text2.setText("");
		text2.setText(eigenschaftGraph.eigenschaften1Print());
	}
	
	// Matrix-Daten
	public JPanel generateMatrixGrid(int dimension, JToggleButton[][] ttbuttons, boolean enabled) {
		JPanel matrix = new JPanel(new GridLayout(dimension, dimension));
		
		for(int i = 0; i<dimension;i++) {
			for(int j = 0; j < dimension; j++) {
			
				ttbuttons[i][j] = new JToggleButton("0");
				
				ttbuttons[i][j].putClientProperty("xKoordinate", j);
				ttbuttons[i][j].putClientProperty("yKoordinate", i);
				
				//Diagonal der Matrix
				if(i != j && enabled) {
					ttbuttons[i][j].addActionListener(this);
				}else if(i != j && !enabled) {
					Color farbe = new Color(230,230,250);
					ttbuttons[i][j].setBackground(farbe);
					ttbuttons[i][j].setForeground(Color.BLACK); 
					ttbuttons[i][j].setEnabled(false);
								
				}else {
					ttbuttons[i][j].setBackground(Color.BLACK);
					ttbuttons[i][j].setForeground(Color.WHITE); 
					ttbuttons[i][j].setEnabled(false);
					
				}
				ttbuttons[i][j].setToolTipText("Von "+(i+1)+" nach "+(j+1));
				matrix.add(ttbuttons[i][j]);
			}
		}
		return matrix;
	}
	
	public void guiToAdjazenzMatrix(){
		int [][] matrix = new int[this.adjazenzMatrix.length][this.adjazenzMatrix.length];
		for(int i=0; i<this.adjazenzMatrix.length;i++) {
			for(int j=0; j<this.adjazenzMatrix.length; j++) {
				if(this.adjazenzMatrix[i][j].getText().equals("0")) {
					matrix[i][j] = 0;
				}else {
					matrix[i][j] = 1;
				}	
			}
		}
		
		this.matrix.setAdjazentMatrix(matrix);	
	}

	public void updateMatrix(int [][] matrix, JToggleButton[][] buttons, JPanel panel) {
		
		for(int i=0; i<matrix.length;i++) {
			for(int j=0; j<matrix.length; j++) {
				if(matrix[i][j] == Integer.MAX_VALUE) {
					buttons[i][j].setText("∞");
				}else {
					buttons[i][j].setText(String.valueOf(matrix[i][j]));
				}
			}
		}
		panel.repaint();
		panel.revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	
		JToggleButton pressed = (JToggleButton) actionEvent.getSource();
		
		int indexX = (int) pressed.getClientProperty("xKoordinate");
		int indexY = (int) pressed.getClientProperty("yKoordinate");
		
		String txt = pressed.getText();
		
		if(pressed.isSelected() && txt.equals("0")) {	
			this.adjazenzMatrix[indexX][indexY].setText("1");
		    this.adjazenzMatrix[indexY][indexX].setSelected(true);
			this.adjazenzMatrix[indexY][indexX].setText("1");
			
			guiToAdjazenzMatrix();
			ergebnisInfoDaten();
			
		}else {
				this.adjazenzMatrix[indexX][indexY].setText("0");
				this.adjazenzMatrix[indexY][indexX].setSelected(false);
				this.adjazenzMatrix[indexY][indexX].setText("0");
						
				guiToAdjazenzMatrix();
				ergebnisInfoDaten();
			}	
	}
}

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class DialogWindows extends JDialog implements ActionListener{
	private JComboBox<String> matrixAuswahl;
	private JButton btn;
	private JPanel panel1, panel2;
	private JLabel text;
	private int matrixDimension;
	private boolean okPressed;
	
	public DialogWindows() {
		initBasics();
		initComponents();
		addComponents();
		setVisible(false);
	}
	
	private void initBasics(){
		setLayout(new GridLayout(0, 1));
		setSize(310, 110);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
	}
	
	private void initComponents(){
		
		panel1 = new JPanel();
		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		text = new JLabel("Bitte wählen Sie Größe der Matrix aus:");
		btn = new JButton("Ok");
		btn.addActionListener(this);
		
		matrixAuswahl = new JComboBox<String>();
		for(int i=3; i<=15; ++i) {
			String auswahl = i + "x" + i;
			matrixAuswahl.addItem(auswahl);
		}
	}
	
	private void addComponents(){
		panel1.add(text);
		panel1.add(matrixAuswahl);
		panel2.add(btn);
		add(panel1);
		add(panel2);
	}
	
	public void okpressed() {
		
			int matrixDimension = matrixAuswahl.getSelectedIndex() +3;
			System.out.println(matrixDimension);
			okPressed = true;
			dispose();
	}
	
	public int updateAndShow(){
		setTitle("Auswahl-Matrix");
		setVisible(true);
		return this.matrixDimension;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == this.btn) {
			this.matrixDimension = matrixAuswahl.getSelectedIndex() +3;
			dispose();
		}		
	}
	
	public boolean isOkPressed(){
		return okPressed;
	}	
}

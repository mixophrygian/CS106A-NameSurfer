/*
 * File: NameSurfer.java
 * ---------------------
 * This program implements the viewer for
 * the baby-name database described in the assignment handout from CS106A, Programming Methodology. 
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import acm.graphics.*;
import java.util.*;


public class NameSurfer extends Program implements NameSurferConstants {

		
	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	 
	public void init() {
	    // Adds the interactors to the window//
		add(new JLabel("Name"), SOUTH);
		textField = new JTextField(MAX_NAME_SIZE);
		textField.addActionListener(this);
		add(textField, SOUTH);
		Clear = new JButton ("Clear");
		Graph = new JButton ("Graph");
		add(Graph, SOUTH);
		add(Clear, SOUTH);
		
		nameGraph = new NameSurferGraph();
		add(nameGraph);
		
		addActionListeners();
		
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	 
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == textField || source == Graph) {
			String userEntry = textField.getText();
			userEntry = userEntry.toLowerCase();
			currentSurfer = dataBaseONames.findEntry(userEntry);
			if(currentSurfer != null){
			//add current Surfer to the database and update!//
				nameGraph.addEntry(currentSurfer);
				nameGraph.update();

		}else{
			textField.setText(userEntry.substring(0,1).toUpperCase() + userEntry.substring(1) + " is a fairly unpopular name");
		}
		}
		if (source == Clear) {
			nameGraph.clear();
			nameGraph.update();
			textField.setText(null);
		}
		}
	
	private int MAX_NAME_SIZE = 30;
	private JTextField textField;
	private JButton Graph;
	private JButton Clear;
	private NameSurferEntry currentSurfer;
	private NameSurferDataBase dataBaseONames = new NameSurferDataBase(NAMES_DATA_FILE);
	private NameSurferGraph nameGraph;
}


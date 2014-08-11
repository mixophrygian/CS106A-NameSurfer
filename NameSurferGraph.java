/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	 
	public NameSurferGraph() {
		addComponentListener(this);
	    displayedNames = new ArrayList<NameSurferEntry>();;
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	 
	public void clear() {
		   displayedNames.clear();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	 
	public void addEntry(NameSurferEntry entry) {
		  displayedNames.add(entry);
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	 
	public void update() {
		 removeAll();
	        //displays the grid and labels for each decade//
	                int verticalLineX = 0;
	                int lineSpaceWidth = getWidth() / NDECADES;
	                int topHorizontalLineY = GRAPH_MARGIN_SIZE;
	                int bottomHorizontalLineY = getHeight() - GRAPH_MARGIN_SIZE;
	                int decadeLabelCounter = 0;
	                add(new GLine(0, bottomHorizontalLineY, getWidth(), bottomHorizontalLineY));
	                add(new GLine(0, topHorizontalLineY, getWidth(), topHorizontalLineY));
	                for(int i = 0; i < NDECADES; i++){
	                        add(new GLine(verticalLineX, 0, verticalLineX, getHeight()));
	                        add(new GLabel(""+(1900 + decadeLabelCounter)+"", verticalLineX + 4, bottomHorizontalLineY + 14 ));
	                        decadeLabelCounter += 10;
	                        verticalLineX += lineSpaceWidth;
	                        }
	                if(displayedNames.size() > 0){
	                	drawGraphs(displayedNames);
	                }

	}
	
	
	public void drawGraphs(ArrayList<NameSurferEntry> displayedNames) {
		//draws any and all of the graphs contained in the displayedNames ArrayList//
		for (int i = 0; i < displayedNames.size(); i++){
			NameSurferEntry thisSurfer = displayedNames.get(i);
			color = selectColor(i);
			int topHorizontalLineY = GRAPH_MARGIN_SIZE;
            int bottomHorizontalLineY = getHeight() - GRAPH_MARGIN_SIZE;
			int lineSpaceWidth = getWidth() / NDECADES;
			int currentX = 0;
			int nextX = currentX + lineSpaceWidth;
			int currentRank;
			int nextRank;
			int currentRankY;
			int nextRankY;
			for ( int j = 0; j < NDECADES; j++) {
				currentRank = thisSurfer.getRank(j);
				
				if(currentRank == 0){
					currentRankY = bottomHorizontalLineY;
				}else{
				currentRankY = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * currentRank / MAX_RANK;
				}
				
				if(j == (NDECADES-1)){
					break;
				}else{
				nextRank = thisSurfer.getRank(j+1);
				}
				if(nextRank == 0){
					nextRankY = bottomHorizontalLineY;
				}else{
				nextRankY = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * nextRank / MAX_RANK;
				}
				GLine line = new GLine(currentX, currentRankY, nextX, nextRankY);
				line.setColor(color);
				add(line);
				GLabel name = new GLabel(""+thisSurfer.getName()+" " + "("+currentRank+")", currentX, currentRankY);
				if(currentRank == 0) {
					name.setLabel(""+thisSurfer.getName()+"*");
				}
				name.setColor(color);
				add(name);
				currentX = nextX;
				nextX = currentX + lineSpaceWidth;
				currentRank = nextRank;
				
			}
			
		}
		
	}


	public Color selectColor(int i) {
		//Cycles through four colors for each new graph created//
		i += 1;
		if( i >= 4) { 
			i = (i % 4); 
		}
		switch(i) {
		case 0: return MAGENTA;
		case 1: return BLACK;
		case 2: return RED;
		case 3: return BLUE;
		default: return BLACK;
			
		}
		
	}


	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	private ArrayList <NameSurferEntry> displayedNames; 
	public static final Color BLACK = new Color(0, 0, 0);
	public static final Color RED = new Color(255, 0, 0);
	public static final Color BLUE = new Color(0, 0, 255);
	public static final Color MAGENTA = new Color(255, 0, 255);
	public Color color;
}

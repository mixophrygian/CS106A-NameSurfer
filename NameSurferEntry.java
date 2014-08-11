/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;
import acm.program.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;


public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	 
	public NameSurferEntry(String line) {
	    // This method takes a line from the data file containing Name Surfer data and 
		//turns it into a new NameSurfer Entry//

		int nameEnd = line.indexOf(' ');
		name = line.substring(0, nameEnd);
		numbersOnly = line.substring(nameEnd + 1);
		decadeArray = new int[NDECADES];

		StringTokenizer tokenizer = new StringTokenizer(numbersOnly);
		int i = 0;
		int numberToken;
		while(tokenizer.hasMoreTokens()) {
		        String token = tokenizer.nextToken();
		        numberToken = Integer.parseInt(token);
		        decadeArray[i] = numberToken;
		        i++;
		}
	}
	
	public int[] getDecadeArray() {
		return decadeArray;
	}
	

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	 
	public String getName() {
		 return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		int rank = decadeArray[decade];   
		return rank;
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		return ""+name + " [" + numbersOnly + "]";
	}
	
	private String name;
	private int[] decadeArray;
	private String numbersOnly;

	
}


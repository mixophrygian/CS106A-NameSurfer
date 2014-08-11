/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 */
 
import acm.program.*;
import acm.util.*;
import java.awt.Event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class NameSurferDataBase implements NameSurferConstants {
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {

		namesDatabase = new HashMap <String,NameSurferEntry>();
		
		try {
			BufferedReader rd = new BufferedReader (new FileReader(filename));
			while(true) {
				String line = rd.readLine();
				if(line == null) break;
				NameSurferEntry nameSurfer = new NameSurferEntry(line);
				namesDatabase.put(nameSurfer.getName(), nameSurfer);
			}
			rd.close();
		}catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}

	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	 
	public NameSurferEntry findEntry(String name) {
		
		if(namesDatabase.keySet().contains(name)){
			NameSurferEntry currentSurfer = namesDatabase.get(name);
			return currentSurfer;
		}else
		return null;
	}
	
	private HashMap<String, NameSurferEntry> namesDatabase;
}



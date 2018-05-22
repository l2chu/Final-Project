package main;

import java.io.*;
import java.util.*;

public class FileIO {
	private String fName = null;
	private boolean DEBUG = false;

	/**
	 * Construct a FileIO object
	 * 
	 * @param fname
	 */
	public FileIO(String fname) {
		String filePath = new File("").getAbsolutePath();
		this.fName = (filePath + fname);
	}

	/**
	 * read the data in the fName file and put into a string arraylist
	 * 
	 * @return the arraylist of all the strings in the file
	 */
	public ArrayList<String> readData() {
		// make new String arraylist
		ArrayList<String> dataArray = new ArrayList<String>();
		try {
			// get the file
			FileReader file = new FileReader(this.fName);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			// keep going until end of file line (null)
			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					if (DEBUG)
						System.out.println("Reading " + line);

					// add to string array
					dataArray.add(line);
					if (DEBUG)
						System.out.println("just added: " + line);

				}
			}
			// close buffer
			buff.close();
		} catch (Exception e) {
			System.out.println("Error -- " + e.toString());
		}
		return dataArray;
	}

}

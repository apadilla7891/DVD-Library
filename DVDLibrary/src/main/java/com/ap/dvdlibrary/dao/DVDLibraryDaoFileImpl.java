package com.ap.dvdlibrary.dao;

import com.ap.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Andy Padilla
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao{
    
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    private Map<String, DVD> library = new HashMap<>();
    
    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException, FileNotFoundException{
        loadLibrary();
        DVD newDVD = library.put(title, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException, FileNotFoundException{
        loadLibrary();
        DVD removedDVD = library.remove(title);
        writeLibrary();
        return removedDVD;
    }

    @Override
    public DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException, FileNotFoundException{
        loadLibrary();
        library.replace(title, dvd);
        writeLibrary();
        return library.get(title);
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException, FileNotFoundException{
        loadLibrary();
        return new ArrayList(library.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException, FileNotFoundException{
        loadLibrary();
        return library.get(title);
    }

    @Override
    public DVD searchDVD(String title) throws DVDLibraryDaoException, FileNotFoundException{
        loadLibrary();
        return library.get(title);
    }
    
    private DVD unmarshallDVD(String dvdAsText){
    // dvdAsText gets a line from the file
    // it is in the following format
    // title::release date::mpaa rating::director::studio::user note
    //  [0]     [1]            [2]     [3]     [4]     [5]
        String[] dvdTokens = dvdAsText.split(DELIMITER);

    // DVD title is in index 0 of the array.
        String dvdTitle = dvdTokens[0];

    // create a new dvd object using the constructor and extracted title from file
        DVD dvdFromFile = new DVD(dvdTitle);

     //using setters, manually set the other values.

    // Index 1 - release date
        dvdFromFile.setReleaseDate(dvdTokens[1]);

    // Index 2 - mpaa rating
        dvdFromFile.setRating(dvdTokens[2]);

    // Index 3 - director
        dvdFromFile.setDirector(dvdTokens[3]);
        
    // Index 4 - studio
        dvdFromFile.setStudio(dvdTokens[4]);

    // Index 5 - user note
        dvdFromFile.setUserNote(dvdTokens[5]);
        
    // return created dvd
        return dvdFromFile;
    }
    
    
    private void loadLibrary() throws DVDLibraryDaoException, FileNotFoundException {
        Scanner scanner;

        try {
            // Creates Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            //catches the FileNotFoundException and translates it to the DVDLibraryDaoException created
            throw new DVDLibraryDaoException("Error DVD library data could not be loaded into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDVD holds the most recent dvd unmarshalled
        DVD currentDVD;
        // Goes through LIBRARY_FILE line by line, decoding each line into a dvd object by calling the unmarshallDVD method.
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVD
            currentDVD = unmarshallDVD(currentLine);

            // Foloowing what was done earlier we will us the dvd title as the map key for the dvd object.
            library.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }
    
    private String marshallDVD(DVD aDVD){
        // Turna a dvd object into a line of text for our file.
        // it will be in the following format 
        //title::release date::mpaa rating::director::studio::user note
        // Start with the title and add delimiter to crate space between each value
        String dvdAsText = aDVD.getTitle() + DELIMITER;
        // release date
        dvdAsText += aDVD.getReleaseDate() + DELIMITER;
        // MPAA rating
        dvdAsText += aDVD.getRating() + DELIMITER;
        // director
        dvdAsText += aDVD.getDirector() + DELIMITER;
        // studio
        dvdAsText += aDVD.getStudio() + DELIMITER;
        // user note, since this is the last value skip adding delimiter
        dvdAsText += aDVD.getUserNote();
        // return the new line of text
        return dvdAsText;
    }
    
    //Writes all dvds in the library out to a LIBRARY_FILE.  
    private void writeLibrary() throws DVDLibraryDaoException, FileNotFoundException {
        PrintWriter out;
        
        //catches and translates IOExceptions to DVDLibraryDaoExceptions
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException( "Could not save dvd data.", e);
        }

        // Write out the dvd objects to the library file.
        //Reuses a previously created method to do this
        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {
            // turn a dvd into a String
            dvdAsText = marshallDVD(currentDVD);
            // write the dvd object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up and close printwriter
        out.close();
    }
}

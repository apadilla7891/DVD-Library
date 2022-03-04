package com.ap.dvdlibrary.dao;

import com.ap.dvdlibrary.dto.DVD;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author Andy Padilla
 */
public interface DVDLibraryDao {
    /**
     * Adds the given DVD to the library. Using the title if there is already
     * a dvd in the library it will return it, otherwise it will
     * return null.
     */
    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException, FileNotFoundException;
    
    
    /**
     * Removes from the library the given dvd.
     * Returns the dvd object that is being removed or null if
     * there is no dvd associated with the given title
     */
    DVD removeDVD(String title) throws DVDLibraryDaoException, FileNotFoundException;
    
    /**
     * Looks up the dvd object associated with the given title.
     * Prompts users for each of the new values it will hold.
     * Returns null if no such dvd exists
     */
    DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException, FileNotFoundException;
    
    /**
     * Returns a List of all dvds in the library.
     */
    List<DVD> getAllDVDs() throws DVDLibraryDaoException, FileNotFoundException;

    /**
     * Returns the dvd object associated with the given title.
     * Returns null if no such dvd exists
     */
    DVD getDVD(String title) throws DVDLibraryDaoException, FileNotFoundException;

    /**
     * looks up the dvd object associated with the given title.
     * Returns if its exists in library or null if no such dvd exists
     */
    DVD searchDVD(String title) throws DVDLibraryDaoException, FileNotFoundException;
}


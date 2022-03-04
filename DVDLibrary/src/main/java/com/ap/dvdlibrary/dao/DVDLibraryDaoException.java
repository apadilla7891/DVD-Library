package com.ap.dvdlibrary.dao;

/**
 *
 * @author Andy Padilla
 */
public class DVDLibraryDaoException extends Exception{
    
    //exception case when something is wrong but it isn't cased by another exception ie one of the fields of a dvd isn't valid
    public DVDLibraryDaoException(String message) {
        super(message);
    }
    
    //exception when something is wrong and is caused by another exception ie implementation-specific exception
    public DVDLibraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}

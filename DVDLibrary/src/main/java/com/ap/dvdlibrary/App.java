package com.ap.dvdlibrary;

import com.ap.dvdlibrary.controller.DVDLibraryController;
import com.ap.dvdlibrary.dao.DVDLibraryDao;
import com.ap.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.ap.dvdlibrary.ui.DVDLibraryView;
import com.ap.dvdlibrary.ui.UserIO;
import com.ap.dvdlibrary.ui.UserIOConsoleImpl;
import java.io.FileNotFoundException;

/**
 *
 * @author Andy Padilla
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }  
}

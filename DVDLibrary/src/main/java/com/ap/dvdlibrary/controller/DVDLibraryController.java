package com.ap.dvdlibrary.controller;

import com.ap.dvdlibrary.dao.DVDLibraryDao;
import com.ap.dvdlibrary.dao.DVDLibraryDaoException;
import com.ap.dvdlibrary.dto.DVD;
import com.ap.dvdlibrary.ui.DVDLibraryView;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author Andy Padilla
 */
public class DVDLibraryController {
    
    //dependency injection- make it so that it is up to App to set up the view and dao that is to be used
    private DVDLibraryView view;
    private DVDLibraryDao dao;
    //constructor used to associate the proper dao and view
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() throws FileNotFoundException {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try{
            while (keepGoing) {
                //calls method that directs menu selection to DVDLibraryView
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVDs();
                        break;
                    case 2:
                        addDVD();
                        break;
                    case 3:
                        viewDVD();
                        break;
                    case 4:
                        removeDVD();
                        break;
                    case 5:
                        changeDVD();
                        break;
                    case 6:
                        findDVD();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch(DVDLibraryDaoException e){
           view.displayErrorMessage(e.getMessage()); 
        }
    }
    //used to call external method to do menu selection
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    // calls displayAddDVDBanner, getNewDVDInfo and displayAddSuccessBanner from the DVDLibraryView
    //calls the addDVD from the DVDLibraryDaoFileImpl
    private void addDVD() throws DVDLibraryDaoException, FileNotFoundException{
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayAddSuccessBanner();
    }
    
    // calls displayDisplayAllBanner, letting user know that all dvds are about to be displayed, and the format the results printed
    // puts all dvds in the map into and array and then passes it to the appropriate class to be printed to user 
    private void listDVDs() throws DVDLibraryDaoException, FileNotFoundException{
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    //calls displayRemoveDVDBanner leeting user know that they'll be removing a dvd from the library
    //gets the dvd user is searching for and if it is in the library removes it while informing user of the result.
    private void removeDVD() throws DVDLibraryDaoException, FileNotFoundException{
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD removedDVD = dao.removeDVD(dvdTitle);
        view.displayRemoveResult(removedDVD);
    }
    
    //calls displayDisplayDVDBanner letting user know that a specific dvd info will be shown
    //  gets the dvd title from user and if it is in the library display all its information, otherwise it informs user no such movie exists.
    private void viewDVD() throws DVDLibraryDaoException, FileNotFoundException{
        view.displayDisplayDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvdView = dao.getDVD(dvdTitle);
        view.displayDVD(dvdView);
    }
    
    //calls displaySearchDVDBanner letting user know that a specific dvd will be looked up
    //  gets the dvd title from user and if it is in the library let the user know it exists, otherwise it informs user no such movie exists.
    private void findDVD() throws DVDLibraryDaoException, FileNotFoundException{
        view.displaySearchDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvdView = dao.getDVD(dvdTitle);
        view.searchDVD(dvdView);
    }
    
    //calls displayEditDVDBanner letting user know that a specific dvd will be edited
    //  gets the dvd title from user and if it is in the library allow user to change its information, otherwise it informs user no such movie exists.
    private void changeDVD() throws DVDLibraryDaoException, FileNotFoundException{
        view.displayEditDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvdView = dao.getDVD(dvdTitle);
        view.editDVD(dvdView);
        dao.editDVD(dvdTitle, dvdView);
    }
    
    //calls displayUnknownCommand to print out and handle when user enters something that doesnt work
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    //calls displayExitBanner to print out message for when user exits the program.
    private void exitMessage() {
        view.displayExitBanner();
    }
}

package com.ap.dvdlibrary.ui;

import com.ap.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Andy Padilla
 */
public class DVDLibraryView {
    
    //dependency injection to make it loosely coupled
    private UserIO io;
    //constructor to initialize the IO member
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }
    
    //prints out the menu to the console and gets the user choice
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List all DVDs");
        io.print("2. Add a DVD");
        io.print("3. View a DVD");
        io.print("4. Remove a DVD");
        io.print("5. Edit a DVD's information");
        io.print("6. Search for a DVD by title");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    //creates new dvd in library and prompts user to fill out its information
    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter release date");
        String rating = io.readString("Please enter MPAA rating");
        String director = io.readString("Please enter director");
        String studio = io.readString("Please enter studio");
        String userNote = io.readString("Please enter any notes you have on the DVD");
        DVD currentDVD = new DVD(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setRating(rating);
        currentDVD.setDirector(director);
        currentDVD.setStudio(studio);
        currentDVD.setUserNote(userNote);
        return currentDVD;
    }
 
    //indicates that a new DVD is being added
    public void displayAddDVDBanner() {
        io.print("=== Add DVD ===");
    }
    
    //lets user know that DVD was successfully added
    public void displayAddSuccessBanner() {
        io.readString("DVD successfully added.  Please hit enter to continue");
    }
    
     //displays all dvds in the library to the console
    public void displayDVDList(List<DVD> dvdList) {
        // loops through the list and prints out dvd information in the folllowing format
        // title: rating : release date
        for (DVD currentDVD : dvdList) {
            String dvdInfo = String.format("#%s : %s : %s",
                currentDVD.getTitle(),
                currentDVD.getRating(),
                currentDVD.getReleaseDate());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    //banner printed to console leeting user know all dvds from library are being displayed
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
        io.print("=== Title : rating : Release Date ===");
    }
    
    //banner printed to console letting user know a dvd will be displayed
     public void displayDisplayDVDBanner () {
        io.print("=== Display DVD ===");
    }
    
    // asks user for the title of the DVD they wish to get
    public String getDVDChoice() {
        return io.readString("Please enter the title of the DVD.");
    }
    
    //checks to see if DVD exists, if it doesnt let the user know other wise print out all the information for the specific dvd
    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("Rating: " + dvd.getRating());
            io.print("Director: " + dvd.getDirector());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Note: " + dvd.getUserNote());
            io.print("");
        } else {
            io.print("No such DVD in library.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //banner printed letting user know a dvd will be removed
    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }
    
    //checks if dvd is not null, if it is lets user know there is no such dvd otherwise lets user know dvd has been removed
    public void displayRemoveResult(DVD containDVD) {
        if(containDVD != null){
            io.print("DVD successfully removed from library.");
        }else{
            io.print("No such DVD in library.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //banner printed to console letting user know the program will search for a dvd
     public void displaySearchDVDBanner () {
        io.print("=== Search DVD ===");
    }
     
    //checks to see if DVD exists, if it doesnt let the user know other wise lets user know it is currently in the library
    public void searchDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle() +" is currently in the library");
        } else {
            io.print("No such DVD in library.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //banner printed to console letting user know the program will edit the information of a dvd
     public void displayEditDVDBanner () {
        io.print("=== Edit DVD ===");
    }
    
    //checks to see if DVD exists, if it doesnt let the user know other wise lets user edit the dvd information aside from title.
    public void editDVD(DVD dvd) {
        if (dvd != null) {
            io.print("You are editing the information for " + dvd.getTitle());
            String releaseDate = io.readString("Please enter release date");
            String rating = io.readString("Please enter MPAA rating");
            String director = io.readString("Please enter director");
            String studio = io.readString("Please enter studio");
            String userNote = io.readString("Please enter any notes you have on the DVD");
            dvd.setReleaseDate(releaseDate);
            dvd.setRating(rating);
            dvd.setDirector(director);
            dvd.setStudio(studio);
            dvd.setUserNote(userNote);
        } else {
            io.print("No such DVD in library.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //banner for exiting program
    public void displayExitBanner() {
        io.print("Thank you for using this program. Good bye!");
    }
    
    
    //banner for unknown commands
    public void displayUnknownCommandBanner() {
        io.print("Error Unknown Command!");
    }    
    
    //error message
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}

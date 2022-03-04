package com.ap.dvdlibrary.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Andy Padilla
 */
public class DVD {
    //dvd info
    private String title;
    private String releaseDate;
    private String rating;
    private String director;
    private String studio;
    private String userNote;
    //adding local date
    private LocalDate release;

    //constructor using dvd title
    public DVD(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }


    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        this.release = LocalDate.parse(this.releaseDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
    
    
}

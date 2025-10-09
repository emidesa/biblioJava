package cda.bibliotheque.model;

import java.sql.Date;
import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private LocalDate release_date;
    private boolean isAvailable;

    public Book() {
    }

    public Book(int id, String title, LocalDate release_date, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.isAvailable = isAvailable;
    }

    public Book(int id, String title, java.util.Date release_date, boolean isAvailable) {
        this.id = id;
        this.title = title;
        if (release_date != null) {
            this.release_date = new java.sql.Date(release_date.getTime()).toLocalDate();
        } else {
            this.release_date = null;
        }
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public Date getRelease_date_Date() {
        return (release_date == null) ? null : java.sql.Date.valueOf(release_date);
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
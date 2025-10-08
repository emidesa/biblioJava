package cda.bibliotheque.model;

import java.sql.Date;
import java.time.LocalDate;

public class Author {
    private int id;
    private String lastname;
    private String firstname;
    private LocalDate born_at;

    public Author() {
    }

    public Author(int id, String lastname, String firstname, LocalDate bornAt) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.born_at = bornAt;
    }
    
    public Author(int id, String lastname, String firstname, java.util.Date bornAt) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        // Convert java.util.Date to LocalDate via Instant
        if (bornAt != null) {
            this.born_at = new java.sql.Date(bornAt.getTime()).toLocalDate();
        } else {
            this.born_at = null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public LocalDate getBorn_at() {
        return born_at;
    }

    public Date getBorn_at_Date() {
        return (born_at == null) ? null : java.sql.Date.valueOf(born_at);
    }

    public void setBorn_at(LocalDate born_at) {
        this.born_at = born_at;
    }
}

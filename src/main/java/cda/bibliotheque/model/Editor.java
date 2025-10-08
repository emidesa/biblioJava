package cda.bibliotheque.model;

import java.sql.Date;
import java.time.LocalDate;

public class Editor {
    private int id;
    private String label;
    private LocalDate created_at;

    public Editor() {
    }

    public Editor(int id, String label, LocalDate created_at) {
        this.id = id;
        this.label = label;
        this.created_at = created_at;
    }

    public Editor(int id, String label, java.util.Date created_at) {
        this.id = id;
        this.label = label;
        if (created_at != null) {
            this.created_at = new java.sql.Date(created_at.getTime()).toLocalDate();
        } else {
            this.created_at = null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public Date getCreated_at_Date() {
        return (created_at == null) ? null : java.sql.Date.valueOf(created_at);
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
}
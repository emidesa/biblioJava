package cda.bibliotheque.model;

public class Client {
     private int id;
    private String lastname;
    private String firstname;
    private String email;

    public Client() {
    }

    public Client(int id, String lastname, String firstname, String email) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
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

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}

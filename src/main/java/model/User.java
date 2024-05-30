package model;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String personID;

    public User(Long id, String name, String surname, String personID) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personID = personID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}

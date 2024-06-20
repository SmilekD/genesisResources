package cz.engeto.genesisresources.model;


public class User {
    private Long id;
    private String name;
    private String surname;
    private String personId;
    private String uuid;

    public User(){

    }

    public User(Long id, String name, String surname, String personID, String Uuid) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personId = personID;
        this.uuid = Uuid;
    }

    public User(Long id,String name, String surname){
        this.id = id;
        this.name = name;
        this.surname = surname;
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
        return personId;
    }

    public void setPersonID(String personID) {
        this.personId = personID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}

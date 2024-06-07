package cz.engeto.genesisResources.model;


public class User {
    private Long id;
    private String name;
    private String surname;
    private String personid;
    private String uuid;

    public User(){

    }

    public User(Long id, String name, String surname, String personID, String Uuid) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personid = personID;
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
        return personid;
    }

    public void setPersonID(String personID) {
        this.personid = personID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}

package dk.voresgruppe.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Administrator {


    private int iD;
    private String firstname;
    private String lastname;
    private String fullName;
    private String username;
    private String password;
    private User administratorLogin;

    public User getAdministratorLogin() {
        return administratorLogin;
    }

    public Administrator(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        fullName = firstname + " " + lastname;
        this.username = username;
        this.password = password;
        administratorLogin = new User(username,password);
    }

    @Override
    public String toString() {
        return iD +
                "," + firstname +
                "," + lastname +
                "," + username +
                "," + password;
    }

    public int getId() {
        return iD;
    }

    public SimpleStringProperty getIdProperty(){
        SimpleStringProperty simpleStringProperty = new SimpleStringProperty(String.valueOf(iD));
        return simpleStringProperty;
    }

    public void setId(int iD) {
        this.iD = iD;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFullName() {
        return fullName;
    }

    public SimpleStringProperty getFullnameProperty(){
        SimpleStringProperty simpleStringProperty = new SimpleStringProperty(fullName);
        return simpleStringProperty;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

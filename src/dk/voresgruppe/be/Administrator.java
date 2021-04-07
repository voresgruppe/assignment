package dk.voresgruppe.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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

    public Administrator(int iD, String firstname, String lastname, String username, String password) {
        this.iD = iD;
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

    public int getiD() {
        return iD;
    }

    public IntegerProperty getIdProperty(){
        IntegerProperty integerProperty = new SimpleIntegerProperty(iD);
        return integerProperty;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

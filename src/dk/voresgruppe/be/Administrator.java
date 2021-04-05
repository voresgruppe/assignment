package dk.voresgruppe.be;

public class Administrator {


    private int iD;
    private String firstname;
    private String lastname;
    private String username;
    private String password;

    public Administrator(int iD, String firstname, String lastname, String username, String password) {
        this.iD = iD;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return iD +
                "," + firstname +
                "," + lastname +
                "," + username +
                "," + password;
    }
}

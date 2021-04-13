package dk.voresgruppe.be;

import javafx.beans.property.SimpleStringProperty;

public class Teacher {
    private int teacherID;
    private String firstName;
    private String lastName;
    private String fullName;
    private User teacherLogin;


    public Teacher( String firstname, String lastname, User teacherLogin) {
        this.firstName = firstname;
        this.lastName = lastname;
        fullName = firstname + " " + lastname;
        this.teacherLogin = teacherLogin;
    }
    public User getTeacherLoginLogin() {
        return teacherLogin;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public SimpleStringProperty getFullNameProperty(){
        return new SimpleStringProperty(fullName);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getTeacherLogin() {
        return teacherLogin;
    }

    public void setTeacherLogin(User teacherLogin) {
        this.teacherLogin = teacherLogin;
    }

    @Override
    public String toString() {
        return firstName+  ", " + lastName + ", " + teacherLogin;
    }
}

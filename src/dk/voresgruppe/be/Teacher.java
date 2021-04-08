package dk.voresgruppe.be;

public class Teacher {
    private String firstName;
    private String lastName;
    private User teacherLogin;


    public Teacher(String fName, String lName, User teacherLogin) {
        this.firstName = fName;
        this.lastName = lName;
        this.teacherLogin = teacherLogin;
    }
    public User getTeacherLoginLogin() {
        return teacherLogin;
    }

    @Override
    public String toString() {
        return firstName+  ", " + lastName + ", " + teacherLogin;
    }
}

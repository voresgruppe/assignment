package dk.voresgruppe.be;

public class Teacher {
    private String firstName;
    private String lastName;
    private String birthday;
    private User teacherLogin;


    public Teacher(String fName, String lName, String birthDay, User teacherLogin) {
        this.firstName = fName;
        this.lastName = lName;
        this.birthday = birthDay;
        this.teacherLogin = teacherLogin;
    }
    public User getTeacherLoginLogin() {
        return teacherLogin;
    }

    @Override
    public String toString() {
        return firstName+  ", " + lastName + ", " + birthday + ", " + teacherLogin;
    }
}

package dk.voresgruppe.be;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String firstName;
    private String lastName;
    private String currentCourse;
    private User studentLogin;
    private String birthday;

    private List<Schedule> toShowUp = new ArrayList<>();
    private List<Schedule> showedUp = new ArrayList<>();

    public Student(String firstName, String lastName, String birthday, String currentCourse, User studentLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentCourse = currentCourse;
        this.studentLogin = studentLogin;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
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

    public String getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(String currentCourse) {
        this.currentCourse = currentCourse;
    }

    public User getStudentLogin() {
        return studentLogin;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Schedule> getToShowUp() {
        return toShowUp;
    }

    public void setToShowUp(List<Schedule> toShowUp) {
        this.toShowUp = toShowUp;
    }

    public void addToToShowUp (Schedule schedule) {
        toShowUp.add(schedule);
    }

    public Schedule getScheduleFromDate (Date date) {
        for (Schedule currentSchedule : toShowUp) {
            if (currentSchedule.getDate().equals(date)) {
                return currentSchedule;
            }
        }
        return null;
    }

    public List<Schedule> getShowedUp() {
        return showedUp;
    }

    public void setShowedUp(List<Schedule> showedUp) {
        this.showedUp = showedUp;
    }

    public void addToShowedUp (Schedule schedule) {
        showedUp.add(schedule);
    }

    public void setStudentLogin(User studentLogin) {
        this.studentLogin = studentLogin;
    }

    public int getAbsencePercentage() {
        if(showedUp.isEmpty()) {
            return 100;
        }
        else {
            return toShowUp.size() - showedUp.size() / toShowUp.size() * 100;
        }
    }

    public int getAbsenceDays() {
        if(showedUp.isEmpty()) {
            return toShowUp.size();
        }
        else {
            return toShowUp.size() - showedUp.size();
        }
    }

    @Override
    public String toString() {
        return firstName+  ", " + lastName + ", " + currentCourse + ", " + birthday + ", " + studentLogin;
    }
}

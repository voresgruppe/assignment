package dk.voresgruppe.be;

import dk.voresgruppe.util.Utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Student {

    private String firstName;
    private String lastName;
    private String fullName;
    private String currentCourse;
    private User studentLogin;
    private String birthday;

    private List<Date> toShowUp = datesToShowUp();
    private List<Date> showedUp = datesShowedUp();
    private List<Schedule> weekSchedule = new ArrayList<>();
    private Utils utils = new Utils();
    private Double absencePercentage;

    public Student(String firstName, String lastName, String birthday, String currentCourse, User studentLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentCourse = currentCourse;
        this.studentLogin = studentLogin;
        this.birthday = birthday;
    }
    private List<Date> datesToShowUp() {
        List<Date> datesToShowUp = new ArrayList<>();
        int d = 1;
        int m = 8;
        int y = 2020;
        while(!(d==25 && m==12 && y==2020)) {
            Date date = new Date(d,m,y);
            YearMonth YM =YearMonth.of(y,m);
            Calendar c = Calendar.getInstance();
            c.set(y,m,d);
            if(c.get(Calendar.DAY_OF_WEEK)<6){
                datesToShowUp.add(date);
            }
            if(d<YM.lengthOfMonth()){
                d+=1;
            }
            else {
                d=1;
                m+=1;
                if(m>12) {
                    m=1;
                    y+=1;
                }
            }
        }
        return datesToShowUp;
    }

    //only for the purposes of mockdata
    private List<Date> datesShowedUp() {
        List<Date> datesShowedUp = datesToShowUp();
        Random random = new Random();
        for(int i=3; i<datesShowedUp.size(); i+= random.nextInt(8 - 1 +1)){
            datesShowedUp.remove(i);
        }
        return datesShowedUp;
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


    public Schedule getScheduleFromDate (Date date) {
        int weekday = utils.getWeekNumberFromDate(date);
        return weekSchedule.get(weekday);
    }

    public List<Date> getToShowUp() {
        return toShowUp;
    }

    public void setToShowUp(List<Date> toShowUp) {
        this.toShowUp = toShowUp;
    }

    public List<Date> getShowedUp() {
        return showedUp;
    }

    public void setShowedUp(List<Date> showedUp) {
        this.showedUp = showedUp;
    }

    public void addToShowedUp (Date date) {
        showedUp.add(date);
    }

    public void setStudentLogin(User studentLogin) {
        this.studentLogin = studentLogin;
    }

    public double getAbsencePercentage() {
        absencePercentage = calcAbsencePercentage();
        return absencePercentage;
    }

    public double calcAbsencePercentage(){
        if(showedUp.isEmpty()) {
            return 100;
        }
        else {
            return (int) (toShowUp.size() - showedUp.size() / (double) toShowUp.size() * 100);
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

    public List<Schedule> getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(List<Schedule> weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

    public String getFullName(){
        fullName = firstName + " " + lastName;
        return fullName;
    }

    @Override
    public String toString() {
        return firstName+  ", " + lastName + ", " + currentCourse + ", " + birthday + ", " + studentLogin;
    }
}

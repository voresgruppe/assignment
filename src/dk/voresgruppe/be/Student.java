package dk.voresgruppe.be;

import dk.voresgruppe.util.Utils;
import javafx.beans.property.SimpleStringProperty;


import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class Student {

    private int studentID;
    private String firstName;
    private String lastName;
    private String fullName;
    private int classID;
    private User studentLogin;
    private String mostAbsentDay;

    private List<Date> toShowUp = datesToShowUp();
    private List<Date> showedUp = datesShowedUp();
    private List<Schedule> weekSchedule = new ArrayList<>();
    private Double absencePercentage = 0.0;

    private Utils utils = new Utils();

    public Student(int studentId, int classID, String firstName, String lastName, User studentLogin) {
        this.studentID = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        fullName = firstName + " " + lastName;
        this.classID = classID;
        this.studentLogin = studentLogin;
    }

    private List<Date> datesToShowUp() {
        List<Date> datesToShowUp = new ArrayList<>();
        int day = 1;
        int month = 8;
        int year = 2020;

        while (!(day == 25 && month == 12 && year == 2020)) {
            Date date = new Date(day, month, year);
            YearMonth YM = YearMonth.of(year, month);
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            if (calendar.get(Calendar.DAY_OF_WEEK) < 6) {
                datesToShowUp.add(date);
            }
            if (day < YM.lengthOfMonth()) {
                day += 1;
            } else {
                day = 1;
                month += 1;
                if (month > 12) {
                    month = 1;
                    year += 1;
                }
            }
        }
        return datesToShowUp;
    }

    public int getStudentID() {
        return studentID;
    }

    public SimpleStringProperty getIDProperty(){
        return new SimpleStringProperty(String.valueOf(studentID));
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    //TODO: get the dates where students have showed up
    private List<Date> datesShowedUp() {
        return this.showedUp;
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

    public String getFullName() {
        return fullName;
    }
    public SimpleStringProperty getFullnameProperty(){
        return new SimpleStringProperty(fullName);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public User getStudentLogin() {
        return studentLogin;
    }


    public Schedule getScheduleFromDate(Date date) {
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

    public void addToShowedUp(Date date) {
        showedUp.add(date);
    }

    public void setStudentLogin(User studentLogin) {
        this.studentLogin = studentLogin;
    }

    public double getAbsencePercentage() {
        absencePercentage = calcAbsencePercentage();
        //max 2 decimaler
        return (double) Math.round(absencePercentage * 100d) / 100d;
    }

    public double calcAbsencePercentage() {
        if (showedUp.isEmpty()) {
            return 100;
        } else {
            return ((this.toShowUp.size() - this.showedUp.size()) / (double) this.toShowUp.size() * 100);
        }
    }

    public int getAbsenceDays() {
        if (showedUp.isEmpty()) {
            return toShowUp.size();
        } else {
            return toShowUp.size() - showedUp.size();
        }
    }

    public List<Schedule> getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(List<Schedule> weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

    @Override
    public String toString() {
        return firstName + ", " + lastName;
    }

    public String getMostAbsentDay() {
        int monday = 0;
        int tuesday = 0;
        int wednesday = 0;
        int thursday = 0;
        int friday = 0;
        for (Date currentDate : showedUp) {
            int day = utils.getWeekDayFromDate(currentDate);
            switch (day) {
                case 1:
                    monday += 1;
                    break;
                case 2:
                    tuesday += 1;
                    break;
                case 3:
                    wednesday += 1;
                    break;
                case 4:
                    thursday += 1;
                    break;
                case 5:
                    friday += 1;
                    break;
            }

        }
        ArrayList<Integer> days = new ArrayList<>();
        days.add(monday);
        days.add(tuesday);
        days.add(wednesday);
        days.add(thursday);
        days.add(friday);
        Collections.sort(days);
        int dayOfWeek = days.get(4);
        if (dayOfWeek == monday) {
            mostAbsentDay = "Mandag";
        } else if (dayOfWeek == tuesday) {
            mostAbsentDay = "Tirsdag";
        } else if (dayOfWeek == wednesday) {
            mostAbsentDay = "Onsdag";
        } else if (dayOfWeek == thursday) {
            mostAbsentDay = "Torsdag";
        } else if (dayOfWeek == friday) {
            mostAbsentDay = "Fredag";
        }

        return mostAbsentDay;
    }
}

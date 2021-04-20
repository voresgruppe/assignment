package dk.voresgruppe.be;

import dk.voresgruppe.bll.ClassManager;
import dk.voresgruppe.bll.StudentAttendanceManager;
import dk.voresgruppe.util.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;


import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class Student {
    private StudentAttendanceManager  studentAttendanceManager= new StudentAttendanceManager();
    private ClassManager classManager = new ClassManager();

    private int studentID;
    private String firstName;
    private String lastName;
    private String fullName;
    private int classID;
    private User studentLogin;
    private String mostAbsentDay;

    private List<Date> toShowUp;
    private List<Date> validShowedUp;
    private ObservableList<StudentAttendance> showedUp;
    private Double absencePercentage = 0.0;

    private Utils utils = new Utils();

    public Student(int classID, String firstName, String lastName, User studentLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        fullName = firstName + " " + lastName;
        this.classID = classID;
        this.studentLogin = studentLogin;
        toShowUp = datesToShowUp();
    }

    private List<Date> datesToShowUp() {
        List<Date> datesToShowUp = new ArrayList<>();

        Date startDate = classManager.getClassFromID(classID).getStartDate();
        Date endDate = classManager.getClassFromID(classID).getEndDate();
        int day = startDate.getDay();
        int month = startDate.getMonth();
        int year = startDate.getYear();

        while (!(day == endDate.getDay() && month == endDate.getMonth() && year == endDate.getYear())) {
            Date date = new Date(day, month, year);
            YearMonth YM = YearMonth.of(year, month);
            if (utils.getWeekDayFromDate(date)>= Calendar.MONDAY && utils.getWeekDayFromDate(date)<= Calendar.FRIDAY) {
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

    private void updateShowedUp(){
        showedUp = studentAttendanceManager.getStudentAttendancesFromStudentID(studentID);
        List<Date> returnList = new ArrayList<>();
        for(StudentAttendance current: showedUp){
            if(utils.isDateBetweenDates(classManager.getClassFromID(classID).getStartDate(),classManager.getClassFromID(classID).getEndDate(), current.getAttendanceDate())){
                returnList.add(current.getAttendanceDate());
            }
        }
        validShowedUp =returnList;
    }

    public List<Date> getValidShowedUp() {
        return validShowedUp;
    }

    public void setValidShowedUp(List<Date> validShowedUp) {
        this.validShowedUp = validShowedUp;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
        updateShowedUp();
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

    public List<Date> getToShowUp() {
        return toShowUp;
    }

    public void setToShowUp(List<Date> toShowUp) {
        this.toShowUp = toShowUp;
    }

    public StudentAttendanceManager getStudentAttendanceManager() {
        return studentAttendanceManager;
    }

    public void setStudentAttendanceManager(StudentAttendanceManager studentAttendanceManager) {
        this.studentAttendanceManager = studentAttendanceManager;
    }

    public void setMostAbsentDay(String mostAbsentDay) {
        this.mostAbsentDay = mostAbsentDay;
    }

    public ObservableList<StudentAttendance> getShowedUp() {
        return showedUp;
    }

    public void setShowedUp(ObservableList<StudentAttendance> showedUp) {
        this.showedUp = showedUp;
    }

    public void setAbsencePercentage(Double absencePercentage) {
        this.absencePercentage = absencePercentage;
    }

    public Utils getUtils() {
        return utils;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public void addToShowedUp(StudentAttendance studentAttendance) {
        studentAttendanceManager.add(studentAttendance);
        updateShowedUp();
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
        if (validShowedUp.isEmpty()) {
            return 100;
        } else {
            System.out.println("totale mødedage: " + toShowUp.size());
            return ((this.toShowUp.size() - this.validShowedUp.size()) / (double) this.toShowUp.size() * 100);
        }
    }

    public int getAbsenceDays() {
        if (validShowedUp.isEmpty()) {
            return toShowUp.size();
        } else {
            return toShowUp.size() - validShowedUp.size();
        }
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
        for (StudentAttendance currentDate : showedUp) {
            int day = utils.getWeekDayFromDate(currentDate.getAttendanceDate());
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

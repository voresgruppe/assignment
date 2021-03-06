package dk.voresgruppe.gui.StudentView;

import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.StudentAttendance;
import dk.voresgruppe.bll.*;
import dk.voresgruppe.util.UserError;
import dk.voresgruppe.util.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class StudentViewController implements Initializable {
    public Label lblGreeting;
    private Student loggedStudent;
    private StudentManager sMan;
    private Utils utils = new Utils();
    private UserError userError = new UserError();
    private CourseManager courseMan;
    private StudentAttendanceManager saMan= new StudentAttendanceManager();
    private ScheduleManager scMan;
    private ClassManager classMan;

    @FXML
    public BorderPane bpAbsenceChart;
    @FXML
    public TextField txtFieldAbsencePercentage;
    @FXML
    public TextField txtFieldAbsenceDays;
    @FXML
    public ImageView imgProfilePic;
    public Button btnLogUd;



    public StudentViewController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public Student getLoggedStudent() {
        return loggedStudent;
    }

    public void setLoggedStudent(Student loggedStudent) {
        this.loggedStudent = loggedStudent;
        lblGreeting.setText(setLblGreeting());
        updateData();
        sMan = new StudentManager();
        courseMan = new CourseManager();
        scMan = new ScheduleManager();
        classMan = new ClassManager();
        setPics();
    }

    private void updateData(){
        txtFieldAbsencePercentage.setText(loggedStudent.getAbsencePercentage() + "%");
        txtFieldAbsenceDays.setText(String.valueOf(loggedStudent.getAbsenceDays()));
        bpAbsenceChart.setCenter(attendanceChart());
    }


    public LineChart attendanceChart() {
        final NumberAxis xAxis = new NumberAxis(1, loggedStudent.getToShowUp().size(), 10);
        final NumberAxis yAxis = new NumberAxis(0, 100, 5);
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        //lineChart.setTitle("Fravær");


        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Fraværs Procent");
        //populating the series with data

        int i = 0;
        int x = 0;
        for (Date currentDate : loggedStudent.getToShowUp()) {
            for (Date currentShowDate : loggedStudent.getValidShowedUp()) {
                if (utils.checkIfDatesMatch(currentDate, currentShowDate)) {
                    i++;
                }
            }
            x++;
            double y = (x - i) / (double) x * 100;
            series.getData().add(new XYChart.Data(x, y));
        }
        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);
        return lineChart;
    }


    public String setLblGreeting() {
        String label = "";
        if (LocalTime.now().getHour() < 11) {
            label += "Godmorgen ";
        } else if (LocalTime.now().getHour() >= 11 && LocalTime.now().getHour() <= 16) {
            label += "Goddag ";
        } else if (LocalTime.now().getHour() > 16) {
            label += "Godaften ";
        }

        return label + loggedStudent.getFirstName();
    }

    public StudentManager getsMan() {
        return sMan;
    }

    public void setsMan(StudentManager sMan) {
        this.sMan = sMan;
    }

    private void addProfilePic(String path) {
        File file = new File(path);
        Image pic = new Image(String.valueOf(file));
        imgProfilePic.setImage(pic);
    }

    public void handleRegisterAttendance(ActionEvent actionEvent) {
        if (utils.getWeekDayFromDate(utils.getCurrentDate()) != Calendar.SATURDAY && utils.getWeekDayFromDate(utils.getCurrentDate()) != Calendar.SUNDAY) {
            Date date = utils.dateFromLocalDate(LocalDate.now());
            StudentAttendance newStudentAttendance =new StudentAttendance(loggedStudent.getStudentID(), date);
            int courseID = -1;
            if(utils.getWeekDayFromDate(utils.dateFromLocalDate(LocalDate.now())) == Calendar.MONDAY){
                courseID = scMan.getScheduleFromId(classMan.getClassFromID(loggedStudent.getClassID()).getScheduleID()).getMonday();
            }else if(utils.getWeekDayFromDate(utils.dateFromLocalDate(LocalDate.now())) == Calendar.TUESDAY){
                courseID = scMan.getScheduleFromId(classMan.getClassFromID(loggedStudent.getClassID()).getScheduleID()).getTuesday();
            }else if(utils.getWeekDayFromDate(utils.dateFromLocalDate(LocalDate.now())) == Calendar.WEDNESDAY){
                courseID = scMan.getScheduleFromId(classMan.getClassFromID(loggedStudent.getClassID()).getScheduleID()).getWednesday();
            }else if(utils.getWeekDayFromDate(utils.dateFromLocalDate(LocalDate.now())) == Calendar.THURSDAY){
                courseID = scMan.getScheduleFromId(classMan.getClassFromID(loggedStudent.getClassID()).getScheduleID()).getThursday();
            }else if(utils.getWeekDayFromDate(utils.dateFromLocalDate(LocalDate.now())) == Calendar.FRIDAY){
                courseID = scMan.getScheduleFromId(classMan.getClassFromID(loggedStudent.getClassID()).getScheduleID()).getFriday();
            }
            newStudentAttendance.setCourseID(courseID);
            loggedStudent.addToShowedUp(newStudentAttendance);
            updateData();
        }
        else {
            String header = "dude gå hjem det ";
            if (utils.getWeekDayFromDate(utils.getCurrentDate()) == Calendar.SATURDAY) {
                header += "Lørdag";
            }
            if (utils.getWeekDayFromDate(utils.getCurrentDate()) == Calendar.SUNDAY) {
                header += "Søndag";
            }

            UserError.showError("Fremmøde ikke registreret", header);
        }
    }

    public void setPics() {
        switch (loggedStudent.getFullName()) {
            case "Jane Jørgensen":
                addProfilePic("image/ProfilePic.png");
                break;
            case "Søren Mikkelsen":
                addProfilePic("image/soren.jpg");
                break;
        }
    }

    public void handleLogOut(ActionEvent actionEvent) {
        Stage stage = (Stage) btnLogUd.getScene().getWindow();
        stage.close();
    }


}

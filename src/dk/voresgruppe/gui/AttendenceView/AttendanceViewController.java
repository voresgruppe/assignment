package dk.voresgruppe.gui.AttendenceView;

import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Schedule;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.bll.StudentManager;
import dk.voresgruppe.util.UserError;
import dk.voresgruppe.util.Utils;
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
import java.util.Calendar;
import java.util.ResourceBundle;

public class AttendanceViewController implements Initializable {
    public Label lblGreeting;
    public ImageView giraf;
    private Student loggedStudent;
    private StudentManager sMan;
    private Utils utils = new Utils();
    private UserError userError = new UserError();

@FXML
    public BorderPane bpAbsenceChart;
@FXML
    public TextField txtFieldAbsencePercentage;
@FXML
    public TextField txtFieldAbsenceDays;
@FXML
    public ImageView imgProfilePic;
    public Button btnLogUd;

    public AttendanceViewController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*File file = new File("image/Giraf.PNG");
        Image pic = new Image(String.valueOf(file));
        giraf.setImage(pic);

         */
    }

    public Student getLoggedStudent() {
        return loggedStudent;
    }

    public void setLoggedStudent(Student loggedStudent) {
        this.loggedStudent = loggedStudent;
        lblGreeting.setText(setLblGreeting());
        txtFieldAbsencePercentage.setText(loggedStudent.getAbsencePercentage() + "%");
        txtFieldAbsenceDays.setText(String.valueOf(loggedStudent.getAbsenceDays()));
        bpAbsenceChart.setCenter(attendanceChart());
        setPics();
    }


    public LineChart attendanceChart() {
        final NumberAxis xAxis = new NumberAxis(1, loggedStudent.getToShowUp().size(), 10);
        final NumberAxis yAxis = new NumberAxis(0, 100, 5);
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        //lineChart.setTitle("Fravær");


        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Fraværs Procent");
        //populating the series with data
        int i= 0;
        int x =0;
        for(Date currentDate: loggedStudent.getToShowUp()) {
            for(Date currentShowDate: loggedStudent.getShowedUp()) {
                if (utils.checkIfDatesMatch(currentDate, currentShowDate)) {
                    i++;
                }
            }
            x++;
            double y = (x - i) /(double) x * 100;
            series.getData().add(new XYChart.Data(x, y));
        }
        lineChart.getData().add(series);
        lineChart.setCreateSymbols(false);
       return lineChart;
    }



    public String setLblGreeting () {
        String label = "";
        if(LocalTime.now().getHour() < 11) {
            label += "Godmorgen ";
        }
        else if(LocalTime.now().getHour() >= 11 && LocalTime.now().getHour() <=16 ) {
            label += "Goddag ";
        }
        else if(LocalTime.now().getHour() >16 ) {
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
        if(utils.getWeekDayFromDate(utils.getCurrentDate()) != Calendar.SATURDAY && utils.getWeekDayFromDate(utils.getCurrentDate()) != Calendar.SUNDAY)
        loggedStudent.addToShowedUp(new Date(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
        else {
            String header = "dude gå hjem det ";
            if(utils.getWeekDayFromDate(utils.getCurrentDate()) == Calendar.SATURDAY) {
                System.out.println(utils.getWeekDayFromDate(utils.getCurrentDate()));
                header+="Lørdag";
            }
            if(utils.getWeekDayFromDate(utils.getCurrentDate()) == Calendar.SUNDAY) {
                header+="Søndag";
            }

            UserError.showError("Fremmøde ikke registreret", header);
        }
    }

    public void setPics(){
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

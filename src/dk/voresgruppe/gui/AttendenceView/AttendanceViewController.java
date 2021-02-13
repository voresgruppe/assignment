package dk.voresgruppe.gui.AttendenceView;

import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.bll.StudentManager;
import dk.voresgruppe.util.UserError;
import dk.voresgruppe.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
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
import java.util.ResourceBundle;

public class AttendanceViewController implements Initializable {
    public Label lblGreeting;
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
        addProfilePic("image/ProfilePic.png");
    }

    public Student getLoggedStudent() {
        return loggedStudent;
    }

    public void setLoggedStudent(Student loggedStudent) {
        this.loggedStudent = loggedStudent;
        lblGreeting.setText(setLblGreeting());
        txtFieldAbsencePercentage.setText(loggedStudent.getAbsencePercentage() + "%");
        txtFieldAbsenceDays.setText(String.valueOf(loggedStudent.getAbsenceDays()));
    }

    /*
    public LineChart attendanceChart() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("dato");

    }

     */

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
        if(utils.getWeekDayFromDate(utils.getCurrentDate()) < 6)
        loggedStudent.addToShowedUp(new Date(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
        else {
            String header = "dude gå hjem det ";
            if(utils.getWeekDayFromDate(utils.getCurrentDate()) == 6) {
                header+="Lørdag";
            }
            if(utils.getWeekDayFromDate(utils.getCurrentDate()) == 7) {
                header+="Søndag";
            }
            UserError.showError("Fremmøde ikke registreret", header);
        }
    }

    public void handleLogOut(ActionEvent actionEvent) {
        Stage stage = (Stage) btnLogUd.getScene().getWindow();
        stage.close();
    }



}

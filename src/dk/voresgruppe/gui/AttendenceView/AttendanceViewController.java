package dk.voresgruppe.gui.AttendenceView;

import dk.voresgruppe.be.Student;
import dk.voresgruppe.bll.StudentManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AttendanceViewController implements Initializable {
    public Label lblGreeting;
    private Student loggedStudent;
    private StudentManager sMan;

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
            label += "GodAften ";
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
    }

    public void handleLogOut(ActionEvent actionEvent) {
        Stage stage = (Stage) btnLogUd.getScene().getWindow();
        stage.close();
    }



}

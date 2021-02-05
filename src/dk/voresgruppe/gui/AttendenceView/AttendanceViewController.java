package dk.voresgruppe.gui.AttendenceView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class AttendanceViewController implements Initializable {


@FXML
    public BorderPane bpAbsenceChart;
@FXML
    public TextField txtFieldAbsencePercentage;
@FXML
    public TextField txtFieldAbsenceDays;
@FXML
    public ImageView imgProfilePic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File file = new File("image/ProfilePic.png");
        Image pic = new Image(String.valueOf(file));
        imgProfilePic.setImage(pic);

    }

    public void handleRegisterAttendance(ActionEvent actionEvent) {
    }

    public void handleLogOut(ActionEvent actionEvent) {

    }



}

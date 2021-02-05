package dk.voresgruppe.gui.AttendenceView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AttendanceViewController{

@FXML
    public BorderPane bpProfilePicture;
@FXML
    public BorderPane bpAbsenceChart;
@FXML
    public TextField txtFieldAbsencePercentage;
@FXML
    public TextField txtFieldAbsenceDays;


    public void initialize() {
        //bpProfilePicture.setCenter();

    }

    public void handleRegisterAttendance(ActionEvent actionEvent) {
    }

    public void handleLogOut(ActionEvent actionEvent) {

    }


}

package dk.voresgruppe.gui.TeacherView.StudentInfoView;

import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.bll.TeacherManager;
import dk.voresgruppe.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StudentInfoController implements Initializable {
    public TextField txtfieldAttendancePercent;
    public TextField txtfieldAttendanceDays;
    public Button btnEditAttendance;
    public Button btnClose;
    public Label lblGreeting;
    public DatePicker datePicker;
    public Label lblDidStudentShowUp;
    private Student currentStudent;
    private TeacherManager tMan;
    private Utils utils= new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void lookUpStudent(Student studentToLookup){
        this.currentStudent = studentToLookup;
        lblGreeting.setText("Fraværs overblik for " + currentStudent.getFullName());
        tMan = new TeacherManager();
        updateUpdatelbl();
    }

    public void editAttendance(ActionEvent actionEvent) {
        if (datePicker.getValue() != null) {
            Date date = utils.dateFromLocalDate(datePicker.getValue());
            tMan.updateStudentAttendance(currentStudent, date);
            updateUpdatelbl();
        }
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void updateDidStudentShowUp(ActionEvent actionEvent) {
        updateUpdatelbl();
    }

    public void updateUpdatelbl(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        Date date = utils.dateFromLocalDate(datePicker.getValue());
        LocalDate day = datePicker.getValue();
        if (date != null) {
            if(tMan.didStudentShowUpAt(currentStudent, date)){
                lblDidStudentShowUp.setText(formatter.format(day) + " mødt op");
            }else lblDidStudentShowUp.setText(formatter.format(day) + " fraværende");
        } else {
            lblDidStudentShowUp.setText("");
        }
        txtfieldAttendancePercent.setText(currentStudent.getAbsencePercentage() + "");
        txtfieldAttendanceDays.setText(currentStudent.getAbsenceDays() + "");
    }
}

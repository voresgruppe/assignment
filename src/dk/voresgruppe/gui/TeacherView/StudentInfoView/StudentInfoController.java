package dk.voresgruppe.gui.TeacherView.StudentInfoView;

import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.StudentAttendance;
import dk.voresgruppe.bll.*;
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
import java.util.Calendar;
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
    private CourseManager courseMan;
    private ScheduleManager scMan;
    private ClassManager classMan;
    private StudentAttendanceManager saMan;
    private Utils utils= new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePicker.setValue(LocalDate.now());
    }

    public void lookUpStudent(Student studentToLookup){
        this.currentStudent = studentToLookup;
        lblGreeting.setText("Fraværs overblik for " + currentStudent.getFullName());
        tMan = new TeacherManager();
        courseMan = new CourseManager();
        scMan = new ScheduleManager();
        classMan = new ClassManager();
        saMan = new StudentAttendanceManager();
        updateUpdatelbl();
    }

    public void editAttendance(ActionEvent actionEvent) {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate != null) {
            Date date = utils.dateFromLocalDate(selectedDate);
            StudentAttendance newStudentAttendance = new StudentAttendance(currentStudent.getStudentID(), date);
            newStudentAttendance.setCourseID(getTodaysCourse(currentStudent,selectedDate));
            if(saMan.checkIfAlreadyExists(newStudentAttendance)){
                currentStudent.delete(newStudentAttendance);
            }else currentStudent.addToShowedUp(newStudentAttendance);
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
            if(saMan.didStudentShowUp(currentStudent, date)){
                lblDidStudentShowUp.setText(formatter.format(day) + " mødt op");
            }else lblDidStudentShowUp.setText(formatter.format(day) + " fraværende");
        } else {
            lblDidStudentShowUp.setText("");
        }
        txtfieldAttendancePercent.setText(currentStudent.getAbsencePercentage() + "");
        txtfieldAttendanceDays.setText(currentStudent.getAbsenceDays() + "");
    }

    private int getTodaysCourse(Student loggedStudent, LocalDate date){
        int courseID = -1;
        if(utils.getWeekDayFromDate(utils.dateFromLocalDate(date)) == Calendar.MONDAY){
            courseID = courseMan.getCourseFromID(scMan.getScheduleFromId(classMan.getClassFromID(loggedStudent.getClassID()).getScheduleID()).getMonday()).getCourseID();
        }else if(utils.getWeekDayFromDate(utils.dateFromLocalDate(date)) == Calendar.TUESDAY){
            courseID = courseMan.getCourseFromID(scMan.getScheduleFromId(classMan.getClassFromID(loggedStudent.getClassID()).getScheduleID()).getTuesday()).getCourseID();
        }else if(utils.getWeekDayFromDate(utils.dateFromLocalDate(date)) == Calendar.WEDNESDAY){
            courseID = courseMan.getCourseFromID(scMan.getScheduleFromId(classMan.getClassFromID(loggedStudent.getClassID()).getScheduleID()).getWednesday()).getCourseID();
        }else if(utils.getWeekDayFromDate(utils.dateFromLocalDate(date)) == Calendar.THURSDAY){
            courseID = courseMan.getCourseFromID(scMan.getScheduleFromId(classMan.getClassFromID(loggedStudent.getClassID()).getScheduleID()).getThursday()).getCourseID();
        }else if(utils.getWeekDayFromDate(utils.dateFromLocalDate(date)) == Calendar.FRIDAY){
            courseID = courseMan.getCourseFromID(scMan.getScheduleFromId(classMan.getClassFromID(loggedStudent.getClassID()).getScheduleID()).getFriday()).getCourseID();
        }
        return courseID;
    }
}

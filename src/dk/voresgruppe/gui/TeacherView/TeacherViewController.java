package dk.voresgruppe.gui.TeacherView;

import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.bll.StudentManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class TeacherViewController {
    public Button logud_teacher;
    private Teacher loggedTeacher;
    public StudentManager studentManager;
    public TableView<Student> tableView;
    private ObservableList<Student> observableListStudents;
    @FXML
    public TableColumn<Student,String> elevID;
    @FXML
    public TableColumn<Student,String> fraværID;



    public void StudentName(ActionEvent event) {

    }


    public void Teacher_Close(ActionEvent event) {
        Stage stage = (Stage) logud_teacher.getScene().getWindow();
        stage.close();
    }


    public void initialize(){
/* jeg kunne ikke åbne teacherView uden at udkommentere det her
      studentManager = new StudentManager();

      tableView.setItems(observableListStudents);

      elevID.cellValueFactoryProperty().setValue(elevID.getCellValueFactory());
      fraværID.cellValueFactoryProperty().setValue(elevID.getCellValueFactory());



 */


    }

    public void setLoggedStudent(Teacher loggedTeacher) {
        this.loggedTeacher = loggedTeacher;
    }
}

package dk.voresgruppe.gui.TeacherView;

import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.bll.StudentManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class TeacherViewController {
    public Button logud_teacher;
    private Teacher loggedTeacher;
    public StudentManager sMan;
    @FXML
    public TableView<Student> tableView = new TableView<>();
    @FXML
    private ObservableList<Student> observableListStudents;
    @FXML
    public TableColumn<Student, String> StudentName;
    @FXML
    public TableColumn<Student, Double> absencePercentage;


    public void StudentName(ActionEvent event) {

    }


    public void Teacher_Close(ActionEvent event) {
        Stage stage = (Stage) logud_teacher.getScene().getWindow();
        stage.close();
    }

    public StudentManager getStudentManager() {
        return sMan;
    }

    public void setStudentManager(StudentManager studentManager) {
        this.sMan = studentManager;
        setStudentTableView();
    }

    public void initialize() {

    }

    public void setLoggedStudent(Teacher loggedTeacher) {
        this.loggedTeacher = loggedTeacher;
    }

    public void setStudentTableView() {
        observableListStudents = FXCollections.observableArrayList();
        for(Student currentStudent: sMan.getAllStudents()){
            currentStudent.getFullName();
            currentStudent.getAbsencePercentage();
        }
        observableListStudents.addAll(sMan.getAllStudents());
        tableView.setItems(observableListStudents);

        StudentName.setCellValueFactory(new PropertyValueFactory<Student, String>("fullName"));
        absencePercentage.setCellValueFactory(new PropertyValueFactory<Student, Double>("absencePercentage"));
    }
}

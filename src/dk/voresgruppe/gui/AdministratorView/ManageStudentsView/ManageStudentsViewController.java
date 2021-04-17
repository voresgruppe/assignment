package dk.voresgruppe.gui.AdministratorView.ManageStudentsView;

import dk.voresgruppe.be.Student;
import dk.voresgruppe.bll.ClassManager;
import dk.voresgruppe.bll.StudentManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageStudentsViewController {
    private StudentManager sMan;
    private ClassManager cMan =new ClassManager();
    private Student selectedStudent;

    @FXML
    private TableView<Student> tblviewStudents;
    @FXML
    private TableColumn<Student, String> studentID;
    @FXML
    private TableColumn<Student, String > studentName;
    @FXML
    private TableColumn<Student, String> studentClass;

    public void initStudents(){
        tblviewStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedStudent = newValue);;
        tblviewStudents.setItems(sMan.getallStudents_OBS());

        studentID.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getIDProperty());
        studentName.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getFullnameProperty());
        studentClass.cellValueFactoryProperty().setValue(cellData -> cMan.getClassFromID(cellData.getValue().getStudentID()).getClassNameProperty());
    }

    public void setsMan(StudentManager sMan) {
        this.sMan = sMan;
    }

    public void setcMan(ClassManager cMan) {
        this.cMan = cMan;
    }

    public void addNewStudent(ActionEvent actionEvent) {
    }

    public void handleDeleteStudent(ActionEvent actionEvent) {
    }

    public void handleEditStudent(ActionEvent actionEvent) {
    }
}

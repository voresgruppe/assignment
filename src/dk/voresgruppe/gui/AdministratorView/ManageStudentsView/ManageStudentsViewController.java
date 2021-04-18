package dk.voresgruppe.gui.AdministratorView.ManageStudentsView;

import dk.voresgruppe.be.Student;
import dk.voresgruppe.bll.ClassManager;
import dk.voresgruppe.bll.StudentManager;
import dk.voresgruppe.gui.AdministratorView.ManageAdministratorsView.NewAdministratorViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

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
        studentClass.cellValueFactoryProperty().setValue(cellData -> cMan.getClassFromID(cellData.getValue().getClassID()).getClassNameProperty());
    }

    public void setsMan(StudentManager sMan) {
        this.sMan = sMan;
    }

    public void setcMan(ClassManager cMan) {
        this.cMan = cMan;
    }

    public void addNewStudent(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("NewStudentView.fxml"));
            Parent mainLayout = loader.load();
            NewStudentViewController nac = loader.getController();
            nac.setManagers(sMan, cMan);
            nac.init();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void handleDeleteStudent(ActionEvent actionEvent) {
        sMan.delete(selectedStudent);
    }

    public void handleEditStudent(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditStudentView.fxml"));
            Parent mainLayout = loader.load();
            EditStudentViewController nsc = loader.getController();
            nsc.setManagers(sMan, cMan);
            nsc.setSelectedStudent(selectedStudent);
            nsc.init();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

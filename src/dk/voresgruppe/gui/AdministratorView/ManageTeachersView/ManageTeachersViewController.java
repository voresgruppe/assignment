package dk.voresgruppe.gui.AdministratorView.ManageTeachersView;

import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.bll.TeacherManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageTeachersViewController {
    private TeacherManager tMan;
    private Teacher selectedTeacher;

    @FXML
    private TableView<Teacher> tblviewTeachers;
    @FXML
    private TableColumn<Teacher, String> teacherID;
    @FXML
    private TableColumn<Teacher, String> teacherName;

    public void initTeachers(){
        tblviewTeachers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedTeacher = newValue);

        tblviewTeachers.setItems(tMan.getAllTeachers());
        teacherID.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getIDProperty());
    }

    public void settMan(TeacherManager tMan) {
        this.tMan = tMan;
    }

    public void addNewTeacher(ActionEvent actionEvent) {
    }

    public void handleDeleteTeacher(ActionEvent actionEvent) {
        tMan.delete(selectedTeacher);
    }

    public void handleEditTeacher(ActionEvent actionEvent) {
    }
}

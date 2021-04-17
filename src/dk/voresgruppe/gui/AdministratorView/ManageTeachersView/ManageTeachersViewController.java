package dk.voresgruppe.gui.AdministratorView.ManageTeachersView;

import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.bll.TeacherManager;
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
        teacherName.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getFullNameProperty());
    }

    public void settMan(TeacherManager tMan) {
        this.tMan = tMan;
    }

    public void addNewTeacher(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("NewTeacherView.fxml"));
            Parent mainLayout = loader.load();
            NewTeacherViewController ntc = loader.getController();
            ntc.settMan(tMan);
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void handleDeleteTeacher(ActionEvent actionEvent) {
        tMan.delete(selectedTeacher);
    }

    public void handleEditTeacher(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditTeacherView.fxml"));
            Parent mainLayout = loader.load();
            EditTeacherViewController etc = loader.getController();
            etc.settMan(tMan);
            etc.setCurrentTeacher(selectedTeacher);
            etc.initTxtFields();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}

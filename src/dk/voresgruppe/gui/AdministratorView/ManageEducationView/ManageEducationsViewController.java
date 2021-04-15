package dk.voresgruppe.gui.AdministratorView.ManageEducationView;

import dk.voresgruppe.be.Education;
import dk.voresgruppe.bll.EducationManager;
import dk.voresgruppe.gui.AdministratorView.ManageAdministratorsView.EditAdministratorViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageEducationsViewController {
    private EducationManager eMan;
    private Education selectedEducation;


    @FXML
    private TableView<Education> tblviewEducations;
    @FXML
    private TableColumn<Education, String>  educationID;
    @FXML
    private TableColumn<Education, String>  educationName;

    public ManageEducationsViewController() {
    }

    public void init(){
        tblviewEducations.setItems(eMan.getAllEducations());
        educationID.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getIdProperty());
        educationName.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getNameProperty());
        educationsListener();
    }

    private void educationsListener(){
        tblviewEducations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedEducation = newValue;
        });
    }

    public void seteMan(EducationManager eMan) {
        this.eMan = eMan;
    }

    public void addNewEducation(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("NewEducationView.fxml"));
            Parent mainLayout = loader.load();
            NewEducationViewController evc = loader.getController();
            evc.seteMan(eMan);
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteEducation(ActionEvent actionEvent) {eMan.delete(selectedEducation);
    }

    public void handleEditEducation(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditEducationView.fxml"));
            Parent mainLayout = loader.load();
            EditEducationViewController evc = loader.getController();
            evc.seteMan(eMan);
            evc.setSelectedEducation(selectedEducation);
            evc.init();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

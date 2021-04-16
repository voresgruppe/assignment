package dk.voresgruppe.gui.AdministratorView.ManageAdministratorsView;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.bll.AdministratorManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageAdministratorsView {
    private AdministratorManager aMan;
    private Administrator selectedAdministrator;


    @FXML
    private TableColumn<Administrator, String> administratorID;
    @FXML
    private TableColumn<Administrator, String> administratorName;
    @FXML
    private TableView<Administrator> tblviewAdministrators;


    public ManageAdministratorsView() {
    }

    public void init(){
        administratorsListener();

        tblviewAdministrators.setItems(aMan.getAllAdministrators());
        administratorID.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getIdProperty());
        administratorName.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getFullnameProperty());
    }

    private void administratorsListener() {
        tblviewAdministrators.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedAdministrator = newValue);
    }


    public void setAMan(AdministratorManager aMan) {
        this.aMan = aMan;
    }

    public void addNewAdministrator(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("NewEducationView.fxml"));
            Parent mainLayout = loader.load();
            NewAdministratorViewController nvc = loader.getController();
            nvc.setaMan(aMan);
            nvc.setMav(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void handleDeleteAdministrator(ActionEvent actionEvent) {
        aMan.delete(selectedAdministrator);
    }

    public void handleEditAdministrator(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditAdministratorView.fxml"));
            Parent mainLayout = loader.load();
            EditAdministratorViewController evc = loader.getController();
            evc.setCurrentAdministrator(selectedAdministrator);
            evc.setaMan(aMan);
            evc.setMav(this);
            evc.initTxtFields();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

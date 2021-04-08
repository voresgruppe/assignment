package dk.voresgruppe.gui.AdministratorView;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.bll.AdministratorManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministratorViewController {
    private Administrator loggedAdministrator;
    private AdministratorManager aMan;


    public void setLoggedAdministrator(Administrator loggedAdministrator) {
        this.loggedAdministrator = loggedAdministrator;
    }

    public void manageAdministrators(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageAdministratorsView/ManageAdministratorsView.fxml"));
            Parent mainLayout = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void manageTeachers(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageTeachersView/ManageTeachersView.fxml"));
            Parent mainLayout = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void manageStudents(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageStudentsView/ManageStudentsView.fxml"));
            Parent mainLayout = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

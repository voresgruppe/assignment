package dk.voresgruppe.gui.AdministratorLoginView;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.be.User;
import dk.voresgruppe.bll.AdministratorManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministratorLoginView {
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    private AdministratorManager aMan = new AdministratorManager();


    public void administratorLogin(ActionEvent actionEvent) {
        ObservableList<Administrator> administrators = aMan.getAllAdministrators();

        User tempUser = new User(txtUsername.getText(), txtPassword.getText());

        for(Administrator currentAdministrator: administrators) {
            User currentUser = currentAdministrator.getAdministratorLogin();
            if(tempUser.getUserName().matches(currentUser.getUserName()) && tempUser.getPassword().matches(currentUser.getPassword())) {
                openAdminView();
            }
        }

    }

    private void openAdminView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AdministratorView/AdministratorView.fxml"));
            Parent mainLayout = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void cancelLogin(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}


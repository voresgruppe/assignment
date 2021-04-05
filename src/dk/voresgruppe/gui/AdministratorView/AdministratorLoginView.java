package dk.voresgruppe.gui.AdministratorView;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.be.User;
import dk.voresgruppe.bll.AdministratorManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AdministratorLoginView {
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
                System.out.print("Succes current Administrator: " + currentAdministrator.getFirstname() + " " + currentAdministrator.getLastname());
            }
        }

    }
}

package dk.voresgruppe.gui.AdministratorLoginView.AdministratorView.NewAdministratorView;

import dk.voresgruppe.util.UserError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewAdministratorViewController {


    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML

    public void saveNewAdministrator(ActionEvent actionEvent) {
        String firstname = txtFirstName.getText();
        String lastname =txtLastName.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        if(!firstname.isEmpty() && !lastname.isEmpty() && !userName.isEmpty() && !password.isEmpty()) {
            //TODO

        }
        else {
            UserError.showError("Something went wrong", "all fields needs to be filled out");
        }
    }

    public void Cancel(ActionEvent actionEvent) {
    }
}

package dk.voresgruppe.gui.AdministratorView.ManageAdministratorsView;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.bll.AdministratorManager;
import dk.voresgruppe.util.UserError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewAdministratorViewController {
    private AdministratorManager aMan;
    private ManageAdministratorsView mav;


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

    public void saveNewAdministrator(ActionEvent actionEvent) {
        String firstname = txtFirstName.getText();
        String lastname =txtLastName.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        if(!firstname.isEmpty() && !lastname.isEmpty() && !userName.isEmpty() && !password.isEmpty()) {
            Administrator a = new Administrator(firstname,lastname,userName,password);
            aMan.add(a);
            mav.init();
            closeWindow();
        }
        else {
            UserError.showError("Something went wrong", "all fields needs to be filled out");
        }
    }

    public void Cancel(ActionEvent actionEvent) {
        closeWindow();
    }

    public void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void setaMan(AdministratorManager aMan) {
        this.aMan = aMan;
    }

    public void setMav(ManageAdministratorsView mav) {
        this.mav = mav;
    }
}

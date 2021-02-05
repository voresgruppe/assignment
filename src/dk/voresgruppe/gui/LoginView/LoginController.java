package dk.voresgruppe.gui.LoginView;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController {
    public TextField UserID;
    public TextField PassID;
    public CheckBox cboxRememberMe;
    public ImageView imgCompanyLogo;

    public void btnLogin(ActionEvent actionEvent) {
    }

    public void btnClose(ActionEvent actionEvent) {
        System.exit(1);
    }
}

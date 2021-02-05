package dk.voresgruppe.gui.LoginView;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {
    public TextField UserID;
    public PasswordField PassID;
    
    public CheckBox cboxRememberMe;
    public ImageView imgCompanyLogo;
    public ImageView imgPwIcon;

    public void initialize(){
        //imgPwIcon = new ImageView(new Image(getClass().getResourceAsStream("pw_eye_visibility_off.png"))); ???
    }


    public void btnLogin(ActionEvent actionEvent) {
    }

    public void btnClose(ActionEvent actionEvent) {
        System.exit(1);
    }
}

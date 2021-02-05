package dk.voresgruppe.gui.LoginView;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField UserID;
    public PasswordField PassID;
    
    public CheckBox cboxRememberMe;
    public ImageView imgCompanyLogo;
    public ImageView imgPwIcon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("image/pw_eye_visibility.png");
        Image image = new Image(String.valueOf(file));
        imgPwIcon.setImage(image);
    }

    public void btnLogin(ActionEvent actionEvent) {
    }

    public void btnClose(ActionEvent actionEvent) {
        System.exit(1);
    }


}

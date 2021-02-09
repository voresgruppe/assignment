package dk.voresgruppe.gui.LoginView;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AttendenceView/AttendanceView.fxml"));
            Parent mainLayout = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnClose(ActionEvent actionEvent) {
        System.exit(1);
    }


}

package dk.voresgruppe.gui.AdministratorView;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.Teacher;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdministratorLoginViewController {
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    public ImageView imgCompanyLogo;

    private AdministratorManager aMan = new AdministratorManager();

    public void initialize(){
        File logo = new File("image/logo.png");
        Image image1 = new Image(String.valueOf(logo));
        imgCompanyLogo.setImage(image1);
    }


    public void administratorLogin(ActionEvent actionEvent) {
        ObservableList<Administrator> administrators = aMan.getAllAdministrators();

        User tempUser = new User(txtUsername.getText(), txtPassword.getText());

        for(Administrator currentAdministrator: administrators) {
            User currentUser = currentAdministrator.getAdministratorLogin();
            if(tempUser.getUserName().matches(currentUser.getUserName()) && tempUser.getPassword().matches(currentUser.getPassword())) {
                openAdminView(currentAdministrator);
            }
        }

    }


    private void openAdminView(Administrator currentAdmin) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AdministratorView.fxml"));
            Parent mainLayout = loader.load();
            AdministratorViewController avc = loader.getController();
            avc.setLoggedAdministrator(currentAdmin);
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.setTitle("Administrator view");
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


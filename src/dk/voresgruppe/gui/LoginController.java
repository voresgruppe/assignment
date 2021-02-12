package dk.voresgruppe.gui;


import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.User;
import dk.voresgruppe.bll.StudentManager;
import dk.voresgruppe.gui.AttendenceView.AttendanceViewController;
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
    StudentManager sMan = new StudentManager();
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
        imgCompanyLogo.setImage(image);
        addcplogo("image/logo.png");



        for(Student currentStudent : sMan.getAllStudents()) {
            System.out.println(currentStudent);
        }
        UserID.setText("jane1988");
        PassID.setText("jane1988");



    }
    //Sets company logo in imageview img-company-logo
      private void addcplogo(String s) {
        File logo = new File(s);
        Image img = new Image(String.valueOf(logo));
        imgCompanyLogo.setImage(img);
    }


    public void btnLogin(ActionEvent actionEvent) {
        if(!UserID.getText().isEmpty() && !PassID.getText().isEmpty()) {
            User tempUser = new User(UserID.getText(), PassID.getText());
            for (Student currentStudent : sMan.getAllStudents() ) {
                User currentUser = currentStudent.getStudentLogin();
                if (tempUser.getUserName().matches(currentUser.getUserName()) && tempUser.getPassword().matches(currentUser.getPassword())) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("AttendenceView/AttendanceView.fxml"));
                        Parent mainLayout = loader.load();
                        AttendanceViewController cvc = loader.getController();
                        cvc.setLoggedStudent(currentStudent);
                        Stage stage = new Stage();
                        stage.setScene(new Scene(mainLayout));
                        stage.show();
                        //((Stage) (imgCompanyLogo.getScene().getWindow())).hide();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void btnClose(ActionEvent actionEvent) {
        System.exit(1);
    }


}

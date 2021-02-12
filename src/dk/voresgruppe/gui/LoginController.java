package dk.voresgruppe.gui;


import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.User;
import dk.voresgruppe.bll.StudentManager;
import dk.voresgruppe.gui.AttendenceView.AttendanceViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public BorderPane borderPaneTxtField;
    public BorderPane borderPanePassField;
    public StackPane stackPane;
    public TextField txtPassShown;
    StudentManager sMan = new StudentManager();
    public TextField UserID;
    public PasswordField PassID;
    
    public CheckBox cboxRememberMe;
    public ImageView imgCompanyLogo;
    public ImageView imgPwIcon;

    private boolean hidePass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("image/pw_eye_visibility.png");
        Image image = new Image(String.valueOf(file));
        imgPwIcon.setImage(image);
        for(Student currentStudent : sMan.getAllStudents()) {
            System.out.println(currentStudent);
        }
        UserID.setText("jane1988");
        PassID.setText("jane1988");

        hidePass = true;
        addListener();


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


    public void showPass(MouseEvent mouseEvent) {


        if (hidePass){
            File file = new File("image/pw_eye_visibility_off.png");
            Image image = new Image(String.valueOf(file));
            imgPwIcon.setImage(image);
            hidePass = false;
            borderPaneTxtField.setVisible(true);
            borderPanePassField.setVisible(false);
            txtPassShown.requestFocus();
            txtPassShown.deselect();
            txtPassShown.end();
        }else {
            File file = new File("image/pw_eye_visibility.png");
            Image image = new Image(String.valueOf(file));
            imgPwIcon.setImage(image);
            hidePass = true;
            borderPaneTxtField.setVisible(false);
            borderPanePassField.setVisible(true);
            PassID.requestFocus();
            PassID.deselect();
            PassID.end();
        }

    }

    public void addListener(){
        txtPassShown.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                PassID.setText(newValue);
            }
        });

        PassID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                txtPassShown.setText(newValue);
            }
        });
        if(!PassID.getText().isEmpty()){
            txtPassShown.setText(PassID.getText());
        }
    }

}

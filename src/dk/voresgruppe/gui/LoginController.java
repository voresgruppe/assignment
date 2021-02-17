package dk.voresgruppe.gui;


import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.be.User;
import dk.voresgruppe.bll.StudentManager;
import dk.voresgruppe.bll.TeacherManager;
import dk.voresgruppe.gui.AttendenceView.AttendanceViewController;
import dk.voresgruppe.gui.TeacherView.TeacherViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;






import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginController implements Initializable {
    public BorderPane borderPaneTxtField;
    public BorderPane borderPanePassField;
    public StackPane stackPane;
    public TextField txtPassShown;
    StudentManager sMan = new StudentManager();
    TeacherManager tMan = new TeacherManager();
    public TextField UserID;
    public PasswordField PassID;

    public CheckBox cboxRememberMe;
    public ImageView imgCompanyLogo;
    public ImageView imgPwIcon;

    private boolean hidePass;
    private boolean rememberMe = false;

    //This will be the file where the username and password will be saved
    File saveFile = new File("resources/data/RememberMe");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("image/pw_eye_visibility.png");
        Image image = new Image(String.valueOf(file));
        imgPwIcon.setImage(image);

        File logo = new File("image/logo.png");
        Image image1 = new Image(String.valueOf(logo));
        imgCompanyLogo.setImage(image1);

        for(Student currentStudent : sMan.getAllStudents()) {
            System.out.println(currentStudent);
        }
        for(Teacher currentTeacher : tMan.getAllTeachers()){
            System.out.println(currentTeacher);
        }

        hidePass = true;
        addListener();


        updateLoginFields();

    }



    public void btnLogin(ActionEvent actionEvent) {
        if(rememberMe) {
            saveLoginInfo();
        }
        if(!UserID.getText().isEmpty() && !PassID.getText().isEmpty()) {
            User tempUser = new User(UserID.getText(), PassID.getText());
            for (Student currentStudent : sMan.getAllStudents() ) {
                User currentUser = currentStudent.getStudentLogin();
                if (tempUser.getUserName().matches(currentUser.getUserName()) && tempUser.getPassword().matches(currentUser.getPassword())) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("AttendenceView/AttendanceView.fxml"));
                        Parent mainLayout = loader.load();
                        AttendanceViewController avc = loader.getController();
                        avc.setLoggedStudent(currentStudent);
                        Stage stage = new Stage();
                        stage.setScene(new Scene(mainLayout));
                        stage.show();
                        //((Stage) (imgCompanyLogo.getScene().getWindow())).hide();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }

            for(Teacher currentTeacher : tMan.getAllTeachers()){
                User currentUser = currentTeacher.getTeacherLoginLogin();
                if(tempUser.getUserName().matches(currentUser.getUserName()) && tempUser.getPassword().matches(currentUser.getPassword())){
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("TeacherView/teacherView.fxml"));
                        Parent mainLayout = loader.load();
                        TeacherViewController tvc = loader.getController();
                        tvc.setLoggedStudent(currentTeacher);
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


    public void handleRememberMeCbox(ActionEvent actionEvent) {
            rememberMe = true;
    }

    public void saveLoginInfo(){
        try {
            if(!saveFile.exists()) {saveFile.createNewFile();}  //if the file !exist create a new one

            BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile.getAbsolutePath()));
            bw.write(cboxRememberMe.isSelected() + "");  //write if the checkbox is selected or not
            bw.newLine(); //leave a new line
            bw.write(UserID.getText()); //write the name
            bw.newLine(); //leave a new Line
            bw.write(PassID.getText()); //write the password


            bw.close(); //close the BufferdWriter

        } catch (IOException e) { e.printStackTrace(); }
    }

    public void updateLoginFields(){
        try {

                if(saveFile.exists()){    //if this file exists

                    Scanner scan = new Scanner(saveFile);   //Use Scanner to read the File
                    if(scan.nextBoolean()) {  //check if the checkbox was on
                        cboxRememberMe.setSelected(true); //set the checkbox on
                        scan.nextLine();  //go to next line in txt file
                        UserID.setText(scan.nextLine());  //append the text to name field
                        PassID.setText(scan.nextLine()); //append the text to password field
                    }
                    scan.close();
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Teacher_btn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TeacherView/teacherView.fxml"));
        Parent mainLayout = loader.load();
        TeacherViewController tvc = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(mainLayout));
        stage.show();


    }
}

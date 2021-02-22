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
import javafx.scene.control.*;

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
    public Button btnSwitch;
    public Label lblLogin;
    StudentManager sMan = new StudentManager();
    TeacherManager tMan = new TeacherManager();
    public TextField UserID;
    public PasswordField PassID;

    public CheckBox cboxRememberMe;
    public ImageView imgCompanyLogo;
    public ImageView imgPwIcon;

    private boolean hidePass;
    private boolean isStudentView = true;
    

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

        for(Student currentStudent : sMan.getallStudents_OBS()) {
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
        if(cboxRememberMe.isSelected()) {
            saveLoginInfo();
        }
        if(isStudentView) {
            if (!UserID.getText().isEmpty() && !PassID.getText().isEmpty()) {
                User tempUser = new User(UserID.getText(), PassID.getText());
                for (Student currentStudent : sMan.getallStudents_OBS()) {
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
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }
                }

            }
        }

            if(!isStudentView) {
                if(!UserID.getText().isEmpty() && !PassID.getText().isEmpty()) {
                    User tempUser = new User(UserID.getText(), PassID.getText());
                    for (Teacher currentTeacher : tMan.getAllTeachers()) {
                        User currentUser = currentTeacher.getTeacherLoginLogin();
                        if (tempUser.getUserName().matches(currentUser.getUserName()) && tempUser.getPassword().matches(currentUser.getPassword())) {
                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("TeacherView/teacherView.fxml"));
                                Parent mainLayout = loader.load();
                                TeacherViewController tvc = loader.getController();
                                tvc.setLoggedTeacher(currentTeacher);
                                Stage stage = new Stage();
                                stage.setScene(new Scene(mainLayout));
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();

                            }
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
                if(cboxRememberMe.isSelected()){
                    saveLoginInfo();
                }
            }
        });

        PassID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                txtPassShown.setText(newValue);
                if(cboxRememberMe.isSelected()){
                    saveLoginInfo();
                }
            }
        });

        UserID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(cboxRememberMe.isSelected()){
                    saveLoginInfo();
                }
            }
        });
        if(!PassID.getText().isEmpty()){
            txtPassShown.setText(PassID.getText());
        }
    }


    public void handleRememberMeCbox(ActionEvent actionEvent) {
            saveLoginInfo();
    }

    public void saveLoginInfo(){
        try {
            if(!saveFile.exists()) {saveFile.createNewFile();}  //if the file !exist create a new one

            BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile.getAbsolutePath()));
            bw.write(cboxRememberMe.isSelected() + "");  //write if the checkbox is selected or not
            bw.newLine(); //leave a new line
            if(!UserID.getText().isEmpty()) {
                bw.write(UserID.getText()); //write the name
                bw.newLine(); //leave a new Line
            }else {     //if the UserID field is empty, write this random string in the txt file
                bw.write("dd+VXnaYQm^C\\Eb-7h6>Yb4KmE_9gZb~!-%8PDQH#s");
                bw.newLine();
            }
            if(!PassID.getText().isEmpty()){
                bw.write(PassID.getText()); //write the password
            }else {     //if the PassID field is empty, write this random string in the txt file
                bw.write("pHYRj&U.)Np*2W<M7`]u*]`.+Gfsgk%DX8U6&Wzgy>");
            }

            bw.close(); //close the BufferdWriter

        } catch (IOException e) { e.printStackTrace(); }
    }

    public void updateLoginFields(){
        try {

                if(saveFile.exists()){    //if this file exists

                    Scanner scan = new Scanner(saveFile);   //Use Scanner to read the File
                    if(scan.nextBoolean()) {  //check if the checkbox was on
                        scan.nextLine(); //go to next line in txt file
                        String id = scan.nextLine();    //append the text to string
                        String pass = scan.nextLine();  //append the text to string
                        cboxRememberMe.setSelected(true); //set the checkbox on
                        if(!id.contains("dd+VXnaYQm^C\\Eb-7h6>Yb4KmE_9gZb~!-%8PDQH#s")){    //check if the id was saved as empty
                            UserID.setText(id);
                        }
                        if(!pass.contains("pHYRj&U.)Np*2W<M7`]u*]`.+Gfsgk%DX8U6&Wzgy>")){   //check if the pass was saved as empty
                            PassID.setText(pass);
                        }
                    }
                    scan.close();
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Teacher_btn(ActionEvent event) throws IOException {
        if (isStudentView) {
            isStudentView = false;
            btnSwitch.setText("Switch to Student");
            lblLogin.setText("Teacher Login");
        }

        else {
            isStudentView =true;
            btnSwitch.setText("Switch to Teacher");
            lblLogin.setText("Student Login");
        }



    }
}

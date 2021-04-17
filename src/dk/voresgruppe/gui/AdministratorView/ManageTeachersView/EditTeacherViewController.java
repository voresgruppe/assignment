package dk.voresgruppe.gui.AdministratorView.ManageTeachersView;

import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.be.User;
import dk.voresgruppe.bll.TeacherManager;
import dk.voresgruppe.util.UserError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditTeacherViewController {
    private TeacherManager tMan;
    private Teacher currentTeacher;


    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;

    public void settMan(TeacherManager tMan) {
        this.tMan = tMan;
    }

    public void setCurrentTeacher(Teacher currentTeacher) {
        this.currentTeacher = currentTeacher;
    }

    public void initTxtFields(){
        txtFirstName.setText(currentTeacher.getFirstName());
        txtLastName.setText(currentTeacher.getLastName());
        txtUserName.setText(currentTeacher.getTeacherLogin().getUserName());
        txtPassword.setText(currentTeacher.getTeacherLogin().getPassword());
    }

    public void saveNewTeacher(ActionEvent actionEvent) {
        if(txtFirstName != null && txtLastName!= null && txtUserName!= null && txtPassword != null) {
            User teacherUser = new User(txtUserName.getText(), txtPassword.getText());
            Teacher t = new Teacher(txtFirstName.getText(),txtLastName.getText(),teacherUser);
            tMan.replace(currentTeacher,t);
            closeWindow();
        }
        else{
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
}

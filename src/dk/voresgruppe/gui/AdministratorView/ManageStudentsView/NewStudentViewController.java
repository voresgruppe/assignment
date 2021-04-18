package dk.voresgruppe.gui.AdministratorView.ManageStudentsView;

import dk.voresgruppe.be.*;
import dk.voresgruppe.be.Class;
import dk.voresgruppe.bll.ClassManager;
import dk.voresgruppe.bll.StudentManager;
import dk.voresgruppe.util.UserError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class NewStudentViewController {
    private StudentManager sMan;
    private ClassManager cMan;
    private Class selectedClass;

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private ComboBox<Class> studentClass;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    public void setManagers(StudentManager sMan, ClassManager cMan) {
        this.sMan = sMan;
        this.cMan = cMan;
    }



    public void init(){
        studentClass.setItems(cMan.getAllClasses());
        studentClass.setConverter(new StringConverter<>() {

            @Override
            public String toString(Class e) {
                if(e == null){
                    return null;
                }
                return e.getClassName();
            }

            @Override
            public Class fromString(String s) {
                return studentClass.getItems().stream().filter(e -> e.getClassName().equals(s)).findFirst().orElse(null);
            }

        });

        studentClass.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null) {
                selectedClass = cMan.getClassFromID(newval.getClassID());
            }

        });
    }

    public void saveNewStudent(ActionEvent actionEvent) {
        if(txtFirstName != null && txtLastName!= null && txtUserName!= null && txtPassword != null) {
            if(selectedClass != null){
                User studentUser = new User(txtUserName.getText(), txtPassword.getText());
                Student s = new Student( selectedClass.getClassID(), txtFirstName.getText(),txtLastName.getText(),studentUser);
                sMan.add(s);
                closeWindow();
            }
            else {
                UserError.showError("Something went wrong", "you need to pick a class for the student");
            }
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

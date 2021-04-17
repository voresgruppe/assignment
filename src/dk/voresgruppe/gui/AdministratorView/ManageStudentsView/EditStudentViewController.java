package dk.voresgruppe.gui.AdministratorView.ManageStudentsView;

import dk.voresgruppe.be.Class;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.User;
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

public class EditStudentViewController {
    private StudentManager sMan;
    private ClassManager cMan;
    private Class selectedClass;
    private Student selectedStudent;

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

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public void init(){
       initComboBox();
       txtFirstName.setText(selectedStudent.getFirstName());
       txtLastName.setText(selectedStudent.getLastName());
       txtUserName.setText(selectedStudent.getStudentLogin().getUserName());
       txtPassword.setText(selectedStudent.getStudentLogin().getPassword());
    }

    public void initComboBox(){
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
                sMan.replace(selectedStudent,s);
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
    }}

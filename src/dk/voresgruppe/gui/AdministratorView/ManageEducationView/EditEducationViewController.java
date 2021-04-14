package dk.voresgruppe.gui.AdministratorView.ManageEducationView;

import dk.voresgruppe.be.Education;
import dk.voresgruppe.bll.EducationManager;
import dk.voresgruppe.util.UserError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditEducationViewController {
    private EducationManager eMan;
    private Education selectedEducation;

    @FXML
    private TextField txtName;
    @FXML
    private Button btnCancel;

    public void init(){
        txtName.setText(selectedEducation.getName());
    }

    public void Cancel(ActionEvent actionEvent) {
        closeWindow();
    }
    public void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void saveEducation(ActionEvent actionEvent) {
        if(txtName.getText() != null){
            eMan.replace(selectedEducation,new Education(txtName.getText()));
            closeWindow();
        }
        else {
            UserError.showError("InputError", "You need to give the Education a name");
        }
    }

    public void seteMan(EducationManager eMan) {
        this.eMan = eMan;
    }

    public void setSelectedEducation(Education selectedEducation) {
        this.selectedEducation = selectedEducation;
    }
}

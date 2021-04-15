package dk.voresgruppe.gui.AdministratorView.ManageClassesView;

import dk.voresgruppe.be.Class;
import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Education;
import dk.voresgruppe.bll.ClassManager;
import dk.voresgruppe.bll.EducationManager;
import dk.voresgruppe.util.UserError;
import dk.voresgruppe.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class EditClassViewController {
    private Utils utils= new Utils();
    private ClassManager cMan;
    private EducationManager eMan;
    private Education selectedEducation;
    private Class selectedClass;


    @FXML
    private TextField className;
    @FXML
    private ComboBox<Education> classEducationName;
    @FXML
    private DatePicker classEndDate;
    @FXML
    private DatePicker classStartDate;
    @FXML
    private Button btnCancel;

    public void init(){
        initEducationComboBox();

        className.setText(selectedClass.getClassName());
        classEducationName.setValue(eMan.getEducationFromId(selectedClass.getEducationID()));

        if(selectedClass.getStartDate() != null){
            classStartDate.setValue(selectedClass.getStartDate().toLocalDate());
        }
        if(selectedClass.getEndDate() != null){
            classEndDate.setValue(selectedClass.getEndDate().toLocalDate());
        }
    }

    public void setSelectedClass(Class selectedClass) {
        this.selectedClass = selectedClass;
    }

    public void initEducationComboBox(){
        classEducationName.setItems(eMan.getAllEducations());

        classEducationName.setConverter(new StringConverter<>() {

            @Override
            public String toString(Education e) {
                if(e == null){
                    return null;
                }
                return e.getName();
            }

            @Override
            public Education fromString(String s) {
                return classEducationName.getItems().stream().filter(e -> e.getName().equals(s)).findFirst().orElse(null);
            }

        });

        classEducationName.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null) {
                selectedEducation = eMan.getEducationFromId(newval.getiD());
            }

        });
    }

    public void setManagers(ClassManager cMan, EducationManager eMan){
        this.cMan = cMan;
        this.eMan = eMan;
    }


    public void handleSaveClass(ActionEvent actionEvent) {
        if(selectedEducation !=null && className.getText() != null) {
            Class newClass = new Class(selectedEducation.getiD(), className.getText());
            if(classStartDate.getValue() != null) {
                Date startDate = utils.dateFromLocalDate(classStartDate.getValue());
                newClass.setStartDate(startDate);
            }
            if(classStartDate.getValue() != null) {
                Date endDate = utils.dateFromLocalDate(classEndDate.getValue());
                newClass.setEndDate(endDate);
            }
            cMan.replace(selectedClass, newClass);
            closeWindow();
        }
        else if (selectedEducation == null){
            UserError.showError("input Error", "you need to select an Education");
        }
        else if (className == null){
            UserError.showError("input Error", "you need to give the class a name");
        }
    }

    public void handleCancel(ActionEvent actionEvent) {
        closeWindow();
    }
    public void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void handleSelectEducation(ActionEvent actionEvent) {
    }
}

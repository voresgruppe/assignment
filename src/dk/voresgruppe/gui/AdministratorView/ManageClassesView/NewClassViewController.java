package dk.voresgruppe.gui.AdministratorView.ManageClassesView;

import dk.voresgruppe.be.*;
import dk.voresgruppe.be.Class;
import dk.voresgruppe.bll.ClassManager;
import dk.voresgruppe.bll.EducationManager;
import dk.voresgruppe.bll.ScheduleManager;
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

public class NewClassViewController {

    private Utils utils= new Utils();
    private ClassManager cMan;
    private EducationManager eMan;
    private ScheduleManager sMan;
    private Education selectedEducation;
    private Schedule selectedSchedule;


    @FXML
    private TextField className;
    @FXML
    private ComboBox<Education> classEducationName;
    @FXML
    private ComboBox<Schedule> classScheduleName;
    @FXML
    private DatePicker classEndDate;
    @FXML
    private DatePicker classStartDate;
    @FXML
    private Button btnCancel;

    public void init(){
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

        classScheduleName.setItems(sMan.getAllSchedules());
        classScheduleName.setConverter(new StringConverter<>() {

            @Override
            public String toString(Schedule sc) {
                if(sc == null){
                    return null;
                }
                return sc.getScheduleName();
            }

            @Override
            public Schedule fromString(String s) {
                return classScheduleName.getItems().stream().filter(sc -> sc.getScheduleName().equals(s)).findFirst().orElse(null);
            }

        });

        classScheduleName.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null) {
                selectedSchedule = sMan.getScheduleFromId(newval.getScheduleID());
            }

        });
    }

    public void setManagers(ClassManager cMan, EducationManager eMan, ScheduleManager sMan){
        this.cMan = cMan;
        this.eMan = eMan;
        this.sMan = sMan;
    }


    public void handleSaveClass(ActionEvent actionEvent) {
        if(selectedSchedule !=null && selectedEducation !=null && className.getText() != null) {
            Class newClass = new Class(selectedEducation.getiD(), className.getText());
            newClass.setScheduleID(selectedSchedule.getScheduleID());
            if(classStartDate.getValue() != null) {
                Date startDate = utils.dateFromLocalDate(classStartDate.getValue());
                newClass.setStartDate(startDate);
            }
            if(classStartDate.getValue() != null) {
                Date endDate = utils.dateFromLocalDate(classEndDate.getValue());
                newClass.setEndDate(endDate);
            }
            cMan.add(newClass);
            closeWindow();
        }
        else if (selectedEducation == null){
            UserError.showError("input Error", "you need to select an Education");
        }
        else if (className == null){
            UserError.showError("input Error", "you need to give the class a name");
        }
        else if(selectedSchedule == null){
            UserError.showError("input Error", "you need to select a Schedule");
        }
    }

    public void handleCancel(ActionEvent actionEvent) {
        closeWindow();
    }
    public void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}

package dk.voresgruppe.gui.AdministratorView.ManageClassesView;

import dk.voresgruppe.be.Class;
import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Education;
import dk.voresgruppe.be.Schedule;
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

public class EditClassViewController {
    private Utils utils= new Utils();
    private ClassManager cMan;
    private EducationManager eMan;
    private ScheduleManager sMan;

    private Education selectedEducation;
    private Class selectedClass;
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
        initEducationComboBox();
        initScheduleComboBox();

        className.setText(selectedClass.getClassName());
        classEducationName.setValue(eMan.getEducationFromId(selectedClass.getEducationID()));
        classScheduleName.setValue(sMan.getScheduleFromId(selectedClass.getScheduleID()));

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

    public void initScheduleComboBox(){
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
            cMan.replace(selectedClass, newClass);
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

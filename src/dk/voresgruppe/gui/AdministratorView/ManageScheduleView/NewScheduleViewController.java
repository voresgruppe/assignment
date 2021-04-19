package dk.voresgruppe.gui.AdministratorView.ManageScheduleView;

import dk.voresgruppe.be.Class;
import dk.voresgruppe.be.Course;
import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Schedule;
import dk.voresgruppe.bll.CourseManager;
import dk.voresgruppe.bll.ScheduleManager;
import dk.voresgruppe.util.UserError;
import dk.voresgruppe.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class NewScheduleViewController {
    private Utils utils = new Utils();
    private ScheduleManager sMan;
    private CourseManager cMan;
    private Course selectedMonday;
    private Course selectedTuesday;
    private Course selectedWednesday;
    private Course selectedThursday;
    private Course selectedFriday;

    @FXML
    private TextField scheduleName;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<Course> scheduleMonday;
    @FXML
    private ComboBox<Course>  scheduleTuesday;
    @FXML
    private ComboBox<Course>  scheduleWednesday;
    @FXML
    private ComboBox<Course>  scheduleThursday;
    @FXML
    private ComboBox<Course>  scheduleFriday;

    private void setComboboxCourse(ComboBox<Course> combobox){
        combobox.setItems(cMan.getAllCourses());

        combobox.setConverter(new StringConverter<Course>() {
            @Override
            public String toString(Course c) {
                if(c==null){
                    return null;
                }
                return c.getName();
            }

            @Override
            public Course fromString(String s) {
                return combobox.getItems().stream().filter(c-> c.getName().equals(s)).findFirst().orElse(null);
            }
        });
    }

    public void init(){

        setComboboxCourse(scheduleMonday);
            scheduleMonday.valueProperty().addListener((obs, oldval, newval) -> {
                if(newval != null) {
                selectedMonday = cMan.getCourseFromID(newval.getCourseID());
                }

            });
        setComboboxCourse(scheduleTuesday);
        scheduleTuesday.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null) {
                selectedTuesday = cMan.getCourseFromID(newval.getCourseID());
            }

        });
        setComboboxCourse(scheduleWednesday);
        scheduleWednesday.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null) {
                selectedWednesday = cMan.getCourseFromID(newval.getCourseID());
            }

        });
        setComboboxCourse(scheduleThursday);
        scheduleThursday.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null) {
                selectedThursday = cMan.getCourseFromID(newval.getCourseID());
            }

        });
        setComboboxCourse(scheduleFriday);
        scheduleFriday.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null) {
                selectedFriday = cMan.getCourseFromID(newval.getCourseID());
            }

        });
    }

    public void setManagers(ScheduleManager sMan, CourseManager cMan){
        this.sMan =sMan;
        this.cMan =cMan;
    }

    public void handleSaveSchedule(ActionEvent actionEvent) {
        if(scheduleName.getText() != null && selectedMonday != null && selectedTuesday != null&& selectedWednesday != null&& selectedThursday != null&& selectedFriday != null) {
            Schedule newSchedule = new Schedule(scheduleName.getText());
            newSchedule.setMonday(selectedMonday.getCourseID());
            newSchedule.setTuesday(selectedTuesday.getCourseID());
            newSchedule.setWednesday(selectedWednesday.getCourseID());
            newSchedule.setThursday(selectedThursday.getCourseID());
            newSchedule.setFriday(selectedFriday.getCourseID());
            sMan.add(newSchedule);
            closeWindow();
        }
        else {
            UserError.showError("input Error", "you need to give the schedule a name and select a course for each day");
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

package dk.voresgruppe.gui.AdministratorView.ManageCoursesView;

import dk.voresgruppe.be.Course;
import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.bll.CourseManager;
import dk.voresgruppe.bll.TeacherManager;
import dk.voresgruppe.util.UserError;
import dk.voresgruppe.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;

public class NewCourseViewController {
    private Utils utils = new Utils();
    private CourseManager cMan;
    private TeacherManager tMan;
    private Teacher selectedTeacher;

    @FXML
    private Button btnCancel;
    @FXML
    private DatePicker courseStartDate;
    @FXML
    private DatePicker courseEndDate;
    @FXML
    private ComboBox<Teacher> courseTeacherName;
    @FXML
    private TextField courseName;

    public void init(){
        courseTeacherName.setItems(tMan.getAllTeachers());

        courseTeacherName.setConverter(new StringConverter<>() {

            @Override
            public String toString(Teacher teacher) {
                if(teacher == null){
                    return null;
                }
                return teacher.getFullName();
            }

            @Override
            public Teacher fromString(String s) {
                return courseTeacherName.getItems().stream().filter(t -> t.getFullName().equals(s)).findFirst().orElse(null);
            }

        });

        courseTeacherName.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null) {
                selectedTeacher = tMan.getTeacherFromId(newval.getTeacherID());
            }

        });



    }

    public void handleSaveCourse(ActionEvent actionEvent) {
        if(selectedTeacher !=null && courseName.getText() != null) {
            Course newCourse = new Course(selectedTeacher.getTeacherID(), courseName.getText());
            if(courseStartDate.getValue() != null) {
                Date startDate = utils.dateFromLocalDate(courseStartDate.getValue());
                newCourse.setStartDate(startDate);
            }
            if(courseEndDate.getValue() != null) {
                Date endDate = utils.dateFromLocalDate(courseEndDate.getValue());
                newCourse.setEndDate(endDate);
            }
            cMan.add(newCourse);
            closeWindow();
        }
        else if (selectedTeacher == null){
            UserError.showError("input Error", "you need to select at teacher");
        }
        else if (courseName == null){
            UserError.showError("input Error", "you need to give the course a name");
        }
    }
    public void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        closeWindow();
    }

    public CourseManager getcMan() {
        return cMan;
    }

    public void setManagers (CourseManager cMan, TeacherManager tMan) {
        this.cMan = cMan;
        this.tMan = tMan;
    }

    public void handleSelectTeacher(ActionEvent actionEvent) {
    }
}

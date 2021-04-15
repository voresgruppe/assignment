package dk.voresgruppe.gui.AdministratorView.ManageCoursesView;

import dk.voresgruppe.be.Course;
import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.bll.CourseManager;
import dk.voresgruppe.bll.TeacherManager;
import dk.voresgruppe.util.UserError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.Instant;
import java.time.ZoneId;

public class EditCourseViewController {
    private CourseManager cMan;
    private TeacherManager tMan;
    private Teacher selectedTeacher;
    private Course selectedCourse;

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
        initTeacherComboBox();

        courseName.setText(selectedCourse.getName());
        courseTeacherName.setValue(tMan.getTeacherFromId(selectedCourse.getTeacherID()));

        if(selectedCourse.getStartDate()!=null) {
            courseStartDate.setValue(selectedCourse.getStartDate().toLocalDate());
        }
        if(selectedCourse.getEndDate()!= null) {
            courseEndDate.setValue(selectedCourse.getEndDate().toLocalDate());
        }
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    private void initTeacherComboBox(){

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
            newCourse.setCourseID(selectedCourse.getCourseID());
            if(courseStartDate.getValue() != null) {
                java.util.Date date = java.util.Date.from(Instant.from(courseStartDate.getValue().atStartOfDay(ZoneId.systemDefault())));
                Date startDate = new Date(date.getDate(), date.getMonth()+1, date.getYear()+1900);
                newCourse.setStartDate(startDate);
            }
            if(courseEndDate.getValue() != null) {
                java.util.Date date = java.util.Date.from(Instant.from(courseEndDate.getValue().atStartOfDay(ZoneId.systemDefault())));
                Date endDate = new Date(date.getDate(), date.getMonth()+1, date.getYear()+1900);
                newCourse.setEndDate(endDate);
            }
            cMan.replace(selectedCourse, newCourse);
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

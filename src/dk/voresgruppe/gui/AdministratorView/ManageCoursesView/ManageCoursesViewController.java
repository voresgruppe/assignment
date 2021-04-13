package dk.voresgruppe.gui.AdministratorView.ManageCoursesView;

import dk.voresgruppe.be.Course;
import dk.voresgruppe.bll.CourseManager;
import dk.voresgruppe.bll.TeacherManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageCoursesViewController {

    private CourseManager cMan;
    private Course selectedCourse;
    private TeacherManager tMan = new TeacherManager();


    @FXML
    private TableColumn<Course, String>  courseTeacher;
    @FXML
    private TableColumn<Course, String>  courseStartDate;
    @FXML
    private TableColumn<Course, String>  courseEndDate;
    @FXML
    private TableColumn<Course, String> courseID;
    @FXML
    private TableColumn<Course, String> courseName;
    @FXML
    private TableView<Course> tblviewCourses;

    public ManageCoursesViewController() {

    }


    public void initCourses(){
        tblviewCourses.setItems(cMan.getAllCourses());
        courseID.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getIDProperty());
        courseName.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getNameProperty());
        courseTeacher.cellValueFactoryProperty().setValue(cellData -> tMan.getTeacherFromId(cellData.getValue().getTeacherID()).getFullNameProperty());
        courseStartDate.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getStartDateProperty());
        courseEndDate.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getEndDateProperty());
    }
    public void courseListener(){
        tblviewCourses.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedCourse = newValue;
        });
    }

    public void setCMan(CourseManager cMan) {
        this.cMan = cMan;
        initCourses();
        courseListener();
    }

    public void addNewCourse(ActionEvent actionEvent) {
    }

    public void handleDeleteCourse(ActionEvent actionEvent) {
    }

    public void handleEditCourse(ActionEvent actionEvent) {
    }
}

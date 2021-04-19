package dk.voresgruppe.gui.TeacherView;

import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.bll.ClassManager;
import dk.voresgruppe.bll.StudentManager;
import dk.voresgruppe.gui.TeacherView.StudentInfoView.StudentInfoController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class TeacherViewController {

    public CheckBox chckbox;
    private Teacher loggedTeacher;
    private StudentManager sMan;
    private ClassManager cMan = new ClassManager();
    @FXML
    private TableView<Student> tableView;
    @FXML
    private ObservableList<Student> observableListStudents;
    @FXML
    private TableColumn<Student, String> StudentName;
    @FXML
    private TableColumn<Student, Double> absencePercentage;
    @FXML
    private TableColumn<Student, String> StudentCourse;
    @FXML
    public TableColumn<Student, String> StudentAbsenceDay;
    @FXML
    public TextField searchname;
    @FXML
    private Button logud_teacher;
    public TeacherViewController(){
        sMan = new StudentManager();
}

    public void initialize() {
        try {
            sMan.orderStudentsWithMostAbsence();
            observableListStudents = sMan.getallStudents_OBS();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableView.setItems(observableListStudents);
        StudentName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        absencePercentage.setCellValueFactory(new PropertyValueFactory<>("absencePercentage"));
        StudentCourse.cellValueFactoryProperty().setValue(cellData -> cMan.getClassFromID(cellData.getValue().getClassID()).getClassNameProperty());
        StudentAbsenceDay.setCellValueFactory(new PropertyValueFactory<>("mostAbsentDay"));
        setListenerAndSearch();
        getClickedStudent();
    }

    public void setLoggedTeacher(Teacher loggedTeacher) {
        this.loggedTeacher = loggedTeacher;
    }

    //log ud og luk programmet
    public void Teacher_Close(ActionEvent event) {
        Stage stage = (Stage) logud_teacher.getScene().getWindow();
        stage.close();
    }

    public void setListenerAndSearch(){
        searchname.textProperty().addListener((observableValue, oldValue, newValue) -> tableView.setItems(sMan.searchStudent(newValue, sMan.getallStudents_OBS())));
    }

    public void getClickedStudent(){
        tableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                Student clickedStudent = tableView.getSelectionModel().getSelectedItem();
                System.out.println(clickedStudent.toString());
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("StudentInfoView/StudentInfoView.fxml"));
                    Parent mainLayout = loader.load();
                    StudentInfoController sic = loader.getController();
                    sic.lookUpStudent(clickedStudent);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(mainLayout));
                    stage.setTitle(clickedStudent.getFullName() + ", " + cMan.getClassFromID(clickedStudent.getClassID()).getClassName() + " Info");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showTeachersStudents(ActionEvent actionEvent) {
        if (chckbox.isSelected()){
            ObservableList<Student> studentsFromTeacher = sMan.getStudentsFromTeacher(loggedTeacher);
            tableView.setItems(sMan.searchStudent(searchname.getText(), studentsFromTeacher));
            searchname.textProperty().addListener((observableValue, oldValue, newValue) -> tableView.setItems(sMan.searchStudent(newValue, studentsFromTeacher)));
        }else {
            tableView.setItems(sMan.searchStudent(searchname.getText(), observableListStudents));
            setListenerAndSearch();
        }
    }
}

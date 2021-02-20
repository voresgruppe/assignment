package dk.voresgruppe.gui.TeacherView;

import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.bll.StudentManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class TeacherViewController {
    public Button logud_teacher;
    private Teacher loggedTeacher;
    public StudentManager sMan;
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
    public TextField searchname;
    public TeacherViewController(){
        sMan = new StudentManager();
}

    public void initialize() {
        try {
            observableListStudents = sMan.getallStudents_OBS();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableView.setItems(observableListStudents);
        StudentName.setCellValueFactory(new PropertyValueFactory<Student,String>("fullName"));
        absencePercentage.setCellValueFactory(new PropertyValueFactory<>("absencePercentage"));
        StudentCourse.setCellValueFactory(new PropertyValueFactory<>("currentCourse"));

        setListenerAndSearch();

    }


    public void search() {
      //  tableView.setItems(sMan.getallStudents_OBS());
        //if (observableListStudents != null){
          //  tableView.setItems(observableListStudents.get(s));
        //}
    }


    public void setLoggedTeacher(Teacher loggedTeacher) {
        this.loggedTeacher = loggedTeacher;
    }





    //log ud og luk programmet
    public void Teacher_Close(ActionEvent event) {
        Stage stage = (Stage) logud_teacher.getScene().getWindow();
        stage.close();
    }

    public void setStudentManager(StudentManager sMan) {
    }

    public void setListenerAndSearch(){
        searchname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                tableView.setItems(sMan.searchStudent(newValue, sMan.getallStudents_OBS()));
            }
        });
    }
}

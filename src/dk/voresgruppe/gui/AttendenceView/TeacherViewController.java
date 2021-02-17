package dk.voresgruppe.gui.AttendenceView;

import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.bll.StudentManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class TeacherViewController {
    public StudentManager studentManager;
    public TableView<Student> tableView;
    private ObservableList<Student> observableListStudents;
    @FXML
    public TableColumn<Student,String> elevID;
    @FXML
    public TableColumn<Student,String> fraværID;



    public void StudentName(ActionEvent event) {

    }


    public void Teacher_Close(ActionEvent event) {
        System.exit(1);
    }


    public void initialize(){
      studentManager = new StudentManager();

      tableView.setItems(observableListStudents);

      elevID.cellValueFactoryProperty().setValue(elevID.getCellValueFactory());
      fraværID.cellValueFactoryProperty().setValue(elevID.getCellValueFactory());
    }
}

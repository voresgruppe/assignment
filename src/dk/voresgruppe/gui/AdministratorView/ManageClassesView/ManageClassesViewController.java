package dk.voresgruppe.gui.AdministratorView.ManageClassesView;

import dk.voresgruppe.be.Class;
import dk.voresgruppe.bll.ClassManager;
import dk.voresgruppe.bll.EducationManager;
import dk.voresgruppe.bll.ScheduleManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageClassesViewController {

    private ClassManager cMan;
    private Class selectedClass;
    private EducationManager eMan;
    private ScheduleManager scheduleMan;


    @FXML
    private TableView <Class> tblviewClasses;
    @FXML
    private TableColumn<Class, String> classID;
    @FXML
    private TableColumn<Class, String> className;
    @FXML
    private TableColumn<Class, String> classEducation;
    @FXML
    private TableColumn<Class, String> classEndDate;
    @FXML
    private TableColumn<Class, String> classStartDate;
    @FXML
    public TableColumn<Class, String> classSchedule;

    public ManageClassesViewController() {
    }

    public void initClasses(){
        tblviewClasses.setItems(cMan.getAllClasses());
        classID.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getIDProperty());
        className.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getClassNameProperty());
        classEducation.cellValueFactoryProperty().setValue(cellData -> eMan.getEducationFromId(cellData.getValue().getEducationID()).getNameProperty());
        classStartDate.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getStartDateProperty());
        classEndDate.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getEndDateProperty());
        classSchedule.cellValueFactoryProperty().setValue(cellData -> scheduleMan.getScheduleFromId(cellData.getValue().getScheduleID()).getScheduleNameProperty());
        classListener();
    }

    public void classListener(){
        tblviewClasses.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedClass = newValue);
    }

    public void setManagers(ClassManager cMan, EducationManager eMan, ScheduleManager scheduleMan){
        this.cMan = cMan;
        this.eMan = eMan;
        this.scheduleMan = scheduleMan;
    }

    public void addNewClass(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("NewClassView.fxml"));
            Parent mainLayout = loader.load();
            NewClassViewController nvc = loader.getController();
            nvc.setManagers(cMan, eMan);
            nvc.init();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void handleDeleteClass(ActionEvent actionEvent) {
        cMan.delete(selectedClass);
    }

    public void handleEditClass(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditClassView.fxml"));
            Parent mainLayout = loader.load();
            EditClassViewController evc = loader.getController();
            evc.setManagers(cMan, eMan);
            evc.setSelectedClass(selectedClass);
            evc.init();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

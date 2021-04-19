package dk.voresgruppe.gui.AdministratorView.ManageScheduleView;

import dk.voresgruppe.be.Schedule;
import dk.voresgruppe.bll.ClassManager;
import dk.voresgruppe.bll.CourseManager;
import dk.voresgruppe.bll.ScheduleManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageSchedulesViewController {
    private ScheduleManager scMan;
    private Schedule selectedSchedule;
    private CourseManager cMan;

    @FXML
    private TableView<Schedule> tblviewSchedule;
    @FXML
    private TableColumn<Schedule, String> scheduleID;
    @FXML
    private TableColumn<Schedule, String>  scheduleName;
    @FXML
    private TableColumn<Schedule, String>  monday;
    @FXML
    private TableColumn<Schedule, String>  tuesday;
    @FXML
    private TableColumn<Schedule, String>  wednesday;
    @FXML
    private TableColumn<Schedule, String>  thursday;
    @FXML
    private TableColumn<Schedule, String>  friday;

    public  void initSchedules(){
        tblviewSchedule.setItems(scMan.getAllSchedules());
        scheduleID.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getScheduleIDProperty());
        scheduleName.cellValueFactoryProperty().setValue(cellData -> cellData.getValue().getScheduleNameProperty());
        monday.cellValueFactoryProperty().setValue(cellData -> cMan.getCourseFromID(cellData.getValue().getMonday()).getNameProperty());
        tuesday.cellValueFactoryProperty().setValue(cellData -> cMan.getCourseFromID(cellData.getValue().getTuesday()).getNameProperty());
        wednesday.cellValueFactoryProperty().setValue(cellData -> cMan.getCourseFromID(cellData.getValue().getWednesday()).getNameProperty());
        thursday.cellValueFactoryProperty().setValue(cellData -> cMan.getCourseFromID(cellData.getValue().getThursday()).getNameProperty());
        friday.cellValueFactoryProperty().setValue(cellData -> cMan.getCourseFromID(cellData.getValue().getFriday()).getNameProperty());

        scheduleListener();
    }

    private void scheduleListener(){
        tblviewSchedule.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedSchedule = newValue);
    }

    public void setManagers(ScheduleManager scMan, CourseManager cMan) {
        this.scMan = scMan;
        this.cMan = cMan;
    }

    public void addNewSchedule(ActionEvent actionEvent) {
    }

    public void handleDeleteSchedule(ActionEvent actionEvent) {
        scMan.delete(selectedSchedule);
    }

    public void handleEditSchedule(ActionEvent actionEvent) {
    }
}

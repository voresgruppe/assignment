package dk.voresgruppe.gui.AdministratorView.ManageScheduleView;

import dk.voresgruppe.be.Schedule;
import dk.voresgruppe.bll.ScheduleManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageSchedulesViewController {
    private ScheduleManager scMan;
    private Schedule selectedSchedule;

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
    }

    public void setScMan(ScheduleManager scMan) {
        this.scMan = scMan;
    }

    public void addNewSchedule(ActionEvent actionEvent) {
    }

    public void handleDeleteSchedule(ActionEvent actionEvent) {
        scMan.delete(selectedSchedule);
    }

    public void handleEditSchedule(ActionEvent actionEvent) {
    }
}

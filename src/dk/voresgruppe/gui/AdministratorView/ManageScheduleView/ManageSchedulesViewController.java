package dk.voresgruppe.gui.AdministratorView.ManageScheduleView;

import dk.voresgruppe.be.Schedule;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageSchedulesViewController {

    public TableView<Schedule> tblviewSchedule;
    public TableColumn scheduleID;
    public TableColumn scheduleName;
    public TableColumn monday;
    public TableColumn tuesday;
    public TableColumn wednesday;
    public TableColumn thursday;
    public TableColumn friday;

    public void addNewSchedule(ActionEvent actionEvent) {
    }

    public void handleDeleteSchedule(ActionEvent actionEvent) {
    }

    public void handleEditSchedule(ActionEvent actionEvent) {
    }
}

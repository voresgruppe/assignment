package dk.voresgruppe.gui.AdministratorLoginView.AdministratorView.ManageAdministratorsView;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.bll.AdministratorManager;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ManageAdministratorsView {
    private AdministratorManager aMan;

    private ObservableList<Administrator> observableListAdministrators;


    @FXML
    private TableColumn administratorID;
    @FXML
    private TableColumn administratorName;
    @FXML
    private TableView tblviewAdministrators;

    public ManageAdministratorsView() {
        aMan = new AdministratorManager();
    }

    public void initialize() {
        observableListAdministrators = aMan.getAllAdministrators();
        tblviewAdministrators.setItems(observableListAdministrators);
        administratorID.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Administrator, Integer>, ObservableValue<Integer>>) p -> p.getValue().getIdProperty().asObject());
        administratorName.setCellValueFactory(new PropertyValueFactory<Administrator, String>("fullName"));

    }

    public void addNewAdministrator(ActionEvent actionEvent) {
    }
}

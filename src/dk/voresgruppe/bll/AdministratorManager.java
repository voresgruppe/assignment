package dk.voresgruppe.bll;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.dal.AdministratorRepository;
import javafx.collections.ObservableList;

public class AdministratorManager {
    private ObservableList<Administrator> allAdministrators;
    private AdministratorRepository aRepo = new AdministratorRepository();

    public AdministratorManager() {
        this.allAdministrators = aRepo.loadTracks();
    }

    public ObservableList<Administrator> getAllAdministrators() {
        return allAdministrators;
    }
}

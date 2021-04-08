package dk.voresgruppe.bll;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.dal.AdministratorRepository;
import javafx.collections.ObservableList;

public class AdministratorManager {
    private ObservableList<Administrator> allAdministrators;
    private AdministratorRepository aRepo = new AdministratorRepository();

    public AdministratorManager() {
        this.allAdministrators = aRepo.loadAdministrators();
    }

    public ObservableList<Administrator> getAllAdministrators() {
        return allAdministrators;
    }

    public void add(Administrator a) {
        int iD = aRepo.addAdministrator(a);
        a.setId(iD);
        allAdministrators.add(a);
    }

    public void delete(Administrator a) {
        allAdministrators.remove(a);
        aRepo.delete(a);
    }

    public void replace(Administrator a, Administrator b){
        aRepo.update(b);
        allAdministrators.set(allAdministrators.indexOf(a),b);
    }
}

package dk.voresgruppe.bll;

import dk.voresgruppe.be.Education;
import dk.voresgruppe.dal.EducationRepository;
import javafx.collections.ObservableList;

public class EducationManager {
    private ObservableList<Education> allEducations;
    private EducationRepository eRepo =new EducationRepository();

    public EducationManager() {
        allEducations =eRepo.loadEducations();
    }

    public ObservableList<Education> getAllEducations() {
        return allEducations;
    }

    public void add(Education e){
        e.setiD(eRepo.addEducation(e));
        allEducations.add(e);
    }

    public  void delete(Education e){
        allEducations.remove(e);
        eRepo.delete(e);
    }

    public void replace(Education a, Education b){
        eRepo.update(b);
        b.setiD(a.getiD());
        allEducations.set(allEducations.indexOf(a),b);
    }
}

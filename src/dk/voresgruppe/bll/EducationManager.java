package dk.voresgruppe.bll;

import dk.voresgruppe.be.Education;
import dk.voresgruppe.be.Teacher;
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
        b.setiD(a.getiD());
        eRepo.update(b);
        allEducations.set(allEducations.indexOf(a),b);
    }

    public Education getEducationFromId(int id){
        for(Education current: allEducations){
            if(current.getiD() == id) {
                return current;
            }
        }
        return null;
    }
}

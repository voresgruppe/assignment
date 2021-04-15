package dk.voresgruppe.bll;

import dk.voresgruppe.be.Class;
import dk.voresgruppe.be.Course;
import dk.voresgruppe.dal.ClassRepository;
import javafx.collections.ObservableList;

public class ClassManager {
    private ObservableList<Class> allClasses;
    private ClassRepository cRepo = new ClassRepository();

    public ClassManager() {
        allClasses = cRepo.loadClasses();
    }

    public ObservableList<Class> getAllClasses(){
        return allClasses;
    }

    public void add(Class c){
        int iD = cRepo.addClass(c);
        c.setClassID(iD);
        allClasses.add(c);
    }

    public void delete(Class c){
        allClasses.remove(c);
        cRepo.delete(c);
    }

    public void replace(Class a, Class b){
        cRepo.update(b);
        allClasses.set(allClasses.indexOf(a),b);
    }
}

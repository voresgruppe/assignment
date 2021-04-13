package dk.voresgruppe.bll;

import dk.voresgruppe.be.Course;
import dk.voresgruppe.dal.CourseRepository;
import javafx.collections.ObservableList;

public class CourseManager {

    private ObservableList<Course> allCourses;
    private CourseRepository cRepo = new CourseRepository();

    public CourseManager() {
        allCourses = cRepo.loadCourses();
    }

    public ObservableList<Course> getAllCourses(){
        return allCourses;
    }

    public void add(Course c){
        int iD = cRepo.addCourse(c);
        c.setCourseID(iD);
        allCourses.add(c);
    }

    public void delete(Course c){
        allCourses.remove(c);
        cRepo.delete(c);
    }

    public void replace(Course a, Course b){
        cRepo.update(b);
        allCourses.set(allCourses.indexOf(a),b);
    }
}

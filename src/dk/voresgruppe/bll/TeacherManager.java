package dk.voresgruppe.bll;

import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.dal.TeacherRepository;
import javafx.collections.ObservableList;


public class TeacherManager {
    private ObservableList<Teacher> allTeachers;
    private TeacherRepository tRepo = new TeacherRepository();

    public TeacherManager()  {
        this.allTeachers = tRepo.loadTeacher();
    }

    public void updateStudentAttendance(Student student, Date date) {
        if(tRepo.hasStudentShowedUp(student, date)){
            tRepo.removeFromShowedUp(student, date);
        }else tRepo.addToShowedUp(student,date);
    }

    public boolean didStudentShowUpAt(Student s, Date d){
        return tRepo.hasStudentShowedUp(s, d);
    }

    public ObservableList<Teacher> getAllTeachers(){
        return allTeachers;
    }

    public void add(Teacher t){
        t.setTeacherID(tRepo.addTeacher(t));
        allTeachers.add(t);
    }
    public void delete(Teacher t) {
        allTeachers.remove(t);
        tRepo.delete(t);
    }

    public void replace(Teacher a, Teacher b){
        b.setTeacherID(a.getTeacherID());
        tRepo.update(b);
        allTeachers.set(allTeachers.indexOf(a),b);
    }



    public Teacher getTeacherFromId(int id){
        for(Teacher current: allTeachers){
            if(current.getTeacherID() == id) {
                return current;
            }
        }
        return null;
    }
}

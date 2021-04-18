package dk.voresgruppe.bll;


import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.dal.TeacherRepository;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public class TeacherManager {
    private ObservableList<Teacher> allTeachers;
    private TeacherRepository tRepo = new TeacherRepository();

    public TeacherManager()  {
        this.allTeachers = tRepo.loadTeacher();
    }

    public void updateStudentAttendance(Student student, LocalDate date) throws SQLException {
        if(tRepo.hasStudentShowedUp(student, date)){  //hvis date allerede er i databasen for den student skal den fjernes og omvendt
            tRepo.removeFromShowedUp(student, date);
        }else tRepo.addToShowedUp(student,date);
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

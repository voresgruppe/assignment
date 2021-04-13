package dk.voresgruppe.bll;


import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.dal.TeacherRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TeacherManager {
    private List<Teacher> allTeachers;
    private TeacherRepository tRepo = new TeacherRepository();

    public TeacherManager()  {
        this.allTeachers = tRepo.loadTeacher();
    }

    public void updateStudentAttendance(Student student, LocalDate date){
        if(tRepo.hasStudentShowedUp(student, date)){  //hvis date allerede er i databasen for den student skal den fjernes og omvendt

        }
    }

    public List<Teacher> getAllTeachers(){
        return allTeachers;
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

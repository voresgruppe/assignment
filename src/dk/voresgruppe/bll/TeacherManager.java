package dk.voresgruppe.bll;


import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.dal.StudentRepository;
import dk.voresgruppe.dal.TeacherRepository;

import java.util.List;

public class TeacherManager {
    private List<Teacher> allTeachers;
    private TeacherRepository tRepo = new TeacherRepository();

    public TeacherManager() {
        this.allTeachers = this.tRepo.loadTeacher();
    }

    public List<Teacher> getAllTeachers(){
        return allTeachers;
    }
}

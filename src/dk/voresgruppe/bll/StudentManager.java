package dk.voresgruppe.bll;

import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.User;
import dk.voresgruppe.dal.StudentRepository;

import java.util.Date;
import java.util.List;

public class StudentManager {
    private List<Student> allStudents;
    private StudentRepository sRepo = new StudentRepository();

    public StudentManager() {
        this.allStudents = this.sRepo.loadStudents();
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }
}

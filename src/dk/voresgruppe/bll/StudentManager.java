package dk.voresgruppe.bll;

import dk.voresgruppe.be.*;
<<<<<<< HEAD
import dk.voresgruppe.dal.ScheduleRepository;
=======
import dk.voresgruppe.be.Module;
import dk.voresgruppe.dal.ScheduleRepositroy;
>>>>>>> parent of f0b2a9d (h)
import dk.voresgruppe.dal.StudentRepository;


import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> allStudents;
    private StudentRepository sRepo = new StudentRepository();
    private ScheduleRepository scheduleRepo = new ScheduleRepository();

    public StudentManager() {
        this.allStudents = this.sRepo.loadStudents();
        for(Student currentStudent: allStudents) {
            List<Schedule> schedules = new ArrayList<>();
            for(int i = 1; i<3; i++){
                Date date = new Date(i, 1, 2021);
                schedules.add(scheduleRepo.schedule(date));
            }
            currentStudent.setToShowUp(schedules);
        }
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

}

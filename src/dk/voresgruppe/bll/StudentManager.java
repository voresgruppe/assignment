package dk.voresgruppe.bll;

import dk.voresgruppe.be.*;
import dk.voresgruppe.dal.ScheduleRepositroy;
import dk.voresgruppe.dal.StudentRepository;


import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> allStudents;
    private StudentRepository sRepo = new StudentRepository();
    private ScheduleRepositroy scheduleRepo = new ScheduleRepositroy();

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

package dk.voresgruppe.bll;

import dk.voresgruppe.be.*;
import dk.voresgruppe.dal.ScheduleRepository;
import dk.voresgruppe.dal.StudentRepository;


import java.util.List;

public class StudentManager {
    private List<Student> allStudents;
    private StudentRepository sRepo = new StudentRepository();
    private ScheduleRepository scheduleRepo = new ScheduleRepository();

    public StudentManager() {
        this.allStudents = this.sRepo.loadStudents();
        for(Student currentStudent: allStudents) {
            List<Schedule> schedules = scheduleRepo.weekSchedules();
            currentStudent.setWeekSchedule(schedules);
        }
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

}

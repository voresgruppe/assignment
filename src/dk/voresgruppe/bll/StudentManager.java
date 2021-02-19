package dk.voresgruppe.bll;

import dk.voresgruppe.be.*;
import dk.voresgruppe.dal.ScheduleRepository;
import dk.voresgruppe.dal.StudentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.List;

public class StudentManager {
    private ObservableList<Student> studentsObservableList;
    private List<Student> allStudents;
    private StudentRepository studentRepository;
    //private StudentRepository sRepo = new StudentRepository();
    private ScheduleRepository scheduleRepo = new ScheduleRepository();

    public StudentManager() {
        studentsObservableList = FXCollections.observableArrayList();
        studentRepository = new StudentRepository();
        studentsObservableList.addAll(studentRepository.loadStudents());

        //this.studentsObservableList = this.sRepo.loadStudents();
       // for(Student currentStudent: studentsObservableList) {
           // List<Schedule> schedules = scheduleRepo.weekSchedules();
          //  currentStudent.setWeekSchedule(schedules);

        }


    public ObservableList<Student> getallStudents_OBS(){
        return studentsObservableList;
    }


}

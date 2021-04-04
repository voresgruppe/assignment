package dk.voresgruppe.bll;

import dk.voresgruppe.be.*;
import dk.voresgruppe.dal.ScheduleRepository;
import dk.voresgruppe.dal.StudentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.Comparator;
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

        //order list by AbsencePercentage
        Comparator<Student> comparator = Comparator.comparingDouble(Student::getAbsencePercentage);
        comparator = comparator.reversed();
        FXCollections.sort(studentsObservableList, comparator);

        }


    public ObservableList<Student> getallStudents_OBS(){
        return studentsObservableList;
    }

    public ObservableList<Student> searchStudent(String filter, ObservableList<Student> students) {
        ObservableList<Student> returnList = FXCollections.observableArrayList();

        for (Student s : students){
            if(s.getFullName().toLowerCase().contains(filter.toLowerCase()) || s.getCurrentCourse().toLowerCase().contains(filter.toLowerCase())){
                if(!returnList.contains(s)){
                    returnList.add(s);
                }
            }
        }
        return returnList;
    }
}

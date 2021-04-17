package dk.voresgruppe.bll;

import dk.voresgruppe.be.*;
import dk.voresgruppe.dal.StudentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.Class;
import java.sql.SQLException;
import java.util.Comparator;

public class StudentManager {
    private ObservableList<Student> studentsObservableList;
    private StudentRepository studentRepository;
    private ObservableList<Student> studentsFromTeacher;
    private ClassManager cMan = new ClassManager();

    public StudentManager() throws SQLException {
        studentsObservableList = FXCollections.observableArrayList();
        studentRepository = new StudentRepository();
        studentsObservableList.addAll(studentRepository.loadStudents());
        Comparator<Student> comparator = Comparator.comparingDouble(Student::getAbsencePercentage);
        comparator = comparator.reversed();
        FXCollections.sort(studentsObservableList, comparator);
    }

    public ObservableList<Student> getallStudents_OBS(){
        return studentsObservableList;
    }

    public ObservableList<Student> getStudentsFromTeacher(Teacher teacher) throws SQLException {
        studentsFromTeacher = studentRepository.loadStudentsWithTeacher(teacher);
        return studentsFromTeacher;
    }

    public ObservableList<Student> searchStudent(String filter, ObservableList<Student> students) {
        ObservableList<Student> returnList = FXCollections.observableArrayList();

        for (Student s : students){
            if(s.getFullName().toLowerCase().contains(filter.toLowerCase()) || cMan.getClassFromID(s.getClassID()).getClassName().toLowerCase().contains(filter.toLowerCase())){
                if(!returnList.contains(s)){
                    returnList.add(s);
                }
            }
        }
        return returnList;
    }
}

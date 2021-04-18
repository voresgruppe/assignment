package dk.voresgruppe.bll;

import dk.voresgruppe.be.*;
import dk.voresgruppe.dal.StudentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Comparator;

public class StudentManager {
    private ObservableList<Student> studentsObservableList;
    private StudentRepository sRepo= new StudentRepository();
    private ObservableList<Student> studentsFromTeacher;
    private ClassManager cMan = new ClassManager();

    public StudentManager() {
        studentsObservableList = sRepo.loadStudents();
        /*
        Comparator<Student> comparator = Comparator.comparingDouble(Student::getAbsencePercentage);
        comparator = comparator.reversed();
        FXCollections.sort(studentsObservableList, comparator);
         */
    }

    public ObservableList<Student> getallStudents_OBS(){
        return studentsObservableList;
    }

    public void add(Student s) {
        int iD = sRepo.addStudent(s);
        s.setStudentID(iD);
        studentsObservableList.add(s);
    }

    public void delete(Student s) {
        studentsObservableList.remove(s);
        sRepo.delete(s);
    }

    public void replace(Student a, Student b){
        b.setStudentID(a.getStudentID());
        sRepo.update(b);
        studentsObservableList.set(studentsObservableList.indexOf(a),b);
    }

    public ObservableList<Student> getStudentsFromTeacher(Teacher teacher) throws SQLException {
        studentsFromTeacher = sRepo.loadStudentsWithTeacher(teacher);
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

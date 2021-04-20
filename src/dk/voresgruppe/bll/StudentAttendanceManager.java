package dk.voresgruppe.bll;

import dk.voresgruppe.be.StudentAttendance;
import dk.voresgruppe.dal.StudentAttendanceRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentAttendanceManager {
    private ObservableList<StudentAttendance> allStudentAttendances;
    private StudentAttendanceRepository saRepo = new StudentAttendanceRepository();

    public StudentAttendanceManager() {allStudentAttendances = saRepo.loadStudentAttendances();
    }

    public ObservableList<StudentAttendance> getAllStudentAttendances() {
        return allStudentAttendances;
    }

    public void add(StudentAttendance sa){
        sa.setStudentAttendanceID(saRepo.add(sa));
        allStudentAttendances.add(sa);
    }

    public void delete(StudentAttendance sa){
        allStudentAttendances.remove(sa);
        saRepo.delete(sa);
    }

    public void replace(StudentAttendance a, StudentAttendance b){
        b.setStudentAttendanceID(a.getStudentAttendanceID());
        saRepo.update(b);
        allStudentAttendances.set(allStudentAttendances.indexOf(a),b);
    }

    public StudentAttendance getStudentAttendanceFromID(int studentAttendanceID){
        for(StudentAttendance studentAttendance: allStudentAttendances){
            if(studentAttendance.getStudentAttendanceID() == studentAttendanceID){
                return studentAttendance;
            }
        }
        return null;
    }

    public ObservableList<StudentAttendance> getStudentAttendancesFromStudentID(int studentID){
        ObservableList<StudentAttendance> returnList = FXCollections.observableArrayList();
        for(StudentAttendance current: allStudentAttendances){
            if(current.getStudentID() == studentID){
                returnList.add(current);
            }
        }
        return returnList;
    }
}

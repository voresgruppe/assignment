package dk.voresgruppe.bll;

import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.StudentAttendance;
import dk.voresgruppe.dal.StudentAttendanceRepository;
import dk.voresgruppe.util.UserError;
import dk.voresgruppe.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentAttendanceManager {
    private ObservableList<StudentAttendance> allStudentAttendances;
    private StudentAttendanceRepository saRepo = new StudentAttendanceRepository();
    private Utils utils = new Utils();

    public StudentAttendanceManager() {allStudentAttendances = saRepo.loadStudentAttendances();
    }

    public ObservableList<StudentAttendance> getAllStudentAttendances() {
        return allStudentAttendances;
    }

    public void add(StudentAttendance sa){
        if (!checkIfAlreadyExists(sa)) {
            sa.setStudentAttendanceID(saRepo.add(sa));
            allStudentAttendances.add(sa);
            UserError.showError("fremmøde Regristreret","gennemført");
        }
        else {
            UserError.showError("fremmøde er allerede Regristreret"," ikke gennemført");
        }
    }

    public void delete(StudentAttendance sa){
        saRepo.deleteFromStudentIDAndDate(sa);
        allStudentAttendances.remove(sa);
        UserError.showError("fremmøde slettet", "gennemført");
    }

    public void replace(StudentAttendance a, StudentAttendance b){
        b.setStudentAttendanceID(a.getStudentAttendanceID());
        saRepo.update(b);
        allStudentAttendances.set(allStudentAttendances.indexOf(a),b);
    }

    public boolean checkIfAlreadyExists(StudentAttendance sa){
        for(StudentAttendance current: allStudentAttendances){
            if(current.getStudentID() == sa.getStudentID() && utils.checkIfDatesMatch(current.getAttendanceDate(), sa.getAttendanceDate())){
                return true;
            }
        }
        return false;
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

    public boolean didStudentShowUp(Student s, Date d){
        if( saRepo.hasStudentShowedUp(s,d)){
            return true;
        }else return false;
    }
}

package dk.voresgruppe.dal;

import dk.voresgruppe.be.Class;
import dk.voresgruppe.be.StudentAttendance;
import dk.voresgruppe.dal.db.DatabaseConnector;
import dk.voresgruppe.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class StudentAttendanceRepository {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    private Connection connect = null;
    private Utils utils = new Utils();

    public StudentAttendanceRepository() {
        try {
            connect = databaseConnector.getConnection();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<StudentAttendance> loadStudentAttendances(){
        try {
            ObservableList<StudentAttendance> studentAttendances = FXCollections.observableArrayList();
            String query = "SELECT * FROM StudentAttendance ORDER BY StudentAttendanceID";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                StudentAttendance sa = new StudentAttendance(resultSet.getInt("studentID"), utils.dateFromString(resultSet.getString("AttendaceDate")));
                sa.setStudentAttendanceID(resultSet.getInt("StudentAttendanceID"));
                studentAttendances.add(sa);
            }

            return studentAttendances;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int add(StudentAttendance sa) {
        int returnId = -1;
        try {
            System.out.println(sa.getCourseID());
            String query = "INSERT INTO StudentAttendance (studentID, courseID, AttendaceDate) VALUES ('" +sa.getStudentID()+"', '"+sa.getCourseID()+"', '"+sa.getAttendanceDate()+"' );";
            PreparedStatement preparedStatement = connect.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                returnId = generatedKeys.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return returnId;
    }

    public void delete(StudentAttendance sa) {
        try {
            int id = sa.getStudentAttendanceID();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM StudentAttendance WHERE StudentAttendanceID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(StudentAttendance sa){
        try {
            String query = "UPDATE StudentAttendance SET studentID = '" +sa.getStudentID()+"', AttendaceDate = '"+sa.getAttendanceDate()+"' WHERE StudentAttendanceID = '" +sa.getStudentAttendanceID()+"'";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

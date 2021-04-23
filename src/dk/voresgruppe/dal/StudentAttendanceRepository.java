package dk.voresgruppe.dal;

import dk.voresgruppe.be.Class;
import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.StudentAttendance;
import dk.voresgruppe.dal.db.DatabaseConnector;
import dk.voresgruppe.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class StudentAttendanceRepository {
    private DatabaseConnector databaseConnector;
    private Utils utils = new Utils();

    public StudentAttendanceRepository() {
        databaseConnector = new DatabaseConnector();
    }

    public ObservableList<StudentAttendance> loadStudentAttendances(){
        try (Connection connect = databaseConnector.getConnection()){
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
        try (Connection connect = databaseConnector.getConnection()){
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
        try (Connection connect = databaseConnector.getConnection()){
            int id = sa.getStudentAttendanceID();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM StudentAttendance WHERE StudentAttendanceID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteFromStudentIDAndDate(StudentAttendance sa) {
        try (Connection connect = databaseConnector.getConnection()){
            int studentID = sa.getStudentID();
            int courseID = sa.getCourseID();
            Date date = sa.getAttendanceDate();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM StudentAttendance WHERE studentID = ? AND courseID = ? AND attendaceDate = '" + date + "'");
            preparedStatement.setInt(1,studentID);
            preparedStatement.setInt(2,courseID);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(StudentAttendance sa){
        try (Connection connect = databaseConnector.getConnection()){
            String query = "UPDATE StudentAttendance SET studentID = '" +sa.getStudentID()+"', AttendaceDate = '"+sa.getAttendanceDate()+"' WHERE StudentAttendanceID = '" +sa.getStudentAttendanceID()+"'";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean hasStudentShowedUp(Student s, dk.voresgruppe.be.Date date) {
        try(Connection connect = databaseConnector.getConnection()){
            String query = "SELECT s.Username, sa.courseID FROM StudentAttendance sa\n" +
                    "JOIN Student s ON sa.studentID = s.StudentID\n" +
                    "WHERE s.Username = '" + s.getStudentLogin().getUserName() + "' AND sa.attendaceDate = '" + date + "';";

            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                if (rs.getString("Username").equals(s.getStudentLogin().getUserName())) {
                    return true;
                }
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}

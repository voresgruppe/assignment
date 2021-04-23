package dk.voresgruppe.dal;

import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.be.User;
import dk.voresgruppe.dal.db.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class TeacherRepository {

    DatabaseConnector databaseConnector;
    private Connection connect;

    public TeacherRepository() {
        databaseConnector = new DatabaseConnector();
    }

    public ObservableList<Teacher> loadTeacher() {

        try (Connection connect = databaseConnector.getConnection()){
            ObservableList<Teacher> allTeachers = FXCollections.observableArrayList();
            String query = "SELECT * FROM Teacher ORDER BY TeacherID";
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                User teacherUser = new User(rs.getString("Username"), rs.getString("Password"));
                Teacher t = new Teacher(rs.getString("Fname"),rs.getString("Lname"),teacherUser);
                t.setTeacherID(rs.getInt("TeacherID"));
                allTeachers.add(t);
            }
            return allTeachers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public int addTeacher(Teacher t){
        int returnID = -1;
        try (Connection connect = databaseConnector.getConnection()){
            String query = "INSERT INTO Teacher(Fname, Lname, Username, [Password]) VALUES ('" + t.getFirstName() +"', '"+t.getLastName()+"', '"+t.getTeacherLogin().getUserName()+"', '"+t.getTeacherLogin().getPassword()+"' );";
            PreparedStatement preparedStatement = connect.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                returnID = generatedKeys.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return returnID;
    }

    public void delete(Teacher t) {
        try (Connection connect = databaseConnector.getConnection()){
            int id = t.getTeacherID();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM Teacher WHERE TeacherID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(Teacher t){
        try (Connection connect = databaseConnector.getConnection()){
            String query = "UPDATE Teacher SET Fname = '" +t.getFirstName()+"', Lname = '"+t.getLastName()+"', Username = '"+t.getTeacherLogin().getUserName()+"', [Password]= '"+t.getTeacherLogin().getPassword()+"' WHERE TeacherID = '" +t.getTeacherID()+"'";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private int getStudentIDFromDB(Student s) {
        try (Connection connect = databaseConnector.getConnection()){
            String sql = "SELECT StudentID FROM Student\n" +
                    "WHERE Username = '" + s.getStudentLogin().getUserName() + "';";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int returnValue = rs.getInt("StudentID");
                return returnValue;
            }
            return -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }


}

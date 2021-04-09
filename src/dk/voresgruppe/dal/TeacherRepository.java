package dk.voresgruppe.dal;

import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.be.User;
import dk.voresgruppe.dal.db.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class TeacherRepository {

    DatabaseConnector dbConnector = new DatabaseConnector();
    private Connection connect;

    public TeacherRepository() {
        try {
            connect = dbConnector.getConnection();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Teacher> loadTeacher() throws SQLException {
        ObservableList<Teacher> allTeachers = FXCollections.observableArrayList();
        String query = "SELECT * FROM Teacher ORDER BY TeacherID";
        Statement statement = connect.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
            User teacherUser = new User(rs.getString("Username"), rs.getString("Password"));
            Teacher t = new Teacher(rs.getString("Fname"),rs.getString("Lname"),teacherUser);
            allTeachers.add(t);
        }
        return allTeachers;
    }

    public boolean hasStudentShowedUp(Student s, LocalDate date){
        String query = "SELECT * FROM StudentAttendance;";

        return true;
    }

}

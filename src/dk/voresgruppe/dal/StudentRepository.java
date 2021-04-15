package dk.voresgruppe.dal;

import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.User;
import dk.voresgruppe.dal.db.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class StudentRepository {

    DatabaseConnector dbConnector = new DatabaseConnector();
    private Connection connect;

    public StudentRepository() {
        try {
            connect = dbConnector.getConnection();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Student> loadStudents() throws SQLException {
        ObservableList<Student> allStudents = FXCollections.observableArrayList();
        String query = "SELECT * FROM Student ORDER BY StudentID";
        Statement statement = connect.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
            User studentUser = new User(rs.getString("Username"), rs.getString("Password"));

            Student s = new Student(rs.getString("Fname"),rs.getString("Lname"),getEducationNameFromStudentID(rs.getInt("StudentID")),studentUser);
            allStudents.add(s);
        }
        return allStudents;
    }

    private String getEducationNameFromStudentID(int studentID) throws SQLException {
        String sql = "SELECT\te.Name\n" +
                "FROM Education e\n" +
                "\t JOIN Class c ON e.EducationID = c.EducationID\n" +
                "\t JOIN Student s ON c.ClassID = s.ClassID\n" +
                "WHERE s.StudentID = " + studentID;
        Statement statement = connect.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            return rs.getString("Name");
        }
        return "-1";
    }

    private Student stringLineToStudent(String line) {
        String[] arrStudent = line.split(",");
        String fName = arrStudent[0];
        String lName = arrStudent[1];
        String course = arrStudent[3];
        User studentUser = new User(arrStudent[4], arrStudent[5]);
        return new Student(fName, lName, course, studentUser);
    }





}

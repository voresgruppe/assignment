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
        return getStudents(allStudents, query);
    }

    public ObservableList<Student> loadStudentsWithTeacher(Teacher teacher) throws SQLException {
        ObservableList<Student> returnList = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT s.Fname, s.Lname, s.Username, s.[Password], s.StudentID FROM Student s\n" +
                "  JOIN StudentAttendance sa ON s.StudentID = sa.studentID\n" +
                "  JOIN Course c ON sa.courseID = c.CourseID\n" +
                "  JOIN Teacher t ON c.TeacherID = t.TeacherID\n" +
                "  WHERE t.Username = '" + teacher.getTeacherLogin().getUserName() + "';";
        return getStudents(returnList, sql);
    }

    private ObservableList<Student> getStudents(ObservableList<Student> returnList, String sql) throws SQLException {
        Statement statement = connect.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            User studentUser = new User(rs.getString("Username"), rs.getString("Password"));

            Student s = new Student(rs.getString("Fname"),rs.getString("Lname"),getEducationNameFromStudentID(rs.getInt("StudentID")),studentUser);
            returnList.add(s);
        }
        return returnList;
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
}

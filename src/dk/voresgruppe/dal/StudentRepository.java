package dk.voresgruppe.dal;

import dk.voresgruppe.be.Date;
import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.be.User;
import dk.voresgruppe.dal.db.DatabaseConnector;
import dk.voresgruppe.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    DatabaseConnector dbConnector = new DatabaseConnector();
    private Connection connect;
    private Utils utils =  new Utils();

    public StudentRepository() {
        try {
            connect = dbConnector.getConnection();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Student> loadStudents() {
        ObservableList<Student> allStudents = FXCollections.observableArrayList();
        String query = "SELECT * FROM Student ORDER BY StudentID";
        return getStudents(allStudents, query);
    }

    public ObservableList<Student> loadStudentsWithTeacher(Teacher teacher) {
        ObservableList<Student> returnList = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT s.Fname, s.Lname, s.Username, s.[Password], s.StudentID, s.ClassID FROM Student s\n" +
                "  JOIN StudentAttendance sa ON s.StudentID = sa.studentID\n" +
                "  JOIN Course c ON sa.courseID = c.CourseID\n" +
                "  JOIN Teacher t ON c.TeacherID = t.TeacherID\n" +
                "  WHERE t.Username = '" + teacher.getTeacherLogin().getUserName() + "';";
        return getStudents(returnList, sql);
    }

    private ObservableList<Student> getStudents(ObservableList<Student> returnList, String sql) {
        try {
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User studentUser = new User(rs.getString("Username"), rs.getString("Password"));
                Student s = new Student(rs.getInt("ClassID"), rs.getString("Fname"), rs.getString("Lname"), studentUser);
                s.setStudentID(rs.getInt("StudentID"));
                /*
                List<dk.voresgruppe.be.Date> dates = new ArrayList<>();
                getStudentDaysShowedUp(s).forEach(date -> dates.add(new dk.voresgruppe.be.Date(date.getDay(), date.getMonth(), date.getYear())));
                s.setShowedUp(dates);
                */
                returnList.add(s);


            }
            return returnList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int addStudent(Student s) {
        int returnId = -1;
        try {
            String query = "INSERT INTO Student(Fname, Lname, Username, [Password], ClassID) VALUES ('"+ s.getFirstName() +"', '"+s.getLastName()+"', '"+s.getStudentLogin().getUserName()+"', '"+s.getStudentLogin().getPassword()+"', '"+s.getClassID()+"' );";
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

    public void delete(Student s) {
        try {
            int id = s.getStudentID();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM Student WHERE StudentID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(Student s){
        try {
            String query = "UPDATE Student SET Fname = '" +s.getFirstName()+"', Lname = '"+s.getLastName()+"', Username = '"+s.getStudentLogin().getUserName()+"', [Password]= '"+s.getStudentLogin().getPassword()+"', ClassID= '"+s.getClassID()+"' WHERE StudentID = '" +s.getStudentID()+"'";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /***
     * returns a list of days that the student has marked as participating in.
     * @param student the student whose participation we want to find
     * @return List of days student has been at the establishment
     */
    private List<Date> getStudentDaysShowedUp(Student student) {
        try {
            String query = "SELECT * FROM StudentAttendance WHERE StudentID=?";
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.setInt(1, student.getStudentID());
            ResultSet rs = pstmt.executeQuery();
            List<Date> dates = new ArrayList<>();
            while (rs.next()) {
                dates.add(utils.dateFromString(rs.getString("attendaceDate")));
            }
            return dates;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
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

    public void showedUpThisDay(Student s, Date d, int courseID){
        try {
            if(!doesAttendanceExist(s,d,courseID)) {
                String sql = "INSERT INTO StudentAttendance(studentID, courseID, attendaceDate) VALUES (?,?,'" + d + "');";
                PreparedStatement preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setInt(1, s.getStudentID());
                preparedStatement.setInt(2, courseID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean doesAttendanceExist(Student s, Date d, int courseID){
        try{
            String sql = "SELECT * FROM StudentAttendance\n" +
                    "WHERE studentID = " + s.getStudentID() + " AND courseID = " + courseID + " AND attendaceDate = '" + d + "';";
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

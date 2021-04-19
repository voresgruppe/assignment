package dk.voresgruppe.dal;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.be.Course;
import dk.voresgruppe.be.Date;
import dk.voresgruppe.dal.db.DatabaseConnector;
import dk.voresgruppe.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CourseRepository {

    private DatabaseConnector databaseConnector = new DatabaseConnector();
    private Connection connect = null;
    private Utils utils = new Utils();


    public CourseRepository() {
        try {
            connect = databaseConnector.getConnection();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Course> loadCourses() {
        try {
            ObservableList<Course> courses = FXCollections.observableArrayList();
            String query = "SELECT * FROM Course ORDER BY CourseID";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                Course c = new Course(resultSet.getInt("TeacherID"),resultSet.getString("Name"));
                c.setCourseID(resultSet.getInt("CourseID"));
                if (resultSet.getString("EndDate")!=null && !resultSet.getString("EndDate").equals("null")) {
                    c.setEndDate(utils.dateFromString(resultSet.getString("EndDate")));
                }
                if (resultSet.getString("StartDate")!=null) {
                    c.setStartDate(utils.dateFromString(resultSet.getString("StartDate")));
                }
                courses.add(c);
            }

            return courses;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addCourse(Course c) {
        int returnId = -1;
        try {
            String query = "INSERT INTO Course (TeacherID, Name, EndDate, StartDate) VALUES ('" +c.getTeacherID()+"', '"+c.getName()+"', '"+c.getEndDate()+"', '"+c.getStartDate()+"' );";
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

    public void delete(Course c) {
        try {
            int id = c.getCourseID();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM Course WHERE CourseId = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Course c){
        try {
            String query = "UPDATE Course SET TeacherID = '" +c.getTeacherID()+"', Name = '"+c.getName()+"', EndDate = '"+c.getEndDate()+"', StartDate= '"+c.getStartDate()+"' WHERE CourseId = " +c.getCourseID()+";";
            PreparedStatement preparedStatement = null;
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}

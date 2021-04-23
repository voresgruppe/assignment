package dk.voresgruppe.dal;

import dk.voresgruppe.be.Class;
import dk.voresgruppe.dal.db.DatabaseConnector;
import dk.voresgruppe.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ClassRepository {

    private DatabaseConnector databaseConnector;
    private Utils utils = new Utils();

    public ClassRepository() {
        databaseConnector = new DatabaseConnector();
    }

    public ObservableList<Class> loadClasses() {
        try (Connection connect = databaseConnector.getConnection()) {
            ObservableList<Class> classes = FXCollections.observableArrayList();
            String query = "SELECT * FROM Class ORDER BY ClassID";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                Class c = new Class(resultSet.getInt("EducationID"),resultSet.getString("ClassName"));
                c.setClassID(resultSet.getInt("ClassID"));
                if (resultSet.getString("EndDate")!=null) {
                    c.setEndDate(utils.dateFromString(resultSet.getString("EndDate")));
                }
                if (resultSet.getString("StartDate")!=null) {
                    c.setStartDate(utils.dateFromString(resultSet.getString("StartDate")));
                }
                if(String.valueOf(resultSet.getInt("ClassID"))!= null){
                    c.setScheduleID(resultSet.getInt("ClassID"));
                }
                if(String.valueOf(resultSet.getInt("scheduleID"))!= null){
                    c.setScheduleID(resultSet.getInt("scheduleID"));
                }
                classes.add(c);
            }

            return classes;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addClass(Class c) {
        int returnId = -1;
        try (Connection connect = databaseConnector.getConnection()) {
            String query = "INSERT INTO Class (EducationID, ClassName, EndDate, StartDate, scheduleID) VALUES ('" +c.getEducationID()+"', '"+c.getClassName()+"', '"+c.getEndDate()+"', '"+c.getStartDate()+"', '"+c.getScheduleID()+"' );";
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

    public void delete(Class c) {
        try (Connection connect = databaseConnector.getConnection()){
            int id = c.getClassID();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM Class WHERE ClassID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Class c){
        try (Connection connect = databaseConnector.getConnection()){
            String query = "UPDATE Class SET EducationID = '" +c.getEducationID()+"', ClassName = '"+c.getClassName()+"', EndDate = '"+c.getEndDate()+"', StartDate= '"+c.getStartDate()+"', scheduleID= '"+c.getScheduleID()+"' WHERE ClassID = '" +c.getClassID()+"'";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

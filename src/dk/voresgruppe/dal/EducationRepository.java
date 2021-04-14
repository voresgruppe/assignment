package dk.voresgruppe.dal;

import dk.voresgruppe.be.Education;
import dk.voresgruppe.dal.db.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EducationRepository {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    private Connection connect = null;

    public EducationRepository() {
        try {
            connect = databaseConnector.getConnection();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Education> loadEducations() {
        try {
            ObservableList<Education> educations = FXCollections.observableArrayList();
            String query = "SELECT * FROM Education ORDER BY EducationId";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                Education e = new Education(resultSet.getString("Name"));
                e.setiD(resultSet.getInt("EducationId"));
                educations.add(e);
            }
            return educations;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addEducation(Education e) {
        int returnId = -1;
        try {
            String query = "INSERT INTO Education(Name) VALUES ('" + e.getName() +"' );";
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

    public void delete(Education e) {
        try {
            int id = e.getiD();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM Education WHERE EducationID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void update(Education e){
        try {
            String query = "UPDATE Education SET Name = '" + e.getName() +"' WHERE EducationID = '" + e.getiD() + "'";
            PreparedStatement preparedStatement = null;
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

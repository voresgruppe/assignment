package dk.voresgruppe.dal;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.dal.db.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class AdministratorRepository {

    DatabaseConnector databaseConnector;


    public AdministratorRepository() {
        databaseConnector = new DatabaseConnector();
    }

    public ObservableList<Administrator> loadAdministrators() {
        try (Connection connect = databaseConnector.getConnection()) {
            ObservableList<Administrator> administrators = FXCollections.observableArrayList();
            String query = "SELECT * FROM Administrator ORDER BY AdministratorId";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                Administrator a = new Administrator(resultSet.getString("Fname"),resultSet.getString("Lname"),resultSet.getString("Username"),resultSet.getString("Password"));
                a.setId(resultSet.getInt("AdministratorId"));
                administrators.add(a);
            }

            return administrators;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addAdministrator(Administrator a) {
        int returnId = -1;
        try (Connection connect = databaseConnector.getConnection()){
            String query = "INSERT INTO Administrator(Fname, Lname, Username, [Password]) VALUES ('" + a.getFirstname() +"', '"+a.getLastname()+"', '"+a.getUsername()+"', '"+a.getPassword()+"' );";
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

    public void delete(Administrator a) {
        try (Connection connect = databaseConnector.getConnection()){
            int id = a.getId();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM Administrator WHERE AdministratorId = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Administrator a){
        try(Connection connect = databaseConnector.getConnection()) {
            String query = "UPDATE Administrator SET Fname = '" +a.getFirstname()+"', Lname = '"+a.getLastname()+"', Username = '"+a.getUsername()+"', [Password]= '"+a.getPassword()+"' WHERE AdministratorId = '" +a.getId()+"'";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}

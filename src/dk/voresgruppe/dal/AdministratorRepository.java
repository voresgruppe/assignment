package dk.voresgruppe.dal;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.dal.db.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class AdministratorRepository {

    DatabaseConnector databaseConnector = new DatabaseConnector();
    private Connection connect = null;

    public AdministratorRepository() {
        try {
            connect = databaseConnector.getConnection();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads all Tracks from MySQL table 'tracks' and make Track Objects from them
     * @return ObservableList of Track objects
     */
    public ObservableList<Administrator> loadAdministrators() {
        try {
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
        try {
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
        try {
            int id = a.getId();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM Administrator WHERE AdministratorId = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.print("delete");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Administrator a){
        String query = "UPDATE Administrator SET Fname = '" +a.getFirstname()+"', Lname = '"+a.getLastname()+"', Username = '"+a.getUsername()+"', [Password]= '"+a.getPassword()+"' WHERE AdministratorId = '" +a.getId()+"'";
    }


}

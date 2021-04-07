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
                Administrator a = new Administrator(resultSet.getInt("AdministratorId"),resultSet.getString("Fname"),resultSet.getString("Lname"),resultSet.getString("Username"),resultSet.getString("Password"));
                administrators.add(a);
            }

            return administrators;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

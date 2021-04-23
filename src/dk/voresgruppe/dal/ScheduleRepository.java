package dk.voresgruppe.dal;

import dk.voresgruppe.be.Schedule;
import dk.voresgruppe.dal.db.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ScheduleRepository {
    private DatabaseConnector databaseConnector;


    public ScheduleRepository() {
       databaseConnector = new DatabaseConnector();
    }

    public ObservableList<Schedule> loadSchedules(){
        try(Connection connect = databaseConnector.getConnection()){
            ObservableList<Schedule> schedules = FXCollections.observableArrayList();
            String query = "SELECT * FROM Schedule ORDER BY scheduleID";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Schedule s = new Schedule(resultSet.getString("scheduleName"));
                s.setMonday(resultSet.getInt("monday"));
                s.setTuesday(resultSet.getInt("tuesday"));
                s.setWednesday(resultSet.getInt("wednesday"));
                s.setThursday(resultSet.getInt("thursday"));
                s.setFriday(resultSet.getInt("friday"));
                s.setScheduleID(resultSet.getInt("scheduleID"));

                schedules.add(s);
            }
            return schedules;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public int addSchedule(Schedule s) {
        int returnId = -1;
        try (Connection connect = databaseConnector.getConnection()){
            String query = "INSERT INTO Schedule (monday, tuesday, wednesday, thursday, friday, scheduleName) VALUES ('" +s.getMonday()+"', '"+s.getTuesday()+"', '"+s.getWednesday()+"', '"+s.getThursday()+"', '"+s.getFriday()+"', '"+ s.getScheduleName()+"' );";
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

    public void delete(Schedule s) {
        try (Connection connect = databaseConnector.getConnection()){
            int id = s.getScheduleID();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM Schedule WHERE scheduleID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Schedule s){
        try (Connection connect = databaseConnector.getConnection()){
            String query = "UPDATE Schedule SET monday = '"+s.getMonday()+"', tuesday = '"+s.getTuesday()+"', wednesday= '"+s.getWednesday()+"', thursday= '"+s.getThursday()+"', friday= '"+s.getFriday()+"', scheduleName= '"+s.getScheduleName()+"' WHERE scheduleID = " +s.getScheduleID()+";";
            PreparedStatement preparedStatement = null;
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

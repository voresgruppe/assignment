package dk.voresgruppe.dal;

import dk.voresgruppe.be.Schedule;
import dk.voresgruppe.dal.db.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ScheduleRepository {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    private Connection connect = null;

    public ScheduleRepository() {
        try {
            connect = databaseConnector.getConnection();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Schedule> loadSchedules(){
        try{
            ObservableList<Schedule> schedules = FXCollections.observableArrayList();
            String query = "SELECT * FROM Schedule ORDER BY scheduleID";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Schedule s = new Schedule(resultSet.getString("scheduleName"));
                if(String.valueOf(resultSet.getInt("monday")) != null){
                    s.setMonday(resultSet.getInt("monday"));
                }
                if(String.valueOf(resultSet.getInt("tuesday")) != null){
                    s.setTuesday(resultSet.getInt("tuesday"));
                }
                if(String.valueOf(resultSet.getInt("wednesday")) != null){
                    s.setWednesday(resultSet.getInt("wednesday"));
                }
                if(String.valueOf(resultSet.getInt("thursday")) != null){
                    s.setThursday(resultSet.getInt("thursday"));
                }
                if(String.valueOf(resultSet.getInt("friday")) != null){
                    s.setFriday(resultSet.getInt("friday"));
                }
                if(String.valueOf(resultSet.getInt("scheduleID")) != null){
                    s.setScheduleID(resultSet.getInt("scheduleID"));
                }
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
        try {
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
        try {
            int id = s.getScheduleID();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM Schedule WHERE scheduleID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Schedule s){
        try {
            String query = "UPDATE Schedule SET monday = '"+s.getMonday()+"', tuesday = '"+s.getTuesday()+"', wednesday= '"+s.getWednesday()+"', thursday= '"+s.getThursday()+"', friday= '"+s.getFriday()+"', scheduleName= '"+s.getScheduleName()+"' WHERE scheduleID = " +s.getScheduleID()+";";
            PreparedStatement preparedStatement = null;
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

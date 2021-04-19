package dk.voresgruppe.be;


import javafx.beans.property.SimpleStringProperty;

public class Schedule {
    private String scheduleName;
    private int monday;
    private int tuesday;
    private int wednesday;
    private int thursday;
    private int friday;
    private int scheduleID;

    public Schedule(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public SimpleStringProperty getScheduleNameProperty(){
        return new SimpleStringProperty(String.valueOf(scheduleName));
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public int getMonday() {
        return monday;
    }

    public void setMonday(int monday) {
        this.monday = monday;
    }

    public int getTuesday() {
        return tuesday;
    }

    public void setTuesday(int tuesday) {
        this.tuesday = tuesday;
    }

    public int getWednesday() {
        return wednesday;
    }

    public void setWednesday(int wednesday) {
        this.wednesday = wednesday;
    }

    public int getThursday() {
        return thursday;
    }

    public void setThursday(int thursday) {
        this.thursday = thursday;
    }

    public int getFriday() {
        return friday;
    }

    public void setFriday(int friday) {
        this.friday = friday;
    }

    public int getScheduleID() {
        return scheduleID;
    }
    public SimpleStringProperty getScheduleIDProperty(){
        return new SimpleStringProperty(String.valueOf(scheduleID));
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }
}

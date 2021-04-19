package dk.voresgruppe.be;

import javafx.beans.property.SimpleStringProperty;

public class Class {

    private int classID;
    private int educationID;
    private int scheduleID;
    private String className;
    private Date startDate;
    private Date endDate;

    public Class(int educationID, String className) {
        this.educationID = educationID;
        this.className = className;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public int getClassID() {
        return classID;
    }

    public SimpleStringProperty getIDProperty(){
        return new SimpleStringProperty(String.valueOf(classID));
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getEducationID() {
        return educationID;
    }
    public SimpleStringProperty getEducationIDProperty(){
        return new SimpleStringProperty(String.valueOf(educationID));
    }


    public void setEducationID(int educationID) {
        this.educationID = educationID;
    }

    public String getClassName() {
        return className;
    }
    public SimpleStringProperty getClassNameProperty(){
        return new SimpleStringProperty(String.valueOf(className));
    }


    public void setClassName(String className) {
        this.className = className;
    }

    public Date getStartDate() {
        return startDate;
    }
    public SimpleStringProperty getStartDateProperty(){
        return new SimpleStringProperty(String.valueOf(startDate));
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public SimpleStringProperty getEndDateProperty(){
        return new SimpleStringProperty(String.valueOf(endDate));
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

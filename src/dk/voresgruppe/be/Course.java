package dk.voresgruppe.be;

import javafx.beans.property.SimpleStringProperty;

public class Course {

    private int courseID;
    private int teacherID;
    private String name;
    private Date startDate;
    private Date endDate;

    public Course(int courseID, int teacherID, String name) {
        this.courseID = courseID;
        this.teacherID = teacherID;
        this.name = name;
    }

    public int getCourseID() {
        return courseID;
    }

    public SimpleStringProperty getIDProperty(){
        return new SimpleStringProperty(String.valueOf(courseID));
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getTeacherID() {
        return teacherID;
    }
    public SimpleStringProperty getTeacherIDProperty(){
        return new SimpleStringProperty(String.valueOf(teacherID));
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getName() {
        return name;
    }
    public SimpleStringProperty getNameProperty(){
        return new SimpleStringProperty(String.valueOf(name));
    }

    public void setName(String name) {
        this.name = name;
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

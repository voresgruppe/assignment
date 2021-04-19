package dk.voresgruppe.be;

public class StudentAttendance {

    private int studentAttendanceID;
    private int studentID;
    private int courseID;
    private Date attendanceDate;

    public StudentAttendance(int studentID, Date attendanceDate) {
        this.studentID = studentID;
        this.attendanceDate = attendanceDate;
    }

    public int getStudentAttendanceID() {
        return studentAttendanceID;
    }

    public void setStudentAttendanceID(int studentAttendanceID) {
        this.studentAttendanceID = studentAttendanceID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
}

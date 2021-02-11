package dk.voresgruppe.be;



public class Module {
    String startTime;
    String endTime;
    String subject;
    private Date date;

    public Module(String startTime, String endTime, String subject, Date date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return startTime + "-" + endTime + " " + subject;
    }
}

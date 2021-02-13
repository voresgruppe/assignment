package dk.voresgruppe.be;



public class Module {
    String startTime;
    String endTime;
    String subject;
    int weekDay;

    public Module(String startTime, String endTime, String subject, int weekDay) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.weekDay = weekDay;
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

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public String toString() {
        return startTime + "-" + endTime + " " + subject;
    }
}

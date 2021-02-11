package dk.voresgruppe.be;


import java.util.List;

public class Schedule {

    private Date date;
    List<Module> modules;

    public Schedule(Date date, List<Module> modules) {
        this.date = date;
        this.modules = modules;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        String returnString = date + " ";
        for(Module currentModule : modules) {
            returnString += currentModule +",";
        }
        return returnString;
    }
}

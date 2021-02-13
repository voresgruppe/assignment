package dk.voresgruppe.be;


import java.util.List;

public class Schedule {

    private int weekDay;
    private List<Module> modules;

    public Schedule(int weekDay, List<Module> modules) {
        this.weekDay = weekDay;
        this.modules = modules;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        String result = String.valueOf(weekDay);
        if(!modules.isEmpty()) {
            for (Module currentModule : modules) {
                result += " " +  currentModule;
            }
        }
        return result;
    }
}

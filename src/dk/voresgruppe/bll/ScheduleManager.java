package dk.voresgruppe.bll;

import dk.voresgruppe.be.Education;
import dk.voresgruppe.be.Schedule;
import dk.voresgruppe.dal.ScheduleRepository;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

public class ScheduleManager {
    private ObservableList<Schedule> allSchedules;
    private ScheduleRepository scRepo = new ScheduleRepository();

    public ScheduleManager() {
        allSchedules = scRepo.loadSchedules();
    }

    public ObservableList<Schedule> getAllSchedules(){
        return allSchedules;
    }

    public void add(Schedule sc){
        sc.setScheduleID(scRepo.addSchedule(sc));
        allSchedules.add(sc);
    }

    public void delete(Schedule sc){
        allSchedules.remove(sc);
        scRepo.delete(sc);
    }

    public void update(Schedule a, Schedule b){
        b.setScheduleID(a.getScheduleID());
        scRepo.update(b);
        allSchedules.set(allSchedules.indexOf(a),b);
    }

    public Schedule getScheduleFromId(int id){
        for(Schedule current: allSchedules){
            if(current.getScheduleID() == id) {
                return current;
            }
        }
        return null;
    }
}

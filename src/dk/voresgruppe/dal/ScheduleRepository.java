package dk.voresgruppe.dal;

import dk.voresgruppe.be.*;
import dk.voresgruppe.be.Module;
import dk.voresgruppe.util.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepository {

    private Utils utils =new Utils();

    File file = new File("resources/data/MockSchedule Erhvervsøkonom 1.semester 2");

    public List<Module> loadModules() {
        List<Module> allModules = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                List<Module> dayModules = stringLineToModule(line);
                for (Module currentModule : dayModules) {
                    allModules.add(currentModule);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allModules;
    }

    private List<Module> stringLineToModule(String line) {
        String[] arrModule = line.split(",");
        List<Module> modules = new ArrayList<>();

        int weekday = Integer.parseInt(arrModule[0]);
        for(int i = 1; i<arrModule.length; i++) {
            String moduleToSplit = arrModule[i];
            String[] moduleSplits = moduleToSplit.split(" ");

            String timeToSplit = moduleSplits[0];
            String[] timeSplits = timeToSplit.split("-");

            String startTime = timeSplits[0];
            String endTime = timeSplits[1];

            String subject = moduleSplits[1];

            Module module = new Module(startTime, endTime, subject, weekday);
            modules.add(module);
        }
        return modules;
    }

    public List<Schedule> weekSchedules() {
        List<Module> allmodules = loadModules();
        List<Module> day1 = new ArrayList<>();
        List<Module> day2 = new ArrayList<>();
        List<Module> day3 = new ArrayList<>();
        List<Module> day4 = new ArrayList<>();
        List<Module> day5 = new ArrayList<>();
        List<Module> day6 = new ArrayList<>();
        List<Module> day7 = new ArrayList<>();
        for(Module currentModule : allmodules) {
            if(currentModule.getWeekDay() == 1) {
                day1.add(currentModule);
            }
            if(currentModule.getWeekDay() == 2) {
                day2.add(currentModule);
            }
            if(currentModule.getWeekDay() == 3) {
                day3.add(currentModule);
            }
            if(currentModule.getWeekDay() == 4) {
                day4.add(currentModule);
            }
            if(currentModule.getWeekDay() == 5) {
                day5.add(currentModule);
            }
        }
        List<Schedule> result = new ArrayList<>();
        result.add(new Schedule(1, day1));
        result.add(new Schedule(2, day2));
        result.add(new Schedule(3, day3));
        result.add(new Schedule(4, day4));
        result.add(new Schedule(5, day5));
        result.add(new Schedule(6, day6));
        result.add(new Schedule(7, day7));

        return result;
    }

}

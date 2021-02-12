package dk.voresgruppe.dal;

import dk.voresgruppe.be.*;
import dk.voresgruppe.be.Module;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepositroy {
    File file = new File("data/MockSchedule Ervhervsøkonom 1.semester");

    public List<Module> loadModules() {
        List<Module> allModules = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Module module = stringLineToModule(line);
                allModules .add(module);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allModules;
    }

    private Module stringLineToModule(String line) {
        String[] arrModule = line.split(",");

        String dateToSplit = arrModule[0];
        String[] daySplits = dateToSplit.split("/");

        int day = Integer.valueOf(daySplits[0]);
        int month = Integer.valueOf(daySplits[0]);
        int year = Integer.valueOf(daySplits[2]);
        Date date = new Date(day, month, year);

        String moduleToSplit = arrModule[1];
        String[] moduleSplits = moduleToSplit.split(" ");

        String timeToSplit = moduleSplits[0];
        String[] timeSplits = timeToSplit.split("-");

        String startTime = timeSplits[0];
        String endTime = timeSplits[1];

        String subject = moduleSplits[1];

        return new Module(startTime, endTime, subject, date);
    }

    public Schedule schedule (Date date) {
        List<Module> allModules = loadModules();
        List<Module> forSchedule = new ArrayList<>();

        for (Module currentModule: allModules) {
            if (currentModule.getDate().equals(date)) {
                forSchedule.add(currentModule);
            }
        }
        return new Schedule(date, forSchedule);
    }
}

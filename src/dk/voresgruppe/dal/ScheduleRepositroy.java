package dk.voresgruppe.dal;

import com.sun.webkit.network.Util;
import dk.voresgruppe.be.*;
import dk.voresgruppe.be.Module;
import dk.voresgruppe.util.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepositroy {

    private Utils utils =new Utils();

    File file = new File("assignment/resources/data/MockSchedule Ervhervsøkonom 1.semester");

    public List<Module> loadModules() {
        List<Module> allModules = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Module module = stringLineToModule(line);
                allModules.add(module);

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

        int day = Integer.parseInt(daySplits[0]);
        int month = Integer.parseInt(daySplits[1]);
        int year = Integer.parseInt(daySplits[2]);
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
            if (utils.checkIfDatesMatch(currentModule.getDate(), date)) {
                forSchedule.add(currentModule);
            }
        }
        return new Schedule(date, forSchedule);
    }
}

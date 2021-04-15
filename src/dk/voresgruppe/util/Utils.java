package dk.voresgruppe.util;

import dk.voresgruppe.be.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Utils {
    Calendar cal = new GregorianCalendar();

    public Date getCurrentDate() {
        return new Date(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());

    }

    public int getWeekNumberFromDate(Date date) {
        LocalDate lDate = LocalDate.of(date.getYear(),date.getMonth(), date.getDay());
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return lDate.get(weekFields.weekOfWeekBasedYear());
    }

    public int getCurrentWeek() {
        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return date.get(weekFields.weekOfWeekBasedYear());
    }

    public boolean checkIfDatesMatch (Date date1, Date date2) {
        return date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth() && date1.getDay() == date2.getDay();
    }

    public int getWeekDayFromDate (Date date) {
        Calendar c = Calendar.getInstance();
        c.set(date.getYear(), date.getMonth(), date.getDay());
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public Date dateFromString(String string){
        if(string.split("/").length>2) {
            String[] arrDate = string.split("/");
            String day = arrDate[0];
            String month = arrDate[1];
            String year = arrDate[2];
            return new Date(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
        }
        else if(string.split("-").length>2) {
            String[] arrDate = string.split("-");
            String day = arrDate[0];
            String month = arrDate[1];
            String year = arrDate[2];
            return new Date(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
        }
        return null;
    }

    public Date dateFromLocalDate(LocalDate localDate){
        return new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
    }
}

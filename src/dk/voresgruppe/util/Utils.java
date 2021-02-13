package dk.voresgruppe.util;

import dk.voresgruppe.be.Date;

import java.time.LocalDate;

public class Utils {

    public Date getCurrentDate() {
        return new Date(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
    }

    public boolean checkIfDatesMatch (Date date1, Date date2) {
        return date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth() && date1.getDay() == date2.getDay();
    }

}

package dtu.application;

import java.util.Calendar;
import java.util.GregorianCalendar;

// The DateServer has not 100% code coverage, as it basically replaced immediately by the
// mock date server for testing.
public class DateServer {

    public Calendar getDate() {
        //kan den linje jeg har udkommenteret bare fjernes??? før blev c returneret.
        Calendar calendar = new GregorianCalendar();
        //Calendar c = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        return calendar;
    }
    public int getYear() {
        return getDate().get(Calendar.YEAR);
    }
}

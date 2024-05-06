package dtu.application;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateServer {

    public Calendar getDate() {
        Calendar calendar = new GregorianCalendar();
        return calendar;
    }
    public Calendar createDate(int week, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar;
    }
    public int getYear() {
        return getDate().get(Calendar.YEAR);
    }
}

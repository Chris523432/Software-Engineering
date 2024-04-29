package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Vacation extends Absence{
    private Calendar endDate;
    public Vacation(Calendar startDate, Calendar endDate) {
        super(startDate);
        this.endDate = endDate;
    }
    public Calendar getEndDate() {
        return endDate;
    }
    public List<Calendar> getInterval() {
        List<Calendar> interval = new ArrayList<>();
        Calendar startDate = getDate();
        Calendar tempDate = (Calendar) startDate.clone();
        interval.add(tempDate);
        while (!tempDate.before(endDate)) {
            tempDate.add(Calendar.DAY_OF_MONTH, 1);
            interval.add((Calendar) tempDate.clone());
        }
        return interval;
    }
    @Override
    public String getType() {
        return "Vacation";
    }
}

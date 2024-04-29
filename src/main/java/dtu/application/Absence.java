package dtu.application;

import java.util.Calendar;

public abstract class Absence {
    private Calendar startDate;
    public Absence(Calendar startDate) {
        this.startDate = startDate;
    }
    public Calendar getDate() {
        return startDate;
    }
    public abstract String getType();
}

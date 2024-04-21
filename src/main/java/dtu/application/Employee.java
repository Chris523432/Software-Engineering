package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Employee {
    private String initials;
    private boolean isLoggedIn;
    private List<Absence> absence = new ArrayList<>();
    public void registerIllness(Calendar currentDate) {
        Illness a = new Illness(currentDate);
        absence.add(a);
    }

    public void registerVacation(Calendar startDate, Calendar endDate) {
        Vacation a = new Vacation(startDate, endDate);
        absence.add(a);
    }

    public boolean isAbsent(Calendar currentDate, Calendar startDate, Calendar endDate) {
        Vacation vacation = new Vacation(startDate, endDate);
        List<Calendar> vacationInterval = vacation.getInterval();
        for (int i = absence.size() - 1; i >= 0; i--) {
            if (absence.get(i) instanceof Illness) {
                return isIll(currentDate);
            }
            Vacation v = (Vacation) absence.get(i);
            if (v.getEndDate().before(currentDate)) {
                return false;
            }
            if (vacationInterval.stream().anyMatch(v.getInterval()::contains)) {
                return true;
            }
        }
        return false;
    }

    public boolean isIll(Calendar currentDate) {
        return absence.stream()
                .filter(a -> a instanceof Illness)
                .anyMatch(a -> a.getDate().equals(currentDate));
    }

    public Employee(String initials) {
        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }

    public void login() {
        isLoggedIn = true;
    }

    public void logout() {
        isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}

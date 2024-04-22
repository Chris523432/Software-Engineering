package dtu.application;

import java.util.Calendar;

public class Illness extends Absence {
    public Illness(Calendar currentDate) {
        super(currentDate);
    }
    @Override
    public String getType() {
        return "Illness";
    }
}

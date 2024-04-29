package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Employee {
    private String initials;
    private boolean isLoggedIn;

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

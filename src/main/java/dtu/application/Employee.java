package dtu.application;

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

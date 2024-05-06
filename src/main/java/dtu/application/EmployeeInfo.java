package dtu.application;

import java.util.ArrayList;
import java.util.List;
//Thomas
public class EmployeeInfo {
    private String initials;

    public EmployeeInfo(Employee employee) {
        this.initials = employee.getInitials();
    }

    public String getInitials() {
        return initials;
    }

}

package dtu.application;

import java.util.ArrayList;
import java.util.List;

public class EmployeeInfo {
    private String initials;
    private List<ProjectInfo> leadingProjects;

    public EmployeeInfo(Employee employee) {
        this.initials = employee.getInitials();
        this.leadingProjects = new ArrayList<>();
        for (Project p : employee.getLeadingProjects()) {
            leadingProjects.add(new ProjectInfo(p,this));
        }
    }

    public String getInitials() {
        return initials;
    }

    public List<ProjectInfo> getLeadingProjects() {
        return leadingProjects;
    }
}

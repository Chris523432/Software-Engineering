package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Employee {
    private String initials;
    private List<Project> leadingProjects;

    public Employee(String initials) {
        this.initials = initials;
        this.leadingProjects = new ArrayList<>();
    }

    public String getInitials() {
        return initials;
    }


    public void removeProject(Project p) {
        leadingProjects.remove(p);
    }

    public void addProject(Project p) {
        leadingProjects.add(p);
    }

    public List<Project> getLeadingProjects() {
        return leadingProjects;
    }
}

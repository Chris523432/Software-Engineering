package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {
    private String name;
    private String id;
    private List<Activity> activities = new ArrayList<>();
    private static int previousId = 0;

    public Project(String name, String year) {
        this.name = name;
        previousId += 1;
        this.id = year + String.format("%03d", previousId);
    }
    public void addProject(String name) {
        Activity a = new Activity(name);
        a.setParentProject(this);
        activities.add(a);
    }
    public List<Activity> getActivities() {
        return activities;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
}

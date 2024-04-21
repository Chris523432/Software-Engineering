package dtu.application;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private String id;
    private List<Activity> activities = new ArrayList<>();
    private IdGenerator idGenerator = new IdGenerator();

    public Project(String name, int year) {
        this.name = name;
        this.id = idGenerator.generateProjectId(year);
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

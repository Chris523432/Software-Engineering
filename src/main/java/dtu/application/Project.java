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

    public String getStartWeek() throws Exception {
        if (activities.isEmpty()) {
            throw new Exception("Project does not have a start week");
        }
        int week = 53;
        int year = 10000;
        for (Activity a : activities) {
            if (a.getStartYear() <= year || a.getStartWeek() < week && a.getStartYear() <= year) {
                week = a.getStartWeek();
                year = a.getStartYear();
            }
        }
        return "Week: " + week + "Year: " + year;
    }

    public String getEndWeek() throws Exception {
        if (activities.isEmpty()) {
            throw new Exception("Project does not have an end week");
        }
        int week = 0;
        int year = 0;
        for (Activity a : activities) {
            if (a.getEndYear() >= year || a.getEndWeek() > week && a.getEndYear() >= year) {
                week = a.getEndWeek();
                year = a.getEndYear();
            }
        }
        return "Week: " + week + "Year: " + year;
    }

    public String getProjectStatus() throws Exception {
        if (activities.isEmpty()) {
            throw new Exception("Project does not have a status");
        }
        for (Activity a : activities) {
            if (!a.getStatus()) {
                return "Project is incomplete";
            }
        }
        return "Project is complete";
    }

}

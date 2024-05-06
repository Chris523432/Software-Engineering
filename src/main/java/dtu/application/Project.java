package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {
    private String name;
    private String id;
    private List<Activity> activities;
    private ProjectIdGenerator idGenerator = new ProjectIdGenerator();
    private Employee projectLeader;

    public Project(String name, int year) { //Bastian
        this.name = name;
        idGenerator.setYear(year);
        this.id = idGenerator.generateId();
        this.activities = new ArrayList<>();
    }
    public String addActivity(String name) { //Bastian
        Activity a = new Activity(name);
        activities.add(a);
        return a.getId();
    }
    public List<Activity> getActivities() { //Christian
        return activities;
    }
    public String getName() { //Christian
        return name;
    }
    public String getId() { //Christian
        return id;
    }

    public Calendar getStartDate() { //Chris
        Calendar date = null;
        for (Activity a : activities) {
            if (a.getStartDate() != null && (date == null || a.getStartDate().before(date))) {
                date = a.getStartDate();
            }
        }
        return date;
    }

    public Calendar getEndDate() { //Chris
        Calendar date = null;
        for (Activity a : activities) {
            if (a.getEndDate() != null && (date == null || a.getEndDate().after(date))) {
                date = a.getEndDate();
            }
        }
        return date;
    }

    public boolean isComplete() { //Chris
        if (activities.isEmpty()) {
            return false;
        }
        for (Activity a : activities) {
            if (!a.isComplete()) {
                return false;
            }
        }
        return true;
    }

    public void assignProjectLeader(Employee projectLeader) { //Chris
        this.projectLeader = projectLeader;
    }
    public Employee getProjectLeader() { //Chris
        return projectLeader;
    }

}

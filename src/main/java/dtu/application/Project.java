package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Project {
    private String name;
    private String id;
    private List<Activity> activities;
    private IdGenerator idGenerator = new IdGenerator();
    private Employee projectLeader;

    public Project(String name, int year) {
        this.name = name;
        this.id = idGenerator.generateProjectId(year);
        this.activities = new ArrayList<>();
    }
    public void addActivity(String name) {
        Activity a = new Activity(name);
        a.setProject(this);
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

    public Calendar getStartDate() {
        Calendar date = null;
        for (Activity a : activities) {
            if (date == null || a.getStartDate().before(date)) {
                date = a.getStartDate();
            }
        }
        return date;
    }

    public Calendar getEndDate() {
        Calendar date = null;
        for (Activity a : activities) {
            if (date == null || a.getEndDate().after(date)) {
                date = a.getEndDate();
            }
        }
        return date;
    }

    public String getProjectStatus() {
        //bør vel bare være en attribut i project?
        if (activities.isEmpty()) {
            return("Project is incomplete");
        }
        for (Activity a : activities) {
            if (!a.isComplete()) {
                return "Project is incomplete";
            }
        }
        return "Project is complete";
    }

    public void assignProjectLeader(Employee projectLeader) {
        this.projectLeader = projectLeader;
    }
    public Employee getProjectLeader() {
        return projectLeader;
    }

}

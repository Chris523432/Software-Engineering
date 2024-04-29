package dtu.application;

import java.util.ArrayList;
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

    public String getStartWeek() throws Exception {
        //pls indfør date objekt eller noget
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
        if (year  == 0 || week == 0) {
            throw new Exception("Project does not have a start week");
        }
        return "Week: " + week + "Year: " + year;
    }

    public String getEndWeek() {
        //pls indfør date objekt eller noget
        if (activities.isEmpty()) {
            return("Project does not have an end week");
        }
        int week = 0;
        int year = 0;
        for (Activity a : activities) {
            if (a.getEndYear() >= year || a.getEndWeek() > week && a.getEndYear() >= year) {
                week = a.getEndWeek();
                year = a.getEndYear();
            }
        }
        if (year  == 0 || week == 0) {
            return("Project does not have an end week");
        }
        return "Week: " + week + "Year: " + year;
    }

    public String getProjectStatus() throws Exception {
        //bør vel bare være en attribut i project?
        if (activities.isEmpty()) {
            throw new Exception("Project does not have a status");
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

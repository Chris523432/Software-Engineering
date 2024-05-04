package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProjectInfo {
    private String name;
    private String id;
    private List<ActivityInfo> activities;
    private EmployeeInfo projectLeader;


    //projectLeader argument protects from endless loop given circular reference
    //to and from project and employee
    public ProjectInfo(Project project, EmployeeInfo projectLeader) {
        this.name = project.getName();
        this.id = project.getId();
        this.projectLeader = (projectLeader == null && project.getProjectLeader() != null) ? new EmployeeInfo(project.getProjectLeader()) : projectLeader;
        this.activities = new ArrayList<>();
        for (Activity activity : project.getActivities()) {
            activities.add(new ActivityInfo(activity));
        }
    }
    public ProjectInfo(Project project) {
        this(project,null);
    }
    public List<ActivityInfo> getActivities() {
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
        for (ActivityInfo a : activities) {
            if (date == null || a.getStartDate().before(date)) {
                date = a.getStartDate();
            }
        }
        return date;
    }

    public Calendar getEndDate() {
        Calendar date = null;
        for (ActivityInfo a : activities) {
            if (date == null || a.getEndDate().after(date)) {
                date = a.getEndDate();
            }
        }
        return date;
    }

    public boolean isComplete() {
        if (activities.isEmpty()) {
            return false;
        }
        for (ActivityInfo a : activities) {
            if (!a.isComplete()) {
                return false;
            }
        }
        return true;
    }

    public EmployeeInfo getProjectLeader() {
        return projectLeader;
    }

}

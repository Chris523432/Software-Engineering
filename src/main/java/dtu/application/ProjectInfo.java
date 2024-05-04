package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProjectInfo {
    private String name;
    private String id;
    private List<ActivityInfo> activities;
    private EmployeeInfo projectLeader;

    public ProjectInfo(Project project) {
        this.name = project.getName();
        this.id = project.getId();
        this.projectLeader = project.getProjectLeader() == null ? null : new EmployeeInfo(project.getProjectLeader());
        this.activities = new ArrayList<>();
        for (Activity activity : project.getActivities()) {
            activities.add(new ActivityInfo(activity));
        }
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

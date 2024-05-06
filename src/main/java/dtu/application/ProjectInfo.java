package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
//Thomas
public class ProjectInfo {
    private String name;
    private String id;
    private List<ActivityInfo> activities;
    private EmployeeInfo projectLeader;
    private Calendar startDate;
    private Calendar endDate;


    //projectLeader argument protects from endless loop given circular reference
    //to and from project and employee. This is no longer needed since
    //the reference from Employee to Project was removed, but this comment is
    //kept to explain the constructor.
    public ProjectInfo(Project project, EmployeeInfo projectLeader) {
        this.name = project.getName();
        this.id = project.getId();
        this.projectLeader = (projectLeader == null && project.getProjectLeader() != null) ? new EmployeeInfo(project.getProjectLeader()) : projectLeader;
        this.activities = new ArrayList<>();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
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
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
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

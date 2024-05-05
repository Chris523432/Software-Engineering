package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ActivityInfo {
    private String name;
    private String id;
    private int budgetedHours;
    private boolean complete;
    private List<EmployeeInfo> assignedEmployees;
    private Calendar startDate;
    private Calendar endDate;
    public ActivityInfo(Activity activity) {
        this.name = activity.getName();
        this.id = activity.getId();
        this.assignedEmployees = new ArrayList<>();
        for (Employee employee : activity.getAssignedEmployees()) {
            assignedEmployees.add(new EmployeeInfo(employee));
        }
        this.budgetedHours = activity.getBudgetedHours();
        this.endDate = activity.getEndDate();
        this.startDate = activity.getStartDate();
        this.complete = activity.isComplete();
    }

    public List<EmployeeInfo> getAssignedEmployees() {
        return assignedEmployees;
    }
    public boolean isAssigned(EmployeeInfo employee) {
        return assignedEmployees.contains(employee);
    }
    public boolean isComplete() {
        return complete;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public int getBudgetedHours() {
        return budgetedHours;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }
}

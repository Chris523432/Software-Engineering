package dtu.application;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String name;
    private String id;
    private Project parentProject;
    private int budgetedHours;
    private boolean status = false;
    private IdGenerator idGenerator = new IdGenerator();
    private List<Employee> assignedEmployees = new ArrayList<>();
    public Activity(String name) {
        this.name = name;
        this.id = idGenerator.generateActivityId();
    }

    public Activity(String name, int budgetedHours) throws Exception {
        new Activity(name);
        setAllocatedTime(budgetedHours);
    }
    public void setAllocatedTime(int budgetedHours) throws Exception {
        if (budgetedHours < 0) {
            throw new OperationNotAllowedException("Invalid value");
        }
        this.budgetedHours = budgetedHours;
    }
    public void assignEmployee(Employee employee) {
        assignedEmployees.add(employee);
    }
    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }
    public boolean isAssigned(Employee employee) {
        return assignedEmployees.contains(employee);
    }
    public void inComplete() {
        status = false;
    }
    public void complete() {
        status = true;
    }
    public boolean getStatus() {
        return status;
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
    public void setParentProject(Project project) {
        this.parentProject = project;
    }
    public Project getParentProject() {
        return parentProject;
    }
}

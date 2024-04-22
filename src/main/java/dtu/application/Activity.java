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
    private int startweek;
    private int endweek;
    private int startyear;
    private int endyear;
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

    public void setStartWeek(int startweek, int startyear) throws Exception{
        if (startweek > endweek && startyear <= endyear) {
            throw new OperationNotAllowedException("start week cannot be after end week");
        }
        if (startweek < 0 || startweek > 52) {
            throw new OperationNotAllowedException("Please enter a valid week");
        }
        this.startweek = startweek;
        this.startyear = startyear;
    }

    public void setEndWeek(int endWeek, int endyear) throws OperationNotAllowedException {
        if (startweek > endweek && startyear <= endyear) {
            throw new OperationNotAllowedException("End week cannot be before start week");
        }
        if (endWeek < 0 || endWeek > 52) {
            throw new OperationNotAllowedException("Please enter a valid week");
        }
        this.endweek = endWeek;
        this.endyear = endyear;
    }

    public int getStartWeek() {
        return startweek;
    }

    public int getEndWeek() {
        return endweek;
    }

    public int getEndYear() {
        return endyear;
    }

    public int getStartYear() {
        return startyear;
    }


}

package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Activity {
    private String name;
    private String id;
    private int budgetedHours;
    private boolean complete;
    private ActivityIdGenerator idGenerator = new ActivityIdGenerator();
    private List<Employee> assignedEmployees;
    private Calendar startDate;
    private Calendar endDate;
    private DateServer dateServer = new DateServer();
    public Activity(String name) {
        this.name = name;
        this.id = idGenerator.generateId();
        this.assignedEmployees = new ArrayList<>();
        this.complete = false;
    }

    public void setAllocatedTime(int budgetedHours) throws OperationNotAllowedException {
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
        complete = false;
    }
    public void complete() {
        complete = true;
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
    public void setStartWeek(int startWeek, int startYear) throws OperationNotAllowedException {
        if (startWeek <= 0 || startWeek > 52 || startYear <= 0) {
            throw new OperationNotAllowedException("Please enter a valid week");
        }
        Calendar date = dateServer.createDate(startWeek, startYear);

        if (endDate != null && (endDate.get(Calendar.WEEK_OF_YEAR) != startWeek || endDate.get(Calendar.YEAR) != startYear)){
            if (date.after(endDate)) {
                throw new OperationNotAllowedException("start week cannot be after end week");
            }
        }
        this.startDate = date;
    }

    public void setEndWeek(int endWeek, int endYear) throws OperationNotAllowedException {
        if (endWeek <= 0 || endWeek > 52 || endYear <= 0) {
            throw new OperationNotAllowedException("Please enter a valid week");
        }
        Calendar date = dateServer.createDate(endWeek, endYear);
        if (startDate != null) {
            if (date.before(startDate)) {
                throw new OperationNotAllowedException("End week cannot be before start week");
            }
        }
        this.endDate = date;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }
}

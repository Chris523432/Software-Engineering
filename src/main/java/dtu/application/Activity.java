package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Activity {
    private String name;
    private String id;
    private int budgetedHours;
    private boolean complete;
    private IdGenerator idGenerator = new ActivityIdGenerator();
    private List<Employee> assignedEmployees;
    private Calendar startDate;
    private Calendar endDate;
    private DateServer dateServer = new DateServer();
    public Activity(String name) { //Bastian
        this.name = name;
        this.id = idGenerator.generateId();
        this.assignedEmployees = new ArrayList<>();
        this.complete = false;
    }

    public void setAllocatedTime(int budgetedHours) throws OperationNotAllowedException { //Bastian
        if (budgetedHours < 0) {
            throw new OperationNotAllowedException("Invalid value");
        }
        this.budgetedHours = budgetedHours;
    }
    public void assignEmployee(Employee employee) { //Chris
        assignedEmployees.add(employee);
    }
    public List<Employee> getAssignedEmployees() { //Chris
        return assignedEmployees;
    }
    public boolean isAssigned(Employee employee) { //Chris
        return assignedEmployees.contains(employee);
    }
    public void inComplete() { //Bastian
        complete = false;
    }
    public void complete() { //Bastian
        complete = true;
    }
    public boolean isComplete() { //Bastian
        return complete;
    }
    public String getName() { //Bastian
        return name;
    }
    public String getId() { //Bastian
        return id;
    }
    public int getBudgetedHours() { //Bastian
        return budgetedHours;
    }
    public void setStartWeek(int startWeek, int startYear) throws OperationNotAllowedException { //Chris
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

    public void setEndWeek(int endWeek, int endYear) throws OperationNotAllowedException { //Chris
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

    public Calendar getStartDate() { //Chris
        return startDate;
    }

    public Calendar getEndDate() { //Chris
        return endDate;
    }
}

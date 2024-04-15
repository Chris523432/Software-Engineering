package dtu.application;

public class Activity {
    private String name;
    private int id;
    private Project parentProject;
    private int budgetedHours;
    private static int previousId = 0;

    public Activity(String name) {
        this.name = name;
        previousId += 1;
        this.id = previousId;
    }

    public Activity(String name, int budgetedHours) {
        new Activity(name);
        setAllocatedTime(budgetedHours);
    }
    public void setAllocatedTime(int budgetedHours) {
        this.budgetedHours = budgetedHours;
    }
    public String getName() {
        return name;
    }
    public int getId() {
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

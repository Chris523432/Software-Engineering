package dtu.application;

public class Activity {
    private String name;
    private String id;
    private Project parentProject;
    private int budgetedHours;
    private static int previousId = 0;

    public Activity(String name) {
        this.name = name;
        previousId += 1;
        this.id = String.format("%05d", previousId);
        //Jeg tænker at vi laver activity id'en om således at der er 5 cifre som starter i 00001 og vokser derfra
        //da der skal kunne være rigtig mange aktiviteter.
    }

    public Activity(String name, int budgetedHours) {
        this.name = name;
        previousId += 1;
        this.id = String.format("%05d", previousId);
        setAllocatedTime(budgetedHours);
    }
    public void setAllocatedTime(int budgetedHours) {
        this.budgetedHours = budgetedHours;
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

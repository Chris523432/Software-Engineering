package example.cucumber;

import dtu.application.*;
import example.junit.MockDateHolder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class CreateActivitySteps {
    private Application application;
    private ErrorMessageHolder errorMessageHolder;
    private String tempName;
    private Activity tempActivity;
    private MockDateHolder mockDateHolder;
    private String projectId;
    private String activityId;

    public CreateActivitySteps(Application application, ErrorMessageHolder errorMessageHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        application.resetAllIds();
        mockDateHolder = new MockDateHolder(application);
        mockDateHolder.setYear2024();
    }
    @Given("project with name {string} is in the system")
    public void projectWithNameIsInTheSystem(String name) {
        try {
            projectId = application.createProject(name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @When("an activity with name {string} is added to the project")
    public void anActivityWithNameIsAddedTo(String activityName) {
        try {
            activityId = application.createActivity(projectId, activityName);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        tempName = activityName;
    }
    @When("the budgeted time is {int} hours")
    public void theBudgetedTimeIsHours(int hours) throws DoesNotExistErrorException, OperationNotAllowedException {
        application.setAllocatedTime(activityId, hours);
    }
    @Then("the activity with budgeted time {int} is in the project")
    public void theActivityWithBudgetedTimeIsIn(int hours) throws DoesNotExistErrorException {
        Activity a = application.getActivity(activityId);
        Project project = application.getProject(projectId);
        assertTrue(project.getActivities().contains(a));
        assertEquals(hours, a.getBudgetedHours());
    }
    @Given("project with name {string} is not in the system")
    public void projectWithNameIsNotInTheSystem(String name) {
        assertFalse(application.getProjects().stream().anyMatch(proj -> proj.getName().equals(name)));
    }

    @When("an activity with empty name \\(string is empty or only spaces) is added to the project")
    public void anActivityWithEmptyNameStringIsEmptyOrOnlySpacesIsAddedTo() {
        try {
            application.createActivity(projectId, "  ");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("activity with empty name is not in the project")
    public void activityWithEmptyNameIsNotIn() throws DoesNotExistErrorException {
        Project p = application.getProject(projectId);
        assertFalse(p.getActivities().stream().anyMatch(a -> a.getName().trim().isEmpty()));
    }
}

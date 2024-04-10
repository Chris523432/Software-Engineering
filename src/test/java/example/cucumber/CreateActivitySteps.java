package example.cucumber;

import dtu.application.Activity;
import dtu.application.Application;
import dtu.application.DateServer;
import dtu.application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;
public class CreateActivitySteps {
    private Application application;
    private ErrorMessageHolder errorMessageHolder;
    private String tempName;

    public CreateActivitySteps(Application application, ErrorMessageHolder errorMessageHolder, MockDateHolder dateHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
    }
    @Given("project with name {string} is in the system")
    public void projectWithNameIsInTheSystem(String name) {
        try {
            application.createProject(name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @When("an activity with name {string} is added to {string}")
    public void anActivityWithNameIsAddedTo(String activityName, String projectName) {
        try {
            application.createActivity(projectName, activityName);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        tempName = activityName;
    }
    @When("the budgeted time is {int} hours")
    public void theBudgetedTimeIsHours(int hours) {
        application.setAllocatedTime(tempName, hours);
    }
    @Then("the activity {string} with budgeted time {int} is in {string}")
    public void theActivityWithBudgetedTimeIsIn(String activityName, int hours, String projectName) throws Exception {
        Activity a = application.getActivity(activityName);
        assertTrue(application.getProject(projectName).getActivities().contains(a));
        assertEquals(hours, a.getBudgetedHours());
    }
    @Given("project with name {string} is not in the system")
    public void projectWithNameIsNotInTheSystem(String name) {
        assertFalse(application.getProjects().stream().anyMatch(proj -> proj.getName().equals(name)));
    }

    @When("an activity with epmty name \\(string is empty or only spaces) is added to {string}")
    public void anActivityWithEpmtyNameStringIsEmptyOrOnlySpacesIsAddedTo(String projectName) {
        try {
            application.createActivity(projectName, "  ");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("activity with empty name is not in {string}")
    public void activityWithEmptyNameIsNotIn(String projectName) throws Exception {
        Project p = application.getProject(projectName);
        assertFalse(p.getActivities().stream().anyMatch(a -> a.getName().trim().isEmpty()));
    }
}

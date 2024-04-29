package example.cucumber;

import dtu.application.*;
import example.junit.MockDateHolder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class CreateActivitySteps {
    private Application application;
    private ErrorMessageHolder errorMessageHolder;
    private String tempName;
    private Activity tempActivity;
    private IdGenerator idGenerator = new IdGenerator();
    private MockDateHolder mockDateHolder;

    public CreateActivitySteps(Application application, ErrorMessageHolder errorMessageHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        idGenerator.resetIds();
        mockDateHolder = new MockDateHolder(application);
        mockDateHolder.setYear2024();
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
    public void theBudgetedTimeIsHours(int hours) throws DoesNotExistErrorException, OperationNotAllowedException {
        application.setAllocatedTime(tempName, hours);
    }
    @Then("the activity {string} with budgeted time {int} is in {string}")
    public void theActivityWithBudgetedTimeIsIn(String activityName, int hours, String projectName) throws DoesNotExistErrorException {
        Activity a = application.getActivity(activityName);
        assertTrue(application.getProject(projectName).getActivities().contains(a));
        assertEquals(hours, a.getBudgetedHours());
    }
    @Given("project with name {string} is not in the system")
    public void projectWithNameIsNotInTheSystem(String name) {
        assertFalse(application.getProjects().stream().anyMatch(proj -> proj.getName().equals(name)));
    }

    @When("an activity with empty name \\(string is empty or only spaces) is added to {string}")
    public void anActivityWithEmptyNameStringIsEmptyOrOnlySpacesIsAddedTo(String projectName) {
        try {
            application.createActivity(projectName, "  ");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("activity with empty name is not in {string}")
    public void activityWithEmptyNameIsNotIn(String projectName) throws DoesNotExistErrorException {
        Project p = application.getProject(projectName);
        assertFalse(p.getActivities().stream().anyMatch(a -> a.getName().trim().isEmpty()));
    }
}

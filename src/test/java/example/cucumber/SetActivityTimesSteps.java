package example.cucumber;

import dtu.application.Activity;
import dtu.application.Application;
import dtu.application.DateServer;
import dtu.application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;


public class SetActivityTimesSteps {
    private Application application;
    private ErrorMessageHolder errorMessageHolder;

    private Project parentProject;

    private Activity a;
    public SetActivityTimesSteps(Application application, ErrorMessageHolder errorMessageHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("employee is logged in")
    public void employee_is_logged_in() {
        application.registerUser("Hej");
        try {
            application.login("Hej");
            assertTrue(application.isLoggedIn("Hej"));
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Given("there exists an activity with id {int}")
    public void there_exists_an_activity_with_id(Integer int1) throws Exception {
        try {
            application.createProject("lala");
            application.createActivity("lala", "Test");
            a = application.getActivityByName("Test");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        parentProject = application.getProject("lala");
        assertTrue(parentProject.getActivities().contains(a));
    }
    @When("the allocated time {int} hours is set on activity with id {int}")
    public void the_allocated_time_hours_is_set_on_activity_with_id(Integer int1, Integer int2) {
        try {
            application.setAllocatedTime(int2, int1);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("the activity with id {int} has allocated time {int} hours")
    public void the_activity_with_id_has_allocated_time_hours(Integer int1, Integer int2) {
        assertTrue(a.getBudgetedHours() == int2);
    }

}

package example.cucumber;

import dtu.application.Activity;
import dtu.application.Application;
import dtu.application.IdGenerator;
import dtu.application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;


public class SetActivityTimesSteps {
    private Application application;
    private ErrorMessageHolder errorMessageHolder;

    private Project parentProject;
    private IdGenerator idGenerator = new IdGenerator();
    private Activity a;
    public SetActivityTimesSteps(Application application, ErrorMessageHolder errorMessageHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        idGenerator.resetIds();
    }

    @Given("employee {string} is logged in")
    public void employee_is_logged_in(String name) {
        application.registerUser(name);
        try {
            application.login(name);
            assertTrue(application.isLoggedIn(name));
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Given("there exists an activity with id {string}")
    public void there_exists_an_activity_with_id(String activityIdentifier) throws Exception {
        try {
            application.createProject("ProjectTest");
            application.createActivity("ProjectTest", "ActivityTest");
            a = application.getActivity("ActivityTest");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        parentProject = application.getProject("ProjectTest");
        assertTrue(parentProject.getActivities().contains(a));
    }
    @When("the allocated time {int} hours is set on activity with id {string}")
    public void the_allocated_time_hours_is_set_on_activity_with_id(int hours, String activityIdentifier) {
        try {
            application.setAllocatedTime(activityIdentifier, hours);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("the activity with id {string} has allocated time {int} hours")
    public void the_activity_with_id_has_allocated_time_hours(String int1, Integer int2) {
        assertTrue(a.getBudgetedHours() == int2);
    }

}

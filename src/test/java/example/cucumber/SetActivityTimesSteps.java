package example.cucumber;

import dtu.application.Activity;
import dtu.application.Application;
import dtu.application.IdGenerator;
import dtu.application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;


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
        assertEquals(a.getBudgetedHours(), (int) int2);
    }

    @Given("there does not exist an activity with id {string}")
    public void thereDoesNotExistAnActivityWithId(String activityIdentifier) throws Exception {
        try {
            application.getActivity(activityIdentifier);
        } catch (Exception e) {
            assertFalse(false);
        }
    }

    @When("the start week is set to Week {int} on activity with id {string}")
    public void the_start_week_is_set_to_week_on_activity_with_id(Integer int1, String activity) throws Exception {
        try {
            application.setStartWeekToActivity(activity, int1, 2024);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity with id {string} has start week Week {int}")
    public void the_activity_with_id_has_start_week_week(String activityidentifier, Integer int1) throws Exception {
        assertEquals(application.getStartWeekForActivity(activityidentifier), (int) int1);
    }


    @Given("the activity id {string} has end week Week {int}")
    public void the_activity_id_has_end_week_week(String activityidentifier, Integer int1) throws Exception {
        application.setEndWeekToActivity(activityidentifier, int1, 2024);
        assertEquals(application.getEndWeekForActivity(activityidentifier), (int) int1);
    }

    @When("the end week is set to Week {int} on activity with id {string}")
    public void the_end_week_is_set_to_week_on_activity_with_id(Integer int1, String string) throws Exception {
        try {
            application.setEndWeekToActivity(string, int1, 2024);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity with id {string} has end week Week {int}")
    public void the_activity_with_id_has_end_week_week(String activityidentifier, Integer int1) throws Exception {
        assertEquals(application.getEndWeekForActivity(activityidentifier), (int) int1);
    }

    @Given("the activity has start week Week {int}")
    public void the_activity_has_start_week_week(Integer int1) throws Exception {
        application.setStartWeekToActivity("1", int1, 2024);
    }

}

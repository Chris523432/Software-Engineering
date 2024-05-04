package example.cucumber;

import dtu.application.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Calendar;

import static org.junit.Assert.*;


public class SetActivityTimesSteps {
    private Application application;
    private ErrorMessageHolder errorMessageHolder;

    private Project parentProject;
    private Activity a;
    public SetActivityTimesSteps(Application application, ErrorMessageHolder errorMessageHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        application.resetAllIds();
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
    public void there_exists_an_activity_with_id(String activityIdentifier) throws DoesNotExistErrorException {
        try {
            String projectId = application.createProject("ProjectTest");
            application.createActivity(projectId, "ActivityTest");
            a = application.getActivity(activityIdentifier);
            parentProject = application.getProject(projectId);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

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
    @Then("the activity has allocated time {int} hours")
    public void the_activity_with_id_has_allocated_time_hours(Integer int2) {
        assertEquals(a.getBudgetedHours(), (int) int2);
    }

    @Given("there does not exist an activity with id {string}")
    public void thereDoesNotExistAnActivityWithId(String activityIdentifier) {
        try {
            application.getActivity(activityIdentifier);
        } catch (Exception e) {
            assertFalse(false);
        }
    }

    @When("the start week is set to Week {int} and start year {int} on activity with id {string}")
    public void the_start_week_is_set_to_week_and_start_year_on_activity_with_id(Integer week, Integer year ,String activity) {
        try {
            application.setStartWeekToActivity(activity, week, year);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity with id {string} has start week Week {int} and start year {int}")
    public void the_activity_with_id_has_start_week_week_and_start_year(String activityidentifier, int week, int year) throws DoesNotExistErrorException {
        assertEquals(application.getStartDateForActivity(activityidentifier).get(Calendar.WEEK_OF_YEAR), week);
        assertEquals(application.getStartDateForActivity(activityidentifier).get(Calendar.YEAR), year);
    }


    @Given("the activity id {string} has end week {int} and end year {int}")
    public void the_activity_id_has_end_week_week_and_end_year(String activityidentifier, int week, int year) throws DoesNotExistErrorException, OperationNotAllowedException {
        application.setEndWeekToActivity(activityidentifier, week, year);
        assertEquals(application.getEndDateForActivity(activityidentifier).get(Calendar.WEEK_OF_YEAR),  week);
        assertEquals(application.getEndDateForActivity(activityidentifier).get(Calendar.YEAR), year);
    }

    @When("the end week is set to Week {int} and end year is set to {int} on activity with id {string}")
    public void the_end_week_is_set_to_week_and_end_year_is_set_to_on_activity_with_id(Integer week, Integer year, String string) {
        try {
            application.setEndWeekToActivity(string, week, year);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity with id {string} has end week {int} and end year {int}")
    public void the_activity_with_id_has_end_week_and_end_year(String activityidentifier, int week, int year) throws DoesNotExistErrorException {
        assertEquals(application.getEndDateForActivity(activityidentifier).get(Calendar.WEEK_OF_YEAR), week);
        assertEquals(application.getEndDateForActivity(activityidentifier).get(Calendar.YEAR), year);
    }

    @Given("the activity has start week {int} and start year {int}")
    public void the_activity_has_start_week_(int week, int year) throws DoesNotExistErrorException, OperationNotAllowedException {
        application.setStartWeekToActivity("1", week, year);
    }

}

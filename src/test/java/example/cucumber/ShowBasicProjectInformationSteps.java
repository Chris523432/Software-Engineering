package example.cucumber;

import dtu.application.Application;
import dtu.application.DoesNotExistErrorException;
import dtu.application.IdGenerator;
import dtu.application.OperationNotAllowedException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Calendar;

import static org.junit.Assert.*;

public class ShowBasicProjectInformationSteps {

    private Application application;
    private ErrorMessageHolder errorMessageHolder;
    private IdGenerator idGenerator = new IdGenerator();
    private Calendar tempDate;
    public ShowBasicProjectInformationSteps(Application application, ErrorMessageHolder errorMessageHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        idGenerator.resetIds();
    }


    @Given("the project with name {string} exists")
    public void the_project_with_name_exists(String projectname) {
        try {
            application.createProject(projectname);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("the activity with id {string} has the earliest start week {int} in year {int}")
    public void the_activity_with_id_has_the_earliest_start_week(String activityidentifier, int week, int year) throws DoesNotExistErrorException, OperationNotAllowedException {
        application.getProject("project1").addActivity("Test");
        application.getProject("project1").addActivity("Test2");
        application.setStartWeekToActivity(activityidentifier, week, year);
        application.getActivity("2").setStartWeek(1, 2025);
    }

    @When("the employee requests the start week of project with name {string}")
    public void the_employee_requests_the_start_week_of_project_with_name(String string) {
        try {
            tempDate = application.getStartDateForProject(string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the found start week is week {int}")
    public void the_found_start_week_is_week(int week) throws DoesNotExistErrorException {
        assertEquals(week, application.getStartDateForProject("project1").get(Calendar.WEEK_OF_YEAR));
    }

    @Given("the activity with id {string} has the latest end week {int} in year {int}")
    public void the_activity_with_id_has_the_latest_end_week_in_year(String string, int week, int year) throws DoesNotExistErrorException, OperationNotAllowedException {
        application.createActivity("project1", "Test1");
        application.createActivity("project1", "Test2");
        application.setEndWeekToActivity(string, week, year);
        application.setEndWeekToActivity("2", week, 2023);
    }
    @Given("the project with name {string} does not exist")
    public void the_project_with_name_does_not_exist(String string) {
        try {
            application.getProject(string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the employee requests the end week of project with name {string}")
    public void the_employee_requests_the_end_week_of_project_with_name(String project) {
        try {
            tempDate = application.getEndDateForProject(project);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the found end week is week {int}")
    public void the_found_end_week_is_week(int week) throws DoesNotExistErrorException {
        assertEquals(week, application.getEndDateForProject("project1").get(Calendar.WEEK_OF_YEAR));
    }

    @Given("{string} is complete")
    public void is_complete(String string) {
        try {
            application.createActivity(string, "Test");
            application.getActivity("Test").complete();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Given("{string} is incomplete")
    public void is_incomplete(String string) {
        try {
            application.createActivity(string, "Test");
            application.getActivity("Test").inComplete();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the employee requests the status of {string}")
    public void the_employee_requests_the_status_of(String project) {
        try {
            application.getProjectStatus(project);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the message {string} will be given")
    public void theMessageWillBeGiven(String status) throws DoesNotExistErrorException {
        assertEquals(status, application.getProjectStatus("project1"));
    }

    @Given("the project with name {string} does not exists")
    public void the_project_with_name_does_not_exists(String string) {
        try {
            application.getProject(string);
        } catch (Exception e) {
            assertFalse(false);
        }
    }
    @Then("no date is given")
    public void no_date_is_given() {
        assertNull(tempDate);
    }
}

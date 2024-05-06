package example.cucumber;

import dtu.application.Application;
import dtu.application.DoesNotExistException;
import dtu.application.OperationNotAllowedException;
import dtu.application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Calendar;

import static org.junit.Assert.*;

public class ShowBasicProjectInformationSteps {

    private Application application;
    private ErrorMessageHolder errorMessageHolder;
    private ObjectIdHolder objectIdHolder;
    private Calendar tempDate;
    private Boolean isComplete;
    public ShowBasicProjectInformationSteps(Application application, ErrorMessageHolder errorMessageHolder, ObjectIdHolder objectIdHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        application.resetAllIds();
        this.objectIdHolder = objectIdHolder;
    }


    @Given("the project with name {string} exists")
    public void the_project_with_name_exists(String projectname) {
        try {
            String projectId = application.createProject(projectname);
            objectIdHolder.setId(projectId);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("the activity with id {string} has the earliest start week {int} in year {int}")
    public void the_activity_with_id_has_the_earliest_start_week(String activityidentifier, int week, int year) throws DoesNotExistException, OperationNotAllowedException {
        Project project = application.getProject(objectIdHolder.getId());
        project.addActivity("Test");
        application.setStartWeekToActivity(activityidentifier, week, year);
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
    public void the_found_start_week_is_week(int week) throws DoesNotExistException {
        assertEquals(week, application.getStartDateForProject(objectIdHolder.getId()).get(Calendar.WEEK_OF_YEAR));
    }

    @Given("the activity with id {string} has the latest end week {int} in year {int}")
    public void the_activity_with_id_has_the_latest_end_week_in_year(String string, int week, int year) throws DoesNotExistException, OperationNotAllowedException {
        application.createActivity(objectIdHolder.getId(), "Test1");
        application.createActivity(objectIdHolder.getId(), "Test2");
        application.setEndWeekToActivity(string, week, year);
        application.setEndWeekToActivity("2", week, 2023);
    }
    @Given("the project with name {string} does not exist")
    public void the_project_with_name_does_not_exist(String string) {
        boolean matched = false;
        for (Project project : application.getProjects()) {
            if (project.getName().equals(string)) {
                matched = true;
            }
        }
        assertFalse(matched);
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
    public void the_found_end_week_is_week(int week) throws DoesNotExistException {
        assertEquals(week, application.getEndDateForProject(objectIdHolder.getId()).get(Calendar.WEEK_OF_YEAR));
    }

    @Given("the project only has 1 activity, which is completed")
    public void is_complete() {
        try {
            String activityId = application.createActivity(objectIdHolder.getId(), "Test");
            application.getActivity(activityId).complete();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Given("the project has an incomplete activity")
    public void is_incomplete() {
        try {
            application.createActivity(objectIdHolder.getId(), "Test");
            application.getActivity("Test").inComplete();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the employee requests the status of the project")
    public void the_employee_requests_the_status_of() {
        try {
            isComplete = application.getProjectStatus(objectIdHolder.getId());
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the project is complete")
    public void theProjectIsComplete() {
        assertTrue(isComplete);
    }

    @Then("the project is incomplete")
    public void theProjectIsIncomplete() {
        assertFalse(isComplete);
    }

    @Given("the project with name {string} does not exists")
    public void the_project_with_name_does_not_exists(String string) {
        try {
            application.getProject(string);
        } catch (Exception e) {
            assertFalse(false);
            assertFalse(application.doesProjectExist("24001"));
        }
    }
    @Then("no date is given")
    public void no_date_is_given() {
        assertNull(tempDate);
    }
}

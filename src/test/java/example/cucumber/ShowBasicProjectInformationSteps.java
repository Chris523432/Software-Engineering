package example.cucumber;

import dtu.application.Activity;
import dtu.application.Application;
import dtu.application.IdGenerator;
import dtu.application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class ShowBasicProjectInformationSteps {

    private Application application;
    private ErrorMessageHolder errorMessageHolder;

    private Project parentProject;
    private IdGenerator idGenerator = new IdGenerator();
    private Activity a;
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

    @Given("the activity with id {string} has the earliest start week {int}")
    public void the_activity_with_id_has_the_earliest_start_week(String string, Integer int1) throws Exception {
        application.getProject("project1").addProject("Test");
        application.getProject("project1").addProject("Test2");
        application.getActivity(string).setStartWeek(1, 2024);
        application.getActivity("2").setStartWeek(1, 2025);
    }

    @When("the employee requests the start week of project with name {string}")
    public void the_employee_requests_the_start_week_of_project_with_name(String string) throws Exception {
        try {
            application.getProject(string).getStartWeek();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the found start week is week {int}")
    public void the_found_start_week_is_week(Integer int1) throws Exception {
        String startweek = "Week: " + int1 + "Year: " + 2024;
        assertEquals(startweek, application.getProject("project1").getStartWeek());
    }

    @Given("the activity with id {string} has the latest end week {int}")
    public void the_activity_with_id_has_the_latest_end_week(String string, Integer int1) throws Exception {
        application.getProject("project1").addProject("Test");
        application.getProject("project1").addProject("Test2");
        application.getActivity(string).setEndWeek(7, 2024);
        application.getActivity("2").setEndWeek(10, 2024);
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
    public void the_employee_requests_the_end_week_of_project_with_name(String string) {
        try {
            application.getProject(string).getEndWeek();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the found end week is week {int}")
    public void the_found_end_week_is_week(Integer int1) throws Exception {
        String endweek = "Week: " + int1 + "Year: " + 2024;
        assertEquals(endweek, application.getProject("project1").getEndWeek());
    }

    @Given("{string} is complete")
    public void is_complete(String string) {
        try {
            application.getProject(string).addProject("Test");
            application.getActivity("Test").complete();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Given("{string} is incomplete")
    public void is_incomplete(String string) {
        try {
            application.getProject(string).addProject("Test");
            application.getActivity("Test").inComplete();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the employee requests the status of {string}")
    public void the_employee_requests_the_status_of(String string) throws Exception {
        try {
            application.getProject("project1").getProjectStatus();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the message {string} will be given")
    public void theMessageWillBeGiven(String status) throws Exception {
        assertEquals(status, application.getProject("project1").getProjectStatus());
    }

    @Given("the project with name {string} does not exists")
    public void the_project_with_name_does_not_exists(String string) throws Exception {
        try {
            application.getProject(string);
        } catch (Exception e) {
            assertFalse(false);
        }

    }
}

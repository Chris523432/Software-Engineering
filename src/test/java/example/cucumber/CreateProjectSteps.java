package example.cucumber;

import dtu.application.Application;
import dtu.application.DateServer;
import example.junit.MockDateHolder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;
//Bastian
public class CreateProjectSteps {

    private Application application;
    private ErrorMessageHolder errorMessageHolder;
    private MockDateHolder mockDateHolder;
    private DateServer dateServer;
    private String projectId;
    public CreateProjectSteps(Application application, ErrorMessageHolder errorMessageHolder, DateServer dateServer) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        this.dateServer = dateServer;
        mockDateHolder = new MockDateHolder(application);
        mockDateHolder.setYear2024();
    }
    @Given("there is not a project with the name {string}")
    public void there_is_not_a_project_with_the_name(String name) {
        assertFalse(application.getProjects().stream().anyMatch((proj) -> (proj.getName()).equals(name)));
    }
    @Given("the year is {int}")
    public void the_year_is(int year) {
        assertEquals(year, dateServer.getYear());
    }
    @When("a project with name {string} is created")
    public void a_project_with_name_is_created(String name) {
        try {
            projectId = application.createProject(name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("the project is in the system")
    public void the_project_is_in_the_system() {
        try {
            assertTrue(application.getProjects().contains(application.getProject(projectId)));
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
            fail();
        }
    }
    @Then("the project with number {string} is in the system")
    public void the_project_with_number_is_in_the_system(String id) {
        try {
            assertTrue(application.getProjects().contains(application.getProject(id)));
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Given("a project with empty name is created \\(string is empty or only spaces)")
    public void aProjectWithEmptyNameIsCreatedStringIsEmptyOrOnlySpaces() {
        try {
            application.createProject("    ");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String errorMessage) {
        assertEquals(errorMessage, this.errorMessageHolder.getErrorMessage());
    }
    @Then("project with empty name is not in the system")
    public void projectWithEmptyNameIsNotInTheSystem() {
        assertFalse(application.getProjects().stream().anyMatch((proj) -> (proj.getName()).trim().isEmpty()));
    }
}

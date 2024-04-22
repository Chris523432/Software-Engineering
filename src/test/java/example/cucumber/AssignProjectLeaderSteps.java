package example.cucumber;

import dtu.application.Activity;
import dtu.application.Application;
import dtu.application.IdGenerator;
import dtu.application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class AssignProjectLeaderSteps {

    private Application application;
    private ErrorMessageHolder errorMessageHolder;

    private Project parentProject;
    private IdGenerator idGenerator = new IdGenerator();
    private Activity a;
    public AssignProjectLeaderSteps(Application application, ErrorMessageHolder errorMessageHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        idGenerator.resetIds();
    }

    @When("the employee assigns {string} as project leader in {string}")
    public void the_employee_assigns_as_project_leader_in(String string, String string2) throws Exception {
        try {
            application.getProject(string2).assignProjectLeader(application.getEmployee("baba"));
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the employee {string} is the project leader in {string}")
    public void theEmployeeIsTheProjectLeaderIn(String projectleader, String project) throws Exception {
        assertEquals(application.getEmployee(projectleader), application.getProject(project).getProjectLeader());
    }
}

package example.cucumber;

import dtu.application.*;
import example.junit.MockDateHolder;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class AssignProjectLeaderSteps {

    private Application application;
    private ErrorMessageHolder errorMessageHolder;

    private Project parentProject;
    private MockDateHolder mockDateHolder;
    private Activity a;
    public AssignProjectLeaderSteps(Application application, ErrorMessageHolder errorMessageHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        application.resetAllIds();
        mockDateHolder = new MockDateHolder(application);
        mockDateHolder.setYear2024();
    }

    @When("the employee assigns {string} as project leader in {string}")
    public void the_employee_assigns_as_project_leader_in(String string, String string2) {
        try {
            application.assignProjectLeader(string2, string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the employee {string} is the project leader in {string}")
    public void theEmployeeIsTheProjectLeaderIn(String projectleader, String project) throws DoesNotExistErrorException {
        assertEquals(application.getEmployee(projectleader), application.getProject(project).getProjectLeader());
    }
}

package example.cucumber;

import dtu.application.*;
import example.junit.MockDateHolder;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;
//Christian
public class AssignProjectLeaderSteps {

    private Application application;
    private ErrorMessageHolder errorMessageHolder;
    private MockDateHolder mockDateHolder;
    private ObjectIdHolder projectIdHolder;

    public AssignProjectLeaderSteps(Application application, ErrorMessageHolder errorMessageHolder,ObjectIdHolder objectIdHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        application.resetAllIds();
        mockDateHolder = new MockDateHolder(application);
        mockDateHolder.setYear2024();
        this.projectIdHolder = objectIdHolder;
    }

    @When("the employee assigns {string} as project leader in the project")
    public void the_employee_assigns_as_project_leader_in(String string) {
        try {
            application.assignProjectLeader(projectIdHolder.getId(), string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the employee {string} is the project leader in the project")
    public void theEmployeeIsTheProjectLeaderIn(String projectleader) throws DoesNotExistException {
        assertEquals(application.getEmployee(projectleader),
                application.getProject(projectIdHolder.getId()).getProjectLeader());
    }
}

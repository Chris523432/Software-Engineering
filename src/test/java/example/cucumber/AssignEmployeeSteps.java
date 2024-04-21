package example.cucumber;

import dtu.application.Application;
import dtu.application.IdGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class AssignEmployeeSteps {
    private Application application;
    private ErrorMessageHolder errorMessageHolder;
    private String tempName;
    private IdGenerator idGenerator = new IdGenerator();
    public AssignEmployeeSteps(Application application, ErrorMessageHolder errorMessageHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        idGenerator.resetIds();
    }

    @Given("the employee with initials {string} is not assigned to activity with id {string}")
    public void theEmployeeWithInitialsIsNotAssignedToActivityWithId(String initials, String id) throws Exception {
        assertFalse(application.isAssignedSearch(id, initials));
    }

    @When("the employee with initials {string} is assigned to activity with id {string}")
    public void theEmployeeWithInitialsIsAssignedToActivityWithId(String initials, String id) {
        try {
            application.assignEmployee(id, initials);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity with id {string} has employee with initials {string} assigned")
    public void theActivityWithIdHasEmployeeWithInitialsAssigned(String id, String initials) throws Exception {
        assertTrue(application.isAssignedSearch(id, initials));
    }

    @Then("the error message {string} is given.")
    public void theErrorMessageIsGiven(String errorMessage) {
        assertEquals(errorMessage, errorMessageHolder.getErrorMessage());
    }
}

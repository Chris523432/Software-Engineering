package example.cucumber;
import dtu.application.Application;
import dtu.application.Employee;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class EmployeeLoginLogoutSteps {

    private Application application;
    private Employee employee;
    private String initials;
    private ErrorMessageHolder errorMessageHolder;

    public EmployeeLoginLogoutSteps(Application application, ErrorMessageHolder errorMessageHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
    }
    @Given("the employee with initials {string} is registered in the system")
    public void the_employee_with_initials_is_registered_in_the_system(String initials) {
        this.initials = initials;
        application.registerUser(this.initials);
    }
    @Given("the employee is not logged in")
    public void the_employee_is_not_logged_in() {
        try {
            assertFalse(application.isLoggedIn(initials));
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
     }
    @Then("the employee login succeeds")
    public void the_employee_login_succeeds() {
        try {
            application.login(initials);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("the employee is logged in")
    public void the_employee_is_logged_in() {
        try {
            assertTrue(application.isLoggedIn(initials));
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("the employee with initials {string} is not registered in the system")
    public void theEmployeeWithInitialsIsNotRegisteredInTheSystem(String initials) {
        assertFalse(application.getEmployees().stream().anyMatch((emp) -> emp.getInitials().equals(initials)));
    }
    @When("the employee logs in")
    public void theEmployeeLogsIn() {
        try {
            application.getEmployee(initials);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("the error message {string} is thrown")
    public void theErrorMessageIsThrown(String errorMessage) {
        assertEquals(errorMessage, this.errorMessageHolder.getErrorMessage());
    }

    @When("the employee logs out")
    public void theEmployeeLogsOut() {
        try {
            application.logout(initials);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
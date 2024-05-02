package example.junit;
import dtu.application.*;
import example.cucumber.ErrorMessageHolder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssignEmployeeTest {

    private Application application;
    private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
    @Before
    public void setUp() throws OperationNotAllowedException {
        application = new Application();
        application.createProject("p");
    }
    @Test
    public void inputSetA() throws DoesNotExistErrorException {
        try {
            application.createActivity("p", "a");
            application.registerUser("barc");
            application.assignEmployee("a", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertTrue(application.isAssignedSearch("a", "barc"));
    }
    @Test
    public void inputSetB() {
        try {
            application.createActivity("p", "a");
            application.registerUser("barc");
            application.assignEmployee("a", "barc");
            application.assignEmployee("a", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Employee is already assigned to activity", errorMessageHolder.getErrorMessage());
    }
    @Test
    public void inputSetC() {
        try {
            application.createActivity("p", "a");
            application.assignEmployee("a", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Employee does not exist", errorMessageHolder.getErrorMessage());
    }

    @Test
    public void inputSetD() {
        try {
            application.assignEmployee("a", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Activity does not exist", errorMessageHolder.getErrorMessage());
    }
}

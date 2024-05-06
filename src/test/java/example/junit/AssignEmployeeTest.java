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
        application.resetAllIds();
        application.createProject("p");
    }
    @Test
    public void inputSetA() throws DoesNotExistException {
        try {
            application.createActivity("24001", "a");
            application.registerUser("barc");
            application.assignEmployee("1", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertTrue(application.isAssignedSearch("1", "barc"));
    }
    @Test
    public void inputSetB() {
        try {
            application.createActivity("24001", "a");
            application.registerUser("barc");
            application.assignEmployee("1", "barc");
            application.assignEmployee("1", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Employee is already assigned to activity", errorMessageHolder.getErrorMessage());
    }
    @Test
    public void inputSetC() {
        try {
            application.createActivity("24001", "a");
            application.assignEmployee("1", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Employee does not exist", errorMessageHolder.getErrorMessage());
    }

    @Test
    public void inputSetD() {
        try {
            assertFalse(application.doesActivityExist("1"));
            application.assignEmployee("1", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Activity does not exist", errorMessageHolder.getErrorMessage());
    }
}

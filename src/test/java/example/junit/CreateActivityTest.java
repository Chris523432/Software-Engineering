package example.junit;
import dtu.application.*;
import example.cucumber.ErrorMessageHolder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class CreateActivityTest {
    private Application application;
    private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
    @Before
    public void setUp() {
        application = new Application();
    }

    @Test
    public void inputSetA() throws OperationNotAllowedException, DoesNotExistErrorException {
        String name = "test";
        application.createProject("p");
        Project p = application.getProject("p");
        assertTrue(p.getActivities().isEmpty());
        try {
            application.createActivity("p", name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertTrue(p.getActivities().contains(application.getActivity(name)));
    }

    @Test
    public void inputSetB() {
        String name = "test";
        try {
            application.createActivity("p", name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Project is not in the system", errorMessageHolder.getErrorMessage());
        try {
            application.getActivity(name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Activity does not exist", errorMessageHolder.getErrorMessage());
    }

    @Test
    public void inputSetC() throws OperationNotAllowedException, DoesNotExistErrorException {
        String name = "   ";
        try {
            application.createActivity("p", name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Activity can not be added without a name", errorMessageHolder.getErrorMessage());
        application.createProject("p");
        Project p = application.getProject("p");
        assertTrue(p.getActivities().isEmpty());
        try {
            application.createActivity("p", name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Activity can not be added without a name", errorMessageHolder.getErrorMessage());
        assertTrue(p.getActivities().isEmpty());
    }
}

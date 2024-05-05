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
        application.resetAllIds();
    }

    @Test
    public void inputSetA() throws OperationNotAllowedException, DoesNotExistException {
        String name = "test";
        application.createProject("p");
        Project p = application.getProject("24001");
        assertTrue(p.getActivities().isEmpty());
        try {
            String activityId = application.createActivity("24001", name);
            assertTrue(p.getActivities().contains(application.getActivity(activityId)));
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Test
    public void inputSetB() {
        String name = "test";
        try {
            application.createActivity("24001", name);
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
    public void inputSetC() throws OperationNotAllowedException, DoesNotExistException {
        String name = "   ";
        try {
            application.createActivity("24001", name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Activity can not be added without a name", errorMessageHolder.getErrorMessage());
        application.createProject("p");
        Project p = application.getProject("24001");
        assertTrue(p.getActivities().isEmpty());
        try {
            application.createActivity("24001", name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Activity can not be added without a name", errorMessageHolder.getErrorMessage());
        assertTrue(p.getActivities().isEmpty());
    }
}

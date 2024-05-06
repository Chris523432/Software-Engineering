package example.junit;

import dtu.application.*;
import example.cucumber.ErrorMessageHolder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
//Bastian
public class AssignProjectLeaderTest {
    private Application application;
    private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
    @Before
    public void setUp() {
        application = new Application();
        application.resetAllIds();
    }

    @Test
    public void inputSetA() throws OperationNotAllowedException, DoesNotExistException {
        application.createProject("p");
        application.registerUser("barc");
        Project p = application.getProject("24001");
        Employee e1 = application.getEmployee("barc");
        try {
            application.assignProjectLeader("24001", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals(e1, p.getProjectLeader());
        application.registerUser("tcbc");
        Employee e2 = application.getEmployee("tcbc");
        try {
            application.assignProjectLeader("24001", "tcbc"); //The previous leader is overwritten
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals(e2, p.getProjectLeader());
    }

    @Test
    public void inputSetB() throws DoesNotExistException, OperationNotAllowedException {
        application.createProject("p");
        try {
            assertNull(application.getProject("24001").getProjectLeader());
            application.assignProjectLeader("24001", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Employee does not exist", errorMessageHolder.getErrorMessage());
        assertNull(application.getProject("24001").getProjectLeader());
    }

    @Test
    public void inputSetC() throws DoesNotExistException {
        try {
            application.assignProjectLeader("24001", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Project is not in the system", errorMessageHolder.getErrorMessage());
        try {
            application.registerUser("barc");
            Employee e = application.getEmployee("barc");
            assertFalse(application.getProjects().stream().anyMatch(p -> p.getProjectLeader().equals(e)));
            application.assignProjectLeader("24001", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Project is not in the system", errorMessageHolder.getErrorMessage());
        Employee e = application.getEmployee("barc");
        assertFalse(application.getProjects().stream().anyMatch(p -> p.getProjectLeader().equals(e)));
    }
}

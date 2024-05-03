package example.junit;

import dtu.application.*;
import example.cucumber.ErrorMessageHolder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssignProjectLeaderTest {
    private Application application;
    private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
    @Before
    public void setUp() {
        application = new Application();
        application.resetAllIds();
    }

    @Test
    public void inputSetA() throws OperationNotAllowedException, DoesNotExistErrorException {
        application.createProject("p");
        application.registerUser("barc");
        Project p = application.getProject("24001");
        Employee e1 = application.getEmployee("barc");
        try {
            application.assignProjectLeader("24001", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        //shows this that if a new leader is assigned to the same project
        //then the previous leader is overwritten
        assertEquals(e1, p.getProjectLeader());
        assertTrue(e1.getLeadingProjects().contains(p));
        application.registerUser("tcbc");
        Employee e2 = application.getEmployee("tcbc");
        try {
            application.assignProjectLeader("24001", "tcbc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals(e2, p.getProjectLeader());
        assertTrue(e2.getLeadingProjects().contains(p));
        assertFalse(e1.getLeadingProjects().contains(p));
    }

    @Test
    public void inputSetB() throws DoesNotExistErrorException, OperationNotAllowedException {
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
    public void inputSetC() throws Exception {
        try {
            application.assignProjectLeader("24001", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Project is not in the system", errorMessageHolder.getErrorMessage());
        try {
            application.registerUser("barc");
            assertTrue(application.getEmployee("barc").getLeadingProjects().isEmpty());
            application.assignProjectLeader("24001", "barc");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals("Project is not in the system", errorMessageHolder.getErrorMessage());
        assertTrue(application.getEmployee("barc").getLeadingProjects().isEmpty());
    }
}

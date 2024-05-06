package example.junit;
import dtu.application.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
//Chris
public class EmployeeInfoTest {
    private Application application;
    private String projectId1;
    private String projectId2;

    @Before
    public void setUp() throws OperationNotAllowedException, DoesNotExistException {
        application = new Application();
        application.registerUser("Barc");
        projectId1 = application.createProject("Test1");
        projectId2 = application.createProject("Test2");
    }

    @Test
    public void inputSetA() throws DoesNotExistException {
        application.assignProjectLeader(projectId1, "Barc");
        application.assignProjectLeader(projectId2, "Barc");
        assertTrue(equalsEmployee(application.getEmployee("Barc")));
    }

    @Test
    public void inputSetB() throws DoesNotExistException {
        application.assignProjectLeader(projectId1, "Barc");
        assertTrue(equalsEmployee(application.getEmployee("Barc")));
    }
    @Test
    public void inputSetC() throws DoesNotExistException {
        assertTrue(equalsEmployee(application.getEmployee("Barc")));
    }

    public boolean equalsEmployee(Employee employee) throws DoesNotExistException {
        EmployeeInfo employeeInfo = application.getEmployeeInfo(employee.getInitials());
        boolean result = application.getEmployees().size() == application.getEmployeeInfoList().size()
                && employee.getInitials().equals(employeeInfo.getInitials());
        return result;
    }
}


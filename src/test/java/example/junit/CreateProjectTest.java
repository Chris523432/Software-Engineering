package example.junit;
import dtu.application.*;
import example.cucumber.ErrorMessageHolder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class CreateProjectTest {
    private Application application;
    private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
    @Before
    public void setUp() {
        application = new Application();
        application.resetAllIds();
    }

    @Test
    public void inputSetA() throws DoesNotExistErrorException {
        String name = "test";
        try {
            application.createProject(name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertTrue(application.getProjects().contains(application.getProject(name)));
    }

    @Test
    public void inputSetB() {
        String name = "   ";
        try {
            application.createProject(name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals(errorMessageHolder.getErrorMessage(), "Project can not be created without a name");
        assertTrue(application.getProjects().isEmpty());
    }
}

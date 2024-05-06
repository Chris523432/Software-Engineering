package example.junit;
import dtu.application.Application;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
//Bastian
public class TestIdGeneration {
    private MockDateHolder mockDateHolder;
    private Application application;
    @Before
    public void setUp() {
        application = new Application();
        application.resetAllIds();
        mockDateHolder = new MockDateHolder(application);
        mockDateHolder.setYear2024();
    }

    @Test
    public void testGenerateProjectCurrentYear() throws Exception {
        for (int i = 1; i <= 999; i++) {
            application.createProject("Test");
            int id = Integer.parseInt(application.getProjects().get(i - 1).getId());
            assertEquals(24000 + i, id);
        }
    }
    @Test
    public void testGenerateProjectNewYear() throws Exception {
        for (int i = 0; i < 9; i++) {
            for (int j = 1; j <= 10; j++) {
                application.createProject("Test");
                int id = Integer.parseInt(application.getProjects().get(((i) * 10) + j - 1).getId());
                int expectedId = (1000 * i) + 24000 + j;
                assertEquals(expectedId, id);
            }
            mockDateHolder.advanceDateByYear();
        }
    }
    @Test
    public void generateMoreProjectsThanIntended() throws Exception {
        testGenerateProjectCurrentYear(); //Makes 999 projects and validates the id's
        for (int i = 1; i <= 999; i++) {
            application.createProject("Test");
            int id = Integer.parseInt(application.getProjects().get(999 + i - 1).getId());
            assertEquals(1000 + (i - 1), id % 10000); //we only need to check the last 4 digits
        }
    }

    @Test
    public void generateActivityIds() throws Exception {
        for (int i = 0; i < 5; i++) {
            application.createProject("test");
            String projectId = application.getProjects().get(i).getId();
            for (int j = 1; j <= 100; j++) {
                application.createActivity(projectId, "test");
                int id = Integer.parseInt(application.getProject(projectId).getActivities().get(j - 1).getId());
                assertEquals((100 * i) + j, id);
            }
        }
    }
}

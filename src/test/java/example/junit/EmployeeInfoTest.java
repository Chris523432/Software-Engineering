package example.junit;
import dtu.application.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

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
                && equalsProjects(employee.getLeadingProjects(), employeeInfo.getLeadingProjects());
        return result;
    }
    public Boolean equalsProjects(List<Project> projects, List<ProjectInfo> projectInfoList) {
        boolean result = projects.size() == projectInfoList.size();
        for (int i = 0; i < projects.size(); i++) {
            result = result && equalsProject(projects.get(i), projectInfoList.get(i));
        }
        return result;
    }
    public Boolean equalsProject(Project project, ProjectInfo projectinfo) {
        boolean result = project.getName().equals(projectinfo.getName())
                && project.getId().equals(projectinfo.getId())
                && project.getEndDate() == projectinfo.getEndDate()
                && project.getStartDate() == projectinfo.getStartDate()
                && project.isComplete() == projectinfo.isComplete()
                && equals(project.getProjectLeader(), projectinfo.getProjectLeader())
                && equalsActivityList(project.getActivities(), projectinfo.getActivities());
        return result;
    }
    public Boolean equalsActivityList(List<Activity> activityList, List<ActivityInfo> activityInfoList) {
        boolean result = activityList.size() == activityInfoList.size();
        for (int i = 0; i < activityList.size(); i++) {
            String id = activityList.get(i).getId();
            String idInfo = activityInfoList.get(i).getId();
            result = result && id.equals(idInfo);
        }
        return result;
    }
    public Boolean equals(Employee employee, EmployeeInfo employeeinfo) {
        if (employee == null && employeeinfo == null) {
            return true;
        }
        boolean result = employee.getInitials().equals(employeeinfo.getInitials());
        result = result && employee.getLeadingProjects().size() == employeeinfo.getLeadingProjects().size();
        for (int i = 0; i < employee.getLeadingProjects().size(); i++) {
            String id = employee.getLeadingProjects().get(i).getId();
            String idInfo = employeeinfo.getLeadingProjects().get(i).getId();
            result = result && id.equals(idInfo);
        }
        return result;
    }
}


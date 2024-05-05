package example.junit;
import dtu.application.*;
import example.cucumber.ErrorMessageHolder;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProjectinfoTest {
    private Application application;
    private String projectId;
    private Project project;
    private String activityId1;
    private String activityId2;
    private ProjectInfo projectInfo;

    @Before
    public void setUp() throws OperationNotAllowedException, DoesNotExistException {
        application = new Application();
        projectId = application.createProject("Test");
        project = application.getProject(projectId);
        application.registerUser("Barc");
    }

    @Test
    public void inputsetA() throws DoesNotExistException, OperationNotAllowedException {
        application.assignProjectLeader(projectId, "Barc");
        activityId1 = application.createActivity(projectId, "Test");
        activityId2 = application.createActivity(projectId, "Test");
        application.getActivity(activityId1).setStartWeek(10, 2024);
        application.getActivity(activityId1).setEndWeek(15, 2024);
        application.getActivity(activityId2).setStartWeek(20, 2024);
        application.getActivity(activityId2).setEndWeek(30, 2024);
        projectInfo = new ProjectInfo(project);
        assertTrue(equalsProject(project, projectInfo));
    }
    @Test
    public void inputsetB() throws DoesNotExistException {
        application.assignProjectLeader(projectId, "Barc");
        projectInfo = new ProjectInfo(project);
        assertTrue(equalsProject(project, projectInfo));
    }

    @Test
    public void inputsetC() throws DoesNotExistException, OperationNotAllowedException {
        application.assignProjectLeader(projectId, "Barc");
        activityId1 = application.createActivity(projectId, "Test");
        activityId2 = application.createActivity(projectId, "Test");
        application.getActivity(activityId1).setStartWeek(15, 2024);
        application.getActivity(activityId1).setEndWeek(18, 2024);
        application.getActivity(activityId2).setStartWeek(5, 2024);
        application.getActivity(activityId2).setEndWeek(20, 2024);
        application.getActivity(activityId1).complete();
        application.getActivity(activityId2).complete();
        projectInfo = new ProjectInfo(project);
        assertTrue(equalsProject(project, projectInfo));
    }
    @Test
    public void inputsetD() throws DoesNotExistException, OperationNotAllowedException {
        activityId1 = application.createActivity(projectId, "Test");
        activityId2 = application.createActivity(projectId, "Test");
        application.getActivity(activityId1).setStartWeek(2, 2024);
        application.getActivity(activityId1).setEndWeek(9, 2024);
        application.getActivity(activityId2).setStartWeek(4, 2024);
        application.getActivity(activityId2).setEndWeek(10, 2024);
        application.getActivity(activityId1).complete();
        application.getActivity(activityId2).complete();
        projectInfo = new ProjectInfo(project);
        assertTrue(equalsProject(project, projectInfo));
    }
    @Test
    public void inputsetE() throws DoesNotExistException, OperationNotAllowedException {
        activityId1 = application.createActivity(projectId, "Test");
        activityId2 = application.createActivity(projectId, "Test");
        application.getActivity(activityId1).setStartWeek(25, 2024);
        application.getActivity(activityId1).setEndWeek(30, 2024);
        application.getActivity(activityId2).setStartWeek(50, 2024);
        application.getActivity(activityId2).setEndWeek(52, 2024);
        projectInfo = new ProjectInfo(project);
        assertTrue(equalsProject(project, projectInfo));
    }
    @Test
    public void inputsetF() {
        projectInfo = new ProjectInfo(project);
        assertTrue(equalsProject(project, projectInfo));
    }
    @Test
    public void inputsetG() throws DoesNotExistException, OperationNotAllowedException {
        application.assignProjectLeader(projectId, "Barc");
        activityId1 = application.createActivity(projectId, "Test");
        activityId2 = application.createActivity(projectId, "Test");
        application.getActivity(activityId1).setStartWeek(19, 2024);
        application.getActivity(activityId1).setEndWeek(29, 2024);
        application.getActivity(activityId2).setStartWeek(19, 2024);
        application.getActivity(activityId2).setEndWeek(30, 2024);
        application.getActivity(activityId1).complete();
        application.getActivity(activityId2).inComplete();
        projectInfo = new ProjectInfo(project);
        assertTrue(equalsProject(project, projectInfo));
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

    public Boolean equalsActivityList(List<Activity> activityList, List<ActivityInfo> activityInfoList) {
        boolean result = activityList.size() == activityInfoList.size();
        for (int i = 0; i < activityList.size(); i++) {
            String id = activityList.get(i).getId();
            String idInfo = activityInfoList.get(i).getId();
            result = result && id.equals(idInfo);
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
}

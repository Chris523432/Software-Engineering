package example.junit;
import dtu.application.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
//Chris
public class ActivityInfoTest {
    private Application application;
    private String projectId;
    private String activityId;
    @Before
    public void setUp() throws OperationNotAllowedException, DoesNotExistException {
        application = new Application();
        projectId = application.createProject("Test");
        activityId = application.createActivity(projectId, "Test");
        application.unCompleteActivity(activityId);
        application.registerUser("Barc");
        application.registerUser("Dodo");
    }
    @Test
    public void inputSetA() throws DoesNotExistException, OperationNotAllowedException {
        application.getActivity(activityId).setStartWeek(10,2024);
        application.getActivity(activityId).setEndWeek(24,2024);
        application.assignEmployee(activityId,"Barc");
        application.assignEmployee(activityId, "Dodo");
        application.setAllocatedTime(activityId, 72);
        assertTrue(equalActivity(application.getActivity(activityId), application.getActivityInfo(activityId)));
    }
    @Test
    public void inputSetB() throws DoesNotExistException, OperationNotAllowedException {
        application.getActivity(activityId).setStartWeek(5,2024);
        application.getActivity(activityId).setEndWeek(50,2024);
        application.assignEmployee(activityId,"Barc");
        application.assignEmployee(activityId, "Dodo");
        assertTrue(equalActivity(application.getActivity(activityId), application.getActivityInfo(activityId)));
    }
    @Test
    public void inputSetC() throws DoesNotExistException, OperationNotAllowedException {
        application.getActivity(activityId).setStartWeek(5,2024);
        application.getActivity(activityId).setEndWeek(50,2024);
        assertTrue(equalActivity(application.getActivity(activityId), application.getActivityInfo(activityId)));
    }
    @Test
    public void inputSetD() throws DoesNotExistException {
        assertTrue(equalActivity(application.getActivity(activityId), application.getActivityInfo(activityId)));
    }
    @Test
    public void inputSetE() throws DoesNotExistException {
        application.completeActivity(activityId);
        assertTrue(equalActivity(application.getActivity(activityId), application.getActivityInfo(activityId)));
    }



    public boolean equalActivityEmployeeList(Activity activity, ActivityInfo activityInfo) {
        boolean result = activity.getAssignedEmployees().size() == activityInfo.getAssignedEmployees().size();
        for (int i = 0; i < activity.getAssignedEmployees().size(); i++) {
            String id = activity.getAssignedEmployees().get(i).getInitials();
            String idInfo = activityInfo.getAssignedEmployees().get(i).getInitials();
            result = result && id.equals(idInfo);
        }
        return result;
    }

    public boolean equalActivity(Activity activity, ActivityInfo activityInfo) throws DoesNotExistException {
        boolean result = equalActivityEmployeeList(activity, activityInfo)
                && activity.getName().equals(activityInfo.getName())
                && activity.getId().equals(activityInfo.getId())
                && activity.getBudgetedHours() == activityInfo.getBudgetedHours()
                && activity.getEndDate() == activityInfo.getEndDate()
                && activity.getStartDate() == activityInfo.getStartDate()
                && application.isActivityComplete(activityId) == activityInfo.isComplete();
        return result;
    }


}

package dtu.application;

import java.util.Calendar;
import java.util.List;
//Thomas
public interface Model {
    void registerUser(String initials);

    String createProject(String name) throws OperationNotAllowedException;

    String createActivity(String projectIdentifier, String activityName) throws OperationNotAllowedException, DoesNotExistException;

    boolean isLoggedIn(String initials) throws Exception;

    void login(String initials) throws Exception;

    void logout(String initials) throws Exception;

    ProjectInfo getProjectInfo(String projectIdentifier) throws DoesNotExistException;

    EmployeeInfo getEmployeeInfo(String initials) throws DoesNotExistException;

    Boolean doesEmployeeExist(String initials);

    ActivityInfo getActivityInfo(String activityId) throws DoesNotExistException;

    boolean doesActivityExist(String activityIdentifier) throws DoesNotExistException;

    void assignEmployee(String aIdentifier, String initials) throws OperationNotAllowedException, DoesNotExistException;

    List<EmployeeInfo> getEmployeeInfoList();

    List<ProjectInfo> getProjectInfoList();

    void setAllocatedTime(String activityIdentifier, int hours) throws OperationNotAllowedException, DoesNotExistException;

    void assignProjectLeader(String project, String initials) throws DoesNotExistException;

    boolean doesProjectExist(String projectidentifier);

    void setStartWeekToActivity(String activity, int week, int year) throws OperationNotAllowedException, DoesNotExistException;

    void setEndWeekToActivity(String activity, int week, int year) throws OperationNotAllowedException, DoesNotExistException;

    Calendar getStartDateForActivity(String activity) throws DoesNotExistException;

    Calendar getEndDateForActivity(String activity) throws DoesNotExistException;

    Calendar getStartDateForProject(String project) throws DoesNotExistException;

    Calendar getEndDateForProject(String project) throws DoesNotExistException;

    //Christian
    boolean getProjectStatus(String project) throws DoesNotExistException;
    //Christian
    void completeActivity(String activityId) throws DoesNotExistException;
    //Christian
    void unCompleteActivity(String activityId) throws DoesNotExistException;
    //Christian
    boolean isActivityComplete(String activityId) throws DoesNotExistException;



    String getCurrentUser();
}

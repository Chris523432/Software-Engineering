package dtu.application;

import java.util.Calendar;
import java.util.List;

public interface Model {
    void registerUser(String initials);

    String createProject(String name) throws OperationNotAllowedException;

    String createActivity(String projectIdentifier, String activityName) throws OperationNotAllowedException, DoesNotExistErrorException;

    boolean isLoggedIn(String initials) throws Exception;

    void login(String initials) throws Exception;

    void logout(String initials) throws Exception;

    ProjectInfo getProjectInfo(String projectIdentifier) throws DoesNotExistErrorException;

    EmployeeInfo getEmployeeInfo(String initials) throws DoesNotExistErrorException;

    Boolean doesEmployeeExist(String initials);

    ActivityInfo getActivityInfo(String activityId) throws DoesNotExistErrorException;

    boolean doesActivityExist(String activityIdentifier) throws DoesNotExistErrorException;

    void assignEmployee(String aIdentifier, String initials) throws OperationNotAllowedException, DoesNotExistErrorException;

    List<EmployeeInfo> getEmployeeInfoList();

    List<ProjectInfo> getProjectInfoList();

    void setAllocatedTime(String activityIdentifier, int hours) throws OperationNotAllowedException, DoesNotExistErrorException;

    void assignProjectLeader(String project, String initials) throws DoesNotExistErrorException;

    boolean doesProjectExist(String projectidentifier);

    void addEmployee(String initials);

    void setStartWeekToActivity(String activity, int week, int year) throws OperationNotAllowedException, DoesNotExistErrorException;

    void setEndWeekToActivity(String activity, int week, int year) throws OperationNotAllowedException, DoesNotExistErrorException;

    Calendar getStartDateForActivity(String activity) throws DoesNotExistErrorException;

    Calendar getEndDateForActivity(String activity) throws DoesNotExistErrorException;

    Calendar getStartDateForProject(String project) throws DoesNotExistErrorException;

    Calendar getEndDateForProject(String project) throws DoesNotExistErrorException;

    boolean getProjectStatus(String project) throws DoesNotExistErrorException;

    String getCurrentUser();

    int getCurrentYear();
}

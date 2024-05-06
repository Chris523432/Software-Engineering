package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Application implements Model {
    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private DateServer dateServer = new DateServer();
    private String currentUser;
    @Override
    public void registerUser(String initials) { //Bastian
        Employee employee = new Employee(initials);
        employees.add(employee);
    }
    @Override
    public String createProject(String name) throws OperationNotAllowedException { //Bastian
        if (name.trim().isEmpty()) {
            throw new OperationNotAllowedException("Project can not be created without a name");
        }
        assert name != null && !name.trim().isEmpty(): "Pre-condition createProject";
        int year = dateServer.getDate().get(Calendar.YEAR) % 100;
        Project project = new Project(name, year);
        projects.add(project);
        assert projects.contains(project): "Post condition createProject";
        return project.getId();
    }
    @Override
    public String createActivity(String projectIdentifier, String activityName) throws OperationNotAllowedException, DoesNotExistException { //Bastian
        if (activityName.trim().isEmpty()) {
            throw new OperationNotAllowedException("Activity can not be added without a name");
        }
        Project p = getProject(projectIdentifier);
        assert activityName != null && !activityName.trim().isEmpty()
                && doesProjectExist(projectIdentifier): "Pre-condition createActivity";
        String activityId = p.addActivity(activityName);
        assert p.getActivities().contains(getActivity(activityId)): "Post condition createActivity";
        return activityId;
    }
    @Override
    public boolean isLoggedIn(String initials) { //Thomas
        return getCurrentUser() != null && getCurrentUser().equals(initials);
    }

    @Override
    public void login(String initials) throws Exception { //Bastian
        getEmployee(initials);
        currentUser = initials;
    }
    @Override
    public void logout(String initials) { //Bastian
        currentUser = null;
    }

    public Project getProject(String projectIdentifier) throws DoesNotExistException { //Bastian
        for (Project p : projects) {
            if (p.getId().equals(projectIdentifier)) {
                return p;
            }
        }
        throw new DoesNotExistException("Project is not in the system");
    }

    @Override
    public ProjectInfo getProjectInfo(String projectIdentifier) throws DoesNotExistException { //Thomas
        return new ProjectInfo(getProject(projectIdentifier));
    }

    public Employee getEmployee(String initials) throws DoesNotExistException { //Bastian
        for (Employee e : employees) {
            if (e.getInitials().equals(initials)) {
                return e;
            }
        }
        throw new DoesNotExistException("Employee does not exist");
    }

    @Override
    public EmployeeInfo getEmployeeInfo(String initials) throws DoesNotExistException { //Thomas
        return new EmployeeInfo(getEmployee(initials));
    }

    @Override
    public Boolean doesEmployeeExist(String initials) { //Chris
        for (Employee e : employees) {
            if (e.getInitials().equals(initials)) {
                return true;
            }
        }
        return false;
    }

    public Activity getActivity(String activityIdentifier) throws DoesNotExistException { //Bastian
        for (Project p : projects) {
            for (Activity a : p.getActivities()) {
                if (a.getId().equals(activityIdentifier)) {
                    return a;
                }
            }
        }
        throw new DoesNotExistException("Activity does not exist");
    }

    @Override
    public ActivityInfo getActivityInfo(String activityId) throws DoesNotExistException { //Thomas
        return new ActivityInfo(getActivity(activityId));
    }

    @Override
    public boolean doesActivityExist(String activityIdentifier) { //Chris
        for (Project p : projects) {
            for (Activity a : p.getActivities()) {
                if (a.getId().equals(activityIdentifier)) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void assignEmployee(String aIdentifier, String initials) throws OperationNotAllowedException, DoesNotExistException { //Bastian
        Activity a = getActivity(aIdentifier);
        Employee e = getEmployee(initials);
        if (isAssigned(a, e)) {
            throw new OperationNotAllowedException("Employee is already assigned to activity");
        }
        assert  doesActivityExist(aIdentifier) && doesEmployeeExist(initials)
                && !isAssignedSearch(aIdentifier, initials): "Pre-condition assignEmployee";
        a.assignEmployee(e);
        assert a.isAssigned(e): "Post condition assignEmployee";
    }
    public boolean isAssigned(Activity activity, Employee employee) {
        return activity.isAssigned(employee);
    }
    public boolean isAssignedSearch(String activityIdentifier, String initials) throws DoesNotExistException { //easier for tests
        return getActivity(activityIdentifier).isAssigned(getEmployee(initials));
    }

    public List<Employee> getEmployees() { //Bastian
        return employees;
    }
    @Override
    public List<EmployeeInfo> getEmployeeInfoList() { //Thomas
        List<EmployeeInfo> infoList = new ArrayList<>(projects.size());
        for (Employee employee : employees) {
            infoList.add(new EmployeeInfo(employee));
        }
        return infoList;
    }

    public List<Project> getProjects() { //Bastian
        return projects;
    }

    @Override
    public List<ProjectInfo> getProjectInfoList() { //Thomas
        List<ProjectInfo> infoList = new ArrayList<>(projects.size());
        for (Project project : projects) {
            infoList.add(new ProjectInfo(project));
        }
        return infoList;
    }
    @Override
    public void setAllocatedTime(String activityIdentifier, int hours) throws OperationNotAllowedException, DoesNotExistException { //Basian
        getActivity(activityIdentifier).setAllocatedTime(hours);
    }
    public void setDateServer(DateServer dateServer) { //Bastian
        this.dateServer = dateServer;
    }

    @Override
    public void assignProjectLeader(String project, String initials) throws DoesNotExistException { //Bastian
        Project p = getProject(project);
        Employee e1 = getEmployee(initials);
        assert doesProjectExist(project) && doesEmployeeExist(initials): "Pre-condition assignProjectLeader";
        p.assignProjectLeader(e1);
        assert p.getProjectLeader().equals(e1): "Post condition assignProjectLeader";
    }

    @Override
    public boolean doesProjectExist(String projectidentifier) { //Chris
        for (Project p : projects) {
           if(p.getId().equals(projectidentifier)) {
               return true;
           }
        }
        return false;
    }

    @Override
    public void setStartWeekToActivity(String activity, int week, int year) throws OperationNotAllowedException, DoesNotExistException { //Chris
        getActivity(activity).setStartWeek(week, year);
    }

    @Override
    public void setEndWeekToActivity(String activity, int week, int year) throws OperationNotAllowedException, DoesNotExistException { //Chris
        getActivity(activity).setEndWeek(week, year);
    }


    @Override
    public Calendar getStartDateForActivity(String activity) throws DoesNotExistException { //Chris
        return getActivity(activity).getStartDate();
    }

    @Override
    public Calendar getEndDateForActivity(String activity) throws DoesNotExistException { //Chris
        return getActivity(activity).getEndDate();
    }


    @Override
    public Calendar getStartDateForProject(String project) throws DoesNotExistException { //Chris
        return getProject(project).getStartDate();
    }

    @Override
    public Calendar getEndDateForProject(String project) throws DoesNotExistException { //Chris
        return getProject(project).getEndDate();
    }

    @Override
    public boolean getProjectStatus(String project) throws DoesNotExistException { //Christian
        return getProject(project).isComplete();
    }

    @Override
    public void completeActivity(String activityId) throws DoesNotExistException { //Christian
        getActivity(activityId).complete();
    }

    @Override
    public void unCompleteActivity(String activityId) throws DoesNotExistException { //Christian
        getActivity(activityId).inComplete();
    }

    @Override
    public boolean isActivityComplete(String activityId) throws DoesNotExistException { //Christian
        return getActivity(activityId).isComplete();
    }

    public void resetAllIds() { //Bastian
        new ActivityIdGenerator().resetId();
        new ProjectIdGenerator().resetId();
    }

    @Override
    public String getCurrentUser() { //Christian
        return currentUser;
    }
}

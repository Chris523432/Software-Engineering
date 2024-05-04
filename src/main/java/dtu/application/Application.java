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
    public void registerUser(String initials) {
        Employee employee = new Employee(initials);
        employees.add(employee);
    }
    @Override
    public String createProject(String name) throws OperationNotAllowedException {
        if (name.trim().isEmpty()) {
            throw new OperationNotAllowedException("Project can not be created without a name");
        }
        int year = dateServer.getDate().get(Calendar.YEAR) % 100;
        Project project = new Project(name, year);
        projects.add(project);
        return project.getId();
    }
    @Override
    public String createActivity(String projectIdentifier, String activityName) throws OperationNotAllowedException, DoesNotExistErrorException {
        if (activityName.trim().isEmpty()) {
            throw new OperationNotAllowedException("Activity can not be added without a name");
        }
        Project p = getProject(projectIdentifier);
        assert activityName != null && !activityName.trim().isEmpty() && projectIdentifier != null
                && !projectIdentifier.trim().isEmpty() && doesProjectExist(projectIdentifier): "Pre-condition createActivity";
        String activityId = p.addActivity(activityName);
        // TODO: er post assert korrekt?
        assert p.getActivities().contains(getActivity(activityId)): "Post condition createActivity";
        return activityId;
    }
    @Override
    public boolean isLoggedIn(String initials) throws Exception {
        return currentUser.equals(initials);
    }

    @Override
    public void login(String initials) throws Exception {
        currentUser = initials;
    }
    @Override
    public void logout(String initials) throws Exception {
        currentUser = null;
    }

    public Project getProject(String projectIdentifier) throws DoesNotExistErrorException {
        for (Project p : projects) {
            if (p.getId().equals(projectIdentifier)) {
                return p;
            }
        }
        throw new DoesNotExistErrorException("Project is not in the system");
    }

    @Override
    public ProjectInfo getProjectInfo(String projectIdentifier) throws DoesNotExistErrorException {
        return new ProjectInfo(getProject(projectIdentifier));
    }

    public Employee getEmployee(String initials) throws DoesNotExistErrorException {
        for (Employee e : employees) {
            if (e.getInitials().equals(initials)) {
                return e;
            }
        }
        throw new DoesNotExistErrorException("Employee does not exist");
    }

    @Override
    public EmployeeInfo getEmployeeInfo(String initials) throws DoesNotExistErrorException {
        return new EmployeeInfo(getEmployee(initials));
    }

    @Override
    public Boolean doesEmployeeExist(String initials) {
        for (Employee e : employees) {
            if (e.getInitials().equals(initials)) {
                return true;
            }
        }
        return false;
    }

    public Activity getActivity(String activityIdentifier) throws DoesNotExistErrorException {
        for (Project p : projects) {
            for (Activity a : p.getActivities()) {
                if (a.getId().equals(activityIdentifier)) {
                    return a;
                }
            }
        }
        throw new DoesNotExistErrorException("Activity does not exist");
    }

    @Override
    public ActivityInfo getActivityInfo(String activityId) throws DoesNotExistErrorException {
        return new ActivityInfo(getActivity(activityId));
    }

    @Override
    public boolean doesActivityExist(String activityIdentifier) throws DoesNotExistErrorException {
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
    public void assignEmployee(String aIdentifier, String initials) throws OperationNotAllowedException, DoesNotExistErrorException {
        Activity a = getActivity(aIdentifier);
        Employee e = getEmployee(initials);
        if (isAssigned(a, e)) {
            throw new OperationNotAllowedException("Employee is already assigned to activity");
        }
        assert aIdentifier != null && !aIdentifier.trim().isEmpty() && doesActivityExist(aIdentifier)
                && initials != null && !initials.trim().isEmpty() && doesEmployeeExist(initials)
                && !isAssignedSearch(aIdentifier, initials): "Pre-condition assignEmployee";
        a.assignEmployee(e);
        assert a.isAssigned(e): "Post condition assignEmployee";
    }
    public boolean isAssigned(Activity activity, Employee employee) {
        return activity.isAssigned(employee);
    }
    public boolean isAssignedSearch(String activityIdentifier, String initials) throws DoesNotExistErrorException { //easier for tests
        return getActivity(activityIdentifier).isAssigned(getEmployee(initials));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    @Override
    public List<EmployeeInfo> getEmployeeInfoList() {
        List<EmployeeInfo> infoList = new ArrayList<>(projects.size());
        for (Employee employee : employees) {
            infoList.add(new EmployeeInfo(employee));
        }
        return infoList;
    }

    public List<Project> getProjects() {
        return projects;
    }

    @Override
    public List<ProjectInfo> getProjectInfoList() {
        List<ProjectInfo> infoList = new ArrayList<>(projects.size());
        for (Project project : projects) {
            infoList.add(new ProjectInfo(project));
        }
        return infoList;
    }
    @Override
    public void setAllocatedTime(String activityIdentifier, int hours) throws OperationNotAllowedException, DoesNotExistErrorException {
        getActivity(activityIdentifier).setAllocatedTime(hours);
    }
    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }

    @Override
    public void assignProjectLeader(String project, String initials) throws DoesNotExistErrorException {
        Project p = getProject(project);
        Employee e1 = getEmployee(initials);
        Employee e2 = p.getProjectLeader();
        assert project != null && !project.trim().isEmpty() && doesProjectExist(project)
                && initials != null && !initials.trim().isEmpty() && doesEmployeeExist(initials): "Pre-condition assignProjectLeader";
        if (e1 != e2) {
            if (e2 != null) {
                e2.removeProject(p);
            }
            e1.addProject(p);
        }
        p.assignProjectLeader(e1);
        assert p.getProjectLeader().equals(e1): "Post condition assignProjectLeader";
    }

    @Override
    public boolean doesProjectExist(String projectidentifier) {
        for (Project p : projects) {
           if(p.getId().equals(projectidentifier)) {
               return true;
           }
        }
        return false;
    }

    @Override
    public void addEmployee(String initials) {
        employees.add(new Employee(initials));
    }


    @Override
    public void setStartWeekToActivity(String activity, int week, int year) throws OperationNotAllowedException, DoesNotExistErrorException {
        getActivity(activity).setStartWeek(week, year);
    }

    @Override
    public void setEndWeekToActivity(String activity, int week, int year) throws OperationNotAllowedException, DoesNotExistErrorException {
        getActivity(activity).setEndWeek(week, year);
    }


    @Override
    public Calendar getStartDateForActivity(String activity) throws DoesNotExistErrorException {
        return getActivity(activity).getStartDate();
    }

    @Override
    public Calendar getEndDateForActivity(String activity) throws DoesNotExistErrorException {
        return getActivity(activity).getEndDate();
    }


    @Override
    public Calendar getStartDateForProject(String project) throws DoesNotExistErrorException {
        return getProject(project).getStartDate();
    }

    @Override
    public Calendar getEndDateForProject(String project) throws DoesNotExistErrorException {
        return getProject(project).getEndDate();
    }

    @Override
    public boolean getProjectStatus(String project) throws DoesNotExistErrorException {
        return getProject(project).isComplete();
    }

    public void resetAllIds() {
        new ActivityIdGenerator().resetId();
        new ProjectIdGenerator().resetId();
    }

    @Override
    public String getCurrentUser() {
        return currentUser;
    }
    @Override
    public int getCurrentYear() {
        return dateServer.getYear();
    }


}

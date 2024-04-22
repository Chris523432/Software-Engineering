package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Application {
    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private DateServer dateServer = new DateServer();
    private String currentUser;
    public void registerUser(String initials) {
        Employee employee = new Employee(initials);
        employees.add(employee);
    }
    public void createProject(String name) throws Exception {
        if (name.trim().isEmpty()) {
            throw new Exception("Project can not be created without a name");
        }
        Project project = new Project(name, dateServer.getYearDigits());
        projects.add(project);
    }
    public void createActivity(String projectIdentifier, String activityName) throws Exception {
        if (activityName.trim().isEmpty()) {
            throw new Exception("Activity can not be added without a name");
        }
        Project p = getProject(projectIdentifier);
        p.addProject(activityName);
    }
    public boolean isLoggedIn(String initials) throws Exception {
        return getEmployee(initials).isLoggedIn();
    }

    public void login(String initials) throws Exception {
        currentUser = initials;
        getEmployee(initials).login();
    }
    public void logout(String initials) throws Exception {
        currentUser = null;
        getEmployee(initials).logout();
    }
    public Project getProject(String projectIdentifier) throws Exception {
        for (Project p : projects) {
            if (p.getName().equals(projectIdentifier) || p.getId().equals(projectIdentifier)) {
                return p;
            }
        }
        throw new Exception("Project is not in the system");
    }
    public Employee getEmployee(String initials) throws Exception {
        for (Employee e : employees) {
            if (e.getInitials().equals(initials)) {
                return e;
            }
        }
        throw new Exception("Employee does not exist");
    }
    public Activity getActivity(String activityIdentifier) throws Exception {
        for (Project p : projects) {
            for (Activity a : p.getActivities()) {
                if (a.getId().equals(activityIdentifier) || a.getName().equals(activityIdentifier)) {
                    return a;
                }
            }
        }
        throw new Exception("Activity does not exist");
    }
    public void assignEmployee(String aIdentifier, String initials) throws Exception {
        Activity a = getActivity(aIdentifier);
        Employee e = getEmployee(initials);
        if (isAssigned(a, e)) {
            throw new Exception("Employee is already assigned to activity");
        }
        a.assignEmployee(e);
    }
    public boolean isAssigned(Activity activity, Employee employee) {
        return activity.isAssigned(employee);
    }
    public boolean isAssignedSearch(String activityIdentifier, String initials) throws Exception { //easier for tests
        return getActivity(activityIdentifier).isAssigned(getEmployee(initials));
    }
    public void registerIllness(String initials) throws Exception {
        Employee e = getEmployee(initials);
        Calendar c = dateServer.getDate();
        if (e.isIll(c)) {
            throw new OperationNotAllowedException("Can't add absence to already existing absence");
        }
        e.registerIllness(dateServer.getDate());
    }
    public void registerIllnessSelf() throws Exception {
        registerIllness(currentUser);
    }
    public void registerVacation(String initials, Calendar startDate, Calendar endDate) throws Exception {
        Employee e = getEmployee(initials);
        Calendar c = dateServer.getDate();
        if (startDate.before(c) || endDate.before(startDate)) {
            throw new OperationNotAllowedException("Invalid dates");
        }
        if (e.isAbsent(c, startDate, endDate)) {
            throw new OperationNotAllowedException("Can't add absence to already existing absence");
        }
        e.registerVacation(startDate, endDate);
    }
    public void registerVacationSelf(Calendar startDate, Calendar endDate) throws Exception {
        registerVacation(currentUser, startDate, endDate);
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    public List<Project> getProjects() {
        return projects;
    }
    public void setAllocatedTime(String activityIdentifier, int hours) throws Exception {
        getActivity(activityIdentifier).setAllocatedTime(hours);
    }
    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }

    public void assignProjectLeader (String project, String employee) throws Exception {
        getProject(project).assignProjectLeader(getEmployee(employee));
    }

    public List<Employee> getProjectLeaders() {
        List<Employee> projectleaders = new ArrayList<>();
        for (Project p : projects) {
            if (p.getProjectLeader() != null && !projectleaders.contains(p.getProjectLeader())) {
                projectleaders.add(p.getProjectLeader());
            }
        }
        return projectleaders;
    }

    public void setStartWeekToActivity(String activity, int week, int year) throws Exception {
        getActivity(activity).setStartWeek(week, year);
    }

    public void setEndWeekToActivity(String activity, int week, int year) throws Exception {
        getActivity(activity).setEndWeek(week, year);
    }

    public int getEndWeekForActivity(String activity) throws Exception {
        return getActivity(activity).getEndWeek();
    }

    public int getStartWeekForActivity(String activity) throws Exception {
        return getActivity(activity).getStartWeek();
    }

    public String getStartWeekForProject(String project) throws Exception {
        return getProject(project).getStartWeek();
    }

    public String getEndWeekForProject(String project) throws Exception {
        return getProject(project).getEndWeek();
    }

    public String getProjectStatus(String project) throws Exception {
        return getProject(project).getProjectStatus();
    }
}

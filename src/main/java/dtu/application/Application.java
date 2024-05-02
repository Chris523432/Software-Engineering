package dtu.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Application {
    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private DateServer dateServer = new DateServer();
    //giver det mening, at employees har et isloggedIn, når det er denne vi bruger??
    private String currentUser;
    public void registerUser(String initials) {
        Employee employee = new Employee(initials);
        employees.add(employee);
    }
    public void createProject(String name) throws OperationNotAllowedException {
        if (name.trim().isEmpty()) {
            throw new OperationNotAllowedException("Project can not be created without a name");
        }
        int year = dateServer.getDate().get(Calendar.YEAR) % 100;
        Project project = new Project(name, year);
        projects.add(project);
    }
    public void createActivity(String projectIdentifier, String activityName) throws OperationNotAllowedException, DoesNotExistErrorException {
        if (activityName.trim().isEmpty()) {
            throw new OperationNotAllowedException("Activity can not be added without a name");
        }
        Project p = getProject(projectIdentifier);
        p.addActivity(activityName);
    }
    //kan vi ikke bare tjekke om de er currentUser?
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

    //tror vores testing liv bliver nemmere, hvis det kun er id. Men vi må lige se
    public Project getProject(String projectIdentifier) throws DoesNotExistErrorException {
        for (Project p : projects) {
            if (p.getName().equals(projectIdentifier) || p.getId().equals(projectIdentifier)) {
                return p;
            }
        }
        throw new DoesNotExistErrorException("Project is not in the system");
    }

    //Der skal returneres EmployeeInfo, hvis den er public.
    //for en privat metode er dette fint.
    //det samme gælder med getActivity osv.
    public Employee getEmployee(String initials) throws DoesNotExistErrorException {
        for (Employee e : employees) {
            if (e.getInitials().equals(initials)) {
                return e;
            }
        }
        throw new DoesNotExistErrorException("Employee does not exist");
    }
    public Activity getActivity(String activityIdentifier) throws DoesNotExistErrorException {
        for (Project p : projects) {
            for (Activity a : p.getActivities()) {
                if (a.getId().equals(activityIdentifier) || a.getName().equals(activityIdentifier)) {
                    return a;
                }
            }
        }
        throw new DoesNotExistErrorException("Activity does not exist");
    }
    public void assignEmployee(String aIdentifier, String initials) throws OperationNotAllowedException, DoesNotExistErrorException {
        Activity a = getActivity(aIdentifier);
        Employee e = getEmployee(initials);
        if (isAssigned(a, e)) {
            throw new OperationNotAllowedException("Employee is already assigned to activity");
        }
        a.assignEmployee(e);
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
    public List<Project> getProjects() {
        return projects;
    }
    public void setAllocatedTime(String activityIdentifier, int hours) throws OperationNotAllowedException, DoesNotExistErrorException {
        getActivity(activityIdentifier).setAllocatedTime(hours);
    }
    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }

    public void assignProjectLeader (String project, String employee) throws Exception {
        Project p = getProject(project);
        Employee e1 = getEmployee(employee);
        Employee e2 = p.getProjectLeader();
        if (e1 != e2) {
            swapProjectLeader(e1, e2, p);
        }
        p.assignProjectLeader(e1);
    }
    public void swapProjectLeader(Employee newLeader, Employee currentLeader, Project p) {
        if (currentLeader != null) {
            currentLeader.removeProject(p);
        }
        newLeader.addProject(p);
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

    public void addEmployee(String initials) {
        employees.add(new Employee(initials));
    }


    public void setStartWeekToActivity(String activity, int week, int year) throws OperationNotAllowedException, DoesNotExistErrorException {
        getActivity(activity).setStartWeek(week, year);
    }

    public void setEndWeekToActivity(String activity, int week, int year) throws OperationNotAllowedException, DoesNotExistErrorException {
        getActivity(activity).setEndWeek(week, year);
    }


    public Calendar getStartDateForActivity(String activity) throws DoesNotExistErrorException {
        return getActivity(activity).getStartDate();
    }

    public Calendar getEndDateForActivity(String activity) throws DoesNotExistErrorException {
        return getActivity(activity).getEndDate();
    }


    public Calendar getStartDateForProject(String project) throws DoesNotExistErrorException {
        return getProject(project).getStartDate();
    }

    public Calendar getEndDateForProject(String project) throws DoesNotExistErrorException {
        return getProject(project).getEndDate();
    }

    public String getProjectStatus(String project) throws DoesNotExistErrorException {
        return getProject(project).getProjectStatus();
    }

    public String getCurrentUser() {
        return currentUser;
    }
}

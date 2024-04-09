package dtu.application;

import dtu.example.ui.App;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Application {
    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private DateServer dateServer;

    public void registerUser(String initials) {
        Employee employee = new Employee(initials);
        employees.add(employee);
    }
    public void createProject(String name) throws Exception {
        if (!name.trim().isEmpty()) {
            Project project = new Project(name, dateServer.getYearString());
            projects.add(project);
        } else {
            throw new Exception("Project can not be created without a name");
        }
    }
    public boolean isLoggedIn(String initials) throws Exception {
        return getEmployee(initials).isLoggedIn();
    }

    public void login(String initials) throws Exception {
        getEmployee(initials).login();
    }
    public void logout(String initials) throws Exception {
        getEmployee(initials).logout();
    }
    public Project getProject(String nameOrId) throws Exception {
        for (Project p : projects) {
            if (p.getName().equals(nameOrId) || p.getId().equals(nameOrId)) {
                return p;
            }
        }
        throw new Exception("Project does not exist");
    }
    public Employee getEmployee(String initials) throws Exception {
        for (Employee e : employees) {
            if (e.getInitials().equals(initials)) {
                return e;
            }
        }
        throw new Exception("Employee does not exist");
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    public List<Project> getProjects() {
        return projects;
    }
    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }
}

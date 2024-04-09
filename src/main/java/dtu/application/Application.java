package dtu.application;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private List<Employee> employees = new ArrayList<Employee>();

    public void registerUser(String initials) {
        Employee employee = new Employee(initials);
        employees.add(employee);
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


}

package example.cucumber;

import dtu.application.Activity;
import dtu.application.Application;
import dtu.application.IdGenerator;
import dtu.application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class ShowBasicProjectInformationSteps {

    private Application application;
    private ErrorMessageHolder errorMessageHolder;

    private Project parentProject;
    private IdGenerator idGenerator = new IdGenerator();
    private Activity a;
    public ShowBasicProjectInformationSteps(Application application, ErrorMessageHolder errorMessageHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        idGenerator.resetIds();
    }

    @Given("employee {string} is logged in")
    public void employee_is_logged_in(String name) {
        application.registerUser(name);
        try {
            application.login(name);
            assertTrue(application.isLoggedIn(name));
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("the project with name {string} exists")
    public void the_project_with_name_exists(String projectname) {
        try {
            application.createProject(projectname);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}

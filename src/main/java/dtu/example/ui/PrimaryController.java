package dtu.example.ui;

import java.io.IOException;

import dtu.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {
    private View view;
    private Application application;
    public PrimaryController(View view, Application application) {
        this.view = view;
        this.application = application;
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    public void showLoginScreenBTN(ActionEvent event) {
        view.showLoginScreen();
    }
    public void showMainScreenBTN(ActionEvent event) {
        view.showMainScreen();
    }
    public void showProjectsScreenBTN(ActionEvent event) {
        view.showViewProjectsScreen();
    }
    public void showLogInScreenBTN(ActionEvent event) {
        view.showLoginScreen();
    }

    public void login(ActionEvent event) {
        String initials = view.getLogin();
        try {
            application.login(initials);
            view.showMainScreen();
        } catch(Exception e) {
            //TODO: show error message
        }
    }
    public void addProject(ActionEvent event) {
        String projectName = view.getNewProjectName();
        try {
            application.createProject(projectName);
        } catch (Exception e) {
            //TODO: show error message
        }
    }
    public void mainAddProject(ActionEvent event) {
        view.showAddProjectScreen();
    }
}

package dtu.example.ui;

import dtu.application.Project;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {
    private Stage primaryStage;
    private InitiateScenes sceneMenu;
    private PrimaryController controller;
    private dtu.application.Application application;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.application = new dtu.application.Application();
        application.addEmployee("buba");
        try {
            application.createProject("Coffee break");
            application.createActivity("24001","get coffee");
            application.setStartWeekToActivity("get coffee", 1,2024);
            application.setEndWeekToActivity("get coffee", 5, 2024);
            application.createActivity("24001","drink coffee");
            application.setStartWeekToActivity("drink coffee", 5, 2024);
            application.setEndWeekToActivity("drink coffee", 11, 2024);

        } catch (Exception e) {
            System.out.println(e);
        }
        //application.createProject("Test Project");
        this.controller = new PrimaryController(this,application);
        this.primaryStage = primaryStage;
        sceneMenu = new InitiateScenes(this, application);
        initiateButtons();
        showLoginScreen();

    }

    public void showLoginScreen() {
        primaryStage.setScene(sceneMenu.getLoginScreen());
        primaryStage.show();
    }

    public void showMainScreen() {
        primaryStage.setScene(sceneMenu.getMainScreen());
        primaryStage.show();
    }
    public void showAddProjectScreen() {
        primaryStage.setScene(sceneMenu.getAddProjectScreen());
        primaryStage.show();
    }

    public void showChooseProjectsScreen() {
        primaryStage.setScene(sceneMenu.getChooseProjectScreen());
        primaryStage.show();
    }
    public void showViewProjectScreen() {
        primaryStage.setScene(sceneMenu.getViewProjectScreen());
        primaryStage.show();
    }
    public void showAddActivityScreen() {
        primaryStage.setScene(sceneMenu.getAddActivityScreen());
        primaryStage.show();
    }
    public void showNewEmployeeScreen() {
        primaryStage.setScene(sceneMenu.getNewEmployeeScreen());
        primaryStage.show();
    }
    public void showAssignProjectLeaderScreen() {
        primaryStage.setScene(sceneMenu.getAssignProjectLeaderScreen());
        primaryStage.show();
    }

    public void initiateButtons() {
        sceneMenu.getLogInBTN().setOnAction(controller::login);
        sceneMenu.getLogOutBTN().setOnAction(controller::logout);
        sceneMenu.getMainAddProjectBTN().setOnAction(controller::mainAddProject);
        sceneMenu.getAddProjectAddProjectBTN().setOnAction(controller::addProjectAddProject);
        sceneMenu.getAddProjectBackBTN().setOnAction(controller::addProjectBack);
        sceneMenu.getMainViewProjectBTN().setOnAction(controller::mainViewProject);
        sceneMenu.getChooseProjectViewProjectBTN().setOnAction(controller::chooseProjectViewProject);
        sceneMenu.getChooseProjectBackBTN().setOnAction(controller::chooseProjectBack);
        sceneMenu.getViewProjectBackBTN().setOnAction(controller::viewProjectBack);
        sceneMenu.getViewProjectAddActivityBTN().setOnAction(controller::viewProjectAddActivity);
        //sceneMenu.getViewProjectDeleteActivityBTN().setOnAction(controller::viewProjectDeleteActivity);
        sceneMenu.getAddActivityAddActivityBTN().setOnAction(controller::addActivityAddActivity);
        sceneMenu.getAddActivityBackBTN().setOnAction(controller::addActivityBack);
        sceneMenu.getMainRegisterNewEmployeeBTN().setOnAction(controller::newEmployee);
        sceneMenu.getNewEmployeeBackBTN().setOnAction(controller::newEmployeeBack);
        sceneMenu.getNewEmployeeAddBTN().setOnAction(controller::newEmployeeAdd);
        sceneMenu.getViewProjectAssignProjectLeaderBTN().setOnAction(controller::viewProjectAssignProjectLeader);
        sceneMenu.getAssignProjectLeaderBackBTN().setOnAction(controller::assignProjectLeaderBack);
        sceneMenu.getAssignProjectLeaderAssignBTN().setOnAction(controller::assignProjectLeader);

    }
    public String getLogin() {
        return sceneMenu.getLogin();
    }
    public String getNewProjectName() {
        return sceneMenu.getProjectName();
    }

    public String getNewActivityName() {
        return sceneMenu.getNewActivityName();
    }
    public String getChooseProjectProjectID() {
        return sceneMenu.getChooseProjectProjectID();
    }
    public void updateViewProjectActivities(String projectID) {
        sceneMenu.updateViewProjectActivities(projectID);
    }

    public String getNewEmployeeInitials() {
        return sceneMenu.getNewEmployeeInitials();
    }
    public void updateProjectNames() {
        sceneMenu.updateProjectNames();
    }
    public String getAssignProjectLeaderInitials() {
        return sceneMenu.getAssignProjectLeaderInitials();
    }
    public String getNewActivityStartWeek() {
        return sceneMenu.getNewActivityStartWeek();
    }
    public String getNewActivityEndWeek() {
        return sceneMenu.getNewActivityEndWeek();
    }
}

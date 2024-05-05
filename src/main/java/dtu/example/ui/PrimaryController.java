package dtu.example.ui;

import java.io.IOException;

import dtu.application.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {
    private View view;
    private Model model;
    public PrimaryController(View view, Model model) {
        this.view = view;
        this.model = model;
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void login(ActionEvent event) {
        String initials = view.getLogin();
        try {
            model.login(initials);
            view.showMainScreen();
        } catch(Exception e) {
            //TODO: show error message
            System.out.println("User is not registered in system");
        }
    }
    public void logout(ActionEvent event) {
        view.showLoginScreen();
    }
    public void mainAddProject(ActionEvent event) {
        view.showAddProjectScreen();
    }
    public void addProjectAddProject(ActionEvent event) {
        String projectName = view.getNewProjectName();
        try {
            model.createProject(projectName);
            System.out.println("Project \"" + view.getNewProjectName() + "\" is added!");
            view.showMainScreen();
        } catch (Exception e) {
            System.out.println(e);
            //TODO: show error message
        }
    }
    public void addProjectBack(ActionEvent event) {
        view.showMainScreen();
    }

    //Don't know how to use updateProjects
    /*
    public void mainViewProjects(ActionEvent event) {
        view.showViewProjectsScreen();
    }
     */
    public void chooseProjectViewProject(ActionEvent event) {
        String projectID = view.getChooseProjectProjectID();
        if (projectID != null) {
            view.updateViewProjectActivities(projectID);
            view.showViewProjectScreen();
        }
    }
    public void chooseProjectBack(ActionEvent event) {
        view.showMainScreen();
    }
    public void viewProjectBack(ActionEvent event) {
        view.showChooseProjectsScreen();
    }
    public void viewProjectAddActivity(ActionEvent event) {
        view.showAddActivityScreen();
    }
    public void addActivityBack(ActionEvent event) {
        String projectID = view.getChooseProjectProjectID();
        view.updateViewProjectActivities(projectID);
        view.showViewProjectScreen();
    }
    public void addActivityAddActivity(ActionEvent event) {
        //sry jeg har fucked med den her metode.
        //jeg anbefaler man ikke kan vælge andet end et navn, når den oprettes og måske budgetteret tid
        //så bliver det her noget nemmere.
        //TODO: fix this shit
        String activityName = view.getNewActivityName();
        String projectIdentifier = view.getChooseProjectProjectID();
        int startWeek,endWeek;
        try {
            String startWeekInput = view.getNewActivityStartWeek();
            startWeek = Integer.parseInt(startWeekInput);
            String endWeekInput = view.getNewActivityEndWeek();
            endWeek = Integer.parseInt(endWeekInput);
            int startYear = model.getCurrentYear();
            int endYear = model.getCurrentYear();
            model.createActivity(projectIdentifier, activityName);
            if (startWeekInput != null) {
                model.setStartWeekToActivity(activityName, startWeek, startYear);
            }
            if (endWeekInput != null) {
                model.setEndWeekToActivity(activityName, endWeek, endYear);
            }
            System.out.println("Activity \"" + activityName + "\" has been added to Project \"" + projectIdentifier + "\"");
            System.out.println("Start Week: " + view.getNewActivityStartWeek());
            System.out.println("End Week: " + view.getNewActivityEndWeek());
        } catch (Exception e) {
            //TODO: show error message
            System.out.println(e);
        }

    }

    public void newEmployee(ActionEvent event) {
        view.showNewEmployeeScreen();
    }
    public void newEmployeeBack(ActionEvent event) {
        view.showMainScreen();
    }
    public void newEmployeeAdd(ActionEvent event) {
        String newEmployeeInitials = view.getNewEmployeeInitials();
        try {
            model.addEmployee(newEmployeeInitials);
            System.out.println("Employee " + newEmployeeInitials + " has been added to the system");
        } catch (Exception e) {
            //TODO: show error message
        }
    }
    public void mainViewProject(ActionEvent event) {
        view.updateProjectNames();
        view.showChooseProjectsScreen();
    }
    public void viewProjectAssignProjectLeader(ActionEvent event) {
        view.showAssignProjectLeaderScreen();
    }
    public void assignProjectLeaderBack(ActionEvent event) {
        view.showViewProjectScreen();
    }

    public void assignProjectLeader(ActionEvent event) {
        String projectLeaderInitials = view.getAssignProjectLeaderInitials();
        String projectID = view.getChooseProjectProjectID();
        try {
            model.assignProjectLeader(projectID, projectLeaderInitials);
            System.out.println("\"" + projectLeaderInitials + "\"" + " was assigned project leader of project: " + projectID);
        } catch (Exception e) {
            //TODO: show error message
        }
    }




}

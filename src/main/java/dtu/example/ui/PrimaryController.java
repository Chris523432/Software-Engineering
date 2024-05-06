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
            System.out.println(e);
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
            showError(e.getMessage());
        }
    }
    public void addProjectBack(ActionEvent event) {
        view.showMainScreen();
    }

    public void chooseProjectViewProject(ActionEvent event) {
        view.updateViewProjectTitle();
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
        String activityName = view.getNewActivityName();
        String projectIdentifier = view.getChooseProjectProjectID();
        try {
            model.createActivity(projectIdentifier, activityName);
            System.out.println("Activity \"" + activityName + "\" has been added to Project \"" + projectIdentifier + "\"");
        } catch (Exception e) {
            showError(e.getMessage());
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
        view.updateViewProjectTitle();
        view.showViewProjectScreen();
    }

    public void assignProjectLeaderAssign(ActionEvent event) {
        view.updateViewProjectTitle();
        String projectLeaderInitials = view.getAssignProjectLeaderInitials();
        String projectID = view.getChooseProjectProjectID();
        try {
            model.assignProjectLeader(projectID, projectLeaderInitials);
            System.out.println("\"" + projectLeaderInitials + "\"" + " was assigned project leader of project: " + projectID);
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    public void viewProjectEditActivity(ActionEvent event) {
        if (view.getViewProjectChosenActivityID() != null) {
            view.showEditActivityScreen();
        }
    }
    public void editActivityBack(ActionEvent event) {
        view.updateViewProjectActivities(view.getChooseProjectProjectID());
        view.showViewProjectScreen();
    }
    public void editActivitySaveChanges(ActionEvent event) {
        String id = view.getViewProjectChosenActivityID();
        String weekInput = view.getEditActivityWeek();
        String yearInput = view.getEditActivityYear();
        String dateField = view.getSelectedDateField();
        String allocatedTimeInput = view.getEditActivityAllocatedTime();
        try {
            if (dateField == null) {
                throw new Exception("Please select what to edit");
            } else if (dateField.equals("Allocated Time")) {
                int allocatedTime;
                try {
                    allocatedTime = Integer.parseInt(allocatedTimeInput);
                } catch (Exception e) {
                    throw new Exception("Allocated Time must be integer");
                }
                model.setAllocatedTime(id, allocatedTime);
            } else {
                int week;
                int year;
                try {
                    week = Integer.parseInt(weekInput);
                    year = Integer.parseInt(yearInput);
                } catch (Exception e) {
                    throw new Exception("Week and year must be integers");
                }
                if (dateField.equals("Start Date")) {
                    model.setStartWeekToActivity(id, week, year);
                } else {
                    model.setEndWeekToActivity(id, week, year);
                }

            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }
    public void assignEmployeeBack(ActionEvent event) {
        view.updateViewProjectActivities(view.getChooseProjectProjectID());
        view.showViewProjectScreen();
    }
    public void viewProjectAssignEmployee(ActionEvent event) {
        if (view.getViewProjectChosenActivityID() != null) {
            view.showAssignEmployeeScreen();
        }
    }
    public void assignEmployeeAssignEmployee(ActionEvent event) {
        String id = view.getViewProjectChosenActivityID();
        String userID = view.getAssignEmployee();
        try {
            model.assignEmployee(id, userID);
            System.out.println("Employee \"" + userID + "\" was assigned activity \"" + id + "\"");
        } catch (Exception e) {
            System.out.println(e);
            showError(e.getMessage());
            //TODO: show error message
        }
    }
    public void viewProjectToggleCompletion(ActionEvent event) {
        String id = view.getViewProjectChosenActivityID();
        try {
            if (model.isActivityComplete(id)) {
                model.unCompleteActivity(id);
            } else {
                model.completeActivity(id);
            }
        } catch (Exception e) {
            //TODO: show error message
            System.out.println(e);
        }
        view.updateViewProjectActivities(view.getChooseProjectProjectID());
        view.showViewProjectScreen();
    }
    public void errorBackToMain(ActionEvent event) {
        view.showMainScreen();
    }
    public void showError(String message) {
        view.updateErrorMessage(message);
        view.showErrorScreen();
    }
}

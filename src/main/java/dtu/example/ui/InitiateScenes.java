package dtu.example.ui;

import dtu.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Calendar;
import java.util.List;

public class InitiateScenes {
    // Everything on loginScreen
    private Scene loginScreen;
    private TextField userLoginField;
    private Button logInBTN;
    private Text loginError;

    // Everything on mainScreen
    private Scene mainScreen;
    private Button logOutBTN;
    private Button mainAddProjectBTN;
    private Button mainViewProjectBTN;

    // Everything on addProjectScreen
    private Scene addProjectScreen;
    private Button addProjectBackBTN;
    private TextField newProjectName;
    private Button addProjectAddProjectBTN;

    // Everything on chooseProjectScreen
    private Scene chooseProjectScreen;
    private Button chooseProjectBackBTN;
    private ComboBox chooseProjectActiveProjects;
    private ObservableList<String> projectNames;
    private Button chooseProjectViewProjectBTN;

    // Everything on viewProjectScreen
    private Scene viewProjectScreen;
    private Button viewProjectBackBTN;
    private ObservableList<String> viewProjectActivities;
    private Button viewProjectAddActivityBTN;
    private Button viewProjectAssignProjectLeaderBTN;
    private Button viewProjectEditActivityBTN;
    private Button viewProjectAssignEmployeeBTN;
    private Button viewProjectChangeCompletionStatusBTN;
    private ListView<String> projectInfo;
    private Text sceneTitle;
    private Text basicProjectInfo;


    // Everything on addActivityScreen
    private Scene addActivityScreen;
    private Button addActivityBackBTN;
    private TextField newActivityNameTF;
    private Button addActivityAddActivityBTN;


    // Everything on assignProjectLeaderScreen
    private Scene assignProjectLeaderScreen;
    private Button assignProjectLeaderBackBTN;
    private TextField assignProjectLeaderInitialsTF;
    private Button assignProjectLeaderAssignBTN;

    // Everything on editActivityScreen
    private Scene editActivityScreen;
    private TextField editActivityWeekTF;
    private TextField editActivityYearTF;
    private Button editActivityBackBTN;
    private Button editActivitySaveChangesBTN;
    private ComboBox editActivityCB;
    private TextField editActivityAllocatedTimeTF;

    // Everything on assignEmployeeScreen
    private Scene assignEmployeeScreen;
    private Button assignEmployeeBackBTN;
    private TextField assignEmployeeTF;
    private Button assignEmployeeAssignBTN;

    // Everything on ErrorScreen
    private Scene errorScreen;
    private Button errorBackToMain;
    private Text errorMessage;

    private View view;
    private Model model;
    private int XDim = 500;
    private int YDim = 300;


    public InitiateScenes(View view, Model model) {
        this.view = view;
        this.model = model;
        initiateLoginScreen();
        initiateMainScreen();
        initiateAddProjectScreen();
        initiateChooseProjectScreen();
        initiateViewProjectScreen();
        initiateAddActivityScreen();
        initiateAssignProjectLeaderScreen();
        initiateEditActivityScreen();
        initiateAssignEmployeeScreen();
        initiateErrorScreen();
    }

    public void initiateLoginScreen() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        userLoginField = new TextField();
        grid.add(userLoginField, 1, 1);

        logInBTN = new Button("Log in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(logInBTN);
        grid.add(hbBtn, 1, 2);

        loginError = new Text("");

        grid.add(loginError,1,3);

        grid.setGridLinesVisible(false);

        loginScreen = new Scene(grid, 300, 275);
    }

    public void initiateMainScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25, 25, 25, 25));

        BorderPane bpTitle = new BorderPane();
        bpTitle.setPadding(new Insets(25, 25, 25, 25));
        Text sceneTitle = new Text("Hello User");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);

        mainAddProjectBTN = new Button("Add Project");
        mainAddProjectBTN.setMinWidth(200);
        mainViewProjectBTN = new Button("View your current projects");
        mainViewProjectBTN.setMinWidth(200);
        logOutBTN = new Button("Log out");

        VBox vbBtn = new VBox(10);
        vbBtn.setAlignment(Pos.CENTER);
        vbBtn.getChildren().addAll(mainAddProjectBTN, mainViewProjectBTN);
        BP.setCenter(vbBtn);
        BP.setTop(logOutBTN);
        bpTitle.setTop(sceneTitle);


        mainScreen = new Scene(new StackPane(bpTitle, BP), XDim, YDim);
    }

    public void initiateAddProjectScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25, 25, 25, 25));

        BorderPane bpTitle = new BorderPane();
        bpTitle.setPadding(new Insets(25, 25, 25, 25));
        Text sceneTitle = new Text("Add Project");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);
        bpTitle.setTop(sceneTitle);

        addProjectBackBTN = new Button("Back");
        BP.setTop(addProjectBackBTN);

        addProjectAddProjectBTN = new Button("Add Project");
        newProjectName = new TextField();

        HBox hbAddProject = new HBox(10);
        hbAddProject.setAlignment(Pos.CENTER);
        hbAddProject.getChildren().addAll(newProjectName, addProjectAddProjectBTN);

        BP.setCenter(hbAddProject);

        addProjectScreen = new Scene(new StackPane(bpTitle, BP), XDim, YDim);

    }

    public void initiateChooseProjectScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25, 25, 25, 25));

        BorderPane bpTitle = new BorderPane();
        bpTitle.setPadding(new Insets(25, 25, 25, 25));
        Text sceneTitle = new Text("Projects");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);
        bpTitle.setTop(sceneTitle);

        projectNames = FXCollections.observableArrayList();
        updateProjectNames();

        chooseProjectActiveProjects = new ComboBox(projectNames);
        chooseProjectActiveProjects.setVisibleRowCount(5);
        chooseProjectActiveProjects.setPromptText("Project");

        chooseProjectViewProjectBTN = new Button("View Project");
        chooseProjectBackBTN = new Button("Back");
        HBox hbProjects = new HBox(10);
        hbProjects.setAlignment(Pos.CENTER);
        hbProjects.getChildren().addAll(chooseProjectActiveProjects, chooseProjectViewProjectBTN);

        BP.setCenter(hbProjects);
        BP.setTop(chooseProjectBackBTN);

        chooseProjectScreen = new Scene(new StackPane(bpTitle, BP), XDim, YDim);
    }

    public void initiateViewProjectScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25, 25, 25, 25));

        BorderPane bpTitle = new BorderPane();
        bpTitle.setPadding(new Insets(25, 25, 25, 25));
        sceneTitle = new Text("");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.TOP_CENTER);
        bpTitle.setTop(sceneTitle);

        viewProjectBackBTN = new Button("Back");
        viewProjectAssignProjectLeaderBTN = new Button("Assign Project Leader");
        viewProjectAddActivityBTN = new Button("Add Activity");
        viewProjectEditActivityBTN = new Button("Edit Activity");
        viewProjectAssignEmployeeBTN = new Button("Assign Employee");
        viewProjectChangeCompletionStatusBTN = new Button("Toggle Completion");
        basicProjectInfo = new Text("");

        VBox vbManageProject = new VBox(10);
        vbManageProject.getChildren().addAll(viewProjectAssignProjectLeaderBTN, viewProjectAddActivityBTN, viewProjectEditActivityBTN,
                viewProjectAssignEmployeeBTN, viewProjectChangeCompletionStatusBTN, basicProjectInfo);

        viewProjectActivities = FXCollections.observableArrayList();
        projectInfo = new ListView<>(viewProjectActivities);

        HBox viewProjectHB = new HBox(10);
        viewProjectHB.setAlignment(Pos.CENTER);
        viewProjectHB.getChildren().addAll(projectInfo, vbManageProject);

        BP.setCenter(viewProjectHB);
        BP.setTop(viewProjectBackBTN);
        //BP.setRight(vbManageProject);

        viewProjectScreen = new Scene(new StackPane(bpTitle, BP), XDim * 1.5, YDim * 1.5);
    }

    public void initiateAddActivityScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25, 25, 25, 25));

        BorderPane bpTitle = new BorderPane();
        bpTitle.setPadding(new Insets(25, 25, 25, 25));
        Text sceneTitle = new Text("Add Activity");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);
        bpTitle.setTop(sceneTitle);

        addActivityBackBTN = new Button("Back");
        addActivityAddActivityBTN = new Button("Add Activity");

        newActivityNameTF = new TextField();
        Label newActivityNameL = new Label("Activity name");

        HBox addActivityHB = new HBox(10);
        addActivityHB.setAlignment(Pos.CENTER);
        addActivityHB.getChildren().addAll(newActivityNameTF, newActivityNameL);

        BP.setTop(addActivityBackBTN);
        BP.setCenter(addActivityHB);
        BP.setBottom(addActivityAddActivityBTN);

        addActivityScreen = new Scene(new StackPane(bpTitle, BP), XDim, YDim);

    }
    public void initiateAssignProjectLeaderScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25, 25, 25, 25));

        BorderPane bpTitle = new BorderPane();
        bpTitle.setPadding(new Insets(25, 25, 25, 25));
        Text sceneTitle = new Text("Assign Project Leader");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);
        bpTitle.setTop(sceneTitle);

        assignProjectLeaderBackBTN = new Button("Back");
        assignProjectLeaderInitialsTF = new TextField();
        assignProjectLeaderAssignBTN = new Button("Assign leader to project");

        HBox assignProjectHB = new HBox(10);
        assignProjectHB.setAlignment(Pos.CENTER);
        assignProjectHB.getChildren().addAll(assignProjectLeaderInitialsTF, assignProjectLeaderAssignBTN);

        BP.setTop(assignProjectLeaderBackBTN);
        BP.setCenter(assignProjectHB);

        assignProjectLeaderScreen = new Scene(new StackPane(bpTitle, BP), XDim, YDim);
    }
    public void initiateEditActivityScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25, 25, 25, 25));

        BorderPane bpTitle = new BorderPane();
        bpTitle.setPadding(new Insets(25, 25, 25, 25));
        Text sceneTitle = new Text("Edit Activity");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);
        bpTitle.setTop(sceneTitle);

        editActivityBackBTN = new Button("Back");
        editActivitySaveChangesBTN = new Button("Save Changes");

        Text text1 = new Text("Edit: ");
        ObservableList<String> editOptions = FXCollections.observableArrayList();
        editOptions.add("Start Date");
        editOptions.add("End Date");
        editOptions.add("Allocated Time");
        editActivityCB = new ComboBox(editOptions);

        HBox editActivityTopHB = new HBox(10);
        editActivityTopHB.setAlignment(Pos.CENTER);
        editActivityTopHB.getChildren().addAll(text1, editActivityCB);

        editActivityWeekTF = new TextField();
        editActivityYearTF = new TextField();
        Label week = new Label("Week: ");
        Label year = new Label("Year: ");

        VBox editActivityLeftVB = new VBox();
        editActivityLeftVB.setAlignment(Pos.CENTER);
        editActivityLeftVB.getChildren().addAll(week, editActivityWeekTF, year, editActivityYearTF);

        editActivityAllocatedTimeTF = new TextField();
        Label editActivityAllocatedTimeL = new Label("Set Allocated Time: ");

        VBox editActivityRightVB = new VBox();
        editActivityRightVB.setAlignment(Pos.TOP_CENTER);
        editActivityRightVB.getChildren().addAll(editActivityAllocatedTimeL, editActivityAllocatedTimeTF);

        HBox editActivityBottomHB = new HBox(30);
        editActivityBottomHB.setAlignment(Pos.CENTER);
        editActivityBottomHB.getChildren().addAll(editActivityLeftVB, editActivityRightVB);

        VBox editActivityVB = new VBox(30);
        editActivityVB.setAlignment(Pos.CENTER);
        editActivityVB.getChildren().addAll(editActivityTopHB, editActivityBottomHB);

        BP.setCenter(editActivityVB);
        BP.setTop(editActivityBackBTN);
        BP.setBottom(editActivitySaveChangesBTN);

        editActivityScreen = new Scene(new StackPane(bpTitle, BP), XDim, YDim);
    }
    public void initiateAssignEmployeeScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25, 25, 25, 25));

        BorderPane bpTitle = new BorderPane();
        bpTitle.setPadding(new Insets(25, 25, 25, 25));
        Text sceneTitle = new Text("Assign Employee to Activity");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);
        bpTitle.setTop(sceneTitle);

        assignEmployeeBackBTN = new Button("Back");
        assignEmployeeTF = new TextField();
        assignEmployeeAssignBTN = new Button("Assign Employee");

        HBox assignEmployeeHB = new HBox(10);
        assignEmployeeHB.setAlignment(Pos.CENTER);
        assignEmployeeHB.getChildren().addAll(assignEmployeeTF, assignEmployeeAssignBTN);

        BP.setTop(assignEmployeeBackBTN);
        BP.setCenter(assignEmployeeHB);

        assignEmployeeScreen = new Scene(new StackPane(bpTitle, BP), XDim, YDim);
    }

    public void initiateErrorScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25, 25, 25, 25));
        Text sceneTitle = new Text("ERROR");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);

        errorBackToMain = new Button("Back To Main");
        errorMessage = new Text("");
        VBox errorVB = new VBox(10);

        errorVB.setAlignment(Pos.CENTER);
        errorVB.getChildren().addAll(sceneTitle,errorMessage, errorBackToMain);

        BP.setCenter(errorVB);

        errorScreen = new Scene(BP, XDim, YDim);
    }
    public void updateProjectNames() {
        projectNames.clear();
        List<ProjectInfo> projects = model.getProjectInfoList();
        for (ProjectInfo p : projects) {
            String display = p.getName() + " - " + p.getId();
            projectNames.add(display);
        }
        //projectNames.add("Test Project");
    }

    public void updateViewProjectTitle() {
        String projectID = view.getChooseProjectProjectID();
        try {
            ProjectInfo project = model.getProjectInfo(projectID);
            String leader = project.getProjectLeader() == null ? "No leader" : project.getProjectLeader().getInitials();
            String title = project.getName() + " | Project Leader: " + leader;
            sceneTitle.setText(title);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void updateBasicProjectInfo(String projectId) {
        try {
            ProjectInfo project = model.getProjectInfo(projectId);
            String startDate = project.getStartDate() == null ? "No start date" : "Week: " + project.getStartDate().get(Calendar.WEEK_OF_YEAR) + " - " + project.getStartDate().get(Calendar.YEAR);
            String endDate = project.getEndDate() == null ? "No end date" : "Week: " + project.getEndDate().get(Calendar.WEEK_OF_YEAR) + " - " + project.getEndDate().get(Calendar.YEAR);
            String projectStatus = project.isComplete() ? "Complete" : "Incomplete";
            String info = "Start Date: " + startDate + "\nEnd Date :" + endDate + "\nStatus: " + projectStatus;
            basicProjectInfo.setText(info);
        } catch (Exception e) {
            view.updateLoginError(e.getMessage());
            view.showErrorScreen();

        }

    }
    public void updateLoginError(String message) {
        loginError.setText(message);
    }
    public void updateViewProjectActivities(String projectID) {
        //set viewProjectActivities to list of activity strings
        try {
            ProjectInfo project = model.getProjectInfo(projectID);
            List<ActivityInfo> activities = project.getActivities();
            viewProjectActivities.clear();
            String activityString;
            for (ActivityInfo activity : activities) {
                activityString = "Activity Name: " + activity.getName() + "\nID: " + activity.getId() + "\n";

                int allocatedTime = activity.getBudgetedHours();
                activityString += "Allocated Time: " + allocatedTime + "\n";

                Calendar startDate = activity.getStartDate();
                String startWeek = (startDate == null) ? "" : "" + startDate.get(Calendar.WEEK_OF_YEAR);
                String startYear = (startDate == null) ? "" : "" + startDate.get(Calendar.YEAR);
                activityString += "Start Week: " + startWeek + " - " + startYear + "\n";

                Calendar endDate = activity.getEndDate();
                String endWeek = (endDate == null) ? "" : "" + endDate.get(Calendar.WEEK_OF_YEAR);
                String endYear = (endDate == null) ? "" : "" + endDate.get(Calendar.YEAR);
                activityString += "End Week: " + endWeek + " - " + endYear + "\n";

                Boolean complete = activity.isComplete();
                String status = (complete) ? "Status: Complete" : "Status: Incomplete";
                activityString += status + "\nAssigned employees: ";

                for (EmployeeInfo employee : activity.getAssignedEmployees()) {
                    activityString += employee.getInitials() + " ";
                }
                viewProjectActivities.add(activityString);
            }
        } catch (Exception e) {
            System.out.println(e);
            //TODO: Show error
        }
    }

    public String getViewProjectChosenActivityID() {
        String activityString = projectInfo.getSelectionModel().getSelectedItem();
        if (activityString == null) {
            return null;
        }
        String[] lines = activityString.split("\\R");
        String id = lines[1].split(": ")[1];
        return id;
    }
    public String getChooseProjectProjectID() {
        if (chooseProjectActiveProjects.getValue() == null) {
            return null;
        }
        String displayValue = chooseProjectActiveProjects.getValue().toString();
        String[] values = displayValue.split(" - ");
        String id = values[values.length-1];
        //System.out.println(id);
        return id;
    }
    public void updateErrorMessage(String message) {
        errorMessage.setText(message);
    }

    public Scene getLoginScreen() {
        return loginScreen;
    }
    public Scene getMainScreen() {
        return mainScreen;
    }

    public Scene getAddProjectScreen() {
        return addProjectScreen;
    }

    public Scene getChooseProjectScreen() {
        return chooseProjectScreen;
    }

    public Scene getViewProjectScreen() {
        return viewProjectScreen;
    }
    public Scene getAddActivityScreen() {
        return addActivityScreen;
    }
    public Scene getEditActivityScreen() {
        return editActivityScreen;
    }
    public Scene getAssignEmployeeScreen() {
        return assignEmployeeScreen;
    }

    public Button getLogInBTN() {
        return logInBTN;
    }
    public Button getLogOutBTN() {
        return logOutBTN;
    }

    public Button getMainAddProjectBTN() {
        return mainAddProjectBTN;
    }

    public Button getAddProjectAddProjectBTN() {
        return addProjectAddProjectBTN;
    }
    public Button getAddProjectBackBTN() {
        return addProjectBackBTN;
    }
    public Button getMainViewProjectBTN() {
        return mainViewProjectBTN;
    }
    public Button getChooseProjectViewProjectBTN() {
        return chooseProjectViewProjectBTN;
    }
    public Button getChooseProjectBackBTN() {
        return chooseProjectBackBTN;
    }
    public Button getViewProjectBackBTN() {
        return viewProjectBackBTN;
    }
    public Button getViewProjectAddActivityBTN() {
        return viewProjectAddActivityBTN;
    }
    public Button getViewProjectEditActivityBTN() {
        return viewProjectEditActivityBTN;
    }
    public Button getEditActivityBackBTN() {
        return editActivityBackBTN;
    }
    public Button getEditActivitySaveChangesBTN() {
        return editActivitySaveChangesBTN;
    }
    public String getLogin() {
        return userLoginField.getText();
    }
    public String getProjectName() {
        return newProjectName.getText();
    }
    public Button getAddActivityAddActivityBTN() {
        return addActivityAddActivityBTN;
    }
    public Button getAddActivityBackBTN() {
        return addActivityBackBTN;
    }

    public String getNewActivityName() {
        return newActivityNameTF.getText();
    }
    public Scene getAssignProjectLeaderScreen() {
        return assignProjectLeaderScreen;
    }
    public Button getAssignProjectLeaderBackBTN() {
        return assignProjectLeaderBackBTN;
    }
    public String getAssignProjectLeaderInitials() {
        return assignProjectLeaderInitialsTF.getText();
    }
    public Button getAssignProjectLeaderAssignBTN() {
        return assignProjectLeaderAssignBTN;
    }
    public Button getViewProjectAssignProjectLeaderBTN() {
        return viewProjectAssignProjectLeaderBTN;
    }
    public String getEditActivityWeek() {
        return editActivityWeekTF.getText();
    }
    public String getEditActivityYear() {
        return editActivityYearTF.getText();
    }
    public String getSelectedDateField() {
        return editActivityCB.getValue() == null ? null : editActivityCB.getValue().toString();
    }
    public Button getAssignEmployeeBackBTN() {
        return assignEmployeeBackBTN;
    }
    public Button getAssignEmployeeAssignBTN() {
        return assignEmployeeAssignBTN;
    }
    public String getAssignEmployeeTF() {
        return assignEmployeeTF.getText();
    }
    public Button getViewProjectAssignEmployeeBTN() {
        return viewProjectAssignEmployeeBTN;
    }
    public String getEditActivityAllocatedTime() {
        return editActivityAllocatedTimeTF.getText();
    }

    public Button getViewProjectChangeCompletionStatusBTN() {
        return viewProjectChangeCompletionStatusBTN;
    }
    public Button getErrorBackToMain() {
        return errorBackToMain;
    }
    public Scene getErrorScreen() {
        return errorScreen;
    }
}

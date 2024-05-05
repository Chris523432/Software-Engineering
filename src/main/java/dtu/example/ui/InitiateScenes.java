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

    // Everything on mainScreen
    private Scene mainScreen;
    private Button logOutBTN;
    private Button mainAddProjectBTN;
    private Button mainViewProjectBTN;
    private Button mainRegisterNewEmployeeBTN;

    // Everything on addProjectScreen
    private Scene addProjectScreen;
    private Button addProjectBackBTN;
    private TextField newProjectName;
    private String selectedProjectName;
    private Project selectedProject;
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
    private Button viewProjectDeleteActivityBTN;
    private Button viewProjectAssignProjectLeaderBTN;

    // Everything on addActivityScreen
    private Scene addActivityScreen;
    private Button addActivityBackBTN;
    private TextField newActivityNameTF;
    private Label newActivityNameL;
    private TextField newActivityStartWeekTF;
    private Label newActivityStartWeekL;
    private TextField newActivityEndWeekTF;
    private Label newActivityEndWeekL;
    private Button addActivityAddActivityBTN;

    // Everything on newEmployeeScreen
    private Scene newEmployeeScreen;
    private Button newEmployeeBackBTN;
    private TextField newEmployeeInitialsTF;
    private Button newEmployeeAddBTN;

    // Everything on assignProjectLeaderScreen;
    private Scene assignProjectLeaderScreen;
    private Button assignProjectLeaderBackBTN;
    private TextField assignProjectLeaderInitialsTF;
    private Button assignProjectLeaderAssignBTN;

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
        initiateNewEmployeeScreen();
        initiateAssignProjectLeaderScreen();
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
        mainRegisterNewEmployeeBTN = new Button("Register new user");
        mainRegisterNewEmployeeBTN.setMinWidth(200);
        logOutBTN = new Button("Log out");

        VBox vbBtn = new VBox(10);
        vbBtn.setAlignment(Pos.CENTER);
        vbBtn.getChildren().addAll(mainAddProjectBTN, mainViewProjectBTN, mainRegisterNewEmployeeBTN);
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
        Text sceneTitle = new Text("Project: " + "Project Leader: ");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);
        bpTitle.setTop(sceneTitle);

        viewProjectBackBTN = new Button("Back");
        viewProjectAddActivityBTN = new Button("Add activity");
        viewProjectAssignProjectLeaderBTN = new Button("Assign project leader");
        viewProjectDeleteActivityBTN = new Button("Delete activity");

        VBox vbManageProject = new VBox(10);
        vbManageProject.getChildren().addAll(viewProjectAddActivityBTN, viewProjectDeleteActivityBTN, viewProjectAssignProjectLeaderBTN);

        viewProjectActivities = FXCollections.observableArrayList();
        ListView<String> projectInfo = new ListView<>(viewProjectActivities);


        HBox viewProjectHB = new HBox(10);
        viewProjectHB.setAlignment(Pos.CENTER);
        viewProjectHB.getChildren().addAll(projectInfo, vbManageProject);

        BP.setCenter(viewProjectHB);
        BP.setTop(viewProjectBackBTN);
        //BP.setRight(vbManageProject);

        viewProjectScreen = new Scene(new StackPane(bpTitle, BP), XDim, YDim);
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
        newActivityNameL = new Label("Activity name");
        newActivityStartWeekTF = new TextField();
        newActivityStartWeekL = new Label("Start week");
        newActivityEndWeekTF = new TextField();
        newActivityEndWeekL = new Label("End week");

        VBox addActivityTextFieldsVB = new VBox(10);
        addActivityTextFieldsVB.setAlignment(Pos.CENTER);
        addActivityTextFieldsVB.getChildren().addAll(newActivityNameTF, newActivityStartWeekTF, newActivityEndWeekTF);

        VBox addActivityLabelsVB = new VBox(10);
        addActivityLabelsVB.setAlignment(Pos.CENTER_LEFT);
        addActivityLabelsVB.getChildren().addAll(newActivityNameL, newActivityStartWeekL, newActivityEndWeekL);

        HBox addActivityHB = new HBox(10);
        addActivityHB.setAlignment(Pos.CENTER);
        addActivityHB.getChildren().addAll(addActivityTextFieldsVB, addActivityLabelsVB);

        BP.setTop(addActivityBackBTN);
        BP.setCenter(addActivityHB);
        BP.setBottom(addActivityAddActivityBTN);

        addActivityScreen = new Scene(new StackPane(bpTitle, BP), XDim, YDim);

    }
    public void initiateNewEmployeeScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25, 25, 25, 25));

        BorderPane bpTitle = new BorderPane();
        bpTitle.setPadding(new Insets(25, 25, 25, 25));
        Text sceneTitle = new Text("New Employee");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);
        bpTitle.setTop(sceneTitle);

        newEmployeeInitialsTF = new TextField();
        newEmployeeAddBTN = new Button("Add");
        newEmployeeBackBTN = new Button("Back");

        HBox newEmployeeHB = new HBox(10);
        newEmployeeHB.setAlignment(Pos.CENTER);
        newEmployeeHB.getChildren().addAll(newEmployeeInitialsTF, newEmployeeAddBTN);

        BP.setCenter(newEmployeeHB);
        BP.setTop(newEmployeeBackBTN);

        newEmployeeScreen = new Scene(new StackPane(bpTitle, BP), XDim, YDim);

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

    public void updateProjectNames() {
        projectNames.clear();
        List<ProjectInfo> projects = model.getProjectInfoList();
        for (ProjectInfo p : projects) {
            String display = p.getName() + " - " + p.getId();
            projectNames.add(display);
        }
        //projectNames.add("Test Project");
    }
    public void updateViewProjectActivities(String projectID) {
        //set viewProjectActivities to list of activity strings
        try {
            ProjectInfo project = model.getProjectInfo(projectID);
            List<ActivityInfo> activities = project.getActivities();
            viewProjectActivities.clear();
            String activityString;
            for (ActivityInfo activity : activities) {
                activityString = "Activity Name: " + activity.getName() + "\n";

                Calendar startDate = activity.getStartDate();
                String startWeek = (startDate == null) ? "" : "" + startDate.get(Calendar.WEEK_OF_YEAR);
                String startYear = (startDate == null) ? "" : "" + startDate.get(Calendar.YEAR);
                activityString += "Start Week: " + startWeek + " - " + startYear + "\n";

                Calendar endDate = activity.getEndDate();
                String endWeek = (endDate == null) ? "" : "" + endDate.get(Calendar.WEEK_OF_YEAR);
                String endYear = (endDate == null) ? "" : "" + endDate.get(Calendar.YEAR);
                activityString += "End Week: " + endWeek + " - " + endYear;
                viewProjectActivities.add(activityString);
            }
        } catch (Exception e) {
            System.out.println(e);
            //TODO: Show error
        }



    }
    public String getChooseProjectProjectID() {
        String displayValue = chooseProjectActiveProjects.getValue().toString();
        String[] values = displayValue.split(" - ");
        String id = values[values.length-1];
        //System.out.println(id);
        return id;
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
    public Scene getNewEmployeeScreen() {
        return newEmployeeScreen;
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
    public Button getViewProjectDeleteActivityBTN() {
        return  viewProjectDeleteActivityBTN;
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
    public String getSelectedProjectName() {
        return selectedProjectName;
    }
    public String getNewActivityName() {
        return newActivityNameTF.getText();
    }
    public String getNewActivityStartWeek() {
        return newActivityStartWeekTF.getText();
    }
    public String getNewActivityEndWeek() {
        return newActivityEndWeekTF.getText();
    }
    public Button getMainRegisterNewEmployeeBTN() {
        return mainRegisterNewEmployeeBTN;
    }

    public Button getNewEmployeeAddBTN() {
        return newEmployeeAddBTN;
    }
    public Button getNewEmployeeBackBTN() {
        return newEmployeeBackBTN;
    }
    public String getNewEmployeeInitials() {
        return newEmployeeInitialsTF.getText();
    }
    public Project getSelectedProject() {
        return selectedProject;
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
}
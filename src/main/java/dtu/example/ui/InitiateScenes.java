package dtu.example.ui;

import dtu.application.Application;
import dtu.application.Project;
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

public class InitiateScenes {
    private Scene loginScreen;
    private Scene mainScreen;
    private Scene viewProjectsScreen;
    private Scene addProjectScreen;
    private Scene activitiesScreen;
    private View view;
    private int XDim = 500;
    private int YDim = 300;
    private Button logIn;
    private Button logOut;
    private Button addProject;
    private Button viewProjects;
    private Button viewProject;
    private Button addActivity;
    private Button addProjectBack;
    private Button viewProjectsBack;
    private String username;
    private Application application;

    public InitiateScenes(View view, Application application) {
        this.view = view;
        this.application = application;
        initiateLoginScreen();
        initiateMainScreen();
        initiateViewProjectsScreen();
        initiateAddProjectScreen();
    }

    public void initiateLoginScreen() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0,0,2,1);

        Label userName = new Label("User Name:");
        grid.add(userName,0,1);

        TextField userTextField = new TextField();
        grid.add(userTextField,1,1);
        username = userTextField.getText();

        logIn = new Button("Log in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(logIn);
        grid.add(hbBtn,1,2);

        grid.setGridLinesVisible(true);

        loginScreen = new Scene (grid, 300, 275);
    }

    public void initiateMainScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25,25,25,25));

        BorderPane bpTitle = new BorderPane();
        bpTitle.setPadding(new Insets(25,25,25,25));
        Text sceneTitle = new Text("User: ");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);

        addProject = new Button("Add Project");
        addProject.setMinWidth(200);
        viewProjects = new Button("View your current projects");
        viewProjects.setMinWidth(200);
        logOut = new Button("Log out");

        VBox vbBtn = new VBox(10);
        vbBtn.setAlignment(Pos.CENTER);
        vbBtn.getChildren().addAll(addProject, viewProjects);
        BP.setCenter(vbBtn);
        BP.setTop(logOut);
        bpTitle.setTop(sceneTitle);


        mainScreen = new Scene(new StackPane(bpTitle, BP), XDim,YDim);
    }
    public void initiateAddProjectScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25,25,25,25));

        BorderPane bpTitle = new BorderPane();
        bpTitle.setPadding(new Insets(25,25,25,25));
        Text sceneTitle = new Text("Add Project");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);
        bpTitle.setTop(sceneTitle);

        addProjectBack = new Button("Back to main menu");
        BP.setTop(addProjectBack);

        addProjectScreen = new Scene(new StackPane(bpTitle, BP), XDim, YDim);

    }

    public void initiateViewProjectsScreen() {
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(25,25,25,25));

        BorderPane bpTitle = new BorderPane();
        bpTitle.setPadding(new Insets(25,25,25,25));
        Text sceneTitle = new Text("(user's) projects");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(sceneTitle, Pos.CENTER);
        TableView<Project> table = new TableView<Project>();

        ObservableList<String> projects = FXCollections.observableArrayList(
                "project 1",
                "project 2",
                "project 3");
        final ComboBox comboBox = new ComboBox(projects);
        comboBox.setVisibleRowCount(5);

        comboBox.setPromptText("Project");

        viewProject = new Button("View Project");
        viewProjectsBack = new Button("Insert image of back symbol");
        HBox hbProjects = new HBox(10);
        hbProjects.setAlignment(Pos.CENTER);
        hbProjects.getChildren().addAll(comboBox,viewProject);

        BP.setCenter(hbProjects);
        BP.setTop(viewProjectsBack);

        viewProjectsScreen = new Scene(new StackPane(bpTitle, BP), XDim,YDim);
    }

    public void initiateActivitiesScreen() {

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
    public Scene getViewProjectsScreen() {
        return viewProjectsScreen;
    }
    public Scene getActivitiesScreen() {
        return activitiesScreen;
    }
    public Button getLogInBTN() {
        return logIn;
    }
    public Button getAddProjectBTN() {
        return addProject;
    }
    public Button getViewProjectsBTN() {
        return viewProjects;
    }
    public Button getViewProjectBTN() {
        return viewProjects;
    }
    public Button getLogOutBTN() {
        return logOut;
    }
    public Button getViewProjectsBackBTN() {
        return viewProjectsBack;
    }
    public String getUsername() {
        return username;
    }
    public Button getAddProjectBackBTN() {
        return addProjectBack;
    }

}
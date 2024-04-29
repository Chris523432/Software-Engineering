package dtu.example.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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

    public void showViewProjectsScreen() {
        primaryStage.setScene(sceneMenu.getViewProjectsScreen());
        primaryStage.show();
    }

    public void initiateButtons() {
        sceneMenu.getLogInBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMainScreen();
                System.out.println(sceneMenu.getUsername());
            }
        });
        //sceneMenu.getAddProjectBTN().setOnAction(controller::);
        sceneMenu.getAddProjectBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showAddProjectScreen();
            }
        });
        sceneMenu.getViewProjectsBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showViewProjectsScreen();
            }
        });
        sceneMenu.getViewProjectsBackBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMainScreen();
            }
        });
        sceneMenu.getLogOutBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showLoginScreen();
            }
        });
        sceneMenu.getAddProjectBackBTN().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showMainScreen();
            }
        });
    }
}

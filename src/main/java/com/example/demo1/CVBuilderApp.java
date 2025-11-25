package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CVBuilderApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showHomeScreen();
        stage.setTitle("CV Builder (2207052)");
        stage.show();
    }

    public static void showHomeScreen() {
        try {
            Parent root = FXMLLoader.load(CVBuilderApp.class.getResource("home.fxml"));
            primaryStage.setScene(new Scene(root, 900, 650));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showCreateCVScreen() {
        try {
            Parent root = FXMLLoader.load(CVBuilderApp.class.getResource("create-cv.fxml"));
            primaryStage.setScene(new Scene(root, 900, 650));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showListScreen() {
        try {
            Parent root = FXMLLoader.load(CVBuilderApp.class.getResource("cv-list.fxml"));
            primaryStage.setScene(new Scene(root, 900, 650));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showPreviewScreen(CV cv) {
        try {
            FXMLLoader loader = new FXMLLoader(CVBuilderApp.class.getResource("preview.fxml"));
            Parent root = loader.load();
            PreviewController ctrl = loader.getController();
            ctrl.setCV(cv);
            primaryStage.setScene(new Scene(root, 900, 650));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

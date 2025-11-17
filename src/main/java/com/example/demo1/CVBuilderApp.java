package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CVBuilderApp extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        showHomeScreen();
        stage.setTitle("CV Builder");
        stage.setResizable(false);
        stage.show();
    }

    public static void showHomeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(CVBuilderApp.class.getResource("home.fxml"));
            Scene scene = new Scene(loader.load(), 900, 650);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showCreateCVScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(CVBuilderApp.class.getResource("create-cv.fxml"));
            Scene scene = new Scene(loader.load(), 900, 650);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showPreviewScreen(CV cv) {
        try {
            FXMLLoader loader = new FXMLLoader(CVBuilderApp.class.getResource("preview.fxml"));
            Scene scene = new Scene(loader.load(), 900, 650);
            PreviewController controller = loader.getController();
            controller.setCV(cv);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package cvbuilder;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import  javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Hello extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
  Label label=new Label("Hello, it's my first project\n");

  StackPane root=new StackPane(label);

  Scene scene=new Scene(root,400,200);

  primaryStage.setScene(scene);

  primaryStage.setTitle("My CVBuilder");

  primaryStage.show();
    }
}

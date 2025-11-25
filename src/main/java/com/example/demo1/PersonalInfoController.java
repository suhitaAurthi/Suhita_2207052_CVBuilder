package com.example.demo1;   // ← change to your actual package name

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonalInfoController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private TextField departmentField;

    @FXML
    private TextField semesterField;

    @FXML
    private Button saveButton;

    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {
        // You can add default settings here if needed
    }

    @FXML
    private void handleSave() {
        String name = nameField.getText();
        String id = idField.getText();
        String department = departmentField.getText();
        String semester = semesterField.getText();

        // Print to console (you can replace this with database saving)
        System.out.println("Saved:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Department: " + department);
        System.out.println("Semester: " + semester);
    }

    @FXML
    private void handleNext() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("course_entry.fxml"));
            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

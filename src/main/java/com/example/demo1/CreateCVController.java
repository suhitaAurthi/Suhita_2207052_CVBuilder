package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;

public class CreateCVController {

    @FXML
    private TextField fullNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextArea educationArea;
    @FXML
    private TextArea skillsArea;
    @FXML
    private TextArea workExperienceArea;
    @FXML
    private TextArea projectsArea;
    @FXML
    private ImageView photoImageView;

    private String selectedPhotoPath;

    @FXML
    private void handleUploadPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Photo");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(photoImageView.getScene().getWindow());
        if (selectedFile != null) {
            selectedPhotoPath = selectedFile.toURI().toString();
            Image image = new Image(selectedPhotoPath);
            photoImageView.setImage(image);
        }
    }

    @FXML
    private void handleGenerateCV() {
        if (validateFields()) {
            CV cv = new CV(
                fullNameField.getText(),
                emailField.getText(),
                phoneField.getText(),
                addressField.getText(),
                educationArea.getText(),
                skillsArea.getText(),
                workExperienceArea.getText(),
                projectsArea.getText(),
                selectedPhotoPath
            );
            CVBuilderApp.showPreviewScreen(cv);
        }
    }

    @FXML
    private void handleBack() {
        CVBuilderApp.showHomeScreen();
    }

    private boolean validateFields() {
        if (fullNameField.getText().trim().isEmpty()) {
            showAlert("Full Name is required");
            return false;
        }
        if (emailField.getText().trim().isEmpty()) {
            showAlert("Email is required");
            return false;
        }
        if (phoneField.getText().trim().isEmpty()) {
            showAlert("Phone Number is required");
            return false;
        }
        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

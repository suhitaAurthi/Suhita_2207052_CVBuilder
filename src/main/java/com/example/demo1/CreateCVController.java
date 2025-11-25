package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CreateCVController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private TextArea educationArea;
    @FXML private TextArea skillsArea;
    @FXML private TextArea workExperienceArea;
    @FXML private TextArea projectsArea;
    @FXML private ImageView photoImageView;

    // If editing an existing CV, this is filled
    private CV editingCv = null;
    private String photoPath = null;

    @FXML
    private void initialize() {
        // nothing special for now
    }

    @FXML
    private void handleUploadPhoto() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose Photo");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File f = chooser.showOpenDialog(fullNameField.getScene().getWindow());
        if (f != null) {
            photoPath = f.toURI().toString();
            photoImageView.setImage(new Image(photoPath));
        }
    }

    @FXML
    private void handleBack() {
        CVBuilderApp.showHomeScreen();
    }

    @FXML
    private void handleGenerateCV() {
        String name = fullNameField.getText().trim();
        if (name.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation error", "Full name is required.");
            return;
        }

        CV cv = (editingCv == null) ? new CV() : editingCv;
        cv.setFullName(name);
        cv.setEmail(emailField.getText());
        cv.setPhone(phoneField.getText());
        cv.setAddress(addressField.getText());
        cv.setEducation(educationArea.getText());
        cv.setSkills(skillsArea.getText());
        cv.setWorkExperience(workExperienceArea.getText());
        cv.setProjects(projectsArea.getText());
        cv.setPhotoPath(photoPath == null ? "" : photoPath);

        if (editingCv == null) {
            int id = DatabaseManager.insertCV(cv);
            if (id > 0) {
                cv.setId(id);
                showAlert(Alert.AlertType.INFORMATION, "Saved", "CV saved successfully.");
                // show preview of newly created CV
                CVBuilderApp.showPreviewScreen(cv);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save CV.");
            }
        } else {
            boolean ok = DatabaseManager.updateCV(cv);
            if (ok) {
                showAlert(Alert.AlertType.INFORMATION, "Updated", "CV updated successfully.");
                CVBuilderApp.showPreviewScreen(cv);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update CV.");
            }
        }
    }

    public void setEditingCV(CV cv) {
        this.editingCv = cv;
        if (cv == null) return;

        fullNameField.setText(cv.getFullName());
        emailField.setText(cv.getEmail());
        phoneField.setText(cv.getPhone());
        addressField.setText(cv.getAddress());
        educationArea.setText(cv.getEducation());
        skillsArea.setText(cv.getSkills());
        workExperienceArea.setText(cv.getWorkExperience());
        projectsArea.setText(cv.getProjects());
        if (cv.getPhotoPath() != null && !cv.getPhotoPath().isEmpty()) {
            photoPath = cv.getPhotoPath();
            photoImageView.setImage(new Image(photoPath));
        }
    }

    private void showAlert(Alert.AlertType type, String header, String body) {
        Alert a = new Alert(type);
        a.setHeaderText(header);
        a.setContentText(body);
        a.showAndWait();
    }
}

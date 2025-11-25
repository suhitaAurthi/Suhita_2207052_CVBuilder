package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class PreviewController {

    @FXML private Label nameLabel;
    @FXML private Text contactText;
    @FXML private Text educationText;
    @FXML private Text skillsText;
    @FXML private Text experienceText;
    @FXML private Text projectsText;
    @FXML private ImageView previewPhotoImageView;
    @FXML private Button editButton;

    private CV currentCv;

    public void setCV(CV cv) {
        this.currentCv = cv;
        if (cv == null) return;

        nameLabel.setText(cv.getFullName());
        contactText.setText(String.format("%s | %s | %s",
                nullToEmpty(cv.getEmail()), nullToEmpty(cv.getPhone()), nullToEmpty(cv.getAddress())));
        educationText.setText(nullToEmpty(cv.getEducation()));
        skillsText.setText(nullToEmpty(cv.getSkills()));
        experienceText.setText(nullToEmpty(cv.getWorkExperience()));
        projectsText.setText(nullToEmpty(cv.getProjects()));

        if (cv.getPhotoPath() != null && !cv.getPhotoPath().isEmpty()) {
            try {
                Image image = new Image(cv.getPhotoPath(), true);
                previewPhotoImageView.setImage(image);
            } catch (Exception e) {
                previewPhotoImageView.setImage(null);
            }
        } else {
            previewPhotoImageView.setImage(null);
        }
    }

    private String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    @FXML
    private void handleBack() {
        // Edit: open create screen with current CV prefilled
        try {
            FXMLLoader loader = new FXMLLoader(CVBuilderApp.class.getResource("create-cv.fxml"));
            Parent root = loader.load();
            CreateCVController ctrl = loader.getController();
            ctrl.setEditingCV(currentCv);

            Stage stage = (Stage) nameLabel.getScene().getWindow();
            stage.setScene(new Scene(root, 900, 650));
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Failed to open edit screen: " + e.getMessage());
        }
    }

    @FXML
    private void handleHome() {
        CVBuilderApp.showHomeScreen();
    }

    private void showAlert(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}

package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class PreviewController {

    @FXML
    private Label nameLabel;
    @FXML
    private Text contactText;
    @FXML
    private Text educationText;
    @FXML
    private Text skillsText;
    @FXML
    private Text experienceText;
    @FXML
    private Text projectsText;
    @FXML
    private ImageView previewPhotoImageView;

    public void setCV(CV cv) {
        nameLabel.setText(cv.getFullName());
        contactText.setText(String.format("%s | %s | %s",
            cv.getEmail(), cv.getPhone(), cv.getAddress()));
        educationText.setText(cv.getEducation());
        skillsText.setText(cv.getSkills());
        experienceText.setText(cv.getWorkExperience());
        projectsText.setText(cv.getProjects());
        
        if (cv.getPhotoPath() != null && !cv.getPhotoPath().isEmpty()) {
            Image image = new Image(cv.getPhotoPath());
            previewPhotoImageView.setImage(image);
        }
    }

    @FXML
    private void handleBack() {
        CVBuilderApp.showCreateCVScreen();
    }

    @FXML
    private void handleHome() {
        CVBuilderApp.showHomeScreen();
    }
}

package com.example.demo1;

import javafx.fxml.FXML;

public class HomeController {

    @FXML
    private void handleCreateNewCV() {
        CVBuilderApp.showCreateCVScreen();
    }

    @FXML
    private void handleViewAll() {
        CVBuilderApp.showListScreen();
    }
}

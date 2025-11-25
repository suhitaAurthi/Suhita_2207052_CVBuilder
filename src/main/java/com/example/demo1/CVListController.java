package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CVListController {

    @FXML private TableView<CV> cvTable;
    @FXML private TableColumn<CV, String> nameColumn;
    @FXML private TableColumn<CV, String> emailColumn;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadTable();
    }

    private void loadTable() {
        List<CV> list = DatabaseManager.getAllCV();
        ObservableList<CV> obs = FXCollections.observableArrayList(list);
        cvTable.setItems(obs);
    }

    @FXML
    private void handlePreviewCV() {
        CV selected = cvTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Select a CV", "Please select a CV to preview.");
            return;
        }
        // refresh from DB (in case)
        CV fresh = DatabaseManager.getCVById(selected.getId());
        if (fresh != null) CVBuilderApp.showPreviewScreen(fresh);
        else showAlert(Alert.AlertType.ERROR, "Not found", "The selected CV is no longer available.");
    }

    @FXML
    private void handleDeleteCV() {
        CV selected = cvTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Select a CV", "Please select a CV to delete.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("Delete CV");
        confirm.setContentText("Are you sure you want to delete the CV for: " + selected.getFullName() + "?");
        if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            boolean ok = DatabaseManager.deleteCV(selected.getId());
            if (ok) {
                showAlert(Alert.AlertType.INFORMATION, "Deleted", "CV deleted.");
                loadTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Unable to delete CV.");
            }
        }
    }

    @FXML
    private void handleRefresh() {
        loadTable();
    }

    @FXML
    private void handleHome() {
        CVBuilderApp.showHomeScreen();
    }

    private void showAlert(Alert.AlertType type, String header, String body) {
        Alert a = new Alert(type);
        a.setHeaderText(header);
        a.setContentText(body);
        a.showAndWait();
    }
}

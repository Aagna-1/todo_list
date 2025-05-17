/**
 TO-DO List Project
 Description:Make a to-do list app using JavaFX. You can add, edit, and delete tasks.
 The list updates every time you make a change. It shows errors if something is wrong, like an empty input.
 The app has a title, a text box, buttons, and a list display.
 @author Aagna Modi
 @since 05/16/2025
 */

package com.example.todo_list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

/**
 *Controller for the To-Do List app.
 *It handles creating, editing, deleting, and moving tasks
 *between pending (incomplete) and completed lists.
 */
public class ToDoTaskController {
    @FXML
    ListView<String> incompleteTaskListView;
    private final ObservableList<String> incompleteTasks = FXCollections.observableArrayList();


    @FXML
    ListView<String> completedTaskListView;
    private final ObservableList<String> completedTasks = FXCollections.observableArrayList();


    @FXML
    private TextField newTask;

    /**
     *Initializes the task lists and links them to the data
     */
    @FXML
    private void initialize() {
        incompleteTaskListView.setItems(incompleteTasks);
        completedTaskListView.setItems(completedTasks);
    }

    /**
     *Adds a new task to the incomplete list when the "Add" button is clicked.
     */
    @FXML
    protected void onCreateNewTaskClick() {
        try {
            String taskDetails = newTask.getText();
            if (taskDetails.isEmpty()) {
                throw new IllegalArgumentException("Add Task button was clicked without entering anything in text field");
            }
            incompleteTasks.add(taskDetails);
            newTask.setText("");
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    /**
     *Edits a selected task in the incomplete list.
     */
    @FXML
    protected void onEditPendingTaskClick() {
        try {
            String selected = incompleteTaskListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new IllegalArgumentException("Please select an item to edit.");
            }

            TextInputDialog dialog = new TextInputDialog(selected);
            dialog.setTitle("Edit Task");
            dialog.setHeaderText("Edit selected task:");
            dialog.setContentText("New value:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(newValue -> {
                try {
                    if (newValue.trim().isEmpty()) {
                        throw new IllegalArgumentException("Task cannot be empty.");
                    }
                    incompleteTasks.set(incompleteTasks.indexOf(selected), newValue.trim());
                } catch (Exception innerEx) {
                    showAlert(innerEx.getMessage());
                }
            });

        } catch (Exception e) {
            showAlert(e.getMessage());
        }

    }

    /**
     *Deletes the selected task from the incomplete list.
     */
    @FXML
    protected void onDeletePendingTaskClick() {
        try {
            String selected = incompleteTaskListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new IllegalArgumentException("Please select an item to remove.");
            }

            try {
                incompleteTasks.remove(incompleteTasks.indexOf(selected));
            } catch (Exception innerEx) {
                showAlert(innerEx.getMessage());
            }

        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    /**
     *Edits a selected task in the completed list.
     */
    @FXML
    protected void onEditCompletedTaskClick() {
        try {
            String selected = completedTaskListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new IllegalArgumentException("Please select an item to edit.");
            }

            TextInputDialog dialog = new TextInputDialog(selected);
            dialog.setTitle("Edit Task");
            dialog.setHeaderText("Edit selected task:");
            dialog.setContentText("New value:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(newValue -> {
                try {
                    if (newValue.trim().isEmpty()) {
                        throw new IllegalArgumentException("Task cannot be empty.");
                    }
                    completedTasks.set(completedTasks.indexOf(selected), newValue.trim());
                } catch (Exception innerEx) {
                    showAlert(innerEx.getMessage());
                }
            });

        } catch (Exception e) {
            showAlert(e.getMessage());
        }

    }

    /**
     *Deletes the selected task from the completed list.
     */
    @FXML
    protected void onDeleteCompletedTaskClick() {
        try {
            String selected = completedTaskListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new IllegalArgumentException("Please select an item to remove.");
            }

            try {
                completedTasks.remove(completedTasks.indexOf(selected));
            } catch (Exception innerEx) {
                showAlert(innerEx.getMessage());
            }

        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    /**
     *Marks a selected incomplete task as complete.
     */
    @FXML
    protected void onMarkCompleteClick() {
        try {
            String selected = incompleteTaskListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new IllegalArgumentException("Please select an item to mark complete");
            }

            try {
                completedTasks.add(selected);
                incompleteTasks.remove(incompleteTasks.indexOf(selected));
            } catch (Exception innerEx) {
                showAlert(innerEx.getMessage());
            }


        } catch (Exception e) {
            showAlert(e.getMessage());
        }

    }

    /**
     *Marks a selected completed task as pending again.
     */
    @FXML
    protected void onMarkPendingClick() {
        try {
            String selected = completedTaskListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new IllegalArgumentException("Please select an item to mark pending");
            }

            try {
                incompleteTasks.add(selected);
                completedTasks.remove(completedTasks.indexOf(selected));
            } catch (Exception innerEx) {
                showAlert(innerEx.getMessage());
            }
        } catch (Exception e) {
            showAlert(e.getMessage());
        }

    }

    /**
     * Shows a warning alert with a custom message.
     * @param message the message to display in the alert box
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
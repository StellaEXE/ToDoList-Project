package com.todolist.ui;

import com.todolist.model.Task;
import com.todolist.model.ToDoList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class ToDoApp extends Application {
    private ToDoList toDoList = new ToDoList();
    private ListView<Task> taskListView;
    private TextField newTaskInput;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX To-Do List");

        // UI Components
        newTaskInput = new TextField();
        newTaskInput.setPromptText("Enter a new task...");
        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> addTask());

        HBox inputBox = new HBox(10, newTaskInput, addButton);
        inputBox.setPadding(new Insets(10));

        // Task List View
        taskListView = new ListView<>(toDoList.getTasks());
        taskListView.setCellFactory(lv -> new TaskCell());

        // Main layout
        VBox layout = new VBox(10, inputBox, taskListView);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addTask() {
        String taskDescription = newTaskInput.getText();
        toDoList.addTask(taskDescription);
        newTaskInput.clear();
    }

    // Custom Cell Factory to display each task with controls
    static class TaskCell extends ListCell<Task> {
        HBox hbox = new HBox(10);
        CheckBox checkBox = new CheckBox();
        Label label = new Label();
        Button deleteButton = new Button("Delete");

        public TaskCell() {
            hbox.getChildren().addAll(checkBox, label, deleteButton);
            // Binding task properties to UI elements
            checkBox.setOnAction(event -> getItem().setCompleted(checkBox.isSelected()));
            deleteButton.setOnAction(event -> {
                getListView().getItems().remove(getItem());
            });
        }

        @Override
        protected void updateItem(Task item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                label.setText(item.getDescription());
                checkBox.setSelected(item.isCompleted());
                // Optional: add styling for completed tasks
                item.isCompletedProperty().addListener((obs, oldVal, newVal) -> {
                    if (newVal) {
                        label.setStyle("-fx-strikethrough: true; -fx-text-fill: gray;");
                    } else {
                        label.setStyle("-fx-strikethrough: false; -fx-text-fill: black;");
                    }
                });
                setGraphic(hbox);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
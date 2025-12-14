package com.todolist.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToDoList {
    private final ObservableList<Task> tasks;

    public ToDoList() {
        this.tasks = FXCollections.observableArrayList();
    }

    public void addTask(String description) {
        if (description != null && !description.trim().isEmpty()) {
            tasks.add(new Task(description.trim()));
        }
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }
}

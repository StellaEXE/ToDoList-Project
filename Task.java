package com.todolist.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private final StringProperty description;
    private final BooleanProperty isCompleted;

    public Task(String description) {
        this.description = new SimpleStringProperty(description);
        this.isCompleted = new SimpleBooleanProperty(false);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted.get();
    }

    public BooleanProperty isCompletedProperty() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted.set(completed);
    }

    @Override
    public String toString() {
        return description.get();
    }
}

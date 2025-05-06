package net.jasonly.todolist.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.UUID;

public class Task {
    private final UUID id;
    private final StringProperty name;
    private final StringProperty description;
    private final ObjectProperty<LocalDate> date;
    private final ObjectProperty<Priority> priority;
    private final ListProperty<String> tags;
    private final BooleanProperty isDone;

    // If id is not known yet, null should be passed.
    public Task(UUID id, StringProperty name, StringProperty description,
                ObjectProperty<LocalDate> date, ObjectProperty<Priority> priority,
                ListProperty<String> tags, BooleanProperty isDone) {
        this.id = id != null ? id : UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.tags = tags;
        this.isDone = isDone;
    }

    public UUID getId() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public ObjectProperty<Priority> priorityProperty() {
        return priority;
    }

    public ListProperty<String> tagsProperty() {
        return tags;
    }

    public BooleanProperty isDoneProperty() {
        return isDone;
    }
}

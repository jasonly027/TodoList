package net.jasonly027.todolist.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Task {
    public final long id;
    private final StringProperty name;
    private final StringProperty description;
    private final ObjectProperty<LocalDate> date;
    private final ObjectProperty<Priority> priority;
    private final ListProperty<String> tags;
    private final BooleanProperty isDone;

    public Task(long id, StringProperty name, StringProperty description,
                ObjectProperty<LocalDate> date, ObjectProperty<Priority> priority,
                ListProperty<String> tags, BooleanProperty isDone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.tags = tags;
        this.isDone = isDone;
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

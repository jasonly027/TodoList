package net.jasonly027.todolist.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.time.LocalDate;
import java.util.UUID;

public class TaskBuilder {
    private UUID id;
    private StringProperty name;
    private StringProperty description;
    private ObjectProperty<LocalDate> date;
    private ObjectProperty<Priority> priority;
    private ListProperty<String> tags;
    private BooleanProperty isDone;

    public TaskBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public TaskBuilder setName(String name) {
        this.name = new SimpleStringProperty(name);
        return this;
    }

    public TaskBuilder setDescription(String description) {
        this.description = new SimpleStringProperty(description);
        return this;
    }

    public TaskBuilder setDate(LocalDate date) {
        this.date = new SimpleObjectProperty<>(date);
        return this;
    }

    public TaskBuilder setPriority(Priority priority) {
        this.priority = new SimpleObjectProperty<>(priority);
        return this;
    }

    public TaskBuilder setTags(String... tags) {
        this.tags = new SimpleListProperty<>(FXCollections.observableArrayList(tags));
        return this;
    }

    public TaskBuilder setIsDone(boolean isDone) {
        this.isDone = new SimpleBooleanProperty(isDone);
        return this;
    }

    // All setters must be called before this, except for id, or this will throw
    public Task build() {
        if (name == null || description == null || date == null || priority == null || tags == null || isDone == null) {
            throw new IllegalStateException("Not all required setters were called before building");
        }
        return new Task(id, name, description, date, priority, tags, isDone);
    }
}

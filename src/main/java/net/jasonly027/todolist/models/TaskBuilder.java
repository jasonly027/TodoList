package net.jasonly027.todolist.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.time.LocalDateTime;

public class TaskBuilder {
    private Long id;
    private StringProperty name;
    private StringProperty description;
    private ObjectProperty<LocalDateTime> date;
    private ObjectProperty<Priority> priority;
    private ListProperty<String> tags;

    public TaskBuilder setId(long id) {
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

    public TaskBuilder setDate(LocalDateTime date) {
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

    public Task build() {
        if (id == null || name == null || description == null || date == null || priority == null || tags == null) {
            throw new IllegalStateException("Not all setters were called before building");
        }
        return new Task(id, name, description, date, priority, tags);
    }
}

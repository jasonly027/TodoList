package net.jasonly027.todolist.models;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

public class Task {
    public final long id;
    private final StringProperty name;
    private final StringProperty description;
    private final ObjectProperty<LocalDateTime> date;
    private final ObjectProperty<Priority> priority;
    private final ListProperty<String> tags;

    public Task(long id, StringProperty name, StringProperty description,
                ObjectProperty<LocalDateTime> date, ObjectProperty<Priority> priority,
                ListProperty<String> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.tags = tags;
    }
}

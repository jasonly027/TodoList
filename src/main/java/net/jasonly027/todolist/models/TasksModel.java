package net.jasonly027.todolist.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class TasksModel {
    private final ObservableList<Task> tasks;

    public TasksModel() {
        tasks = FXCollections.observableArrayList(
                new TaskBuilder()
                        .setId(0)
                        .setName("My first task")
                        .setDescription("Desc. of first task")
                        .setDate(LocalDateTime.now())
                        .setPriority(Priority.HIGH)
                        .setTags("Work")
                        .build(),
                new TaskBuilder()
                        .setId(1)
                        .setName("Walk dog")
                        .setDescription("Go park")
                        .setDate(LocalDateTime.now())
                        .setPriority(Priority.NONE)
                        .setTags("Home")
                        .build()
        );
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }
}

package net.jasonly027.todolist.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class TasksModel {
    private final ObservableList<Task> tasks;

    public TasksModel() {
        tasks = FXCollections.observableArrayList(
                new TaskBuilder()
                        .setId(0)
                        .setName("My first task")
                        .setDescription("Desc. of first task")
                        .setDate(LocalDate.now())
                        .setPriority(Priority.HIGH)
                        .setTags("Work")
                        .setIsDone(false)
                        .build(),
                new TaskBuilder()
                        .setId(1)
                        .setName("Walk dog")
                        .setDescription("Go park")
                        .setDate(LocalDate.now())
                        .setPriority(Priority.NONE)
                        .setTags("Home")
                        .setIsDone(true)
                        .build()
        );
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}

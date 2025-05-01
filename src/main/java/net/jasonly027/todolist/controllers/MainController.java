package net.jasonly027.todolist.controllers;

import net.jasonly027.todolist.models.TasksModel;

public class MainController {
    private TasksModel model;

    public void initModel(TasksModel model) {
        if (this.model != null) {
            throw new IllegalStateException("Model was already initialized");
        }
        this.model = model;
    }
}

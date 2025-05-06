package net.jasonly.todolist.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import net.jasonly.todolist.components.TasksBar;
import net.jasonly.todolist.components.TasksTable;
import net.jasonly.todolist.lib.Database;
import net.jasonly.todolist.models.TasksModel;

public class MainController {
    @FXML
    private StackPane stackPane;
    @FXML
    private TasksBar tasksBar;
    @FXML
    private TasksTable tasksTable;

    private final TasksModel model = new TasksModel(new Database());

    @FXML
    private void initialize() {
        tasksBar.init(model, tasksTable);
        tasksTable.init(model);
    }
}

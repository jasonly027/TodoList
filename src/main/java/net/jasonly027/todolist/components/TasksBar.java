package net.jasonly027.todolist.components;

import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import net.jasonly027.todolist.models.TasksModel;

import static net.jasonly027.todolist.util.Util.initCustomComponent;

public class TasksBar extends HBox {
    @FXML
    private ToggleCompleted toggleCompleted;

    private final StackPane stackPane;

    private TasksModel model;

    public void initModel(TasksModel model) {
        if (this.model != null) {
            throw new IllegalStateException("Model was already initialized");
        }
        this.model = model;
        toggleCompleted.initModel(model);
    }

    public TasksBar(@NamedArg("stackPane") StackPane stackPane) {
        this.stackPane = stackPane;
        initCustomComponent(this, "TasksBar.fxml");
    }

    public TasksModel getModel() {
        return model;
    }

    public void setModel(TasksModel model) {
        this.model = model;
    }

    @FXML
    private void onAddButtonClick(ActionEvent actionEvent) {
        this.stackPane.getChildren().add(new AddTask(stackPane, model));
    }
}

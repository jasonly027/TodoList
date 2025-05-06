package net.jasonly.todolist.components;

import javafx.beans.NamedArg;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import net.jasonly.todolist.models.Task;
import net.jasonly.todolist.models.TasksModel;
import net.jasonly.todolist.lib.ReinitializationException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import static net.jasonly.todolist.lib.Util.initCustomComponent;

// A horizontal bar that contains interactive components
// for creating, modifying, and filtering Tasks.
public class TasksBar extends HBox {
    @FXML
    private TextField taskSearch;
    @FXML
    private Button taskSearchButton;
    @FXML
    private ShowCompleted showCompleted;

    private final StackPane stackPane;
    private TasksModel model;
    private ObservableList<Task> selectedTasks;

    public TasksBar(@NamedArg("stackPane") StackPane stackPane) {
        this.stackPane = stackPane;
        initCustomComponent(this, "TasksBar.fxml");
    }

    @FXML
    private void initialize() {
        setupTaskSearch();
    }

    private Predicate<Task> prevQuery = task -> true;

    private void setupTaskSearch() {
        // Remove last query and apply new one if search field isn't empty
        taskSearch.setOnAction(event -> {
            model.removeFilter(prevQuery);
            if (!taskSearch.getText().isBlank()) {
                prevQuery = task -> {
                    String query = taskSearch.getText().strip().toLowerCase();
                    return task.nameProperty().get().toLowerCase().contains(query)
                            || task.descriptionProperty().get().toLowerCase().contains(query);
                };

                model.addFilter(prevQuery);
            }
        });

        taskSearchButton.setOnAction(taskSearch.getOnAction());
    }

    public void init(TasksModel model, TasksTable tasksTable) {
        if (this.model != null) {
            throw new ReinitializationException();
        }
        this.model = model;
        this.selectedTasks = tasksTable.getSelected();

        showCompleted.init(this.model, tasksTable);
    }

    @FXML
    private void onMarkCompleteClick() {
        List<Task> selected = new ArrayList<>(this.selectedTasks);
        selected.forEach(task -> {
            task.isDoneProperty().set(true);
            model.updateTask(task);
        });
    }

    @FXML
    private void onMarkIncompleteClick() {
        List<Task> selected = new ArrayList<>(this.selectedTasks);
        selected.forEach(task -> {
            task.isDoneProperty().set(false);
            model.updateTask(task);
        });
    }

    @FXML
    private void onDeleteClick() {
        UUID[] taskIds = this.selectedTasks.stream().map(Task::getId).toArray(UUID[]::new);
        model.remove(taskIds);
    }

    @FXML
    private void onAddButtonClick(ActionEvent actionEvent) {
        this.stackPane.getChildren().add(new AddTask(stackPane, model));
    }
}

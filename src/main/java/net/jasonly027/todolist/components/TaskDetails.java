package net.jasonly027.todolist.components;

import javafx.beans.NamedArg;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import net.jasonly027.todolist.models.Priority;
import net.jasonly027.todolist.models.Task;
import net.jasonly027.todolist.models.TasksModel;

import static net.jasonly027.todolist.lib.Util.initCustomComponent;
import static net.jasonly027.todolist.lib.Util.processTaskTags;

// A modal that contains editable information
// on a preexisting Task.
public class TaskDetails extends VBox {
    @FXML
    private TextField taskName;
    @FXML
    private ToggleDone toggleDone;
    @FXML
    private DatePicker taskDate;
    @FXML
    private ComboBox<Priority> taskPriority;
    @FXML
    private TextArea taskDescription;
    @FXML
    private TextField taskTags;

    private final StackPane stackPane;
    private final TasksModel model;
    private final Task task;

    public TaskDetails(@NamedArg("stackPane") StackPane stackPane,
                       @NamedArg("model") TasksModel model,
                       @NamedArg("task") Task task) {
        this.stackPane = stackPane;
        this.model = model;
        this.task = task;
        initCustomComponent(this, "TaskDetails.fxml");
    }

    @FXML
    private void initialize() {
        toggleDone.init(task);
        setupTaskPriority();
        bindFormFields();
    }

    private void setupTaskPriority() {
        taskPriority.getItems().addAll(Priority.values());
    }

    // Create bindings to underlying Task data for view and editing
    private void bindFormFields() {
        taskName.textProperty().bindBidirectional(task.nameProperty());
        taskDescription.textProperty().bindBidirectional(task.descriptionProperty());
        taskDate.valueProperty().bindBidirectional(task.dateProperty());
        taskPriority.valueProperty().bindBidirectional(task.priorityProperty());

        Bindings.bindBidirectional(taskTags.textProperty(), task.tagsProperty(),
                new StringConverter<>() {
                    @Override
                    public String toString(ObservableList<String> object) {
                        return String.join(", ", object);
                    }

                    @Override
                    public ObservableList<String> fromString(String string) {
                        return FXCollections.observableArrayList(processTaskTags(string));
                    }
                });
    }

    @FXML
    private void onDeleteClick() {
        close();
        model.remove(task.getId());
    }

    @FXML
    private void onCloseClick() {
        close();
        model.updateTask(task);
    }

    private void close() {
        stackPane.getChildren().remove(this);
    }
}

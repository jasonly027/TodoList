package net.jasonly027.todolist.components;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.jasonly027.todolist.models.Priority;
import net.jasonly027.todolist.models.Task;
import net.jasonly027.todolist.models.TaskBuilder;
import net.jasonly027.todolist.models.TasksModel;

import static net.jasonly027.todolist.lib.Util.*;
import static net.jasonly027.todolist.lib.Util.initCustomComponent;

// A modal containing a form for creating a new Task.
public class AddTask extends VBox {
    @FXML
    private TextField taskName;
    @FXML
    private DatePicker taskDate;
    @FXML
    private ComboBox<Priority> taskPriority;
    @FXML
    private TextArea taskDescription;
    @FXML
    private TextField taskTags;
    @FXML
    private Text errorText;

    private final StackPane stackPane;
    private final TasksModel model;

    public AddTask(@NamedArg("stackPane") StackPane stackPane,
                   @NamedArg("model") TasksModel model) {
        this.stackPane = stackPane;
        this.model = model;
        initCustomComponent(this, "AddTask.fxml");
    }

    @FXML
    private void initialize() {
        setupTaskPriority();
    }

    private void setupTaskPriority() {
        taskPriority.getItems().addAll(Priority.values());
        taskPriority.setValue(Priority.NONE);
    }

    @FXML
    private void onCloseClick() {
        stackPane.getChildren().remove(this);
    }

    @FXML
    private void onCreateTaskClick() {
        if (!validateForm()) {
            errorText.setText("Name required for new task");
            return;
        }

        Task task = createTaskFromFields();
        model.add(task);

        onCloseClick();
    }

    private boolean validateForm() {
        return !taskName.getText().isBlank();
    }

    private Task createTaskFromFields() {
        return new TaskBuilder()
                .setName(taskName.getText())
                .setDescription(taskDescription.getText())
                .setDate(taskDate.getValue())
                .setPriority(taskPriority.getValue())
                .setTags(processTaskTags(taskTags.getText()))
                .setIsDone(false)
                .build();
    }

}

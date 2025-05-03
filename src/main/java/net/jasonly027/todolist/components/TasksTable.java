package net.jasonly027.todolist.components;

import javafx.beans.NamedArg;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.jasonly027.todolist.models.Priority;
import net.jasonly027.todolist.models.Task;
import net.jasonly027.todolist.models.TasksModel;

import java.time.LocalDateTime;

import static net.jasonly027.todolist.util.Util.initCustomComponent;

public class TasksTable extends VBox {
    @FXML
    private TableView<Task> table;
    @FXML
    private TableColumn<Task, String> nameCol;
    @FXML
    private TableColumn<Task, String> descriptionCol;
    @FXML
    private TableColumn<Task, LocalDateTime> dateCol;
    @FXML
    private TableColumn<Task, Priority> priorityCol;
    @FXML
    private TableColumn<Task, ObservableList<String>> tagsCol;

    private final StackPane stackPane;

    private TasksModel model;

    public void initModel(TasksModel model) {
        if (this.model != null) {
            throw new IllegalStateException("Model was already initialized");
        }
        this.model = model;
        table.setItems(model.getTasks());
    }

    public TasksTable(@NamedArg("stackPane") StackPane stackPane) {
        this.stackPane = stackPane;
        initCustomComponent(this, "TasksTable.fxml");
    }

    @FXML
    private void initialize() {
        setupColumnValues();
        setupTableStyle();

        table.setRowFactory(table -> {
            TableRow<Task> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (row.isEmpty()) return;

                stackPane.getChildren().add(new TaskDetails(stackPane));
            });

            return row;
        });
    }

    // Define how each column gets its value from a Task
    private void setupColumnValues() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        tagsCol.setCellValueFactory(new PropertyValueFactory<>("tags"));
    }

    private void setupTableStyle() {
        table.getColumns().forEach(col -> {
            col.setReorderable(false);
            col.setMinWidth(30);
        });

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}

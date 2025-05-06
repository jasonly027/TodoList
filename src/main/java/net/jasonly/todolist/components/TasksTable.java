package net.jasonly.todolist.components;

import javafx.beans.NamedArg;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.jasonly.todolist.models.Priority;
import net.jasonly.todolist.models.Task;
import net.jasonly.todolist.models.TasksModel;
import net.jasonly.todolist.lib.ReinitializationException;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import static net.jasonly.todolist.lib.Util.initCustomComponent;

// A table visualizing a TasksModel.
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
    @FXML
    private TableColumn<Task, Boolean> isDoneCol;

    private final StackPane stackPane;
    private TasksModel model;

    public TasksTable(@NamedArg("stackPane") StackPane stackPane) {
        this.stackPane = stackPane;
        initCustomComponent(this, "TasksTable.fxml");
    }

    @FXML
    private void initialize() {
        setupTable();
    }

    public void init(TasksModel model) {
        if (this.model != null) {
            throw new ReinitializationException();
        }
        this.model = model;

        this.model.setSortComparator(table.comparatorProperty());
        table.setItems(this.model.getTasks());
        setIsDoneVisibility(false);
    }

    private void setupTable() {
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setupColumnValues();
        setupColumnStyle();

        table.setRowFactory(table -> {
            TableRow<Task> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() < 2 || row.isEmpty()) return;
                stackPane.getChildren().add(new TaskDetails(stackPane, model, row.getItem()));
            });

            return row;
        });
    }

    // Setups how each column gets its value from a Task
    private void setupColumnValues() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        tagsCol.setCellValueFactory(new PropertyValueFactory<>("tags"));
        isDoneCol.setCellValueFactory(new PropertyValueFactory<>("isDone"));
    }

    // Setups the visual appearance of columns
    private void setupColumnStyle() {
        table.getColumns().forEach(col -> {
            col.setReorderable(false);
            col.setSortable(true);
            col.setMinWidth(30);
        });
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        isDoneCol.setCellFactory(cell -> new CheckBoxTableCell<>());
    }

    public ObservableList<Task> getSelected() {
        return table.getSelectionModel().getSelectedItems();
    }

    private final Predicate<Task> incompleteTasksOnly = task -> !task.isDoneProperty().get();

    public void setIsDoneVisibility(boolean visible) {
        if (visible) {
            model.removeFilter(incompleteTasksOnly);
        } else {
            model.addFilter(incompleteTasksOnly);
        }
        isDoneCol.setVisible(visible);
    }
}

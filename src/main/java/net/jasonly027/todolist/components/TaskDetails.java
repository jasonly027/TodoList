package net.jasonly027.todolist.components;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import static net.jasonly027.todolist.util.Util.initCustomComponent;

public class TaskDetails extends VBox {
    private final StackPane stackPane;

    public TaskDetails(@NamedArg("stackPane") StackPane stackPane) {
        this.stackPane = stackPane;
        initCustomComponent(this, "TaskDetails.fxml");
    }

    @FXML
    private void onCloseClick() {
        stackPane.getChildren().remove(this);
    }
}

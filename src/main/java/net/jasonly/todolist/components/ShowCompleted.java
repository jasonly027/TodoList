package net.jasonly.todolist.components;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import net.jasonly.todolist.models.TasksModel;
import net.jasonly.todolist.lib.ReinitializationException;

// A button that toggles visibility of the Done column of TasksTable.
public class ShowCompleted extends Button {
    private boolean eyeIsOpen = false;
    private final SVGPath openEyeSvg = createOpenEye();
    private final SVGPath closedEyeSvg = createClosedEye();

    private TasksModel model;
    private TasksTable tasksTable;

    public ShowCompleted() {
        setText("Completed");
        setMaxHeight(Double.MAX_VALUE);
        setMinWidth(105);
        setGraphic(closedEyeSvg);

        setOnAction(this::toggleCompletedAction);
    }

    private void toggleCompletedAction(ActionEvent actionEvent) {
        eyeIsOpen = !eyeIsOpen;
        tasksTable.setIsDoneVisibility(eyeIsOpen);
        setGraphic(eyeIsOpen ? openEyeSvg : closedEyeSvg);
    }

    public void init(TasksModel model, TasksTable tasksTable) {
        if (this.model != null || this.tasksTable != null) {
            throw new ReinitializationException();
        }
        this.model = model;
        this.tasksTable = tasksTable;
    }

    private static SVGPath createOpenEye() {
        SVGPath openEyeSvg = new SVGPath();
        openEyeSvg.setContent("M2.036 12.322a1.012 1.012 0 0 1 0-.639C3.423 7.51 7.36 4.5 12 4" +
                ".5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 " +
                "12 19.5c-4.638 0-8.573-3.007-9.963-7.178ZM15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z");
        openEyeSvg.setFill(Paint.valueOf("transparent"));
        openEyeSvg.setStroke(Paint.valueOf("black"));
        openEyeSvg.setStrokeWidth(1.5);
        openEyeSvg.setScaleX(.8);
        openEyeSvg.setScaleY(.8);
        return openEyeSvg;
    }

    private static SVGPath createClosedEye() {
        SVGPath closedEyeSvg = new SVGPath();
        closedEyeSvg.setContent("M3.98 8.223A10.477 10.477 0 0 0 1.934 12C3.226 16.338 7.244 19.5" +
                " 12 19.5c.993 0 1.953-.138 2.863-.395M6.228 6.228A10.451 10.451 0 0 1 12 4.5c4" +
                ".756 0 8.773 3.162 10.065 7.498a10.522 10.522 0 0 1-4.293 5.774M6.228 6.228 3 " +
                "3m3.228 3.228 3.65 3.65m7.894 7.894L21 21m-3.228-3.228-3.65-3.65m0 0a3 3 0 1 0-4" +
                ".243-4.243m4.242 4.242L9.88 9.88");
        closedEyeSvg.setFill(Paint.valueOf("transparent"));
        closedEyeSvg.setStroke(Paint.valueOf("black"));
        closedEyeSvg.setStrokeWidth(1.5);
        closedEyeSvg.setScaleX(.8);
        closedEyeSvg.setScaleY(.8);
        return closedEyeSvg;
    }
}

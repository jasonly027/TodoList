package net.jasonly027.todolist.components;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import net.jasonly027.todolist.models.Task;
import net.jasonly027.todolist.lib.ReinitializationException;

// A button that toggles the done status of a Task.
public class ToggleDone extends Button {
    private final SVGPath checkmarkSvg = createCheckmark();
    private final SVGPath crossSvg = createCross();

    private Task task;

    public ToggleDone() {
        this.setTooltip(new Tooltip());
        setMinWidth(35);
    }

    public void init(Task task) {
        if (this.task != null) {
            throw new ReinitializationException();
        }
        this.task = task;

        setupBindings();
        setOnAction(this::onAction);
    }

    private void setupBindings() {
        // Icon is a cross when Task is done, otherwise it's a checkmark
        graphicProperty().bind(
                Bindings.createObjectBinding(
                        () -> task.isDoneProperty().get()
                                ? crossSvg : checkmarkSvg,
                        task.isDoneProperty()
                )
        );
        getTooltip().textProperty().bind(
                Bindings.createStringBinding(
                        () -> task.isDoneProperty().get()
                                ? "Mark task incomplete" : "Mark task complete",
                        task.isDoneProperty()
                )
        );
    }

    private void onAction(ActionEvent actionEvent) {
        task.isDoneProperty().set(!task.isDoneProperty().get());
    }

    private static SVGPath createCheckmark() {
        SVGPath checkmark = new SVGPath();
        checkmark.setContent("m4.5 12.75 6 6 9-13.5");
        checkmark.setFill(Paint.valueOf("transparent"));
        checkmark.setStroke(Paint.valueOf("black"));
        checkmark.setStrokeWidth(1.5);
        checkmark.setScaleX(.8);
        checkmark.setScaleY(.8);
        return checkmark;
    }

    private static SVGPath createCross() {
        SVGPath checkmark = new SVGPath();
        checkmark.setContent("M6 18 18 6M6 6l12 12");
        checkmark.setFill(Paint.valueOf("transparent"));
        checkmark.setStroke(Paint.valueOf("black"));
        checkmark.setStrokeWidth(1.5);
        checkmark.setScaleX(.8);
        checkmark.setScaleY(.8);
        return checkmark;
    }
}

package net.jasonly027.todolist.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class ToggleCompleted extends Button {
    private boolean eyeOpen;
    private SVGPath openEye;
    private SVGPath closedEye;

    @FXML
    private EventHandler<ActionEvent> onOpenEyeClick;
    @FXML
    private EventHandler<ActionEvent> onClosedEyeClick;

    public ToggleCompleted() {
        eyeOpen = false;
        initEyes();
        onOpenEyeClick = ev -> {
        };
        onClosedEyeClick = ev -> {
        };

        setText("Completed");
        setMaxHeight(Double.MAX_VALUE);
        setGraphic(closedEye);

        setOnAction(this::onToggleCompletedClicked);
    }

    private void initEyes() {
        openEye = new SVGPath();
        openEye.setContent("M2.036 12.322a1.012 1.012 0 0 1 0-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178ZM15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z");
        openEye.setFill(Paint.valueOf("transparent"));
        openEye.setStroke(Paint.valueOf("black"));
        openEye.setStrokeWidth(1.5);
        openEye.setScaleX(.8);
        openEye.setScaleY(.8);

        closedEye = new SVGPath();
        closedEye.setContent("M3.98 8.223A10.477 10.477 0 0 0 1.934 12C3.226 16.338 7.244 19.5 12 19.5c.993 0 1.953-.138 2.863-.395M6.228 6.228A10.451 10.451 0 0 1 12 4.5c4.756 0 8.773 3.162 10.065 7.498a10.522 10.522 0 0 1-4.293 5.774M6.228 6.228 3 3m3.228 3.228 3.65 3.65m7.894 7.894L21 21m-3.228-3.228-3.65-3.65m0 0a3 3 0 1 0-4.243-4.243m4.242 4.242L9.88 9.88");
        closedEye.setFill(Paint.valueOf("transparent"));
        closedEye.setStroke(Paint.valueOf("black"));
        closedEye.setStrokeWidth(1.5);
        closedEye.setScaleX(.8);
        closedEye.setScaleY(.8);
    }

    public EventHandler<ActionEvent> getOnOpenEyeClick() {
        return onOpenEyeClick;
    }

    public void setOnOpenEyeClick(EventHandler<ActionEvent> onOpenEyeClick) {
        this.onOpenEyeClick = onOpenEyeClick;
    }

    public EventHandler<ActionEvent> getOnClosedEyeClick() {
        return onClosedEyeClick;
    }

    public void setOnClosedEyeClick(EventHandler<ActionEvent> onClosedEyeClick) {
        this.onClosedEyeClick = onClosedEyeClick;
    }


    private void onToggleCompletedClicked(ActionEvent actionEvent) {
        if (eyeOpen) {
            onOpenEyeClick.handle(actionEvent);
        } else {
            onClosedEyeClick.handle(actionEvent);
        }

        eyeOpen = !eyeOpen;
        setGraphic(eyeOpen ? openEye : closedEye);
    }
}

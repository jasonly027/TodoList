package net.jasonly027.todolist.util;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;

public class SceneHelper {
    public static Scene createSceneWithPercentageSize(Parent parent, double percentWidth,
                                                      double percentHeight) {
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        return new Scene(parent, screenWidth * percentWidth, screenHeight * percentHeight);
    }
}

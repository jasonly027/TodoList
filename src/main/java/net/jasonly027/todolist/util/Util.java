package net.jasonly027.todolist.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;

import java.io.IOException;

public class Util {
    public static Scene createSceneWithPercentageSize(Parent parent, double percentWidth,
                                                      double percentHeight) {
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        return new Scene(parent, screenWidth * percentWidth, screenHeight * percentHeight);
    }

    public static void initCustomComponent(Parent component, String resource) {
        FXMLLoader loader = new FXMLLoader(Util.class.getResource("/fxml/components/" + resource));
        loader.setRoot(component);
        loader.setController(component);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

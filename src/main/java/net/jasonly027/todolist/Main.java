package net.jasonly027.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static net.jasonly027.todolist.util.SceneHelper.createSceneWithPercentageSize;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("TodoList");

        FXMLLoader mainView = new FXMLLoader(getClass().getResource("/fxml/views/mainView.fxml"));
        Scene scene = createSceneWithPercentageSize(mainView.load(), 0.7, 0.8);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

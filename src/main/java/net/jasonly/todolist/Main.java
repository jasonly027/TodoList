package net.jasonly.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import static net.jasonly.todolist.lib.Util.createSceneWithPercentageSize;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("TodoList");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/Main.fxml"));
        Parent root = loader.load();

        stage.setScene(createSceneWithPercentageSize(root, 0.7, 0.8));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
//        new Database();
//        System.out.println("Database created");
    }
}

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 300, 200);
        Controller controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setScene(scene);

        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
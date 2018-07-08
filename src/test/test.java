package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class test extends Application {
    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
        primaryStage.setMinWidth(200);
        primaryStage.setMinHeight(100);

        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        Controller_1 controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setScene(scene);

        primaryStage.setTitle("Title");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }


}
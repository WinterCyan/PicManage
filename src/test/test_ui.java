package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class test_ui extends Application {
    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("test_ui.fxml"));
        primaryStage.setMinWidth(750);
        primaryStage.setMinHeight(550);

        Parent root = loader.load();
        Scene scene = new Scene(root, 1005, 720);
        Controller_ui controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setScene(scene);

        primaryStage.setTitle("main_pane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }


}
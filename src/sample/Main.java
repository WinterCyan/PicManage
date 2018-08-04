package sample;
//
//import javafx.application.application;
//import javafx.fxml.fxmlloader;
//import javafx.scene.parent;
//import javafx.scene.scene;
//import javafx.stage.stage;
//
//import java.io.ioexception;
//
////
//public class main extends application {
//    public void start(stage primarystage) throws ioexception {
//        fxmlloader loader = new fxmlloader(getclass().getresource("sample.fxml"));
//        parent root = loader.load();
//        scene scene = new scene(root, 300, 200);
//        controller controller = loader.getcontroller();
//        controller.setstage(primarystage);
//        controller.setscene(scene);
//
//        primarystage.settitle("test");
//        primarystage.setscene(scene);
//        primarystage.show();
//    }
//}

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        ProgressBar progressBar = new ProgressBar();
        StackPane root = new StackPane(progressBar);
        Scene scene = new Scene(root, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
package test.test_common;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.util.Timer;
import java.util.TimerTask;

public class Controller_1 {
    public Stage stage;
    public Scene scene;
    private int DELAY = 300;
    private boolean clicked = false;
    public static final String TYPE_SINGLE = "single";
    public static final String TYPE_DOUBLE = "double";
    private static final String TYPE_RIGHT = "right";
    private String clickType;

    @FXML
    Button btn1;

    @FXML
    FlowPane flowPane;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void btnAction(ActionEvent event){
        System.out.println(event.getSource().toString());
        System.out.println("Clicked.");
        for (int i = 0; i < 10; i ++){
            VBox vBox0 = new VBox();
            vBox0.setMouseTransparent(false);
            VBox vBox = new VBox();
            vBox.setMouseTransparent(true);
            Button button = new Button("ClickButton");
            Label label = new Label("vBox");
            vBox.getChildren().addAll(button, label);
            vBox0.getChildren().add(vBox);
            vBox0.setId("vBox0");

            // Need optimization.
            vBox0.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY){
                    if (event1.getClickCount() % 2 == 1) {   // single click.
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if (!clicked) {   // clicked is false, which means double click has not invoked.
                                    System.out.println("ImageView is clicked.");
                                    clickType = TYPE_SINGLE;
                                } else clicked = false;
                            }
                        }, DELAY);
                    } else { // execute before single click is triggered.
                        clicked = true;
                        System.out.println("ImageView is double-clicked.");
                        clickType = TYPE_DOUBLE;
                    }
                }else if (event1.getButton() == MouseButton.SECONDARY){
                    System.out.println("ImageView is right-clicked.");
                    clickType = TYPE_RIGHT;
                }
            });
            flowPane.getChildren().add(vBox0);
        }
    }
}


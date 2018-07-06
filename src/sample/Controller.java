package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    public Stage stage;
    public Scene scene;

    @FXML
    Button button;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void buttonAction(ActionEvent event){
        System.out.print("Button!");
        button.setText("Clicked!");
    }
}
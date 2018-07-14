package test.test_info;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TestController {
    public Stage stage;
    public Scene scene;

    @FXML
    Button button;
    @FXML
    InfoPane infoPane;
    @FXML
    PaneController infoPaneController;

    public void setStage(Stage stage) {
        this.stage = stage;
        infoPane.getController().setStage(stage);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        infoPane.getController().setScene(scene);
    }

    @FXML
    private void buttonAction(){
        if (PaneController.getBtnActive()) PaneController.setButton(true);
        else PaneController.setButton(false);
    }
}

package test.test_info;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaneController {
    public Stage stage;
    public Scene scene;

    @FXML
    static Button infoBtn;
    @FXML
    static Text text;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void btnAction(){
        System.out.print("Button!");
        infoBtn.setText("Clicked!");
        text.setText(text.getText()+"On click.");
    }

    public static boolean getBtnActive(){
        return infoBtn.isDisabled();
    }

    public static void setButton(boolean active){
        infoBtn.setDisable(active);
    }
}
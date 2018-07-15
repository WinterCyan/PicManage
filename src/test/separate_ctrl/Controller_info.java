package test.separate_ctrl;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class Controller_info {
    private Controller_main controllerMain;
    public boolean info_state = false;

    @FXML
    public VBox info_pane;

    public void injectMainController(Controller_main controllerMain){
        this.controllerMain = controllerMain;
    }
}

package test.separate_ctrl;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class Controller_menu {
    private Controller_main controllerMain;
    public boolean menu_state = false;

    @FXML
    public VBox menu_pane;

    public void injectMainController(Controller_main controllerMain){
        this.controllerMain = controllerMain;
    }
}

package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class InfoController {
    private MainController controllerMain;
    public boolean info_state = false;

    @FXML
    public VBox info_pane;

    public void injectMainController(MainController controllerMain){
        this.controllerMain = controllerMain;
    }
}

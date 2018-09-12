package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class InfoController {
    private MainController controllerMain;
    public boolean info_state = false;

    @FXML
    public VBox info_pane;
    @FXML
    TextArea general_info;

    public void injectMainController(MainController controllerMain){
        this.controllerMain = controllerMain;
    }

    public void setInfoPane(String info){
        general_info.setText(info);
    }
}

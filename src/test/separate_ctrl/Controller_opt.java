package test.separate_ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller_opt {
    private Controller_main controllerMain;
    void injectMainController(Controller_main controllerMain){
        this.controllerMain = controllerMain;
    }

    @FXML
    public void pathBtnAction(ActionEvent event) {

    }
}

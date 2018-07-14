package multi_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Part1Controller {

    @FXML
    Button button;

    private Controller mainController;
    //注入MainController
    void injectMainController(Controller mainController) {
        this.mainController = mainController;
    }

    //得到想要的TextArea
    private TextArea getTextArea () {
        return this.mainController.getOutputPane();
    }

    @FXML
    private void onButtonAction() {
        getTextArea().appendText("Fuck me\n");
    }

    @FXML
    private void initialize() {
    }
}
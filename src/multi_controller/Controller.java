package multi_controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Controller {
    public Stage stage;
    public Scene scene;
    //一定要初始化这两个
    //并且，这个变量名一定是这样的模式：`<fx:id>Controller`
    //Eg，你的example.fxml中的`fx:id="example"`，那么你的变量名一定是`exampleController`
    //如果不信，欢迎尝试`NPE` :D
    //PS:fxml或者java文件名无所谓，只看`MainController`变量名，肉测!
    @FXML
    Part1Controller partController;

    @FXML
    Part2Controller part2Controller;


    @FXML
    private void initialize() {
        partController.injectMainController(this);
    }

    TextArea getOutputPane() {
        return part2Controller.textArea;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
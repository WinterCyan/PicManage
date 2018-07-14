package test.separate_ctrl;

public class Controller_info {
    private Controller_main controllerMain;
    void injectMainController(Controller_main controllerMain){
        this.controllerMain = controllerMain;
    }
}

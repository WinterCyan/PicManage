package test.separate_ctrl;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller_main {
    public Stage stage;
    public Scene scene;

    @FXML Controller_info infoController;
    @FXML Controller_show showController;
    @FXML Controller_menu menuController;
    @FXML Controller_opt optController;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    @FXML private void initialize(){
        infoController.injectMainController(this);
        showController.injectMainController(this);
        menuController.injectMainController(this);
        optController.injectMainController(this);
    }
}

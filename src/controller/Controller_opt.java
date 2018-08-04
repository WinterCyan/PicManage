package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller_opt {
    private Controller_main controllerMain;
    private Stage primaryStage;

    @FXML Label path_text;
    @FXML Button info_btn;
    @FXML Button menu_btn;
    @FXML ProgressBar progress_bar;

    void injectMainController(Controller_main controllerMain){
        this.controllerMain = controllerMain;
    }

    @FXML
    public void pathBtnAction() throws Exception {
        DirectoryChooser chooser = new DirectoryChooser();
        File selected = chooser.showDialog(primaryStage);
        if (selected == null) path_text.setText("No path selected.");
        else {
            controllerMain.showController.show_pane.getChildren().clear();
            controllerMain.showController.setPath(selected.toPath());
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void infoBtnAction() {
        if (controllerMain.getInfoState()){
            controllerMain.getInfoPane().setMaxWidth(0);
            controllerMain.getInfoPane().setMinWidth(0);
            controllerMain.setInfoState(false);
        }
        else {
            controllerMain.getInfoPane().setMaxWidth(230);
            controllerMain.getInfoPane().setMinWidth(230);
            controllerMain.setInfoState(true);
        }
    }

    @FXML
    public void menuBtnAction(){
        if (controllerMain.getMenuState()){
            controllerMain.getMenuPane().setMaxWidth(0);
            controllerMain.getMenuPane().setMinWidth(0);
            controllerMain.setMenuState(false);
        }
        else {
            controllerMain.getMenuPane().setMaxWidth(150);
            controllerMain.getMenuPane().setMaxWidth(150);
            controllerMain.setMenuState(true);
        }
    }
}
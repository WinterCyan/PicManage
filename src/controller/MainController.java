package controller;

import entity.Folder;
import entity.PhotoList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.util.ArrayList;

public class MainController {
    public Stage stage;
    public Scene scene;
    public static PhotoList photoList = new PhotoList();
    public static ArrayList<Folder> folderList = new ArrayList<>();
    public static Path path;

    @FXML
    InfoController infoController;
    @FXML
    ShowController showController;
    @FXML
    MenuController menuController;
    @FXML
    OptController optController;

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
        optController.setPrimaryStage(stage);
    }

    public VBox getInfoPane(){
        return infoController.info_pane;
    }
    public VBox getMenuPane(){return menuController.menu_pane;}
//    public FlowPane getShowPane(){return }

    public boolean getInfoState(){
        return infoController.info_state;
    }
    public boolean getMenuState(){
        return menuController.menu_state;
    }

    public void setInfoState(boolean state){
        infoController.info_state = state;
    }
    public void setMenuState(boolean state){
        menuController.menu_state = state;
    }
}
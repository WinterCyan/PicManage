package controller;

import entity.Folder;
import entity.Photo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import utility.MetadataInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class OptController {
    private MainController mainController;
    private Stage primaryStage;
    private String type;

    @FXML Label path_text;
    @FXML Button info_btn;
    @FXML Button menu_btn;
    @FXML ProgressBar progress_bar;

    void injectMainController(MainController controllerMain){
        this.mainController = controllerMain;
    }

    @FXML
    public void pathBtnAction() throws Exception {
        DirectoryChooser chooser = new DirectoryChooser();
        File selected = chooser.showDialog(primaryStage);
        if (selected == null) {
            if (path_text.getText().equals("")) path_text.setText("No path selected.");
        }
        else {
            mainController.showController.show_pane.getChildren().clear();
            mainController.showController.setPath(selected.toPath());
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void infoBtnAction() {
        if (mainController.getInfoState()){
            mainController.getInfoPane().setMaxWidth(0);
            mainController.getInfoPane().setMinWidth(0);
            mainController.setInfoState(false);
        }
        else {
            // refresh ui. get information, get label and text area, fill it.
            String type = mainController.showController.getIsShowingType();
            if (type!=null){
                // folder:
                if (type.equals(ElementController.FOLDER_TYPE)){
                    ElementController controller = mainController.showController.getSelectedFolder();
                    Folder folder = controller.getFolder();
                    int photoNum = folder.getFileNum();
                    type = "Folder";
                    int totalSize = folder.getTotalSize();
                    // show information.
                    mainController.infoController.setInfoPane("▶︎Type:\n      "+type+"\n" +
                            "▶Photo Number:\n      "+photoNum+"\n" +
                            "▶Total Size:\n      "+(totalSize/1000)/1000.0+" Mb");

                    mainController.getInfoPane().setMaxWidth(230);
                    mainController.getInfoPane().setMinWidth(230);
                } else if (type.equals(ElementController.PHOTO_TYPE)){
                    // photo:
                    ArrayList<ElementController> controllerList = mainController.showController.getSelectedList();
                    if (controllerList.size()==1){
                        StringBuilder photoInfo = new StringBuilder();
                        ElementController controller = controllerList.get(0);
                        Photo photo = controller.getPhoto();
                        HashMap<String,String> map = MetadataInfo.getMap(photo.getDir());
                        TreeMap<String,String> treeMap = new TreeMap<>();
                        treeMap.putAll(map);
                        for (String tag:treeMap.keySet()){
                            String tagValue = map.get(tag);
                            String oneLine = tagValue.split("] ")[1];
                            String[] twoParts = oneLine.split(" - ");
                            String oneName =twoParts[0];
                            String oneValue =twoParts[1];
                            photoInfo.append("▶︎"+oneName+":\n      "+oneValue+"\n");
                        }
                        mainController.infoController.setInfoPane(photoInfo.toString());
                        mainController.getInfoPane().setMaxWidth(230);
                        mainController.getInfoPane().setMinWidth(230);
                    }
                }
            }
            mainController.setInfoState(true);
        }
    }

    @FXML
    public void menuBtnAction(){
        if (mainController.getMenuState()){
            mainController.getMenuPane().setMaxWidth(0);
            mainController.getMenuPane().setMinWidth(0);
            mainController.setMenuState(false);
        }
        else {
            mainController.getMenuPane().setMaxWidth(150);
            mainController.getMenuPane().setMaxWidth(150);
            mainController.setMenuState(true);
        }
    }
}
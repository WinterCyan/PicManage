package controller;

import db.DBInit;
import db.PhotoTable;
import entity.Photo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import utility.MetadataInfo;
import utility.PathAnalysis;
import utility.StringToTimestamp;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller_show {
    private Controller_main controllerMain;
    private static ArrayList<String> typeList = new ArrayList<>(){{
        add("jpeg");
        add("jpg");
        add("png");
    }};

    @FXML
    FlowPane show_pane;
    @FXML
    Controller_element elementController;

    void injectMainController(Controller_main controllerMain){
        this.controllerMain = controllerMain;
    }

    public void setPath(Path path) throws Exception {
        controllerMain.path = path;
        refreshDB();
    }

    public void refreshDB() throws Exception{
        DBInit.DBInit();
        ArrayList<Path> paths = PathAnalysis.analysis(controllerMain.path);
        for (Path path:paths){
            File file = new File(path.toString());
            HashMap<String, String> map = MetadataInfo.getMap(file.toPath());
            String model = null;
            if (map.get("Model")!=null) model = map.get("Model").substring(20);
            PhotoTable.addPhoto(file.getName(),file.getPath(),model,(int)file.length(),
                    StringToTimestamp.getOriginTime(file),StringToTimestamp.getModifiedTime(file));
        }
    }


    public void refreshViewer() throws Exception {
        show_pane.getChildren().clear();
        for (Photo photo : controllerMain.photoList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/element.fxml"));
            VBox element = loader.load();
            Controller_element controllerElement = loader.getController();
            controllerElement.setName(photo.getName());
            Image thumbnail = new Image(photo.getDir().toUri().toString(), 120, 140, false, false);
            controllerElement.setImage(thumbnail);
            show_pane.getChildren().add(element);
        }
    }
}
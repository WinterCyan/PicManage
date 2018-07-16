package test.separate_ctrl;

import entity.Photo;
import entity.PhotoList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.apache.commons.io.FilenameUtils;
import test.test_common.MetadataInfo;
import ui.Element;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller_show {
    private Controller_main controllerMain;
    private static Path path;
    private static PhotoList photoList = new PhotoList();
    private ArrayList<Element> elements;
    private ArrayList<Path> paths = new ArrayList<>();
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

    public void setPath(Path path) throws IOException {
        Controller_show.path = path;
        this.refreshViewer();
        System.out.println(path);
    }

    public void refreshViewer() throws IOException {
        File dir = new File(path.toString());
        File[] list = dir.listFiles();
        for (File file:list){
            String type = FilenameUtils.getExtension(file.toString());
//            if (file.isFile()&&!type.equals(""))
//                System.out.println(type);
//            if (file.isFile()&&type.equals("")) System.out.println(file.toString());
            if (typeList.contains(type)) {
                Photo photo = new Photo(file);
                HashMap<String, String> map = MetadataInfo.getMap(file.toPath());
                System.out.println(map);
                photo.setSize((float)file.length()/1000000);
                photo.setName(file.getName());
                photo.setDevice(map.get("Model"));
                photo.setDir(file.toPath());
                photo.setTime(map.get("Date/Time Original"));
                photoList.add(photo);
                this.showPhotos(photoList);
            }
        }
    }

    public void showPhotos(PhotoList list) throws IOException {
        for (Photo photo:list){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("element.fxml"));
            VBox element = loader.load();
            Controller_element controllerElement = loader.getController();
            controllerElement.setName(photo.getName());
            show_pane.getChildren().add(element);
        }
    }
}
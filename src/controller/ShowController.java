package controller;

import db.DBInit;
import db.PhotoTable;
import entity.Folder;
import entity.Photo;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;
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

public class ShowController {
    private MainController mainController;
    private static ArrayList<ElementController> selectedList = new ArrayList<>();
    private static ElementController selectedFolder = null;
    private String isShowingType;

    @FXML
    FlowPane show_pane;
    @FXML
    ProgressBar show_progress;
    @FXML
    ElementController elementController;

    public static ArrayList<ElementController> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(ArrayList<ElementController> selectedList) {
        ShowController.selectedList = selectedList;
    }

    public static ElementController getSelectedFolder() {
        return selectedFolder;
    }

    public static void setSelectedFolder(ElementController selectedFolder) {
        ShowController.selectedFolder = selectedFolder;
    }

    void injectMainController(MainController controllerMain){
        this.mainController = controllerMain;
    }

    public void setPath(Path selectedPath) throws Exception {
        mainController.path = selectedPath;
        resetDB(selectedPath);
    }

    public void resetDB(Path selectedPath) throws Exception{
        DBInit.DBInit();
        ArrayList<Path> paths = PathAnalysis.analysis(mainController.path);
        Service service = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        System.out.println("Start time: "+System.currentTimeMillis());
                        mainController.optController.progress_bar.setVisible(true);
                        int progress = 0;
                        HashMap<String, String> map;
                        for (Path path:paths) {
                            File file = new File(path.toString());
                            map = MetadataInfo.getMap(file.toPath());
                            String model;
                            if (map.get("Model") != null) model = map.get("Model").substring(20);
                            else model = "null";
                            PhotoTable.addPhoto(file.getName(), file.getPath(), model, (int) file.length(),
                                    StringToTimestamp.getOriginTime(file), StringToTimestamp.getModifiedTime(file));
                            progress++;
                            updateProgress(progress, paths.size());
                        }
                        mainController.optController.progress_bar.setVisible(false);
                        System.out.println("End time: "+System.currentTimeMillis());
                        Platform.runLater(() -> mainController.optController.path_text.setText("Path: "+selectedPath.toString()));
                        return null;
                    }
                };
            }
        };
        mainController.optController.progress_bar.progressProperty().bind(service.progressProperty());
        service.start();
    }

    public void refreshViewer() throws Exception {
        show_pane.getChildren().clear();
        for (Photo photo : mainController.photoList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/element.fxml"));
            VBox element = loader.load();
            ElementController elementController = loader.getController();
            elementController.setMainController(mainController);
            elementController.setPhoto(photo);
            elementController.setName(photo.getName());
            elementController.setType(ElementController.PHOTO_TYPE);
            Image thumbnail = new Image(photo.getDir().toUri().toString(), 120, 140, false, false);
            elementController.setImage(thumbnail);
            show_pane.getChildren().add(element);
        }
    }

    public void refreshViewerFolder(String type) throws Exception{
        show_pane.getChildren().clear();
        for (Folder folder : mainController.folderList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/element.fxml"));
            VBox element = loader.load();
            ElementController elementController = loader.getController();
            elementController.setMainController(mainController);
            elementController.setFolder(folder);
            elementController.setName(folder.getName());
            elementController.setType(ElementController.FOLDER_TYPE);
            elementController.setFolderType(type);
            elementController.name.setMouseTransparent(true);
            String imagePath = "/pic/folder_icon.png";
            Image image = new Image(imagePath);
            elementController.setImage(image);
            show_pane.getChildren().add(element);
        }
    }

    public String getIsShowingType() {
        return isShowingType;
    }

    public void setIsShowingType(String isShowingType) {
        this.isShowingType = isShowingType;
    }
}
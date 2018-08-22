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
    private MainController controllerMain;
    private static ArrayList<ElementController> selectedList = new ArrayList<>();
    private static ElementController selectedFolder = null;

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
        this.controllerMain = controllerMain;
    }

    public void setPath(Path selectedPath) throws Exception {
        controllerMain.path = selectedPath;
        resetDB(selectedPath);
    }

    public void resetDB(Path selectedPath) throws Exception{
        DBInit.DBInit();
        ArrayList<Path> paths = PathAnalysis.analysis(controllerMain.path);
        Service service = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        controllerMain.optController.progress_bar.setVisible(true);
                        int progress = 0;
                        for (Path path:paths) {
                            File file = new File(path.toString());
                            HashMap<String, String> map = MetadataInfo.getMap(file.toPath());
                            String model = null;
                            if (map.get("Model") != null) model = map.get("Model").substring(20);
                            PhotoTable.addPhoto(file.getName(), file.getPath(), model, (int) file.length(),
                                    StringToTimestamp.getOriginTime(file), StringToTimestamp.getModifiedTime(file));
                            progress++;
                            updateProgress(progress, paths.size());
                        }
                        controllerMain.optController.progress_bar.setVisible(false);
                        Platform.runLater(() -> controllerMain.optController.path_text.setText(selectedPath.toString()));
                        return null;
                    }
                };
            }
        };
        controllerMain.optController.progress_bar.progressProperty().bind(service.progressProperty());
        service.start();
    }


    public void refreshViewer() throws Exception {
        show_pane.getChildren().clear();
        for (Photo photo : controllerMain.photoList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/element.fxml"));
            VBox element = loader.load();
            ElementController elementController = loader.getController();
            elementController.setPhoto(photo);
            elementController.setName(photo.getName());
            elementController.setType(ElementController.photoType);
            Image thumbnail = new Image(photo.getDir().toUri().toString(), 120, 140, false, false);
            elementController.setImage(thumbnail);
            elementController.setShowController(this);
            show_pane.getChildren().add(element);
        }
    }

    public void refreshViewerFolder() throws Exception{
        show_pane.getChildren().clear();
        for (Folder folder : controllerMain.folderList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/element.fxml"));
            VBox element = loader.load();
            ElementController controllerElement = loader.getController();
            controllerElement.setName(folder.getName());
            controllerElement.setType(ElementController.folderType);
            controllerElement.name.setMouseTransparent(true);
            String imagePath = "/pic/folder_icon.png";
            Image image = new Image(imagePath);
            controllerElement.setImage(image);
            show_pane.getChildren().add(element);
        }
    }
}
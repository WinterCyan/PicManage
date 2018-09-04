package controller;

import entity.Folder;
import entity.Photo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ui.FolderMenu;
import ui.PhotoMenu;

public class ElementController {
    @FXML
    ImageView image;
    @FXML
    CheckBox name;
    @FXML
    VBox element_box;
    @FXML
    ShowController showController;

    private static MainController mainController;
    static final String photoType = "photo";
    static final String folderType = "folder";

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        ElementController.type = type;
    }

    private static String type = null;

    private static final PhotoMenu photoMenu = new PhotoMenu();
    private final FolderMenu folderMenu= new FolderMenu(this);
    private boolean folderSelected = false;
    private Photo photo;
    private Folder folder;

    public static void setMainController(MainController mainController) {
        ElementController.mainController = mainController;
    }

    @FXML
    public void onMouseClicked(MouseEvent mouseEvent) {
        switch (getType()){
            case photoType:
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    if (!showController.getSelectedList().isEmpty()) {
                        for (ElementController controller : showController.getSelectedList()) {
                            controller.name.setSelected(false);
                            controller.element_box.setStyle("-fx-background-color: #D6DBDF;");
                        }
                    }
                    showController.getSelectedList().clear();
                    showController.getSelectedList().add(this);
                    name.setSelected(true);
                    element_box.setStyle("-fx-background-color: #85C1E9;");
                    photoMenu.hide();
                }
                break;
            case folderType:
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    folderMenu.hide();
                    if (!folderSelected) {
                        if (showController.getSelectedFolder() != null) {
                            ElementController controller = showController.getSelectedFolder();
                            controller.element_box.setStyle("-fx-background-color: #D6DBDF;");
                            controller.name.setSelected(false);
                            controller.folderSelected = false;
                        }
                        element_box.setStyle("-fx-background-color: #85C1E9;");
                        folderSelected = true;
                        name.setSelected(true);
                        showController.setSelectedFolder(this);
                    }
                }
                break;
            default:
                break;
        }
    }

    @FXML
    public void onMouseEntered() {
        switch (getType()){
            case photoType:
                if (!name.isSelected()) element_box.setStyle("-fx-background-color: #85C1E9;");
                break;
            case folderType:
                if (!folderSelected) element_box.setStyle("-fx-background-color: #85C1E9;");
                break;
        }
    }

    @FXML
    public void onMouseExited() {
        switch (getType()){
            case photoType:
                if (!name.isSelected()) element_box.setStyle("-fx-background-color: #D6DBDF;");
                break;
            case folderType:
                if (!folderSelected) element_box.setStyle("-fx-background-color: #D6DBDF;");
                break;
        }
    }

    @FXML
    public void onCheckAction(ActionEvent actionEvent) {
        this.photoMenu.hide();
        if (name.isSelected()){
            showController.getSelectedList().add(this);
            element_box.setStyle("-fx-background-color: #85C1E9;");
        }
        else{
            showController.getSelectedList().remove(this);
            element_box.setStyle("-fx-background-color: #D6DBDF;");
        }
    }

    public void setName(String name){
        this.name.setText(name);
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }

    @FXML
    public void onContextMenu(ContextMenuEvent contextMenuEvent) {
        switch (getType()){
            case photoType:
                if (showController.getSelectedList().size() == 0){
                    showController.getSelectedList().add(this);
                    name.setSelected(true);
                    element_box.setStyle("-fx-background-color: #85C1E9;");
                } else {
                    if (!name.isSelected()) {
                        for (ElementController controller: showController.getSelectedList()){
                            controller.name.setSelected(false);
                            controller.element_box.setStyle("-fx-background-color: #D6DBDF;");
                        }
                        showController.getSelectedList().clear();
                        showController.getSelectedList().add(this);
                        name.setSelected(true);
                        element_box.setStyle("-fx-background-color: #85C1E9;");
                    }
                }
                photoMenu.show(element_box,contextMenuEvent.getScreenX(),contextMenuEvent.getScreenY());
                break;
            case folderType:
                if (!name.isSelected()) {
                    name.setSelected(true);
                    element_box.setStyle("-fx-background-color: #85C1E9;");
                }
                folderMenu.show(element_box,contextMenuEvent.getScreenX(),contextMenuEvent.getScreenY());
                break;
        }
    }

    public void setPhoto(Photo photo){
        this.photo = photo;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder){
        this.folder = folder;
    }

    public void openFolder() throws Exception{
        int id = folder.getId();
        mainController.photoList.clear();
        mainController.menuController.setPhotoList("select * from photo where device = '"+id+"';", null);
        mainController.showController.refreshViewer();
    }
}

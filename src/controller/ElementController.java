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
    static final String PHOTO_TYPE = "photo";
    static final String FOLDER_TYPE = "folder";
    static final String activityType = "activity";
    static final String deviceType = "device";
    static final String categoryType = "category";
    static final String personType = "person";

    private String type = null;
    private String folderType = null;

    private static final PhotoMenu photoMenu = new PhotoMenu();
    private final FolderMenu folderMenu= new FolderMenu(this);
    private boolean folderSelected = false;
    private Photo photo;
    private Folder folder;

    public static void setMainController(MainController mainController) {
        ElementController.mainController = mainController;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFolderType() {
        return folderType;
    }

    public void setFolderType(String folderType) {
        this.folderType = folderType;
    }

    @FXML
    public void onMouseClicked(MouseEvent mouseEvent) {
        switch (getType()){
            case PHOTO_TYPE:
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
            case FOLDER_TYPE:
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
            case PHOTO_TYPE:
                if (!name.isSelected()) element_box.setStyle("-fx-background-color: #85C1E9;");
                break;
            case FOLDER_TYPE:
                if (!folderSelected) element_box.setStyle("-fx-background-color: #85C1E9;");
                break;
        }
    }

    @FXML
    public void onMouseExited() {
        switch (getType()){
            case PHOTO_TYPE:
                if (!name.isSelected()) element_box.setStyle("-fx-background-color: #D6DBDF;");
                break;
            case FOLDER_TYPE:
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
            case PHOTO_TYPE:
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
            case FOLDER_TYPE:
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

    public void openFolder(String type) throws Exception{
        switch (type){
            case deviceType:
                int devcieId= folder.getId();
                mainController.photoList.clear();
                mainController.menuController.setPhotoList("select * from photo where device = '"+devcieId+"';", null);
                mainController.showController.refreshViewer();
                break;
            case activityType:
                int activityId = folder.getId();
                mainController.photoList.clear();
                mainController.menuController.setPhotoList("select * from photo where activity = '"+activityId+"';", null);
                mainController.showController.refreshViewer();
                break;
            default:
                break;
        }
        mainController.showController.setIsShowingType(PHOTO_TYPE);
    }
}

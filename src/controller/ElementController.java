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
    ShowController controllerShow;

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
    private static final FolderMenu folderMenu= new FolderMenu();
    private boolean folderSelected = false;
    private static Photo photo;
    private static Folder folder;

    @FXML
    public void onMouseClicked(MouseEvent mouseEvent) {
        switch (getType()){
            case photoType:
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    if (!controllerShow.getSelectedList().isEmpty())
                        for (ElementController controller : controllerShow.getSelectedList()) {
                            controller.name.setSelected(false);
                            controller.element_box.setStyle("-fx-background-color: #D6DBDF;");
                        }
                    controllerShow.getSelectedList().clear();
                    controllerShow.getSelectedList().add(this);
                    name.setSelected(true);
                    element_box.setStyle("-fx-background-color: #85C1E9;");
                    this.photoMenu.hide();
                }
                break;
            case folderType:
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    this.folderMenu.hide();
                    if (!folderSelected) {
                        controllerShow.getSelectedFolder().element_box.setStyle("-fx-background-color: #D6DBDF;");
                        controllerShow.getSelectedFolder().folderSelected = false;
                        element_box.setStyle("-fx-background-color: #85C1E9;");
                        folderSelected = true;
                    }
                }
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
            controllerShow.getSelectedList().add(this);
            element_box.setStyle("-fx-background-color: #85C1E9;");
        }
        else{
            controllerShow.getSelectedList().remove(this);
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
    public void onImageMenu(ContextMenuEvent contextMenuEvent) {
        switch (getType()){
            case photoType:
                if (controllerShow.getSelectedList().size() == 0){
                    controllerShow.getSelectedList().add(this);
                    name.setSelected(true);
                    element_box.setStyle("-fx-background-color: #85C1E9;");
                } else {
                    if (!name.isSelected()) {
                        System.out.println(controllerShow.getSelectedList().size());
                        for (ElementController controller:controllerShow.getSelectedList()){
                            controller.name.setSelected(false);
                            controller.element_box.setStyle("-fx-background-color: #D6DBDF;");
                        }
                        controllerShow.getSelectedList().clear();
                        controllerShow.getSelectedList().add(this);
                        name.setSelected(true);
                        element_box.setStyle("-fx-background-color: #85C1E9;");
                    }
                }
                this.photoMenu.show(element_box,contextMenuEvent.getScreenX(),contextMenuEvent.getScreenY());
                break;
            case folderType:
                if (!name.isSelected()) {
                    name.setSelected(true);
                    element_box.setStyle("-fx-background-color: #85C1E9;");
                }
                this.folderMenu.show(element_box,contextMenuEvent.getScreenX(),contextMenuEvent.getScreenY());
                break;
        }
    }

    public void setShowController(ShowController showController_) {
        this.controllerShow = showController_;
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
}

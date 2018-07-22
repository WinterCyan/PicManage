package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class Controller_element {
    @FXML
    ImageView image;
    @FXML
    CheckBox name;
    @FXML
    VBox element_box;


    @FXML
    public void onMouseClicked(MouseEvent mouseEvent) {
    }

    @FXML
    public void onMouseEntered() {
        element_box.setStyle("-fx-background-color: #85C1E9;");
    }

    @FXML
    public void onMouseExited() {
        element_box.setStyle("-fx-background-color: #D6DBDF;");
    }

    @FXML
    public void onCheckAction(ActionEvent actionEvent) {

    }

    public void setName(String name){
        this.name.setText(name);
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }
}

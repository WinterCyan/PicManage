package test.test_common;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.nio.file.Path;

public class Controller_ui {
    public Stage stage;
    public Scene scene;

    private Path path;

    @FXML
    VBox menu_pane;

    @FXML Button info_btn;
    @FXML VBox info_pane;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void menuAction(MouseEvent event){
        Label textArea = (Label) event.getSource();
        switch (textArea.getId()){
            case "ALL":
                System.out.println("ALL menu is clicked.");break;
            case "DEVICE":
                System.out.println("DEVICE menu is clicked.");break;
            case "menu_pane":
                System.out.println("menu_pane is clicked.");break;
            default:break;
        }
    }

    @FXML
    private void menuBtnAction(){
        if (menu_pane.isVisible()) {
            menu_pane.setVisible(false);
            menu_pane.setMinWidth(0);
        }
        else {
            menu_pane.setVisible(true);
            menu_pane.setMinWidth(200);
        }
    }

    @FXML
    private void infoBtnAction(){
        if (!info_pane.isVisible()) {
            info_pane.setVisible(true);
            info_pane.setMinWidth(310);
        }
    }

    @FXML
    private void closeInfoAction(){
        info_pane.setVisible(false);
        info_pane.setMinWidth(0);
    }
}

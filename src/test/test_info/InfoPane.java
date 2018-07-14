package test.test_info;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class InfoPane extends VBox {
    private Parent rootPane;
    private PaneController controller;

    public InfoPane() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("info_pane.fxml"));
        rootPane = loader.load();
        controller = loader.getController();
    }

    public PaneController getController() {
        return controller;
    }
}
package ui;

import controller.ElementController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class FolderMenu extends ContextMenu {
    private static MenuItem open;

    public FolderMenu(ElementController controller){
        open = new MenuItem("open");

        open.setOnAction(event -> {
            try {
                controller.openFolder();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        this.getItems().addAll(open);
    }
}

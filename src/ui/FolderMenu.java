package ui;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class FolderMenu extends ContextMenu {
    private static MenuItem item1 = new MenuItem("Item 1");
    private static MenuItem item2 = new MenuItem("Item 2");
    private static MenuItem item3 = new MenuItem("Item 3");

    public FolderMenu(){
        this.getItems().addAll(item1,item2,item3);
        item1.setOnAction(event -> System.out.println("Item 1 clicked."));
        item2.setOnAction(event -> System.out.println("Item 2 clicked."));
        item3.setOnAction(event -> System.out.println("Item 3 clicked."));
    }
}

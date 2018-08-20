package ui;

import dialog.activity.Controller;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class PhotoMenu extends ContextMenu {
    private static MenuItem activity;
    private static MenuItem category;

    public PhotoMenu(){
        activity = new MenuItem("set activity");
        category = new MenuItem("set category");

        activity.setOnAction(event -> {
            Controller.showDialog();
        });

        category.setOnAction(event -> {

        });

        this.getItems().addAll(activity, category);
    }
}

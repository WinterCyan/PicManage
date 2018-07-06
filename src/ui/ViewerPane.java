package ui;

import entity.PhotoList;
import javafx.scene.layout.GridPane;

public class ViewerPane extends GridPane {
    private GridPane pane;
    private PhotoList photoList;

    public ViewerPane(GridPane pane){
        this.pane = pane;
    }
}

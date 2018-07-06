package entity;

import javafx.scene.layout.GridPane;

public class Viewer extends GridPane {
    public static final int WIDTH = 90;
    public static final int HEIGHT = 90;

    private GridPane pane;
    private PhotoList photoList;

    public Viewer(GridPane pane){
        this.pane = pane;
    }
}

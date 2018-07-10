package ui;

import entity.PhotoList;
import javafx.scene.layout.FlowPane;

public class ViewerPane extends FlowPane {
    private FlowPane pane;
    private PhotoList photoList;

    public ViewerPane(){

    }

    public ViewerPane(FlowPane pane){
        this.pane = pane;
    }

    public FlowPane getPane() {
        return pane;
    }

    public PhotoList getPhotoList() {
        return photoList;
    }
}

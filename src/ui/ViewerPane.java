package ui;

import entity.PhotoList;
import javafx.scene.layout.FlowPane;

import java.nio.file.Path;

public class ViewerPane extends FlowPane {
    private FlowPane pane;
    private PhotoList photoList;
    private Path path;
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

package test.separate_ctrl;

import entity.PhotoList;

import java.nio.file.Path;

public class Controller_show {
    private Controller_main controllerMain;
    private static Path path;
    private PhotoList photoList;

    void injectMainController(Controller_main controllerMain){
        this.controllerMain = controllerMain;
    }

    public PhotoList getPhotoList() {
        return photoList;
    }

    public void setPhotoList(PhotoList photoList) {
        this.photoList = photoList;
    }

    public static void setPath(Path path) {
        Controller_show.path = path;
        System.out.println(path);
    }
}
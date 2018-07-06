package ui;

import entity.*;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Element extends VBox {
    public static final int WIDTH = 90;
    public static final int IMG_HEIGHT = 70;
    public static final int TEXT_HEIGHT = 20;

    private Item item;
    private ImageView thumbnail;
    private TextArea nameText;

    public Item getItem() {
        return item;
    }

    public Element(Item item){
        if (item.getType().equals(Item.TYPE_FOLDER)){
            this.item = item;
            Folder folder = item.getFolder();
        }else if (item.getType().equals(Item.TYPE_PIC)){
            this.item = item;
            Photo photo = item.getPhoto();
        }
    }

//    public Element(PhotoItem item){
//        thumbnail = new ImageView();
//        //  set the height of img and name.
//        this.item = item;
////        thumbnail =
//        nameText.setText(item.getName());
//    }
//
//    public Element(FolderItem item){
//        thumbnail = new ImageView();
//        //  set the height of img and name.
//        this.item = item;
////        thumbnail =
//        nameText.setText(item.getName());
//    }
}

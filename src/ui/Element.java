package ui;

import entity.*;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Element extends VBox {
    private static final int WIDTH = 90;
    private static final int IMG_HEIGHT = 70;
    private static final int TEXT_HEIGHT = 20;

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

    public ImageView getThumbnail() {
        return thumbnail;
    }

    public TextArea getNameText() {
        return nameText;
    }


    //    public Element(PhotoItem item){
//    }
//        nameText.setText(item.getName());
////        thumbnail =
//        this.item = item;
//        //  set the height of img and name.
//        thumbnail = new ImageView();
//    public Element(FolderItem item){
//
//    }
//        nameText.setText(item.getName());
////        thumbnail =
//        this.item = item;
//        //  set the height of img and name.
//        thumbnail = new ImageView();
}

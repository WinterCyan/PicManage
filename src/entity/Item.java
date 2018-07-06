package entity;

import javafx.scene.image.ImageView;

public class Item extends ImageView {
    public static final String TYPE_PIC="picture";
    public static final String TYPE_FOLDER="folder";
    public static final String TYPE_FOLDER_DEVICE="device";
    public static final String TYPE_FOLDER_ACTIVITY="activity";
    public static final String TYPE_FOLDER_CATE="category";
    public static final String TYPE_FOLDER_PERSON="person";
    public static final String TYPE_FOLDER_BIN="bin";

    public String type;
    public String name;

    public String getType() {
        return type;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}

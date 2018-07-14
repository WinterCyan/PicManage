package entity;


public class Item {
    public static final String TYPE_PIC="picture";
    public static final String TYPE_FOLDER="folder";
    public static final String TYPE_FOLDER_DEVICE="device";
    public static final String TYPE_FOLDER_ACTIVITY="activity";
    public static final String TYPE_FOLDER_CATE="category";
    public static final String TYPE_FOLDER_PERSON="person";

    public String type;
    public String name;
    public Info info;

    public String getType() {
        return type;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Folder getFolder(){
        return null;
    }

    public Photo getPhoto(){
        return null;
    }

    public void setInfo(Info info){
        this.info = info;
    }

    public Info getInfo(){
        return info;
    }
}

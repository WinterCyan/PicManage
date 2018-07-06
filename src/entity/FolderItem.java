package entity;

public class FolderItem extends Item {
    private String type = Item.TYPE_FOLDER;
    private static Folder folder;
    private String folderType;

    @Override
    public String getType() {
        return type;
    }

    public String getFolderType(){
        return folderType;
    }

    @Override
    public String getName() {
        return name;
    }

    public Folder getFolder(){
        return folder;
    }

    public FolderItem(Folder folder){
        this.folder = folder;
        this.name = folder.getName();
    }
}

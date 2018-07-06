package entity;

public class PhotoItem extends Item {
    private String type = Item.TYPE_PIC;
    private static Photo photo;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getName() {
        return name;
    }

    public Photo getPhoto(){
        return photo;
    }

    public PhotoItem(Photo photo){
        this.photo = photo;
    }
}

package entity;

import java.util.ArrayList;

public class PhotoList extends ArrayList<Photo> {
    @Override
    public boolean add(Photo photo) {
        return super.add(photo);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }
}

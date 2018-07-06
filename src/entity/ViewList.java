package entity;

import java.util.ArrayList;

public class ViewList extends ArrayList<Item> {
    @Override
    public boolean add(Item item) {
        return super.add(item);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    public void removeAll(){
        for (Item item:this){
            this.remove(item);
        }
    }

    public ViewList(){
        // necessary: use viewList to update gridPane instead of add to gridPane directly.
    }

    public ViewList(Object o){

    }
}

package entity;

import java.io.File;
import java.nio.file.Path;

public class Photo {
    private String id;
    private String name;

    private Path dir;

    private String device;
    private float size;
    private String time;
    private String person;
    private String activity;
    private String comment;
    private String category;
    private boolean bin;
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBin(boolean bin) {
        this.bin = bin;
    }

    public String getId() {

        return id;
    }

    public Path getDir() {
        return dir;
    }

    public void setDir(Path dir) {
        this.dir = dir;
    }

    public String getName() {
        return name;

    }

    public String getDevice() {
        return device;
    }

    public float getSize() {
        return size;
    }

    public String getTime() {
        return time;
    }

    public String getPerson() {
        return person;
    }

    public String getActivity() {
        return activity;
    }

    public String getComment() {
        return comment;
    }

    public String getCategory() {
        return category;
    }

    public boolean isBin() {
        return bin;
    }

    public Photo(File file){
        this.dir = file.toPath();
    }
}

package entity;

import java.nio.file.Path;
import java.util.HashMap;

public class Photo {
    private int id;
    private String name;

    private Path dir;

    private String device;
    private int size;
    private String time;

    private boolean bin;
    private String modifiedTime;
    private String person;
    private String activity;
    private String comment;
    private String category;

    private HashMap<String, String> metadataSet = new HashMap<>();

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setSize(int size) {
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

    public int getId() {

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

    public int getSize() {
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

    public HashMap<String, String> getMetadataSet() {
        return metadataSet;
    }

    public void setMetadataSet(HashMap<String, String> metadataSet) {
        this.metadataSet = metadataSet;
    }
}

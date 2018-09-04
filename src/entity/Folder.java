package entity;

public class Folder {
    private int fileNum;
    private float size;

    int id;

    private String name;
    public int getFileNum() {
        return fileNum;
    }

    public void setFileNum(int fileNum) {
        this.fileNum = fileNum;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

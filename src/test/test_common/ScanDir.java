package test.test_common;

import entity.Photo;
import entity.PhotoList;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class ScanDir {
    public static void printPhoto(Photo photo){
        System.out.println(photo.getSize()+"Mb\n"+photo.getName()+"\n"+photo.getDir()+"\n"
                +photo.getDevice()+"\n"+photo.getTime());
    }

    public static void main(String[] args) {
        ArrayList<Path> paths = new ArrayList<>();
        PhotoList photoList = new PhotoList();
        ArrayList<String> typeList = new ArrayList<>(){{
            add("jpeg");
            add("jpg");
            add("png");
        }};
        File dir = new File("D:\\IdeaUProjects\\PicTest");
        File[] list = dir.listFiles();
//        for (File file : list) {
//            String type = FilenameUtils.getExtension(file.toString());
////            if (file.isFile()&&!type.equals(""))
////                System.out.println(type);
////            if (file.isFile()&&type.equals("")) System.out.println(file.toString());
//            if (typeList.contains(type)) {
//                Photo photo = new Photo(file);
//                HashMap<String, String> map = MetadataInfo.getMap(file.toPath());
//                System.out.println(map);
//                photo.setSize((float)file.length()/1000000);
//                photo.setName(file.getName());
//                photo.setDevice(map.get("Model"));
//                photo.setDir(file.toPath());
//                photo.setTime(map.get("Date/Time Original"));
//                printPhoto(photo);
//                photoList.add(photo);
//            }
//        }
        System.out.println(paths);
    }
}

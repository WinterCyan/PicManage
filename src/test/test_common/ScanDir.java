package test.test_common;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class ScanDir {
    public static void main(String[] args){
        ArrayList<Path> paths = new ArrayList<>();
        File dir = new File("D:\\Nexus\\Camera");
        File[] list = dir.listFiles();
        for (File file:list){
            String type = FilenameUtils.getExtension(file.toString());
//            if (file.isFile()&&!type.equals(""))
//                System.out.println(type);
//            if (file.isFile()&&type.equals("")) System.out.println(file.toString());
            if (type.equals("jpg") || type.equals("png")){
                paths.add(file.toPath());
                System.out.println(paths);
            }
        }
    }
}

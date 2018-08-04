package utility;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class PathAnalysis {
    private static ArrayList<String> typeList = new ArrayList<>(){{
        add("jpeg");
        add("jpg");
        add("png");
    }};

    public static ArrayList<Path> analysis(Path path){
        File[] files = new File(path.toString()).listFiles();
        ArrayList<Path> paths = new ArrayList<>();
        showFiles(files, paths);

        return paths;
    }

    public static void showFiles(File[] files, ArrayList<Path> paths) {
        for (File file : files) {
            if (file.isDirectory()) showFiles(file.listFiles(), paths);
            else if (typeList.contains(FilenameUtils.getExtension(file.toString())))
                    paths.add(file.toPath());
        }
    }
}

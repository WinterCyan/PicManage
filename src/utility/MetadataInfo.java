package utility;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MetadataInfo {
    private static HashMap<String, String> map = new HashMap<>();
    private static final ArrayList<String> tagArray = new ArrayList<>(){{
        add("File Name");
        add("File Size");
        add("Date/Time Original");
        add("Model");
        add("GPS Latitude");
        add("GPS Latitude");
        add("File Modified Date");
    }};

    public static HashMap<String, String> getMap(Path path){
        File file = new File(path.toString());
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            for (Directory directory : metadata.getDirectories())
                for (Tag tag:directory.getTags())
                    if (tagArray.contains(tag.getTagName()))
                        map.put(tag.getTagName(),tag.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}

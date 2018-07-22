package utility;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class StringToTimestamp {
    public static Timestamp getOriginTime(File file) throws Exception{
        HashMap<String, String> map = MetadataInfo.getMap(file.toPath());
        if (map.containsKey("Date/Time Original")){
            String dateString = map.get("Date/Time Original").substring(35,54);
            String dateFormat = "yyyy:MM:dd kk:mm:ss";
            Date date = new SimpleDateFormat(dateFormat).parse(dateString);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
        }
        return null;
    }

    public static Timestamp getModifiedTime(File file) throws Exception{
        HashMap<String, String> map = MetadataInfo.getMap(file.toPath());
        if (map.containsKey("File Modified Date")){
            String dateString1 = map.get("File Modified Date").substring(28,48);
            String dateString2 = map.get("File Modified Date").substring(55,59);
            String dateString = dateString1 + " " + dateString2;
            String dateFormat = "EEE LLL dd kk:mm:ss yyyy";
            Date date = new SimpleDateFormat(dateFormat).parse(dateString);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
        }
        return null;
    }
}

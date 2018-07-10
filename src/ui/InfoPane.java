package ui;

import entity.Info;
import entity.Item;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

public class InfoPane extends VBox {
//    private static String infoType;
//    private TextFlow folderText = new TextFlow();
//    private TextFlow photoTextFile = new TextFlow();
//    private TextFlow photoTextCam = new TextFlow();

//    public static String getInfoType() {
//        return infoType;
//    }
//
//    public static void setInfoType(String infoType) {
//        InfoPane.infoType = infoType;
//    }

    public void InfoPane(String infoType, Info info){
//        this.infoType = infoType;

        HashMap<String, String> dic = info.getInfo();
        if (infoType == Item.TYPE_FOLDER){
            for (HashMap.Entry<String, String> entry : dic.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
            }
        }else if (infoType == Item.TYPE_PIC){
            for (HashMap.Entry<String, String> entry : dic.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
            }
        }
    }
}

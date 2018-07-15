package ui;

import entity.Item;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import test.test_common.MetadataInfo;

import java.nio.file.Path;
import java.util.HashMap;

public class InfoPane extends VBox {
    private HashMap<String, String> info;
    private TextFlow textFlow = new TextFlow();

    public InfoPane(){
    }

    public InfoPane(Item item){
        this.info = item.getInfo();
        Path path = item.getPhoto().getDir();
        info = MetadataInfo.getMap(path);
        for (HashMap.Entry<String, String> entry : info.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key+value+"\n");
            textFlow.getChildren().add(new Text(key+value+"\n"));
        }
    }

    public TextFlow getTextFlow() {
        return textFlow;
    }
}
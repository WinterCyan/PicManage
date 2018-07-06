package utility;

import entity.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SetViewer {
    private ViewList viewList = new ViewList();

    @FXML
    GridPane gridPane;

    @FXML Button allBtn;
    @FXML Button deviceBtn;
    @FXML Button activityBtn;
    @FXML Button personBtn;
    @FXML Button cateBtn;
    @FXML Button binBtn;

    @FXML
    private void setAllBtn(){
        viewList.removeAll();
        for (Photo photo:PhotoAnaly.photos)
            viewList.add(new PhotoItem(photo));
    }
    @FXML
    private void setDeviceBtn(){
        // scan items, fill the pane;
    }
    @FXML
    private void setActivityBtn(){

    }
    @FXML
    private void setPersonBtn(){

    }
    @FXML
    private void setCateBtn(){

    }
    @FXML
    private void setBinBtn(){

    }
    @FXML
    private void setGridPane(MouseEvent event){
        Item clickedItem = new Item();
        Node clickNode = (Node)event.getSource();
        Integer colIndex = GridPane.getColumnIndex(clickNode);
        Integer rowIndex = GridPane.getRowIndex(clickNode);

        // Bugs maybe here. clickedItem maybe initialized wrong.
        for (Node node : gridPane.getChildren())
            if (GridPane.getColumnIndex(node) == colIndex && GridPane.getRowIndex(node) == rowIndex)
                clickedItem = (Item)node;
        if (clickedItem.getType() == Item.TYPE_FOLDER){
            FolderItem folderItem = (FolderItem)clickedItem;
            String folderType = folderItem.getFolderType();
            String name = folderItem.getName();    //get name, connect DB
            switch (folderType){
                case Item.TYPE_FOLDER_DEVICE:
                    //invoke getItemFromDB()
                    break;
            }
        }else if (clickedItem.getType() == Item.TYPE_FOLDER){
            //  set big-pic view.
        }

        //  scan the viewList, fill the pane.
    }

//    private void setFolderClick(FolderItem folderItem){
//        // invoke
//    }

    private ArrayList<Item> getItemFromDB(){
        // set the corresponding connection with DB,
        // get table items(folder to folderItem or photoItems), set corresponding type(if was folderItem), add it to viewList.
        return viewList;
    }
}

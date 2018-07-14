package utility;

import db.DBInfo;
import entity.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ui.Element;

import java.sql.*;
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
        for (Photo photo:PhotoAnalysis.photos)
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
        ObservableList<Node> listNode = gridPane.getChildren();
        Element clickedElement = null;
        Node clickNode = (Node)event.getSource();
        Integer colIndex = GridPane.getColumnIndex(clickNode);
        Integer rowIndex = GridPane.getRowIndex(clickNode);

        // Bugs maybe here. clickedItem maybe initialized wrong.
        for (Node node : listNode)
            if (GridPane.getColumnIndex(node) == colIndex && GridPane.getRowIndex(node) == rowIndex)
                clickedElement = (Element)node;
        if (clickedElement.getItem().getType() == Item.TYPE_FOLDER){
            FolderItem folderItem = (FolderItem)clickedElement.getItem();
            String folderType = folderItem.getFolderType();
//            String name = folderItem.getName();    //get name, connect DB
            switch (folderType){
                case Item.TYPE_FOLDER_DEVICE:
                    //invoke getItemFromDB()
                    break;
                case Item.TYPE_FOLDER_PERSON:
                    //invoke getItemFromDB()
                    break;
            }
        }else if (clickedElement.getItem().getType() == Item.TYPE_PIC){
            //  set big-pic view.
        }
    }

    private ArrayList<Item> getItemFromDB(FolderItem item){
        // set the corresponding connection with DB,
        // get table items(folder to folderItem or photoItems), set corresponding type(if was folderItem), add it to viewList.
        viewList.setType(Item.TYPE_FOLDER);
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
//        ArrayList<String> idList = null;
        try {
            Class.forName(DBInfo.JDBC_DRIVER).getConstructor().newInstance();
            connection = DriverManager.getConnection(DBInfo.DB_URL);
            statement = connection.createStatement();

            switch (item.getFolderType()){
                case Item.TYPE_FOLDER_DEVICE:
                    set = statement.executeQuery(String.format("select id from photo where device='"+item.getName()+"';"));
                    break;
                case Item.TYPE_FOLDER_PERSON:
                    set = statement.executeQuery(String.format("select id from photo where person='"+item.getName()+"';"));
                    break;
                case Item.TYPE_FOLDER_ACTIVITY:
                    set = statement.executeQuery(String.format("select id from photo where activity='"+item.getName()+"';"));
                    break;
                case Item.TYPE_FOLDER_CATE:
                    set = statement.executeQuery(String.format("select id from photo where category='"+item.getName()+"';"));
                    break;
                default:break;
            }

            while (set.next()){
                String id = set.getString("id");
//                idList.add(id);
                for (Photo photo:PhotoAnalysis.photos){
                    if (photo.getId().equals(id)) viewList.add(new PhotoItem(photo));
                }
            }
        } catch (SQLException se){
            se.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(statement != null) statement.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
            try{
                if(connection != null) connection.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return viewList;
    }

    private void fillPane(){
        ArrayList<Element> elements = new ArrayList<>();
        elements = null;
        if (viewList.getType().equals(Item.TYPE_FOLDER)){
            for (Item item:viewList){
                elements.add(new Element(item));
            }
        }
    }
}

package controller;

import db.DBInfo;
import entity.Photo;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller_menu {
    private static Controller_main controllerMain;
    public boolean menu_state = false;

    @FXML
    public VBox menu_pane;

    public void injectMainController(Controller_main controllerMain){
        this.controllerMain = controllerMain;
    }

    @FXML
    public void refreshListAll() throws Exception {
        controllerMain.photoList.clear();
        setPhotoList("select * from photo");
        controllerMain.showController.refreshViewer();
    }
    @FXML
    public void refreshListDevice() throws Exception {
        controllerMain.showController.refreshViewer();
    }
    @FXML
    public void refreshListPerson() throws Exception {
        controllerMain.showController.refreshViewer();
    }
    @FXML
    public void refreshListActivity() throws Exception {
        controllerMain.showController.refreshViewer();
    }
    @FXML
    public void refreshListCate() throws Exception {
        controllerMain.showController.refreshViewer();
    }
    @FXML
    public void refreshListBin() throws Exception {
        controllerMain.showController.refreshViewer();
    }

    private static void setPhotoList(String selectSQL) throws Exception{
        Class.forName(DBInfo.JDBC_DRIVER).getConstructor().newInstance();
        Connection connection = DriverManager.getConnection(DBInfo.DB_DB_URL);
        Statement statementOut = connection.createStatement();
        Statement statementIn = connection.createStatement();
        ResultSet set = statementOut.executeQuery(selectSQL);

        while (set.next()){
            String name = set.getString("name");
            Path dir = Paths.get(set.getString("dir"));
            String activity = null,category = null,person = null,device = null;
            if (set.getObject("activity")!=null){
                int activityId = set.getInt("activity");
                ResultSet activitySet = statementIn.executeQuery(String.format("select * from activity where id = " +
                        activityId+";"));
                if (activitySet.next()) activity = activitySet.getString("name");
                activitySet.close();
            } else activity = null;

            if (set.getObject("category")!=null){
                int categoryId = set.getInt("category");
                ResultSet categorySet = statementIn.executeQuery(String.format("select * from category where id = " +
                        categoryId+";"));
                if (categorySet.next()) category = categorySet.getString("name");
                categorySet.close();
            } else category = null;

            if (set.getObject("person")!=null){
                int personId = set.getInt("person");
                ResultSet personSet = statementIn.executeQuery(String.format("select * from person where id = " +
                        personId+";"));
                if (personSet.next()) person = personSet.getString("name");
                personSet.close();
            } else person = null;

            if (set.getObject("device")!=null){
                int deviceId = set.getInt("device");
                ResultSet deviceSet = statementIn.executeQuery(String.format("select * from device where id = " +
                        deviceId+";"));
                if (deviceSet.next()) device = deviceSet.getString("name");
                deviceSet.close();
            } else device = null;

            Photo photo = new Photo();
            photo.setName(name);
            photo.setDir(dir);
            photo.setActivity(activity);
            photo.setCategory(category);
            photo.setPerson(person);
            photo.setDevice(device);
            controllerMain.photoList.add(photo);
        }
        statementIn.close();
        statementOut.close();
        connection.close();
    }
}

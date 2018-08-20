package controller;

import db.DBInfo;
import entity.Folder;
import entity.Photo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MenuController {
    private static MainController controllerMain;
    public boolean menu_state = false;

    @FXML
    public VBox menu_pane;
    @FXML
    Label menu_all;
    @FXML
    Label menu_device;
    @FXML
    Label menu_person;
    @FXML
    Label menu_activity;
    @FXML
    Label menu_category;
    @FXML
    Label menu_bin;

    public void injectMainController(MainController controllerMain){
        this.controllerMain = controllerMain;
    }

    private void hideMenu(){
        controllerMain.getMenuPane().setMaxWidth(0);
        controllerMain.getMenuPane().setMinWidth(0);
        controllerMain.setMenuState(false);
    }
    @FXML
    public void refreshListAll() throws Exception {
        enableLabel();
        menu_all.setDisable(true);
        hideMenu();
        controllerMain.photoList.clear();
        setPhotoList("select * from photo","select count(*) from photo");
        controllerMain.showController.refreshViewer();
    }
    @FXML
    public void refreshListDevice() throws Exception {
        enableLabel();
        menu_device.setDisable(true);
        hideMenu();
        setDeviceList();
        controllerMain.showController.refreshViewerFolder();
    }
    @FXML
    public void refreshListPerson() throws Exception {
        enableLabel();
        menu_person.setDisable(true);
        hideMenu();
    }

    @FXML
    public void refreshListActivity() throws Exception {
        enableLabel();
        menu_device.setDisable(true);
        hideMenu();
    }

    @FXML
    public void refreshListCate() throws Exception {
        enableLabel();
        menu_category.setDisable(true);
        hideMenu();
        controllerMain.showController.refreshViewer();
    }
    public void refreshListBin() throws Exception {
        enableLabel();
        menu_bin.setDisable(true);
        hideMenu();
        controllerMain.showController.refreshViewer();
    }

    private static void setPhotoList(String selectSQL, String countSQL) throws Exception{
        Connection connection = DriverManager.getConnection(DBInfo.DB_DB_URL);
        Statement statementOut = connection.createStatement();
        Statement statementIn = connection.createStatement();
        ResultSet set = statementOut.executeQuery(selectSQL);
//        Statement statementCount = connection.createStatement();
//        int setSize = statementCount.executeQuery(countSQL).getInt(1);
//        int progress = 0;
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
//            progress ++;
//            updateProgress(progress, setSize);
        }
        statementIn.close();
        statementOut.close();
        connection.close();
//        Service service = new Service() {
//            @Override
//            protected Task createTask() {
//                return new Task() {
//                    @Override
//                    protected Object call() throws Exception {
//                        controllerMain.showController.show_pane.setAlignment(Pos.CENTER);
//                        controllerMain.showController.show_progress.setVisible(true);
//
//                        Class.forName(DBInfo.JDBC_DRIVER).getConstructor().newInstance();
//
//                        controllerMain.showController.show_pane.setAlignment(Pos.TOP_LEFT);
//                        controllerMain.showController.show_progress.setVisible(false);
//                        return null;
//                    }
//                };
//            }
//        };
//        controllerMain.showController.show_progress.progressProperty().bind(service.progressProperty());
//        service.start();
    }

    private static void setDeviceList() throws Exception{
        Class.forName(DBInfo.JDBC_DRIVER).getConstructor().newInstance();
        Connection connection = DriverManager.getConnection(DBInfo.DB_DB_URL);
        Statement statementOut = connection.createStatement();
        Statement statementIn = connection.createStatement();
        ResultSet set = statementOut.executeQuery("select * from device");

        while (set.next()){
            String name = set.getString("name");
            int id = set.getInt("id");
            int fileNum = 0;
            ResultSet sizeSet = statementIn.executeQuery(String.format("select count(*) from photo where device = '"+id+"';"));
            if (sizeSet.next()) fileNum = sizeSet.getInt(1);
            Folder folder = new Folder();
            folder.setFileNum(fileNum);
            folder.setName(name);

            controllerMain.folderList.add(folder);
        }
        statementIn.close();
        statementOut.close();
    }

    private void enableLabel(){
        menu_all.setDisable(false);
        menu_device.setDisable(false);
        menu_person.setDisable(false);
        menu_activity.setDisable(false);
        menu_category.setDisable(false);
        menu_bin.setDisable(false);
    }
}
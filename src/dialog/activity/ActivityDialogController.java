package dialog.activity;

import controller.ElementController;
import controller.ShowController;
import db.DBInfo;
import entity.Photo;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class ActivityDialogController {
    @FXML
    TextField activityName;
    @FXML
    TextField locationText;
    @FXML
    DatePicker datePicker;
    @FXML
    TextArea descriptionText;

    public void okAction() {
//        datePicker.setConverter(new StringConverter<>() {
//            String pattern = "yyyy-MM-dd";
//            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
//
//            {
//                datePicker.setPromptText(pattern.toLowerCase());
//            }
//
//            @Override public String toString(LocalDate date) {
//                if (date != null) {
//                    return dateFormatter.format(date);
//                } else {
//                    return "";
//                }
//            }
//
//            @Override public LocalDate fromString(String string) {
//                if (string != null && !string.isEmpty()) {
//                    return LocalDate.parse(string, dateFormatter);
//                } else {
//                    return null;
//                }
//            }
//        });
        String name = activityName.getText();
        LocalDate date = datePicker.getValue();
        date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String location = locationText.getText();
        String description = descriptionText.getText();
        Optional<ButtonType> result;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to add this activity?");
        if (integrityCheck()) {
            result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ArrayList<ElementController> photoList = ShowController.getSelectedList();
                // change db.
                int currentId = 0;
                try {
                    Class.forName(DBInfo.JDBC_DRIVER).getConstructor().newInstance();
                    Connection connection = DriverManager.getConnection(DBInfo.DB_DB_URL);
                    Statement addActivity = connection.createStatement();
                    Statement updatePhoto = connection.createStatement();
                    if (!location.isEmpty()) addActivity.execute(String.format("insert into activity(name, time, location, description) " +
                            "values('"+name+"','"+date+"','"+location+"','"+description+"');"));
                    else addActivity.execute(String.format("insert into activity(name, time, description) " +
                            "values('"+name+"','"+date+"','"+description+"');"));
                    Statement getIncrement = connection.createStatement();
                    ResultSet set = getIncrement.executeQuery("select auto_increment from information_schema.tables where table_schema='picmanage' and table_name='activity';");
                    if (set.next()) currentId = set.getInt(1)-1;
                    for (ElementController controller:photoList){
                        Photo photo = controller.getPhoto();
                        int id = photo.getId();
                        updatePhoto.executeUpdate("update photo set activity='"+currentId+"' where id='"+id+"';");
                        System.out.println("updated.");
                    }
                    addActivity.close();
                    updatePhoto.close();
                    getIncrement.close();
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                // change photo information.
                for (ElementController controller:photoList) {
                    Photo photo = controller.getPhoto();
                    photo.setActivity(name);
                }
            }
        }else {
            Alert integrityAlert = new Alert(Alert.AlertType.ERROR, "Check the activity information integrity.");
            integrityAlert.showAndWait();
        }
    }

    public void cancelAction() {
        Controller.closeDialog();
    }

    private boolean integrityCheck(){
        // integrity check.
        if (activityName.getText().isEmpty()||datePicker.getValue().equals(null)||descriptionText.getText().isEmpty()) return false;
        return  true;
    }
}

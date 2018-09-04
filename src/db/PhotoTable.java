package db;

import java.sql.*;

public class PhotoTable {
    public static void addPhoto
            (String name, String dir, String device, int size, Timestamp time, Timestamp modified_time) throws Exception{
        Class.forName(DBInfo.JDBC_DRIVER).getConstructor().newInstance();
        Connection connection = DriverManager.getConnection(DBInfo.DB_DB_URL);

        String newPhoto = String.format("insert into photo (name,dir,device,size,time,modified_time,bin) values" +
                "(?,?,?,?,?,?,?);");
        String newDevice = String.format("insert into device (name) values(?);");
        int deviceId = 1;

        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(String.format("select name from device;"));
        if (!deviceExist(set,device)){
//            if (device!=null){
                PreparedStatement deviceStatement = connection.prepareStatement(newDevice);
                deviceStatement.setString(1,device);
                deviceStatement.execute();
                deviceStatement.close();
                Statement statementDeviceId = connection.createStatement();
                ResultSet setDeviceId = statementDeviceId.executeQuery(String.format("select id from device where name=" +
                        "'"+device+"';"));
                if (setDeviceId.next()) deviceId = setDeviceId.getInt("id");
                statementDeviceId.close();
//            }
        }else {
//            if (device!=null) {
                Statement statementDeviceId = connection.createStatement();
                ResultSet setDeviceId = statementDeviceId.executeQuery(String.format("select id from device where name=" +
                        "'"+device+"';"));
                if (setDeviceId.next()) deviceId = setDeviceId.getInt("id");
                statementDeviceId.close();
//            }
        }
        statement.close();

        PreparedStatement photoStatement = connection.prepareStatement(newPhoto);
        photoStatement.setString(1,name);
        photoStatement.setString(2,dir);
        photoStatement.setInt(3,deviceId);
        photoStatement.setInt(4,size);
        photoStatement.setTimestamp(5,time);
        photoStatement.setTimestamp(6,modified_time);
        photoStatement.setBoolean(7,false);
        photoStatement.execute();
        photoStatement.close();

        connection.close();
    }

    private static boolean deviceExist(ResultSet set, String device) throws Exception{
        while (set.next()){
            if (set.getString("name").equals(device)) return true;
        }
        return false;
    }
}

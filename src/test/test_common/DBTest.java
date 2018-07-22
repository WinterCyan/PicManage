package test.test_common;

import db.DBInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBTest {
    public static void main(String[] args) throws Exception{
        Class.forName(DBInfo.JDBC_DRIVER).getConstructor().newInstance();
        Connection connection = DriverManager.getConnection(DBInfo.DB_DB_URL);
//        String newPhoto = "insert into photo values(?,?,?,?,?);";
//        PreparedStatement statement = connection.prepareStatement(newPhoto);
//        statement.setString(1,"name");
//        statement.executeUpdate();
        String newDevice = String.format("insert into photo (name,dir,device,size,time,modified_time,bin) values" +
                "(?,?,?,?,?,?,?);");
        PreparedStatement statement = connection.prepareStatement(newDevice);
        statement.setString(1,"name");
        statement.setString(2,"dir");
        statement.setString(3,null);
        statement.setInt(4,10000);
        statement.setTimestamp(5,null);
        statement.setTimestamp(6,null);
        statement.setBoolean(7,true);
        statement.execute();

        statement.close();
        connection.close();
    }
}

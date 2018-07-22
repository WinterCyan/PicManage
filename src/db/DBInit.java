package db;


import java.sql.*;

public class DBInit {

    public static void DBInit(){
        Connection connection = null;
        Statement statement = null;
        try {
//            Class.forName(DBInfo.JDBC_DRIVER).getConstructor().newInstance();
            connection = DriverManager.getConnection(DBInfo.DB_URL);
            statement = connection.createStatement();
            String dropDB = String.format("drop database if exists picmanage;");
            statement.executeUpdate(dropDB);
            String sql1 = String.format("CREATE DATABASE IF NOT EXISTS picmanage;");
            String sql2 = String.format("USE picmanage;");
            String sql3 = String.format("create table if not exists device(id int not null primary key auto_increment,name " +
                    "varchar(50) not null,manu varchar(50));");
            String sql4 = String.format("create table if not exists person(id int not null primary key auto_increment,faceinfo " +
                    "varchar(255),name varchar(20));");
            String sql5 = String.format("create table if not exists activity(id int not null primary key auto_increment,name " +
                    "varchar(50) not null,time datetime not null,location varchar(50));");
            String sql6 = String.format("create table if not exists category(id int not null primary key auto_increment,name " +
                    "varchar(10) not null);");
            String sql7 = String.format("create table if not exists photo(id int not null primary key auto_increment,name " +
                    "varchar(255) not null,dir varchar(255) not null,device int,foreign key(device) references " +
                    "device(id),size int not null,time datetime,modified_time datetime,person int, foreign key(person) references " +
                    "person(id),activity int,foreign key(activity) references activity(id),comment " +
                    "varchar(255),category int,foreign key(category) references category(id),bin bit not null default 0);");
            String deviceInit = String.format("insert into device values(1,'null','null');");
            statement = connection.createStatement();
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql3);
            statement.executeUpdate(sql4);
            statement.executeUpdate(sql5);
            statement.executeUpdate(sql6);
            statement.executeUpdate(sql7);
            statement.executeUpdate(deviceInit);

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
    }
}

package db;


import java.sql.*;

public class DBInit {

    public DBInit(){
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(DBInfo.JDBC_DRIVER).getConstructor().newInstance();
            connection = DriverManager.getConnection(DBInfo.DB_URL);
            String sql1 = String.format("CREATE DATABASE IF NOT EXISTS picmanage;");
            String sql2 = String.format("USE picmanage;");
            String sql3 = String.format("create table if not exists device(id char(40) not null primary key,name " +
                    "varchar(20) not null,manu varchar(20));");
            String sql4 = String.format("create table if not exists person(id char(5) not null primary key,faceinfo " +
                    "varchar(255),name varchar(20));");
            String sql5 = String.format("create table if not exists activity(id char(10) not null primary key,name " +
                    "varchar(50) not null,time datetime not null,location varchar(50));");
            String sql6 = String.format("create table if not exists category(id char(5) not null primary key,name " +
                    "varchar(10) not null);");
            String sql7 = String.format("create table if not exists photo(id char(10) not null primary key,name " +
                    "varchar(255) not null,dir varchar(255) not null,device char(10),foreign key(device) references " +
                    "device(id),size int not null,time datetime not null,person char(5),foreign key(person) references " +
                    "person(id),activity char(10),foreign key(activity) references activity(id),comment " +
                    "varchar(255),category char(5),foreign key(category) references category(id),bin bit not null default 0);");
            statement = connection.createStatement();
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql3);
            statement.executeUpdate(sql4);
            statement.executeUpdate(sql5);
            statement.executeUpdate(sql6);
            statement.executeUpdate(sql7);

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

    public static void main(String[] args){
        new DBInit();
    }
}

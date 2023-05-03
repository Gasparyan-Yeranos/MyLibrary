package com.example.mylibrary_Servlet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    private static final DBConnectionProvider INSTANCE = new DBConnectionProvider();
    public static DBConnectionProvider getInstance(){
        return INSTANCE;
    }
    private DBConnectionProvider(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library?useUnicode=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;
    public static Connection getConnection(){
        try{
            if(connection==null || connection.isClosed()){
                connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

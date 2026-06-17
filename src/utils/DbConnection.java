package utils;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.SQLException;

public class DbConnection {

    public DbConnection(){
        
    }

    public static Connection getConnection() throws SQLException
     {
        String URL = "jdbc:mysql://localhost:3306/Library_db";
        String USERNAME = "root";
        String PassWord = "root";
        return DriverManager.getConnection(URL,USERNAME,PassWord);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.management.inventory.management;
import java.sql.*;

/**
 *
 * @author gaura
 * 
 * 
 */
public class DatabaseConnection {
   
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load PostgreSQL JDBC driver
        Class.forName("org.postgresql.Driver");

        // Connect to PostgreSQL database
        String url = "jdbc:postgresql://192.168.1.33:5432/inven2"; // Replace with your server IP ,adding nwe comment
        String user = "postgres"; // Your PostgreSQL username
        String password = "1234"; // Your PostgreSQL password

        return DriverManager.getConnection(url, user, password);
    
    }
}

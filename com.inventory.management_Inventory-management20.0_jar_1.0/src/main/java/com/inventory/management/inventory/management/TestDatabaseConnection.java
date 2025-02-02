package com.inventory.management.inventory.management;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gaura
 */
import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        try {
            // Attempt to establish a connection
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Connection to PostgreSQL database established successfully!");
                connection.close(); // Close the connection after testing
            }
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection to PostgreSQL database failed!");
            e.printStackTrace();
        }
    }
    
}

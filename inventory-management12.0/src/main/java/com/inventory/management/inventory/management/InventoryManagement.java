/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.inventory.management.inventory.management;

/**
 *
 * @author gaura
 */
import java.sql.*;
import org.sqlite.JDBC;
public class InventoryManagement {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        try{
             // Load PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            
            // Connect to PostgreSQL database
            String url = "jdbc:postgresql://localhost:5432/inven";
            String user = "your_username"; // Replace with your PostgreSQL username
            String password = "1234"; // Replace with your PostgreSQL password
            
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM emp");
            while (rs.next()) {
                System.out.println(rs.getString("empID") + " " + rs.getString("empName"));
            }
            con.close();
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Error is "+e.getMessage());
        }
        new LoginPage().setVisible(true);
    }
}

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
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:inven.db");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from emp");
            while(rs.next()){
                System.out.println(rs.getString("empID")+" "+rs.getString("empName"));
            }
            con.close();
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Error is "+e.getMessage());
        }
        new A_Welcome().setVisible(true);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.inventory.management.inventory.management;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.print.PrinterException;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
//import org.apache.commons.dbutils.DbUtils;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.print.PrinterException;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.text.SimpleDateFormat;

/**
 *
 * @author gaura
 */
public class EditItems extends javax.swing.JFrame {

    /**
     * Creates new form EditItems
     */
    public EditItems() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        empEnt = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTFItemName = new javax.swing.JTextField();
        jBtnAdd = new javax.swing.JButton();
        jBtnDelete = new javax.swing.JButton();
        jBtnView = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        empEnt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        empEnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empEnt.setText("Edit Items");
        jPanel1.add(empEnt);
        empEnt.setBounds(30, 0, 650, 70);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 740, 10);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Item Name"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 192, 700, 260);

        jLabel4.setText("Item Name");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, 100, 100, 16);
        jPanel1.add(jTFItemName);
        jTFItemName.setBounds(130, 92, 150, 40);

        jBtnAdd.setText("Add");
        jBtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnAdd);
        jBtnAdd.setBounds(530, 130, 72, 23);

        jBtnDelete.setText("Delete");
        jBtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnDelete);
        jBtnDelete.setBounds(610, 130, 75, 23);

        jBtnView.setText("View");
        jBtnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnViewActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnView);
        jBtnView.setBounds(610, 90, 72, 23);

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(530, 90, 70, 23);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 460));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // Get the selected row index
//        int selectedRow = jTable1.getSelectedRow();
//
//        // Get the values from the selected row
//        String selectedItemName = jTable1.getValueAt(selectedRow, 0).toString();
//        String selectedCost = jTable1.getValueAt(selectedRow, 1).toString();
//
//        // Set the values in the combo box and text field
//        itemName.setSelectedItem(selectedItemName);
//        jTextFieldCost.setText(selectedCost);        // TODO add your handling code here:
int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        String itemName = jTable1.getValueAt(selectedRow, 0).toString();
        jTFItemName.setText(itemName);
    }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jBtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddActionPerformed
String itemName = jTFItemName.getText().trim();

    if (!itemName.isEmpty()) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db")) {
            String query = "INSERT INTO items (itemName) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, itemName);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Item added successfully.");
                jTFItemName.setText(""); // Clear the text field after adding
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item name cannot be empty.");
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnAddActionPerformed

    private void jBtnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnViewActionPerformed
 DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Clear the table before populating it

    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db")) {
        String query = "SELECT itemName FROM items";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String itemName = rs.getString("itemName");
            model.addRow(new Object[]{itemName});
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnViewActionPerformed

    private void jBtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeleteActionPerformed
int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        String itemName = jTable1.getValueAt(selectedRow, 0).toString();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db")) {
            String query = "DELETE FROM items WHERE itemName = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, itemName);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Item deleted successfully.");
                jBtnViewActionPerformed(null); // Refresh the table
                jTFItemName.setText(""); // Clear the text field after deleting
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please select an item to delete.");
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnDeleteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new AdminMenu().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditItems().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JButton jBtnAdd;
    private javax.swing.JButton jBtnDelete;
    private javax.swing.JButton jBtnView;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTFItemName;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

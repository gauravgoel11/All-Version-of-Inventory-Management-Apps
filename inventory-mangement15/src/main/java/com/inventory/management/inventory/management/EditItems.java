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
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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
         setExtendedState(this.MAXIMIZED_BOTH);
          setupKeyBindings();
          viewItems();
    }
         private void setupKeyBindings() {
        // Get the input map for the root pane
        JRootPane rootPane = this.getRootPane();
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        // Bind the ESC key to an action
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
        actionMap.put("ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when ESC is pressed
                goBack();
            }
        });
    }

    private void goBack() {
        // Implement the action to go back or close the frame
        // For example, dispose the current frame and show the previous one
        new AdminMenu().setVisible(true);
        this.dispose();
    }
    private void viewItems(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Clear the table before populating it

    try (Connection conn = DatabaseConnection.getConnection()) {
        String query = "SELECT itemName, itemCode FROM items";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String itemName = rs.getString("itemName");
            String itemCode = rs.getString("itemCode");
            model.addRow(new Object[]{itemName, itemCode});
        }
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
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
        jTFItemCode = new javax.swing.JTextField();
        jBtnAdd = new javax.swing.JButton();
        jBtnDelete = new javax.swing.JButton();
        jBtnView = new javax.swing.JButton();
        jBtnChangeName = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTFItemName = new javax.swing.JTextField();
        jBtnDelete1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        empEnt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        empEnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empEnt.setText("EDIT ITEMS");
        jPanel1.add(empEnt);
        empEnt.setBounds(30, 0, 650, 70);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 740, 10);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Item Name", "Item Code"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 192, 710, 410);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Item Code");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, 150, 100, 20);
        jPanel1.add(jTFItemCode);
        jTFItemCode.setBounds(130, 140, 150, 40);

        jBtnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnAdd.setText("Add");
        jBtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnAdd);
        jBtnAdd.setBounds(380, 130, 90, 27);

        jBtnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnDelete.setText("Delete");
        jBtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnDelete);
        jBtnDelete.setBounds(660, 130, 80, 27);

        jBtnView.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnView.setText("View");
        jBtnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnViewActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnView);
        jBtnView.setBounds(380, 90, 90, 27);

        jBtnChangeName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnChangeName.setText("Change Name");
        jBtnChangeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnChangeNameActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnChangeName);
        jBtnChangeName.setBounds(500, 90, 150, 27);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(660, 90, 80, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Item Name");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, 100, 100, 20);
        jPanel1.add(jTFItemName);
        jTFItemName.setBounds(130, 92, 150, 40);

        jBtnDelete1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnDelete1.setText("Change code");
        jBtnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDelete1ActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnDelete1);
        jBtnDelete1.setBounds(500, 130, 150, 27);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 760, 610));

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
//        jTFItemName.setText(itemName);
    }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jBtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddActionPerformed
                                      
    String itemName = jTFItemName.getText().trim();
    String itemCode = jTFItemCode.getText().trim();
    String upperCaseItemName = itemName.toUpperCase();
    String upperCaseItemCode = itemCode.toUpperCase();

    if (!upperCaseItemName.isEmpty() && !upperCaseItemCode.isEmpty()) {
        try (Connection conn = DatabaseConnection.getConnection()) { // Use centralized connection method
            // Check if the item name or item code already exists
            String checkQuery = "SELECT COUNT(*) FROM items WHERE itemName = ? OR itemCode = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, upperCaseItemName);
            checkStmt.setString(2, upperCaseItemCode);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Item name or code already exists.");
            } else {
                // Insert the new item
                String query = "INSERT INTO items (itemName, itemCode) VALUES (?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, upperCaseItemName);
                pstmt.setString(2, upperCaseItemCode);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Item added successfully.");
                    jTFItemName.setText(""); // Clear the item name text field after adding
                    jTFItemCode.setText(""); // Clear the item code text field after adding
                    jBtnViewActionPerformed(null); // Refresh the table
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item name and code cannot be empty.");
    }
    // TODO add your handling code here:


    }//GEN-LAST:event_jBtnAddActionPerformed

    private void jBtnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnViewActionPerformed
                                       
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Clear the table before populating it

    try (Connection conn = DatabaseConnection.getConnection()) {
        String query = "SELECT itemName, itemCode FROM items";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String itemName = rs.getString("itemName");
            String itemCode = rs.getString("itemCode");
            model.addRow(new Object[]{itemName, itemCode});
        }
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }


    }//GEN-LAST:event_jBtnViewActionPerformed

    private void jBtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeleteActionPerformed
                                         
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        String itemName = jTable1.getValueAt(selectedRow, 0).toString();
        String itemCode = jTable1.getValueAt(selectedRow, 1).toString();

        int response = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to delete the item: " + itemName + " with code: " + itemCode + "?",
            "Confirm Deletion", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            try (Connection conn = DatabaseConnection.getConnection()) { // Use centralized DatabaseConnection class
                String query = "DELETE FROM items WHERE itemName = ? AND itemCode = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, itemName);
                pstmt.setString(2, itemCode);

                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null, "Item deleted successfully.");
                    jBtnViewActionPerformed(null); // Refresh the table
                }
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please select an item to delete.");
    }


    }//GEN-LAST:event_jBtnDeleteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new AdminMenu().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jBtnChangeNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnChangeNameActionPerformed
                                                 
                                                   
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        String oldItemName = jTable1.getValueAt(selectedRow, 0).toString();
        String newItemName = JOptionPane.showInputDialog(this, "Enter new name for the item:", oldItemName);

        if (newItemName != null && !newItemName.trim().isEmpty()) {
            String upperNewItemName = newItemName.toUpperCase(); // Convert to uppercase
            int response = JOptionPane.showConfirmDialog(this, 
                "Do you want to change the item name from " + oldItemName + " to " + upperNewItemName + "?",
                "Confirm Name Change", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                Connection conn = null;
                try {
                    conn = DatabaseConnection.getConnection();
                    conn.setAutoCommit(false); // Start transaction

                    String[] tables = {"items", "adminentry", "entry", "part_items", "temp_adminentry", "temp_entry"};
                    for (String table : tables) {
                        String query = "UPDATE " + table + " SET itemName = ? WHERE itemName = ?";
                        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                            pstmt.setString(1, upperNewItemName);
                            pstmt.setString(2, oldItemName);
                            pstmt.executeUpdate();
                        }
                    }

                    conn.commit(); // Commit transaction
                    JOptionPane.showMessageDialog(this, "Item name updated successfully in all tables.");
                    jBtnViewActionPerformed(null); // Refresh the table
                } catch (ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                    if (conn != null) {
                        try {
                            conn.rollback(); // Rollback transaction on error
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(this, "Error on rollback: " + ex.getMessage());
                        }
                    }
                } finally {
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(this, "Error on closing connection: " + ex.getMessage());
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "New item name cannot be empty.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select an item to change its name.");
    }



                                           

    }//GEN-LAST:event_jBtnChangeNameActionPerformed

    private void jBtnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDelete1ActionPerformed
      // TODO add your handling code here:
                                                 
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        String oldItemName = jTable1.getValueAt(selectedRow, 0).toString();
        String oldItemCode = jTable1.getValueAt(selectedRow, 1).toString();

        // Prompt the user to enter a new code
        String newItemCode = JOptionPane.showInputDialog(this, "Enter new code for the item:", oldItemCode);

        if (newItemCode != null && !newItemCode.trim().isEmpty()) {
            // Confirm the change
            int response = JOptionPane.showConfirmDialog(this, 
                "Do you want to change the item code from " + oldItemCode + " to " + newItemCode + "?",
                "Confirm Code Change", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                try (Connection conn = DatabaseConnection.getConnection()) { // Use centralized DatabaseConnection class
                    String query = "UPDATE items SET itemCode = ? WHERE itemName = ? AND itemCode = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, newItemCode);
                    pstmt.setString(2, oldItemName);
                    pstmt.setString(3, oldItemCode);

                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(this, "Item code updated successfully.");
                        jBtnViewActionPerformed(null); // Refresh the table
                    } else {
                        JOptionPane.showMessageDialog(this, "No matching item found to update.");
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "New item code cannot be empty.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select an item to change its code.");
    }


    }//GEN-LAST:event_jBtnDelete1ActionPerformed

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
        SwingUtilities.invokeLater(() -> new AdminMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JButton jBtnAdd;
    private javax.swing.JButton jBtnChangeName;
    private javax.swing.JButton jBtnDelete;
    private javax.swing.JButton jBtnDelete1;
    private javax.swing.JButton jBtnView;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTFItemCode;
    private javax.swing.JTextField jTFItemName;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

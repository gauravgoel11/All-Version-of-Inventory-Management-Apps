package com.inventory.management.inventory.management;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author gaura
 */

import java.sql.*;
import java.util.*;
import org.sqlite.JDBC;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.sql.*;
import java.util.Date;  // For Date handling
import java.text.SimpleDateFormat;  // For formatting Date to string
import com.toedter.calendar.JDateChooser;  // JDateChooser for date selection
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.util.Date;  // For handling Date objects
import java.text.SimpleDateFormat;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

// For formatting Date to a specific pattern
// For autocomplete in JComboBox


public class Part_items extends javax.swing.JFrame {
    private Map<String, String> itemMap = new HashMap<>();
    private Map<String, String> partMap = new HashMap<>();


    /**
     * Creates new form entry
     */
    public Part_items() {
        initComponents();
        setExtendedState(this.MAXIMIZED_BOTH);
         setupKeyBindings();
         viewAllPartItems();
         loadItems(); // Load items into the map and combo boxes
        setupComboBoxListeners();
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
private void viewAllPartItems() {
    try (Connection con = DatabaseConnection.getConnection()) { // Use DatabaseConnection class to handle PostgreSQL connection
        String query = "SELECT * FROM part_items";
        PreparedStatement st = con.prepareStatement(query); // Use PreparedStatement instead of Statement
        ResultSet rs = st.executeQuery();
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);  // Clear existing rows
        
        while (rs.next()) {
            String partName = rs.getString("partName");
            String itemName = rs.getString("itemName");
            int quantity = rs.getInt("quantity");
            model.addRow(new Object[]{partName, itemName, quantity});
        }
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Error: " + e.getMessage());
    } 
}

private void loadItems() {
    try {
        // Use DatabaseConnection class to handle PostgreSQL connection
        Connection con = DatabaseConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM items");
        while (rs.next()) {
            String name = rs.getString("itemName");
            String code = rs.getString("itemCode");
            itemName.addItem(name);
            itemCode.addItem(code);
            itemMap.put(name, code); // Store the relationship in the map
        }
        rs = st.executeQuery("SELECT DISTINCT partName FROM part_items");
        while (rs.next()) {
            String partName = rs.getString("partName");
            empName.addItem(partName);
            partMap.put(partName, partName); // Assuming partName is unique and used as a key
        }
        con.close();
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Error is " + e.getMessage());
    }
}


     private void setupComboBoxListeners() {
         empName.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedEmpName = (String) empName.getSelectedItem();
        if (selectedEmpName != null && partMap.containsKey(selectedEmpName)) {
            empName.setSelectedItem(partMap.get(selectedEmpName));
            
        }
    }
});

        itemName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) itemName.getSelectedItem();
                if (selectedName != null && itemMap.containsKey(selectedName)) {
                    itemCode.setSelectedItem(itemMap.get(selectedName));
                }
            }
        });

        itemCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCode = (String) itemCode.getSelectedItem();
                if (selectedCode != null) {
                    for (Map.Entry<String, String> entry : itemMap.entrySet()) {
                        if (entry.getValue().equals(selectedCode)) {
                            itemName.setSelectedItem(entry.getKey());
                            break;
                        }
                    }
                }
            }
        });
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
        empName = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(empName);
        itemName = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(itemName);
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonDelete = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldQuantity = new javax.swing.JTextField();
        jButtonView = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextFieldNewPartName = new javax.swing.JTextField();
        jButtonAdd = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        itemCode = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(itemCode);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        empEnt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        empEnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empEnt.setText("B.O.M");
        jPanel1.add(empEnt);
        empEnt.setBounds(20, 10, 650, 70);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(10, 90, 670, 20);

        empName.setEditable(true);
        empName.setToolTipText("");
        empName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empNameActionPerformed(evt);
            }
        });
        jPanel1.add(empName);
        empName.setBounds(30, 140, 200, 30);

        itemName.setEditable(true);
        itemName.setToolTipText("");
        jPanel1.add(itemName);
        itemName.setBounds(30, 210, 200, 30);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Quantity");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(360, 190, 100, 20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("part Name");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(351, 120, 140, 20);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Item Name");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, 190, 100, 20);

        jButtonDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDelete);
        jButtonDelete.setBounds(530, 270, 150, 27);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(530, 110, 150, 27);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Existing Part Name");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, 120, 140, 20);
        jPanel1.add(jTextFieldQuantity);
        jTextFieldQuantity.setBounds(350, 210, 160, 30);

        jButtonView.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonView.setText("View");
        jButtonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonView);
        jButtonView.setBounds(530, 150, 150, 27);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Part", "Item", "Quantity"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(20, 310, 660, 340);
        jPanel1.add(jTextFieldNewPartName);
        jTextFieldNewPartName.setBounds(350, 140, 160, 30);

        jButtonAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAdd);
        jButtonAdd.setBounds(530, 190, 150, 27);

        jButtonEdit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonEdit.setText("change quantity");
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEdit);
        jButtonEdit.setBounds(530, 230, 150, 27);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Item Code");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(31, 245, 100, 20);

        itemCode.setEditable(true);
        itemCode.setToolTipText("");
        jPanel1.add(itemCode);
        itemCode.setBounds(30, 270, 200, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(425, 130, 710, 660);

        setSize(new java.awt.Dimension(1394, 892));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
                                              
    int row = jTable1.getSelectedRow();
    if (row >= 0) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String partNameValue = model.getValueAt(row, 0).toString();
        String itemNameValue = model.getValueAt(row, 1).toString(); // Assuming itemName is in column 2

        // Show confirmation dialog before deletion
        int response = JOptionPane.showConfirmDialog(null, 
                "Do you want to delete the entry for part: " + partNameValue + 
                " for item " + itemNameValue + "?", 
                "Confirm Deletion", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM part_items WHERE partName = ? AND itemName = ?";
            try (Connection conn = DatabaseConnection.getConnection(); // Use centralized connection method
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, partNameValue);
                pstmt.setString(2, itemNameValue);

                pstmt.executeUpdate();

                // Remove row from the table
                model.removeRow(row);
                JOptionPane.showMessageDialog(null, "Entry deleted successfully.");
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please select an entry to delete.");
    }     
    viewAllPartItems(); // Ensure this method is adapted to use PostgreSQL if it interacts with the database

    
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new AdminMenu().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
                                          
  String newPartName = jTextFieldNewPartName.getText().trim().toUpperCase();
    String selectedItem = (String) itemName.getSelectedItem();
    String quantity = jTextFieldQuantity.getText().trim();

    if (!newPartName.isEmpty() && !selectedItem.isEmpty() && !quantity.isEmpty()) {
        try (Connection con = DatabaseConnection.getConnection()) { // Use centralized connection method
            // Check if the item already exists for the part
            String checkQuery = "SELECT COUNT(*) FROM part_items WHERE partName = ? AND itemName = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, newPartName);
            checkStmt.setString(2, selectedItem);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                // Show popup if item already exists for the part
                JOptionPane.showMessageDialog(null, "Item already exists for this part.");
            } else {
                // Insert new item if it doesn't exist
                String insertQuery = "INSERT INTO part_items (partName, itemName, quantity) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = con.prepareStatement(insertQuery);
                insertStmt.setString(1, newPartName);
                insertStmt.setString(2, selectedItem);
                insertStmt.setBigDecimal(3, new BigDecimal(quantity)); // Use BigDecimal for numeric type
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Item Added Successfully");
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric value for quantity.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please fill in all fields");
    }
    viewAllPartItems(); // Ensure this method is adapted to use PostgreSQL if it interacts with the database
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
                                            
    int row = jTable1.getSelectedRow();
    if (row >= 0) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String partNameValue = model.getValueAt(row, 0).toString();
        String itemNameValue = model.getValueAt(row, 1).toString(); // Assuming itemName is in column 2
        String oldQuantity = model.getValueAt(row, 2).toString();
        String newQuantity = jTextFieldQuantity.getText();

        // Show confirmation dialog before updating quantity
        int response = JOptionPane.showConfirmDialog(null, 
                "Do you want to change the quantity for item " + itemNameValue + 
                " from " + oldQuantity + " to " + newQuantity + "?", 
                "Confirm Quantity Update", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "UPDATE part_items SET quantity = ? WHERE partName = ? AND itemName = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setBigDecimal(1, new BigDecimal(newQuantity)); // Use BigDecimal for numeric type
                pstmt.setString(2, partNameValue);
                pstmt.setString(3, itemNameValue);
                pstmt.executeUpdate();
                
                // Update table display
                model.setValueAt(newQuantity, row, 2);
                JOptionPane.showMessageDialog(null, "Quantity updated successfully.");
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } 
    } else {
        JOptionPane.showMessageDialog(null, "Please select an entry to update.");
    }



    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActionPerformed
                                           
    try (Connection con = DatabaseConnection.getConnection()) {
        String query = "SELECT * FROM part_items";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);  // Clear existing rows
        
        while (rs.next()) {
            String partName = rs.getString("partName");
            String itemName = rs.getString("itemName");
            int quantity = rs.getInt("quantity");
            model.addRow(new Object[]{partName, itemName, quantity});
        }
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Error: " + e.getMessage());
        JOptionPane.showMessageDialog(null, e.getMessage());
    }

       // TODO add your handling code here:
    }//GEN-LAST:event_jButtonViewActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
          int row = jTable1.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    
    String partnameValue = model.getValueAt(row, 0).toString();
    String itemnameValue = model.getValueAt(row, 1).toString();
    String quantityValue = model.getValueAt(row, 2).toString();
    

    empName.setSelectedItem(partnameValue);
    itemName.setSelectedItem(itemnameValue);
    // Assuming `empName` is a combo box
    jTextFieldQuantity.setText(quantityValue);
    
       // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void empNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empNameActionPerformed
String selectedEmpName = (String) empName.getSelectedItem();
    // Set the selected item as the text of jTextFieldNewPartName
    jTextFieldNewPartName.setText(selectedEmpName);        // TODO add your handling code here:
    }//GEN-LAST:event_empNameActionPerformed

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
            java.util.logging.Logger.getLogger(Part_items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Part_items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Part_items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Part_items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Part_items().setVisible(true);
            }
        });
        SwingUtilities.invokeLater(() -> new AdminMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JComboBox<String> empName;
    private javax.swing.JComboBox<String> itemCode;
    private javax.swing.JComboBox<String> itemName;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonView;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldNewPartName;
    private javax.swing.JTextField jTextFieldQuantity;
    // End of variables declaration//GEN-END:variables
}

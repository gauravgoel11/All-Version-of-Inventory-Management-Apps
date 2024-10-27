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
public class Employee extends javax.swing.JFrame {

    /**
     * Creates new form Employee
     */
    public Employee() {
        initComponents();
        setExtendedState(this.MAXIMIZED_BOTH);
        loadDataIntoTable();
//         setExtendedState(this.MAXIMIZED_BOTH);
 setupKeyBindings();
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
     private void loadDataIntoTable() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
            String sql = "SELECT * FROM emp";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
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
        jButtonAddData = new javax.swing.JButton();
        jButtonUpdateData = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTxtName = new javax.swing.JTextField();
        jDateChooserDateOfBirth = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jTxtMobile = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxtEmployeeId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTxtAadharNumber = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        jButtonAddData.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonAddData.setText("AddData");
        jButtonAddData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddDataActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAddData);
        jButtonAddData.setBounds(470, 130, 110, 27);

        jButtonUpdateData.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonUpdateData.setText("UpdateData");
        jButtonUpdateData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateDataActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonUpdateData);
        jButtonUpdateData.setBounds(470, 170, 120, 27);

        jButtonPrint.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonPrint.setText("Print");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonPrint);
        jButtonPrint.setBounds(590, 130, 110, 27);

        jButtonReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonReset.setText("Erase");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonReset);
        jButtonReset.setBounds(590, 90, 110, 27);

        jButtonDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDelete);
        jButtonDelete.setBounds(710, 130, 90, 27);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("All entry");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(470, 90, 110, 27);

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);
        jButton5.setBounds(710, 90, 92, 27);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "EmployeeID", "Name", "D.O.B", "Mobile Number", "Aadhar Number"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 350, 790, 300);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("                      EMPLOYEE DATA");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(7, 20, 610, 45);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("DOB");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 140, 152, 38);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Name");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 90, 152, 38);

        jTxtName.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jPanel1.add(jTxtName);
        jTxtName.setBounds(180, 90, 257, 38);
        jPanel1.add(jDateChooserDateOfBirth);
        jDateChooserDateOfBirth.setBounds(180, 140, 257, 38);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Employee ID");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 190, 152, 38);

        jTxtMobile.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jPanel1.add(jTxtMobile);
        jTxtMobile.setBounds(180, 240, 257, 38);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Mobile");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 240, 152, 38);

        jTxtEmployeeId.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jTxtEmployeeId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtEmployeeIdActionPerformed(evt);
            }
        });
        jPanel1.add(jTxtEmployeeId);
        jTxtEmployeeId.setBounds(180, 190, 257, 38);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Aadhar No.");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 290, 130, 38);

        jTxtAadharNumber.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jPanel1.add(jTxtAadharNumber);
        jTxtAadharNumber.setBounds(180, 290, 257, 38);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 807, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddDataActionPerformed
        // TODO add your handling code here:
     String name = jTxtName.getText();
     String nameUpperCase = name.toUpperCase();
        java.util.Date dateOfBirth = jDateChooserDateOfBirth.getDate();
        String employeeId = jTxtEmployeeId.getText();
        String mobile = jTxtMobile.getText();
        String aadharNumber = jTxtAadharNumber.getText();

        // Validate that name and employeeId are not empty
        if (nameUpperCase == null || nameUpperCase.trim().isEmpty() || employeeId == null || employeeId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name and Employee ID cannot be empty");
            return;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateOfBirth = (dateOfBirth != null) ? formatter.format(dateOfBirth) : null;

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
            String sql = "INSERT INTO emp (empName, dateOfBirth, empId, mobile, aadharNumber) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nameUpperCase);
            pstmt.setString(2, formattedDateOfBirth);
            pstmt.setString(3, employeeId);
            pstmt.setString(4, mobile);
            pstmt.setString(5, aadharNumber);

            pstmt.executeUpdate();
            conn.close();

            JOptionPane.showMessageDialog(null, "Data Added Successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
            jTxtName.setText("");
    jDateChooserDateOfBirth.setDate(null);
    jTxtEmployeeId.setText("");
    jTxtMobile.setText("");
    jTxtAadharNumber.setText("");
    loadDataIntoTable();


    }//GEN-LAST:event_jButtonAddDataActionPerformed

    private void jTxtEmployeeIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtEmployeeIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtEmployeeIdActionPerformed
    private JFrame frame;
    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        // TODO add your handling code here:
  
    jTxtName.setText("");
    jDateChooserDateOfBirth.setDate(null);
    jTxtEmployeeId.setText("");
    jTxtMobile.setText("");
    jTxtAadharNumber.setText("");


    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jButtonUpdateDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateDataActionPerformed
                                                
    String name = jTxtName.getText();
    java.util.Date dateOfBirth = jDateChooserDateOfBirth.getDate();
    String employeeId = jTxtEmployeeId.getText();
    String mobile = jTxtMobile.getText();
    String aadharNumber = jTxtAadharNumber.getText();

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDateOfBirth = (dateOfBirth != null) ? formatter.format(dateOfBirth) : null;

    if (!employeeId.isEmpty() && !name.isEmpty()) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
            
            // Fetch the current data from the database
            String fetchSql = "SELECT * FROM emp WHERE empId = ? AND empName = ?";
            PreparedStatement fetchPstmt = conn.prepareStatement(fetchSql);
            fetchPstmt.setString(1, employeeId);
            fetchPstmt.setString(2, name);
            ResultSet rs = fetchPstmt.executeQuery();
            
            if (rs.next()) {
                String currentName = rs.getString("empName");
                String currentDateOfBirth = rs.getString("dateOfBirth");
                String currentMobile = rs.getString("mobile");
                String currentAadharNumber = rs.getString("aadharNumber");

                // Build the update query dynamically based on changed fields
                StringBuilder sql = new StringBuilder("UPDATE emp SET ");
                boolean firstField = true;

                if (!name.equals(currentName)) {
                    sql.append("empName = ?");
                    firstField = false;
                }
                if (formattedDateOfBirth != null && !formattedDateOfBirth.equals(currentDateOfBirth)) {
                    if (!firstField) sql.append(", ");
                    sql.append("dateOfBirth = ?");
                    firstField = false;
                }
                if (!mobile.equals(currentMobile)) {
                    if (!firstField) sql.append(", ");
                    sql.append("mobile = ?");
                    firstField = false;
                }
                if (!aadharNumber.equals(currentAadharNumber)) {
                    if (!firstField) sql.append(", ");
                    sql.append("aadharNumber = ?");
                }

                sql.append(" WHERE empId = ? AND empName = ?");

                PreparedStatement pstmt = conn.prepareStatement(sql.toString());
                int paramIndex = 1;

                if (!name.equals(currentName)) {
                    pstmt.setString(paramIndex++, name);
                }
                if (formattedDateOfBirth != null && !formattedDateOfBirth.equals(currentDateOfBirth)) {
                    pstmt.setString(paramIndex++, formattedDateOfBirth);
                }
                if (!mobile.equals(currentMobile)) {
                    pstmt.setString(paramIndex++, mobile);
                }
                if (!aadharNumber.equals(currentAadharNumber)) {
                    pstmt.setString(paramIndex++, aadharNumber);
                }

                pstmt.setString(paramIndex++, employeeId);
                pstmt.setString(paramIndex++, currentName);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Data updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No matching record found.");
                }
                conn.close();
            } else {
                JOptionPane.showMessageDialog(null, "No matching record found.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please enter both Employee ID and Name to update.");
    }
        jTxtName.setText("");
    jDateChooserDateOfBirth.setDate(null);
    jTxtEmployeeId.setText("");
    jTxtMobile.setText("");
    jTxtAadharNumber.setText("");
    loadDataIntoTable();

        // TODO add your handling code here:
  
    }//GEN-LAST:event_jButtonUpdateDataActionPerformed

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        // TODO add your handling code here:
            try {
        boolean printComplete = jTable1.print();
        if (printComplete) {
            JOptionPane.showMessageDialog(null, "Printing Completed");
        } else {
            JOptionPane.showMessageDialog(null, "Printing Cancelled");
        }
    } catch (PrinterException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    }//GEN-LAST:event_jButtonPrintActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
                                            
    String employeeId = jTxtEmployeeId.getText();
    String name = jTxtName.getText();

    if (!employeeId.isEmpty() && !name.isEmpty()) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
            String sql = "DELETE FROM emp WHERE empId = ? AND empName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, employeeId);
            pstmt.setString(2, name);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Data deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No matching record found.");
            }
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please enter both Employee ID and Name to delete.");
    }
    loadDataIntoTable();

        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            try {
        // Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
        String sql = "SELECT * FROM emp";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        
        // Get table model
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        // Clear existing data
        model.setRowCount(0);

        // Get column names dynamically
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        
        // Add rows to the model
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }
        
        // Close connections
        rs.close();
        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
                                     
    // Get the selected row index
    jTxtName.setText("");
    jDateChooserDateOfBirth.setDate(null);
    jTxtEmployeeId.setText("");
    jTxtMobile.setText("");
    jTxtAadharNumber.setText("");
    int selectedRow = jTable1.getSelectedRow();
    
    // Retrieve data from the selected row and set them to the corresponding text fields
    jTxtEmployeeId.setText(jTable1.getValueAt(selectedRow, 0) != null ? jTable1.getValueAt(selectedRow, 0).toString() : "");
    jTxtName.setText(jTable1.getValueAt(selectedRow, 1) != null ? jTable1.getValueAt(selectedRow, 1).toString() : "");
    
    try {
        String dateString = jTable1.getValueAt(selectedRow, 2) != null ? jTable1.getValueAt(selectedRow, 2).toString() : "";
        if (!dateString.isEmpty()) {
            java.util.Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            jDateChooserDateOfBirth.setDate(dateOfBirth);
        } else {
            jDateChooserDateOfBirth.setDate(null);
        }
    } catch (Exception e) {
        jDateChooserDateOfBirth.setDate(null);
    }
    
    jTxtMobile.setText(jTable1.getValueAt(selectedRow, 3) != null ? jTable1.getValueAt(selectedRow, 3).toString() : "");
    jTxtAadharNumber.setText(jTable1.getValueAt(selectedRow, 4) != null ? jTable1.getValueAt(selectedRow, 4).toString() : "");



    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
new AdminMenu().setVisible(true);
            this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException {
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
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee().setVisible(true);
            }
        });
        SwingUtilities.invokeLater(() -> new AdminMenu().setVisible(true));
        

    }
  
  

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonAddData;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonUpdateData;
    private com.toedter.calendar.JDateChooser jDateChooserDateOfBirth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTxtAadharNumber;
    private javax.swing.JTextField jTxtEmployeeId;
    private javax.swing.JTextField jTxtMobile;
    private javax.swing.JTextField jTxtName;
    // End of variables declaration//GEN-END:variables
}

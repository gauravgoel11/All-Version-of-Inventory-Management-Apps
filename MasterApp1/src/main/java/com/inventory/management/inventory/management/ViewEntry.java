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
public class ViewEntry extends javax.swing.JFrame {

    /**
     * Creates new form ViewEntry
     */
    public ViewEntry() {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        empEnt = new javax.swing.JLabel();
        empName = new javax.swing.JComboBox<>();
        try{
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:inven.db");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from emp");
            while(rs.next()){
                String s = rs.getString("empName")+" "+rs.getString("empID");
                empName.addItem(s);
            }
            con.close();
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Error is "+e.getMessage());
        }
        AutoCompleteDecorator.decorate(empName);
        jButtonCusotmEntry = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jBtnTotalWork = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "EmployeeID", "Category", "Quantity", "Date"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("All Entry");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonPrint.setText("Print");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jLabel2.setText("Employee Name");

        jLabel4.setText("From");

        jLabel5.setText("To");

        empEnt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        empEnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empEnt.setText("View Employee Entry");

        empName.setEditable(true);
        empName.setToolTipText("");

        jButtonCusotmEntry.setText("Custom entry");
        jButtonCusotmEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCusotmEntryActionPerformed(evt);
            }
        });

        jButtonReset.setText("Erase");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jBtnTotalWork.setText("Total work");
        jBtnTotalWork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTotalWorkActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(133, 133, 133)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(empName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCusotmEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBtnTotalWork)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(42, 42, 42))
            .addComponent(empEnt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empEnt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(empName, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonPrint)
                            .addComponent(jButtonCusotmEntry)
                            .addComponent(jButtonReset))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonExit)
                            .addComponent(jButton1)
                            .addComponent(jBtnTotalWork))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
            String sql = "SELECT * FROM entry";
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
private JFrame frame;
    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        // TODO add your handling code here:
        frame = new JFrame ("Exit");
        if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit","sqlite Connector",
            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
    {

        System.exit(0); }

    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonCusotmEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCusotmEntryActionPerformed
try {
    // Get selected employee name and ID
    String selectedEmployee = (empName.getSelectedIndex() != -1) ? empName.getSelectedItem().toString() : "";
    
    String empName = "";
    String empID = "";
    
    // Check if an employee is selected
    if (!selectedEmployee.isEmpty()) {
        String[] employeeData = selectedEmployee.split(" ");
        empName = employeeData[0];
        empID = employeeData[1];
    }
    
    // Get selected dates
    java.util.Date fromDate = jDateChooserFrom.getDate();
    java.util.Date toDate = jDateChooserTo.getDate();
    
    // Format the date to "yyyy-MM-dd"
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedFromDate = (fromDate != null) ? formatter.format(fromDate) : null;
    String formattedToDate = (toDate != null) ? formatter.format(toDate) : null;
    
    // Prepare SQL query dynamically
    StringBuilder query = new StringBuilder("SELECT * FROM entry WHERE 1=1");
    
    if (!empName.isEmpty()) {
        query.append(" AND empName = ? AND empID = ?");
    }
    if (formattedFromDate != null) {
        query.append(" AND entryDate >= ?");
    }
    if (formattedToDate != null) {
        query.append(" AND entryDate <= ?");
    }
    
    // Connect to the database
    Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
    PreparedStatement pstmt = conn.prepareStatement(query.toString());
    
    // Set parameters dynamically
    int paramIndex = 1;
    
    if (!empName.isEmpty()) {
        pstmt.setString(paramIndex++, empName);
        pstmt.setString(paramIndex++, empID);
    }
    if (formattedFromDate != null) {
        pstmt.setString(paramIndex++, formattedFromDate);
    }
    if (formattedToDate != null) {
        pstmt.setString(paramIndex++, formattedToDate);
    }
    
    // Execute query
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

      // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCusotmEntryActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        // TODO add your handling code here:
       jDateChooserFrom.setDate(null);
        jDateChooserTo.setDate(null);
        empName.setSelectedIndex(-1);
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jBtnTotalWorkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTotalWorkActionPerformed
                                               
    try {
    // Get selected employee name and ID
    String selectedEmployee = (empName.getSelectedIndex() != -1) ? empName.getSelectedItem().toString() : "";
    
    String empName = "";
    String empID = "";
    
    // Check if an employee is selected
    if (!selectedEmployee.isEmpty()) {
        String[] employeeData = selectedEmployee.split(" ");
        empName = employeeData[0];
        empID = employeeData[1];
    }
    
    // Get selected dates
    java.util.Date fromDate = jDateChooserFrom.getDate();
    java.util.Date toDate = jDateChooserTo.getDate();
    
    // Format the date to "yyyy-MM-dd"
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedFromDate = (fromDate != null) ? formatter.format(fromDate) : null;
    String formattedToDate = (toDate != null) ? formatter.format(toDate) : null;
    
    // Prepare SQL query dynamically
    StringBuilder query = new StringBuilder("SELECT empName, empID, itemName, SUM(quantity) as totalQuantity FROM entry WHERE 1=1");
    
    if (!empName.isEmpty()) {
        query.append(" AND empName = ? AND empID = ?");
    }
    if (formattedFromDate != null) {
        query.append(" AND entryDate >= ?");
    }
    if (formattedToDate != null) {
        query.append(" AND entryDate <= ?");
    }
    query.append(" GROUP BY empName, empID, itemName");
    
    // Connect to the database
    Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
    PreparedStatement pstmt = conn.prepareStatement(query.toString());
    
    // Set parameters dynamically
    int paramIndex = 1;
    
    if (!empName.isEmpty()) {
        pstmt.setString(paramIndex++, empName);
        pstmt.setString(paramIndex++, empID);
    }
    if (formattedFromDate != null) {
        pstmt.setString(paramIndex++, formattedFromDate);
    }
    if (formattedToDate != null) {
        pstmt.setString(paramIndex++, formattedToDate);
    }
    
    // Execute query
    ResultSet rs = pstmt.executeQuery();
    
    // Get the table model from your JTable
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    
    // Clear the existing rows in the table
    model.setRowCount(0);
    
    // Populate the table with the query result
    while (rs.next()) {
        String resultEmpName = rs.getString("empName");
        String resultEmpID = rs.getString("empID");
        String itemName = rs.getString("itemName");
        int totalQuantity = rs.getInt("totalQuantity");
        
        // Add a new row to the table model
        model.addRow(new Object[]{resultEmpName, resultEmpID, itemName, totalQuantity});
    }
    
    // Close connections
    rs.close();
    pstmt.close();
    conn.close();
    
} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, e.getMessage());
}


    }//GEN-LAST:event_jBtnTotalWorkActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      new AdminMenu().setVisible(true);
        this.dispose();         // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(ViewEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewEntry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JComboBox<String> empName;
    private javax.swing.JButton jBtnTotalWork;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCusotmEntry;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonReset;
    private com.toedter.calendar.JDateChooser jDateChooserFrom;
    private com.toedter.calendar.JDateChooser jDateChooserTo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
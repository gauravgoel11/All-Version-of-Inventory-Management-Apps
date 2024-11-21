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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigDecimal;



/**
 *
 * @author gaura
 */
public class SalaryCalculator extends javax.swing.JFrame {
    private Map<String, String> empMap = new HashMap<>();

    /**
     * Creates new form ViewEntry
     */
    public SalaryCalculator() {
        initComponents();
        
         setExtendedState(this.MAXIMIZED_BOTH);
         setupKeyBindings();
         viewSalaryOfLastMonth();
         loadItems();
         empName.setSelectedIndex(-1);
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

public void viewSalaryOfLastMonth() {
    try (Connection conn = DatabaseConnection.getConnection()) {
        // Get the current date
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        // Set the calendar to the first day of the current month
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
        
        // Subtract one day to get the last day of the previous month
        cal.add(java.util.Calendar.DAY_OF_MONTH, -1);
        java.util.Date endDate = cal.getTime();
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
        
        // Set the calendar to the first day of the previous month
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
        java.util.Date startDate = cal.getTime();
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());

        // Prepare SQL query to select distinct employees from the entry table for the last month
        String employeeQuery = "SELECT DISTINCT empName, empID FROM entry WHERE entryDate BETWEEN ? AND ?";
        PreparedStatement employeeStmt = conn.prepareStatement(employeeQuery);
        employeeStmt.setDate(1, sqlStartDate);
        employeeStmt.setDate(2, sqlEndDate);

        ResultSet employeeRs = employeeStmt.executeQuery();

        // Get table model
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        // Clear existing data
        model.setRowCount(0);

        while (employeeRs.next()) {
            String empName = employeeRs.getString("empName");
            int empID = employeeRs.getInt("empID");

            // Fetch base salary from emp table
            String salaryQuery = "SELECT baseSalary FROM emp WHERE empName = ? AND empID = ?";
            PreparedStatement salaryStmt = conn.prepareStatement(salaryQuery);
            salaryStmt.setString(1, empName);
            salaryStmt.setInt(2, empID);
            ResultSet salaryRs = salaryStmt.executeQuery();

            BigDecimal baseSalary = BigDecimal.ZERO;
            if (salaryRs.next()) {
                baseSalary = salaryRs.getBigDecimal("baseSalary");
            }

            // Calculate total parts cost from entry and item tables for the last month
            String partsQuery = "SELECT SUM(en.quantity * i.cost) AS totalPartsCost " +
                                "FROM entry en " +
                                "JOIN items i ON en.itemName = i.itemName " +
                                "WHERE en.empName = ? AND en.empID = ? AND en.entryDate BETWEEN ? AND ?";
            PreparedStatement partsStmt = conn.prepareStatement(partsQuery);
            partsStmt.setString(1, empName);
            partsStmt.setInt(2, empID);
            partsStmt.setDate(3, sqlStartDate);
            partsStmt.setDate(4, sqlEndDate);
            ResultSet partsRs = partsStmt.executeQuery();

            BigDecimal totalPartsCost = BigDecimal.ZERO;
            if (partsRs.next()) {
                totalPartsCost = partsRs.getBigDecimal("totalPartsCost");
            }

            // Calculate total salary
            BigDecimal totalSalary = baseSalary.add(totalPartsCost);

            // Add row to the model
            model.addRow(new Object[]{empName, empID, baseSalary, totalPartsCost, totalSalary});

            // Close result sets and statements for each employee
            salaryRs.close();
            salaryStmt.close();
            partsRs.close();
            partsStmt.close();
        }

        // Close connections
        employeeRs.close();
        employeeStmt.close();
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}



 private void loadItems() { 
 try (Connection con = DatabaseConnection.getConnection()) {
        Statement st = con.createStatement();
        
        // Load items
        ResultSet rs = st.executeQuery("SELECT * FROM emp");
        while (rs.next()) {
            String empNameWithID = rs.getString("empName") + " " + rs.getString("empID");
            empName.addItem(empNameWithID);
            empMap.put(empNameWithID, empNameWithID);        
        }

        // Load employee names with IDs
        
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Error is " + e.getMessage());
    }
    }
         private void setupComboBoxListeners() {
        empName.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedEmpNameWithID = (String) empName.getSelectedItem();
            if (selectedEmpNameWithID != null && empMap.containsKey(selectedEmpNameWithID)) {
                // Split the selected employee name and ID
                String[] empDetails = selectedEmpNameWithID.split(" ");
                if (empDetails.length == 2) {
                    String empName = empDetails[0];
                    String empID = empDetails[1];
                    // Perform actions based on the selected employee name and ID
                    // For example, update a text field or display additional information
//                    System.out.println("Selected Employee: " + empName + ", ID: " + empID);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonPrint = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        empEnt = new javax.swing.JLabel();
        empName = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(empName);
        jButtonReset = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonCalculateSalary = new javax.swing.JButton();

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
                "Name", "EmployeeID", "Salary", "Commission", "Total"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButtonPrint.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonPrint.setText("Print");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Employee Name");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("From");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("To");

        empEnt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        empEnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empEnt.setText("Salary Calculator");

        empName.setEditable(true);
        empName.setToolTipText("");

        jButtonReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonReset.setText("Erase");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonCalculateSalary.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonCalculateSalary.setText("Calculate salary");
        jButtonCalculateSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalculateSalaryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(empEnt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(empName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonCalculateSalary, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(jButtonPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empEnt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonReset))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonCalculateSalary)
                                    .addComponent(jButton2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonPrint)))
                        .addGap(40, 40, 40))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(empName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooserFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        // TODO add your handling code here:
       jDateChooserFrom.setDate(null);
        jDateChooserTo.setDate(null);
        empName.setSelectedIndex(-1);
       
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      new AdminMenu().setVisible(true);
        this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
   
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonCalculateSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalculateSalaryActionPerformed

    // Get selected employee name and ID from the combo box
    String selectedEmpNameWithID = (String) empName.getSelectedItem();
    String selectedEmpName = null;
    Integer selectedEmpID = null;

    if (selectedEmpNameWithID != null && !selectedEmpNameWithID.isEmpty()) {
        String[] empDetails = selectedEmpNameWithID.split(" ");
        if (empDetails.length == 2) {
            selectedEmpName = empDetails[0];
            try {
                selectedEmpID = Integer.parseInt(empDetails[1]);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid employee ID.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid employee selection.");
            return;
        }
    }

    // Get the selected date range from the date choosers
    java.util.Date fromDate = jDateChooserFrom.getDate();
    java.util.Date toDate = jDateChooserTo.getDate();

    // Use default dates if not selected
    java.sql.Date sqlFromDate = (fromDate != null) ? new java.sql.Date(fromDate.getTime()) : java.sql.Date.valueOf("1970-01-01");
    java.sql.Date sqlToDate = (toDate != null) ? new java.sql.Date(toDate.getTime()) : new java.sql.Date(System.currentTimeMillis());

    try (Connection conn = DatabaseConnection.getConnection()) {
        // Prepare SQL query to calculate salary details
        StringBuilder query = new StringBuilder("SELECT e.empName, e.empID, e.baseSalary, ")
            .append("SUM(en.quantity * i.cost) AS commission, ")
            .append("(e.baseSalary + SUM(en.quantity * i.cost)) AS totalSalary ")
            .append("FROM emp e ")
            .append("JOIN entry en ON e.empID = en.empID ")
            .append("JOIN items i ON en.itemName = i.itemName ")
            .append("WHERE en.entryDate BETWEEN ? AND ? ");

        if (selectedEmpName != null && selectedEmpID != null) {
            query.append("AND e.empName = ? AND e.empID = ? ");
        }

        query.append("GROUP BY e.empName, e.empID, e.baseSalary");

        // Prepare statement
        PreparedStatement pstmt = conn.prepareStatement(query.toString());
        pstmt.setDate(1, sqlFromDate);
        pstmt.setDate(2, sqlToDate);

        if (selectedEmpName != null && selectedEmpID != null) {
            pstmt.setString(3, selectedEmpName);
            pstmt.setInt(4, selectedEmpID);
        }

        // Execute query
        ResultSet rs = pstmt.executeQuery();

        // Get the table model from your JTable
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Clear the existing rows in the table
        model.setRowCount(0);

        // Populate the table with the query result
        while (rs.next()) {
            String empName = rs.getString("empName");
            int empID = rs.getInt("empID");
            BigDecimal baseSalary = rs.getBigDecimal("baseSalary");
            BigDecimal commission = rs.getBigDecimal("commission");
            BigDecimal totalSalary = rs.getBigDecimal("totalSalary");

            // Add a new row to the table model
            model.addRow(new Object[]{empName, empID, baseSalary, commission, totalSalary});
        }

        // Close connections
        rs.close();
        pstmt.close();

    } catch (SQLException | ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }


    }//GEN-LAST:event_jButtonCalculateSalaryActionPerformed

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
            java.util.logging.Logger.getLogger(SalaryCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalaryCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalaryCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalaryCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalaryCalculator().setVisible(true);
            }
        });
         SwingUtilities.invokeLater(() -> new AdminMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JComboBox<String> empName;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCalculateSalary;
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

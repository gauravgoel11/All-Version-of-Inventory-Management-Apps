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
import java.awt.event.KeyEvent;
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
import java.util.Map;
import java.util.HashMap;


/**
 *
 * @author gaura
 */
public class ViewEmployeeStock extends javax.swing.JFrame {

    /**
     * Creates new form ViewEntry
     */
    public ViewEmployeeStock() {
        initComponents();
        empName.setSelectedIndex(-1);
         setExtendedState(this.MAXIMIZED_BOTH);
         setupKeyBindings();
         TotalStockView();
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
    private void  TotalStockView(){
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

        // Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
        Statement stmt = conn.createStatement();

        // Create a temporary table to store intermediate results
        stmt.execute("CREATE TEMPORARY TABLE temp_stock (empName TEXT, empID TEXT, itemName TEXT, creditQuantity INTEGER, debitQuantity INTEGER)");

        // Insert total credits into the temporary table
        StringBuilder creditQuery = new StringBuilder("INSERT INTO temp_stock (empName, empID, itemName, creditQuantity, debitQuantity) ");
        creditQuery.append("SELECT empName, empID, itemName, SUM(quantity) as creditQuantity, 0 as debitQuantity FROM entry WHERE 1=1");
        if (!empName.isEmpty()) {
            creditQuery.append(" AND empName = ? AND empID = ?");
        }
        if (formattedFromDate != null) {
            creditQuery.append(" AND entryDate >= ?");
        }
        if (formattedToDate != null) {
            creditQuery.append(" AND entryDate <= ?");
        }
        creditQuery.append(" GROUP BY empName, empID, itemName");

        PreparedStatement creditPstmt = conn.prepareStatement(creditQuery.toString());
        int paramIndex = 1;
        if (!empName.isEmpty()) {
            creditPstmt.setString(paramIndex++, empName);
            creditPstmt.setString(paramIndex++, empID);
        }
        if (formattedFromDate != null) {
            creditPstmt.setString(paramIndex++, formattedFromDate);
        }
        if (formattedToDate != null) {
            creditPstmt.setString(paramIndex++, formattedToDate);
        }
        creditPstmt.executeUpdate();

        // Calculate total debits by reversing the final product and insert into the temporary table
        StringBuilder debitQuery = new StringBuilder("INSERT INTO temp_stock (empName, empID, itemName, creditQuantity, debitQuantity) ");
        debitQuery.append("SELECT empName, empID, part_items.itemName, 0 as creditQuantity, SUM(partentry.quantity * part_items.quantity) as debitQuantity ");
        debitQuery.append("FROM partentry ");
        debitQuery.append("JOIN part_items ON partentry.partName = part_items.partName ");
        debitQuery.append("WHERE 1=1");
        if (!empName.isEmpty()) {
            debitQuery.append(" AND empName = ? AND empID = ?");
        }
        if (formattedFromDate != null) {
            debitQuery.append(" AND entryDate >= ?");
        }
        if (formattedToDate != null) {
            debitQuery.append(" AND entryDate <= ?");
        }
        debitQuery.append(" GROUP BY empName, empID, part_items.itemName");

        PreparedStatement debitPstmt = conn.prepareStatement(debitQuery.toString());
        paramIndex = 1;
        if (!empName.isEmpty()) {
            debitPstmt.setString(paramIndex++, empName);
            debitPstmt.setString(paramIndex++, empID);
        }
        if (formattedFromDate != null) {
            debitPstmt.setString(paramIndex++, formattedFromDate);
        }
        if (formattedToDate != null) {
            debitPstmt.setString(paramIndex++, formattedToDate);
        }
        debitPstmt.executeUpdate();

        // Calculate remaining stock for each item
        String remainingStockQuery = "SELECT empName, empID, itemName, SUM(creditQuantity) - SUM(debitQuantity) as totalStock FROM temp_stock GROUP BY empName, empID, itemName";
        ResultSet rs = stmt.executeQuery(remainingStockQuery);

        // Get the table model from your JTable
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Clear the existing rows in the table
        model.setRowCount(0);

        // Populate the table with the remaining stock
        while (rs.next()) {
            String resultEmpName = rs.getString("empName");
            String resultEmpID = rs.getString("empID");
            String itemName = rs.getString("itemName");
            int totalStock = rs.getInt("totalStock");
            model.addRow(new Object[]{resultEmpName, resultEmpID, itemName, totalStock});
        }

        // Close connections
        rs.close();
        creditPstmt.close();
        debitPstmt.close();
        stmt.execute("DROP TABLE temp_stock");
        stmt.close();
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
        jButtonReset = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jBtnTotalCredit = new javax.swing.JButton();
        jBtnTotalDebit = new javax.swing.JButton();
        jBtnTotalStock = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "EmployeeID", "Category", "Quantity"
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
        empEnt.setText("View Worker Stock");

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

        jBtnTotalCredit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnTotalCredit.setText("Total Credit");
        jBtnTotalCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTotalCreditActionPerformed(evt);
            }
        });

        jBtnTotalDebit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnTotalDebit.setText("Total Debit");
        jBtnTotalDebit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTotalDebitActionPerformed(evt);
            }
        });

        jBtnTotalStock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnTotalStock.setText("Total Stock");
        jBtnTotalStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTotalStockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(empEnt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(empName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnTotalCredit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnTotalDebit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnTotalStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(jButtonReset, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(205, 205, 205))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empEnt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(empName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnTotalCredit)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnTotalDebit)
                            .addComponent(jButtonReset))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonPrint)
                            .addComponent(jBtnTotalStock))
                        .addGap(44, 44, 44)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

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
        // TODO add your handling code here:
            int row = jTable1.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    
    String empNameValue = model.getValueAt(row, 0).toString();
    String quantityValue = model.getValueAt(row, 3).toString();
    String dateValue = model.getValueAt(row, 4).toString();

    empName.setSelectedItem(empNameValue);  // Assuming `empName` is a combo box
    
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
        jDateChooserFrom.setDate(sdf.parse(dateValue));  // Set date field
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Date Parse Error: " + e.getMessage());
    }
    
    }//GEN-LAST:event_jTable1MouseClicked

    private void jBtnTotalCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTotalCreditActionPerformed

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

    }//GEN-LAST:event_jBtnTotalCreditActionPerformed

    private void jBtnTotalDebitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTotalDebitActionPerformed

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
            StringBuilder query = new StringBuilder("SELECT empName, empID, partName, SUM(quantity) as totalQuantity FROM partentry WHERE 1=1");

            if (!empName.isEmpty()) {
                query.append(" AND empName = ? AND empID = ?");
            }
            if (formattedFromDate != null) {
                query.append(" AND entryDate >= ?");
            }
            if (formattedToDate != null) {
                query.append(" AND entryDate <= ?");
            }
            query.append(" GROUP BY empName, empID, partName");

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
                String partName = rs.getString("partName");
                int totalQuantity = rs.getInt("totalQuantity");

                // Add a new row to the table model
                model.addRow(new Object[]{resultEmpName, resultEmpID, partName, totalQuantity});
            }

            // Close connections
            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jBtnTotalDebitActionPerformed

    private void jBtnTotalStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTotalStockActionPerformed
        // TODO add your handling code here:
        
                                              

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

        // Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
        Statement stmt = conn.createStatement();

        // Create a temporary table to store intermediate results
        stmt.execute("CREATE TEMPORARY TABLE temp_stock (empName TEXT, empID TEXT, itemName TEXT, creditQuantity INTEGER, debitQuantity INTEGER)");

        // Insert total credits into the temporary table
        StringBuilder creditQuery = new StringBuilder("INSERT INTO temp_stock (empName, empID, itemName, creditQuantity, debitQuantity) ");
        creditQuery.append("SELECT empName, empID, itemName, SUM(quantity) as creditQuantity, 0 as debitQuantity FROM entry WHERE 1=1");
        if (!empName.isEmpty()) {
            creditQuery.append(" AND empName = ? AND empID = ?");
        }
        if (formattedFromDate != null) {
            creditQuery.append(" AND entryDate >= ?");
        }
        if (formattedToDate != null) {
            creditQuery.append(" AND entryDate <= ?");
        }
        creditQuery.append(" GROUP BY empName, empID, itemName");

        PreparedStatement creditPstmt = conn.prepareStatement(creditQuery.toString());
        int paramIndex = 1;
        if (!empName.isEmpty()) {
            creditPstmt.setString(paramIndex++, empName);
            creditPstmt.setString(paramIndex++, empID);
        }
        if (formattedFromDate != null) {
            creditPstmt.setString(paramIndex++, formattedFromDate);
        }
        if (formattedToDate != null) {
            creditPstmt.setString(paramIndex++, formattedToDate);
        }
        creditPstmt.executeUpdate();

        // Calculate total debits by reversing the final product and insert into the temporary table
        StringBuilder debitQuery = new StringBuilder("INSERT INTO temp_stock (empName, empID, itemName, creditQuantity, debitQuantity) ");
        debitQuery.append("SELECT empName, empID, part_items.itemName, 0 as creditQuantity, SUM(partentry.quantity * part_items.quantity) as debitQuantity ");
        debitQuery.append("FROM partentry ");
        debitQuery.append("JOIN part_items ON partentry.partName = part_items.partName ");
        debitQuery.append("WHERE 1=1");
        if (!empName.isEmpty()) {
            debitQuery.append(" AND empName = ? AND empID = ?");
        }
        if (formattedFromDate != null) {
            debitQuery.append(" AND entryDate >= ?");
        }
        if (formattedToDate != null) {
            debitQuery.append(" AND entryDate <= ?");
        }
        debitQuery.append(" GROUP BY empName, empID, part_items.itemName");

        PreparedStatement debitPstmt = conn.prepareStatement(debitQuery.toString());
        paramIndex = 1;
        if (!empName.isEmpty()) {
            debitPstmt.setString(paramIndex++, empName);
            debitPstmt.setString(paramIndex++, empID);
        }
        if (formattedFromDate != null) {
            debitPstmt.setString(paramIndex++, formattedFromDate);
        }
        if (formattedToDate != null) {
            debitPstmt.setString(paramIndex++, formattedToDate);
        }
        debitPstmt.executeUpdate();

        // Calculate remaining stock for each item
        String remainingStockQuery = "SELECT empName, empID, itemName, SUM(creditQuantity) - SUM(debitQuantity) as totalStock FROM temp_stock GROUP BY empName, empID, itemName";
        ResultSet rs = stmt.executeQuery(remainingStockQuery);

        // Get the table model from your JTable
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Clear the existing rows in the table
        model.setRowCount(0);

        // Populate the table with the remaining stock
        while (rs.next()) {
            String resultEmpName = rs.getString("empName");
            String resultEmpID = rs.getString("empID");
            String itemName = rs.getString("itemName");
            int totalStock = rs.getInt("totalStock");
            model.addRow(new Object[]{resultEmpName, resultEmpID, itemName, totalStock});
        }

        // Close connections
        rs.close();
        creditPstmt.close();
        debitPstmt.close();
        stmt.execute("DROP TABLE temp_stock");
        stmt.close();
        conn.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }






    }//GEN-LAST:event_jBtnTotalStockActionPerformed

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
            java.util.logging.Logger.getLogger(ViewEmployeeStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewEmployeeStock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JComboBox<String> empName;
    private javax.swing.JButton jBtnTotalCredit;
    private javax.swing.JButton jBtnTotalDebit;
    private javax.swing.JButton jBtnTotalStock;
    private javax.swing.JButton jButton2;
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

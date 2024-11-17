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


/**
 *
 * @author gaura
 */
public class EditEntry extends javax.swing.JFrame {
    private Map<String, String> empMap = new HashMap<>();

    /**
     * Creates new form ViewEntry
     */
    public EditEntry() {
        initComponents();
        empName.setSelectedIndex(-1);
         setExtendedState(this.MAXIMIZED_BOTH);
         setupKeyBindings();
         viewEntryOfThirtyDays();
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
                loadItems();
            }
        });
    }

    private void goBack() {
        // Implement the action to go back or close the frame
        // For example, dispose the current frame and show the previous one
        new AdminMenu().setVisible(true);
        this.dispose();
    }

public void viewEntryOfThirtyDays() {
    try (Connection conn = DatabaseConnection.getConnection()) {
        // Calculate the date one month ago from today
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.MONTH, -1);
        java.util.Date oneMonthAgo = cal.getTime();
        java.sql.Date sqlDate = new java.sql.Date(oneMonthAgo.getTime());

        // Prepare SQL query to select entries from the last month
        String sql = "SELECT * FROM entry WHERE entryDate >= ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1, sqlDate);

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
        jButton1 = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        empEnt = new javax.swing.JLabel();
        empName = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(empName);
        jButtonCusotmEntry = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonChangeQuantity = new javax.swing.JButton();
        jButtonDeleteEntry = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldQuantity = new javax.swing.JTextField();
        jBtnTotalWork = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("View All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
        empEnt.setText("View And Edit Worker Entry");

        empName.setEditable(true);
        empName.setToolTipText("");

        jButtonCusotmEntry.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonCusotmEntry.setText("View Custom");
        jButtonCusotmEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCusotmEntryActionPerformed(evt);
            }
        });

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

        jButtonChangeQuantity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonChangeQuantity.setText("Change quantity");
        jButtonChangeQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeQuantityActionPerformed(evt);
            }
        });

        jButtonDeleteEntry.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonDeleteEntry.setText("Delete entry");
        jButtonDeleteEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteEntryActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Quantity");

        jTextFieldQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQuantityActionPerformed(evt);
            }
        });

        jBtnTotalWork.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnTotalWork.setText("Total work");
        jBtnTotalWork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTotalWorkActionPerformed(evt);
            }
        });

        jButtonExit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
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
                            .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonCusotmEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnTotalWork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonPrint, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonReset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonChangeQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonDeleteEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empEnt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jButtonReset))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCusotmEntry)
                            .addComponent(jButtonPrint)
                            .addComponent(jButtonDeleteEntry))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonChangeQuantity)
                            .addComponent(jBtnTotalWork)
                            .addComponent(jButtonExit))
                        .addGap(36, 36, 36))
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
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooserTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldQuantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  try (Connection conn = DatabaseConnection.getConnection()) {
        // Calculate the date one month ago from today
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.MONTH, -1);
        java.util.Date oneMonthAgo = cal.getTime();
        java.sql.Date sqlDate = new java.sql.Date(oneMonthAgo.getTime());

        // Prepare SQL query to select entries from the last month
        String sql = "SELECT * FROM entry WHERE entryDate >= ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1, sqlDate);

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
    } catch (SQLException | ClassNotFoundException e) {
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
    private void jButtonCusotmEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCusotmEntryActionPerformed

        try (Connection conn = DatabaseConnection.getConnection()) {
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

    // Convert java.util.Date to java.sql.Date
    java.sql.Date sqlFromDate = (fromDate != null) ? new java.sql.Date(fromDate.getTime()) : null;
    java.sql.Date sqlToDate = (toDate != null) ? new java.sql.Date(toDate.getTime()) : null;

    // Prepare SQL query dynamically
    StringBuilder query = new StringBuilder("SELECT * FROM entry WHERE 1=1");

    if (!empName.isEmpty()) {
        query.append(" AND empName = ? AND empID = ?");
    }
    if (sqlFromDate != null) {
        query.append(" AND entryDate >= ?");
    }
    if (sqlToDate != null) {
        query.append(" AND entryDate <= ?");
    }

    // Prepare statement
    PreparedStatement pstmt = conn.prepareStatement(query.toString());

    // Set parameters dynamically
    int paramIndex = 1;

    if (!empName.isEmpty()) {
        pstmt.setString(paramIndex++, empName);
        pstmt.setInt(paramIndex++, Integer.parseInt(empID));
    }
    if (sqlFromDate != null) {
        pstmt.setDate(paramIndex++, sqlFromDate);
    }
    if (sqlToDate != null) {
        pstmt.setDate(paramIndex++, sqlToDate);
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
} catch (SQLException | ClassNotFoundException e) {
    JOptionPane.showMessageDialog(null, e.getMessage());
}

      // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCusotmEntryActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        // TODO add your handling code here:
       jDateChooserFrom.setDate(null);
        jDateChooserTo.setDate(null);
        empName.setSelectedIndex(-1);
        jTextFieldQuantity.setText("");
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
    jTextFieldQuantity.setText(quantityValue);
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
        jDateChooserFrom.setDate(sdf.parse(dateValue));  // Set date field
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Date Parse Error: " + e.getMessage());
    }
    
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonDeleteEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteEntryActionPerformed
 // TODO add your handling code here:
int row = jTable1.getSelectedRow();
if (row >= 0) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    String empNameValue = model.getValueAt(row, 0).toString();
    String itemNameValue = model.getValueAt(row, 2).toString(); // Assuming itemName is in column 2
    String entryDate = model.getValueAt(row, 4).toString();
    
    // Show confirmation dialog before deletion
    int response = JOptionPane.showConfirmDialog(null, 
            "Do you want to delete the entry for employee: " + empNameValue + 
            " on " + entryDate + " for item " + itemNameValue + "?", 
            "Confirm Deletion", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
    
    if (response == JOptionPane.YES_OPTION) {
        String sql = "DELETE FROM entry WHERE empName = ? AND itemName = ? AND entryDate = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, empNameValue);
            pstmt.setString(2, itemNameValue);
            pstmt.setDate(3, java.sql.Date.valueOf(entryDate)); // Convert string to java.sql.Date
            pstmt.executeUpdate();
            
            // Remove row from the table
            model.removeRow(row);
            JOptionPane.showMessageDialog(null, "Entry deleted successfully.");
            
            // Refresh the table to show updated data
            viewEntryOfThirtyDays();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
} else {
    JOptionPane.showMessageDialog(null, "Please select an entry to delete.");
}

    }//GEN-LAST:event_jButtonDeleteEntryActionPerformed

    private void jButtonChangeQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeQuantityActionPerformed
 int row = jTable1.getSelectedRow();
if (row >= 0) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    String empNameValue = model.getValueAt(row, 0).toString();
    String itemNameValue = model.getValueAt(row, 2).toString(); // Assuming itemName is in column 2
    String entryDate = model.getValueAt(row, 4).toString();
    String oldQuantity = model.getValueAt(row, 3).toString();
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
            String sql = "UPDATE entry SET quantity = ? WHERE empName = ? AND itemName = ? AND entryDate = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(newQuantity)); // Assuming quantity is an integer
            pstmt.setString(2, empNameValue);
            pstmt.setString(3, itemNameValue);
            pstmt.setDate(4, java.sql.Date.valueOf(entryDate)); // Convert string to java.sql.Date
            pstmt.executeUpdate();
            
            // Update table display
            model.setValueAt(newQuantity, row, 3);
            JOptionPane.showMessageDialog(null, "Quantity updated successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            Logger.getLogger(EditEntry.class.getName()).log(Level.SEVERE, null, e);
        } 
    } 
} else {
    JOptionPane.showMessageDialog(null, "Please select an entry to update.");
}
viewEntryOfThirtyDays();

            // TODO add your handling code here:
    }//GEN-LAST:event_jButtonChangeQuantityActionPerformed

    private void jBtnTotalWorkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTotalWorkActionPerformed
                                          
    try (Connection conn = DatabaseConnection.getConnection()) {
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

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlFromDate = (fromDate != null) ? new java.sql.Date(fromDate.getTime()) : null;
        java.sql.Date sqlToDate = (toDate != null) ? new java.sql.Date(toDate.getTime()) : null;

        // Prepare SQL query dynamically
        StringBuilder query = new StringBuilder("SELECT empName, empID, itemName, SUM(quantity) as totalQuantity FROM entry WHERE 1=1");

        if (!empName.isEmpty()) {
            query.append(" AND empName = ? AND empID = ?");
        }
        if (sqlFromDate != null) {
            query.append(" AND entryDate >= ?");
        }
        if (sqlToDate != null) {
            query.append(" AND entryDate <= ?");
        }
        query.append(" GROUP BY empName, empID, itemName");

        // Prepare statement
        PreparedStatement pstmt = conn.prepareStatement(query.toString());

        // Set parameters dynamically
        int paramIndex = 1;

        if (!empName.isEmpty()) {
            pstmt.setString(paramIndex++, empName);
            pstmt.setInt(paramIndex++, Integer.parseInt(empID));
        }
        if (sqlFromDate != null) {
            pstmt.setDate(paramIndex++, sqlFromDate);
        }
        if (sqlToDate != null) {
            pstmt.setDate(paramIndex++, sqlToDate);
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

    } catch (SQLException | ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }



    }//GEN-LAST:event_jBtnTotalWorkActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        // TODO add your handling code here:
        frame = new JFrame ("Exit");
        if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit","sqlite Connector",
            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
    {

        System.exit(0); }
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jTextFieldQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldQuantityActionPerformed

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
            java.util.logging.Logger.getLogger(EditEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditEntry().setVisible(true);
            }
        });
         SwingUtilities.invokeLater(() -> new AdminMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JComboBox<String> empName;
    private javax.swing.JButton jBtnTotalWork;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonChangeQuantity;
    private javax.swing.JButton jButtonCusotmEntry;
    private javax.swing.JButton jButtonDeleteEntry;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonReset;
    private com.toedter.calendar.JDateChooser jDateChooserFrom;
    private com.toedter.calendar.JDateChooser jDateChooserTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldQuantity;
    // End of variables declaration//GEN-END:variables
}

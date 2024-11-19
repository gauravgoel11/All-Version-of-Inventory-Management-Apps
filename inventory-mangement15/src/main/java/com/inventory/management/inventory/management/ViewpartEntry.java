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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;



/**
 *
 * @author gaura
 */
public class ViewpartEntry extends javax.swing.JFrame {
    private Map<String, String> partMap = new HashMap<>();

    /**
     * Creates new form ViewEntry
     */
    public ViewpartEntry() {
        initComponents();
        
         setExtendedState(this.MAXIMIZED_BOTH);
          setupKeyBindings();
          oneMonthEntryView();
          loadItems();
          jcombopartname.setSelectedIndex(-1);
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
 public void oneMonthEntryView() {
    try {
        // Connect to the database using PostgreSQL
        Connection conn = DatabaseConnection.getConnection();

        // Calculate the date one month ago from today
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.MONTH, -1);
        java.util.Date oneMonthAgo = cal.getTime();
        java.sql.Date sqlDate = new java.sql.Date(oneMonthAgo.getTime());

        // Prepare SQL query to select entries from the last month
        String sql = "SELECT partName, quantity, entryDate FROM partentry WHERE entryDate >= ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1, sqlDate);

        ResultSet rs = pstmt.executeQuery();

        // Get table model
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        // Clear existing data
        model.setRowCount(0);

        // Add rows to the model
        while (rs.next()) {
            String partName = rs.getString("partName");
            int quantity = rs.getInt("quantity");
            java.sql.Date entryDate = rs.getDate("entryDate");
            model.addRow(new Object[]{partName, quantity, entryDate.toString()});
        }

        // Close connections
        rs.close();
        pstmt.close();
        conn.close();
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
  private void loadItems() { 
 try (Connection con = DatabaseConnection.getConnection()) {
        Statement st = con.createStatement();
        
        // Load items
        ResultSet rs = st.executeQuery("SELECT DISTINCT partName FROM part_items");
        while (rs.next()) {
            String partName = rs.getString("partName");
            jcombopartname.addItem(partName);
            partMap.put(partName, partName);        
        }

        // Load employee names with IDs
        
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Error is " + e.getMessage());
    }
    }
         private void setupComboBoxListeners() {
        jcombopartname.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedPartName = (String) jcombopartname.getSelectedItem();
            if (selectedPartName != null && partMap.containsKey(selectedPartName)) {
                jcombopartname.setSelectedItem(partMap.get(selectedPartName));
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
        jButtonExit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        empEnt = new javax.swing.JLabel();
        jcombopartname = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(jcombopartname);
        jButtonCusotmEntry = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jBtnTotalWork = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldQuantity = new javax.swing.JTextField();
        jButtonDeleteEntry = new javax.swing.JButton();
        jButtonChangeQuantity = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "part", "Quantity", "Date"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("View All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonPrint.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonPrint.setText("Print");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

        jButtonExit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Product name");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("From");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("To");

        empEnt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        empEnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empEnt.setText("View And Edit Production Entry");

        jcombopartname.setEditable(true);
        jcombopartname.setToolTipText("");

        jButtonCusotmEntry.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCusotmEntry.setText("View Custom");
        jButtonCusotmEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCusotmEntryActionPerformed(evt);
            }
        });

        jButtonReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonReset.setText("Erase");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jBtnTotalWork.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnTotalWork.setText("Total work");
        jBtnTotalWork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTotalWorkActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Quantity");

        jButtonDeleteEntry.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDeleteEntry.setText("Delete entry");
        jButtonDeleteEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteEntryActionPerformed(evt);
            }
        });

        jButtonChangeQuantity.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonChangeQuantity.setText("Change quantity");
        jButtonChangeQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeQuantityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(empEnt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcombopartname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonCusotmEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnTotalWork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonReset, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(jButtonPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonDeleteEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonChangeQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empEnt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(jButtonReset)
                    .addComponent(jButton2))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jcombopartname, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateChooserFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(jDateChooserTo, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(jTextFieldQuantity))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCusotmEntry)
                            .addComponent(jButtonPrint)
                            .addComponent(jButtonDeleteEntry))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnTotalWork)
                            .addComponent(jButtonExit)
                            .addComponent(jButtonChangeQuantity))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                                         
                                             
    try (Connection conn = DatabaseConnection.getConnection()) {
        String sql = "SELECT partName, quantity, entryDate FROM partentry";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            String partName = rs.getString("partName");
            BigDecimal quantity = rs.getBigDecimal("quantity"); // Use BigDecimal for quantity
            java.sql.Date entryDate = rs.getDate("entryDate");  // Assuming entryDate is stored as a Date in the database

            // Convert java.sql.Date to String if necessary
            String displayDate = (entryDate != null) ? entryDate.toString() : "No Date";
            model.addRow(new Object[]{partName, quantity, displayDate});
        }

        rs.close();
        pstmt.close();
    } catch (ClassNotFoundException | SQLException e) {
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
                                                  
    try (Connection conn = DatabaseConnection.getConnection()) {
        String selectedPart = (jcombopartname.getSelectedIndex() != -1) ? jcombopartname.getSelectedItem().toString() : "";
        java.util.Date fromDate = jDateChooserFrom.getDate();
        java.util.Date toDate = jDateChooserTo.getDate();

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlFromDate = (fromDate != null) ? new java.sql.Date(fromDate.getTime()) : null;
        java.sql.Date sqlToDate = (toDate != null) ? new java.sql.Date(toDate.getTime()) : null;

        StringBuilder query = new StringBuilder("SELECT partName, quantity, entryDate FROM partentry WHERE 1=1");
        if (!selectedPart.isEmpty()) {
            query.append(" AND partName = ?");
        }
        if (sqlFromDate != null) {
            query.append(" AND entryDate >= ?");
        }
        if (sqlToDate != null) {
            query.append(" AND entryDate <= ?");
        }

        PreparedStatement pstmt = conn.prepareStatement(query.toString());
        int paramIndex = 1;
        if (!selectedPart.isEmpty()) {
            pstmt.setString(paramIndex++, selectedPart);
        }
        if (sqlFromDate != null) {
            pstmt.setDate(paramIndex++, sqlFromDate);
        }
        if (sqlToDate != null) {
            pstmt.setDate(paramIndex++, sqlToDate);
        }

        ResultSet rs = pstmt.executeQuery();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            String partName = rs.getString("partName");
            BigDecimal quantity = rs.getBigDecimal("quantity"); // Use BigDecimal for numeric values
            java.sql.Date entryDate = rs.getDate("entryDate");
            model.addRow(new Object[]{partName, quantity, entryDate});
        }

        rs.close();
        pstmt.close();
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }


    }//GEN-LAST:event_jButtonCusotmEntryActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        // TODO add your handling code here:
       jDateChooserFrom.setDate(null);
        jDateChooserTo.setDate(null);
        jcombopartname.setSelectedIndex(-1);
        jTextFieldQuantity.setText("");
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jBtnTotalWorkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTotalWorkActionPerformed
                                               
    try (Connection conn = DatabaseConnection.getConnection()) {
        String selectedPart = (jcombopartname.getSelectedIndex() != -1) ? jcombopartname.getSelectedItem().toString() : "";
        java.util.Date fromDate = jDateChooserFrom.getDate();
        java.util.Date toDate = jDateChooserTo.getDate();

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlFromDate = (fromDate != null) ? new java.sql.Date(fromDate.getTime()) : null;
        java.sql.Date sqlToDate = (toDate != null) ? new java.sql.Date(toDate.getTime()) : null;

        StringBuilder query = new StringBuilder("SELECT partName, SUM(quantity) as totalQuantity FROM partentry WHERE 1=1");
        if (!selectedPart.isEmpty()) {
            query.append(" AND partName = ?");
        }
        if (sqlFromDate != null) {
            query.append(" AND entryDate >= ?");
        }
        if (sqlToDate != null) {
            query.append(" AND entryDate <= ?");
        }
        query.append(" GROUP BY partName");

        PreparedStatement pstmt = conn.prepareStatement(query.toString());
        int paramIndex = 1;
        if (!selectedPart.isEmpty()) {
            pstmt.setString(paramIndex++, selectedPart);
        }
        if (sqlFromDate != null) {
            pstmt.setDate(paramIndex++, sqlFromDate);
        }
        if (sqlToDate != null) {
            pstmt.setDate(paramIndex++, sqlToDate);
        }

        ResultSet rs = pstmt.executeQuery();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            String partName = rs.getString("partName");
            BigDecimal totalQuantity = rs.getBigDecimal("totalQuantity"); // Use BigDecimal for numeric values
            model.addRow(new Object[]{partName, totalQuantity});
        }

        rs.close();
        pstmt.close();
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }




    }//GEN-LAST:event_jBtnTotalWorkActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      new AdminMenu().setVisible(true);
        this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonDeleteEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteEntryActionPerformed
                                                     
                                                     
    int row = jTable1.getSelectedRow();
    if (row >= 0) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String partNameValue = model.getValueAt(row, 0).toString(); // Assuming partName is in column 0
        BigDecimal quantityValue = new BigDecimal(model.getValueAt(row, 1).toString()); // Assuming quantity is in column 1
        String entryDate = model.getValueAt(row, 2).toString(); // Assuming entryDate is in column 2

        int response = JOptionPane.showConfirmDialog(null,
            "Do you want to delete the entry for part: " + partNameValue +
            " with quantity: " + quantityValue + " on " + entryDate + "?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "DELETE FROM partentry WHERE partName = ? AND quantity = ? AND entryDate = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, partNameValue);
                pstmt.setBigDecimal(2, quantityValue); // Use BigDecimal for quantity
                pstmt.setDate(3, java.sql.Date.valueOf(entryDate)); // Convert string to java.sql.Date

                int deletedRows = pstmt.executeUpdate();
                if (deletedRows > 0) {
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(null, "Entry deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "No rows deleted, check your data.");
                }
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please select an entry to delete.");
    }
    oneMonthEntryView(); // Refresh the view to reflect changes



    }//GEN-LAST:event_jButtonDeleteEntryActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    int row = jTable1.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    // Adjust the column indices to match the new table structure
    String empNameValue = model.getValueAt(row, 0).toString(); // Assuming empName is in column 0
    String quantityValue = model.getValueAt(row, 1).toString(); // Assuming quantity is in column 1
    String dateValue = model.getValueAt(row, 2).toString(); // Assuming date is in column 2

    jcombopartname.setSelectedItem(empNameValue);  // Assuming empName is a combo box
    jTextFieldQuantity.setText(quantityValue);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
        jDateChooserFrom.setDate(sdf.parse(dateValue));  // Set date field
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Date Parse Error: " + e.getMessage());
    }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonChangeQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeQuantityActionPerformed
                                                      
    int row = jTable1.getSelectedRow();
    if (row >= 0) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String partNameValue = model.getValueAt(row, 0).toString();
        java.util.Date entryDateUtil = (java.util.Date) model.getValueAt(row, 2);
        java.sql.Date entryDate = new java.sql.Date(entryDateUtil.getTime());
        String oldQuantity = model.getValueAt(row, 1).toString();
        String newQuantity = jTextFieldQuantity.getText();

        int response = JOptionPane.showConfirmDialog(null,
            "Do you want to change the quantity for part " + partNameValue +
            " from " + oldQuantity + " to " + newQuantity + "?",
            "Confirm Quantity Update",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "UPDATE partentry SET quantity = ? WHERE partName = ? AND entryDate = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setBigDecimal(1, new BigDecimal(newQuantity)); // Use BigDecimal for numeric type
                pstmt.setString(2, partNameValue);
                pstmt.setDate(3, entryDate);

                int updatedRows = pstmt.executeUpdate();
                if (updatedRows > 0) {
                    model.setValueAt(newQuantity, row, 1);
                    JOptionPane.showMessageDialog(null, "Quantity updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "No rows updated, check your data.");
                }
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please select an entry to update.");
    }
    oneMonthEntryView();


    }//GEN-LAST:event_jButtonChangeQuantityActionPerformed

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
            java.util.logging.Logger.getLogger(ViewpartEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewpartEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewpartEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewpartEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewpartEntry().setVisible(true);
            }
        });
        SwingUtilities.invokeLater(() -> new AdminMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
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
    private javax.swing.JComboBox<String> jcombopartname;
    // End of variables declaration//GEN-END:variables
}

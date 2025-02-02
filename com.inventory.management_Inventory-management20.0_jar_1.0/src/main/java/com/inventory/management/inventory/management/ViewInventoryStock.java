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
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;


/**
 *
 * @author gaura
 */
public class ViewInventoryStock extends javax.swing.JFrame {
    private Map<String, String> itemMap = new HashMap<>();

    /**
     * Creates new form ViewEntry
     */
    public ViewInventoryStock() {
        initComponents();
        
         setExtendedState(this.MAXIMIZED_BOTH);
          setupKeyBindings();
          totalStockLeft();
          loadItems();
          itemName.setSelectedIndex(-1);
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
 private void totalStockLeft() {
    try (Connection conn = DatabaseConnection.getConnection()) {
        String selectedItem = (itemName.getSelectedIndex() != -1) ? itemName.getSelectedItem().toString() : "";
        java.util.Date fromDate = jDateChooserFrom.getDate();
        java.util.Date toDate = jDateChooserTo.getDate();

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlFromDate = (fromDate != null) ? new java.sql.Date(fromDate.getTime()) : null;
        java.sql.Date sqlToDate = (toDate != null) ? new java.sql.Date(toDate.getTime()) : null;

        StringBuilder query = new StringBuilder(
            "SELECT itemName, SUM(creditQuantity) - SUM(debitQuantity) as totalStock " +
            "FROM ( " +
            "  SELECT itemName, SUM(quantity) as creditQuantity, 0 as debitQuantity " +
            "  FROM adminentry " +
            "  WHERE 1=1 "
        );

        // Applying filters to the 'adminentry' table
        if (!selectedItem.isEmpty()) {
            query.append(" AND itemName = ?");
        }
        if (sqlFromDate != null) {
            query.append(" AND entryDate >= ?");
        }
        if (sqlToDate != null) {
            query.append(" AND entryDate <= ?");
        }

        query.append(" GROUP BY itemName ");
        query.append(" UNION ");
        query.append(" SELECT itemName, 0 as creditQuantity, SUM(quantity) as debitQuantity ");
        query.append(" FROM entry ");
        query.append(" WHERE 1=1 ");

        // Applying filters to the 'entry' table
        if (!selectedItem.isEmpty()) {
            query.append(" AND itemName = ?");
        }
        if (sqlFromDate != null) {
            query.append(" AND entryDate >= ?");
        }
        if (sqlToDate != null) {
            query.append(" AND entryDate <= ?");
        }

        query.append(" GROUP BY itemName ");
        query.append(") GROUP BY itemName");

        PreparedStatement pstmt = conn.prepareStatement(query.toString());

        int paramIndex = 1;
        // Setting parameters for 'adminentry' table filters
        if (!selectedItem.isEmpty()) {
            pstmt.setString(paramIndex++, selectedItem);
        }
        if (sqlFromDate != null) {
            pstmt.setDate(paramIndex++, sqlFromDate);
        }
        if (sqlToDate != null) {
            pstmt.setDate(paramIndex++, sqlToDate);
        }
        // Setting parameters for 'entry' table filters
        if (!selectedItem.isEmpty()) {
            pstmt.setString(paramIndex++, selectedItem);
        }
        if (sqlFromDate != null) {
            pstmt.setDate(paramIndex++, sqlFromDate);
        }
        if (sqlToDate != null) {
            pstmt.setDate(paramIndex++, sqlToDate);
        }

        ResultSet rs = pstmt.executeQuery();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing data

        while (rs.next()) {
            String itemName = rs.getString("itemName");
            int totalStock = rs.getInt("totalStock");
            model.addRow(new Object[]{itemName, totalStock});
        }

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
        ResultSet rs = st.executeQuery("SELECT * FROM items");
        while (rs.next()) {
            String itemNameStr = rs.getString("itemName");
            itemName.addItem(itemNameStr);
            itemMap.put(itemNameStr, itemNameStr); // Assuming you want to map itemName to itself
        }
        
        rs.close();
        st.close();
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, "Error loading items: " + e.getMessage());
    }
    }
         private void setupComboBoxListeners() {
      itemName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) itemName.getSelectedItem();
                if (selectedName != null && itemMap.containsKey(selectedName)) {
                    itemName.setSelectedItem(itemMap.get(selectedName));
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
        jBtnCredit = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        empEnt = new javax.swing.JLabel();
        jButtonReset = new javax.swing.JButton();
        jBtnTotalStock = new javax.swing.JButton();
        itemName = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(itemName);
        jButton1 = new javax.swing.JButton();
        jBtnDebit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Category", "Quantity"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jBtnCredit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jBtnCredit.setText("Credit");
        jBtnCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCreditActionPerformed(evt);
            }
        });

        jButtonPrint.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonPrint.setText("Print");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

        jButtonExit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("ItemName");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("From");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("To");

        empEnt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        empEnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empEnt.setText("View Godown Stock");

        jButtonReset.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonReset.setText("Erase");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jBtnTotalStock.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jBtnTotalStock.setText("Total Stock");
        jBtnTotalStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTotalStockActionPerformed(evt);
            }
        });

        itemName.setEditable(true);
        itemName.setToolTipText("");
        itemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNameActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jBtnDebit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jBtnDebit.setText("Debit");
        jBtnDebit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDebitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(empEnt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(164, 164, 164)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(77, 77, 77)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(itemName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jBtnCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBtnTotalStock)
                                    .addComponent(jBtnDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jButtonReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empEnt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButtonPrint)
                    .addComponent(jBtnCredit)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonReset)
                            .addComponent(jBtnDebit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonExit)
                            .addComponent(jBtnTotalStock)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(itemName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 1070, 640));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCreditActionPerformed
                                           
      try (Connection conn = DatabaseConnection.getConnection()) {
        String selectedItem = (itemName.getSelectedIndex() != -1) ? itemName.getSelectedItem().toString() : "";
        java.util.Date fromDate = jDateChooserFrom.getDate();
        java.util.Date toDate = jDateChooserTo.getDate();

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlFromDate = (fromDate != null) ? new java.sql.Date(fromDate.getTime()) : null;
        java.sql.Date sqlToDate = (toDate != null) ? new java.sql.Date(toDate.getTime()) : null;

        StringBuilder query = new StringBuilder("SELECT itemName, SUM(quantity) as totalQuantity FROM adminentry WHERE 1=1");
        if (!selectedItem.isEmpty()) {
            query.append(" AND itemName = ?");
        }
        if (sqlFromDate != null) {
            query.append(" AND entryDate >= ?");
        }
        if (sqlToDate != null) {
            query.append(" AND entryDate <= ?");
        }
        query.append(" GROUP BY itemName");

        PreparedStatement pstmt = conn.prepareStatement(query.toString());
        int paramIndex = 1;
        if (!selectedItem.isEmpty()) {
            pstmt.setString(paramIndex++, selectedItem);
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
            String itemName = rs.getString("itemName");
            BigDecimal totalQuantity = rs.getBigDecimal("totalQuantity"); // Use BigDecimal for numeric types
            model.addRow(new Object[]{itemName, totalQuantity});
        }

        rs.close();
        pstmt.close();
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    }//GEN-LAST:event_jBtnCreditActionPerformed

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

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        // TODO add your handling code here:
       jDateChooserFrom.setDate(null);
        jDateChooserTo.setDate(null);
        itemName.setSelectedIndex(-1);
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jBtnTotalStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTotalStockActionPerformed
                                                  
    try (Connection conn = DatabaseConnection.getConnection()) {
        String selectedItem = (itemName.getSelectedIndex() != -1) ? itemName.getSelectedItem().toString() : "";
        java.util.Date fromDate = jDateChooserFrom.getDate();
        java.util.Date toDate = jDateChooserTo.getDate();

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlFromDate = (fromDate != null) ? new java.sql.Date(fromDate.getTime()) : null;
        java.sql.Date sqlToDate = (toDate != null) ? new java.sql.Date(toDate.getTime()) : null;

        StringBuilder query = new StringBuilder(
            "SELECT itemName, SUM(creditQuantity) - SUM(debitQuantity) as totalStock " +
            "FROM ( " +
            "  SELECT itemName, SUM(quantity) as creditQuantity, 0 as debitQuantity " +
            "  FROM adminentry " +
            "  WHERE 1=1 "
        );

        // Applying filters to the 'adminentry' table
        if (!selectedItem.isEmpty()) {
            query.append(" AND itemName = ?");
        }
        if (sqlFromDate != null) {
            query.append(" AND entryDate >= ?");
        }
        if (sqlToDate != null) {
            query.append(" AND entryDate <= ?");
        }

        query.append(" GROUP BY itemName ");
        query.append(" UNION ");
        query.append(" SELECT itemName, 0 as creditQuantity, SUM(quantity) as debitQuantity ");
        query.append(" FROM entry ");
        query.append(" WHERE 1=1 ");

        // Applying filters to the 'entry' table
        if (!selectedItem.isEmpty()) {
            query.append(" AND itemName = ?");
        }
        if (sqlFromDate != null) {
            query.append(" AND entryDate >= ?");
        }
        if (sqlToDate != null) {
            query.append(" AND entryDate <= ?");
        }

        query.append(" GROUP BY itemName ");
        query.append(") GROUP BY itemName");

        PreparedStatement pstmt = conn.prepareStatement(query.toString());

        int paramIndex = 1;
        // Setting parameters for 'adminentry' table filters
        if (!selectedItem.isEmpty()) {
            pstmt.setString(paramIndex++, selectedItem);
        }
        if (sqlFromDate != null) {
            pstmt.setDate(paramIndex++, sqlFromDate);
        }
        if (sqlToDate != null) {
            pstmt.setDate(paramIndex++, sqlToDate);
        }
        // Setting parameters for 'entry' table filters
        if (!selectedItem.isEmpty()) {
            pstmt.setString(paramIndex++, selectedItem);
        }
        if (sqlFromDate != null) {
            pstmt.setDate(paramIndex++, sqlFromDate);
        }
        if (sqlToDate != null) {
            pstmt.setDate(paramIndex++, sqlToDate);
        }

        ResultSet rs = pstmt.executeQuery();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing data

        while (rs.next()) {
            String itemName = rs.getString("itemName");
            BigDecimal totalStock = rs.getBigDecimal("totalStock"); // Use BigDecimal for NUMERIC type
            model.addRow(new Object[]{itemName, totalStock});
        }

        rs.close();
        pstmt.close();
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
                        
    }//GEN-LAST:event_jBtnTotalStockActionPerformed

    private void itemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      new AdminMenu().setVisible(true);
        this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBtnDebitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDebitActionPerformed
         try (Connection conn = DatabaseConnection.getConnection()) {
        String selectedItem = (itemName.getSelectedIndex() != -1) ? itemName.getSelectedItem().toString() : "";
        java.util.Date fromDate = jDateChooserFrom.getDate();
        java.util.Date toDate = jDateChooserTo.getDate();

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlFromDate = (fromDate != null) ? new java.sql.Date(fromDate.getTime()) : null;
        java.sql.Date sqlToDate = (toDate != null) ? new java.sql.Date(toDate.getTime()) : null;

        StringBuilder query = new StringBuilder("SELECT itemName, SUM(quantity) as totalQuantity FROM entry WHERE 1=1");
        if (!selectedItem.isEmpty()) {
            query.append(" AND itemName = ?");
        }
        if (sqlFromDate != null) {
            query.append(" AND entryDate >= ?");
        }
        if (sqlToDate != null) {
            query.append(" AND entryDate <= ?");
        }
        query.append(" GROUP BY itemName");

        PreparedStatement pstmt = conn.prepareStatement(query.toString());
        int paramIndex = 1;
        if (!selectedItem.isEmpty()) {
            pstmt.setString(paramIndex++, selectedItem);
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
            String itemName = rs.getString("itemName");
            BigDecimal totalQuantity = rs.getBigDecimal("totalQuantity"); // Use BigDecimal
            model.addRow(new Object[]{itemName, totalQuantity});
        }

        rs.close();
        pstmt.close();
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnDebitActionPerformed

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
            java.util.logging.Logger.getLogger(ViewInventoryStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewInventoryStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewInventoryStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewInventoryStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewInventoryStock().setVisible(true);
            }
        });
        SwingUtilities.invokeLater(() -> new AdminMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JComboBox<String> itemName;
    private javax.swing.JButton jBtnCredit;
    private javax.swing.JButton jBtnDebit;
    private javax.swing.JButton jBtnTotalStock;
    private javax.swing.JButton jButton1;
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

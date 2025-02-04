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
public class ViewAdminEntry extends javax.swing.JFrame {

    /**
     * Creates new form ViewEntry
     */
    public ViewAdminEntry() {
        initComponents();
        itemName.setSelectedIndex(-1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        empEnt = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        itemName = new javax.swing.JComboBox<>();
        try{
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:inven.db");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from items");
            while(rs.next()){
                itemName.addItem(rs.getString("itemName"));
                System.out.println(rs.getString("itemName"));
            }
            con.close();
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Error is "+e.getMessage());
        }

        AutoCompleteDecorator.decorate(itemName);
        jLabel4 = new javax.swing.JLabel();
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtonCusotmEntry = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jBtnTotalCredit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldQuantity = new javax.swing.JTextField();
        jButtonDeleteEntry = new javax.swing.JButton();
        jButtonChangeQuantity = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        empEnt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        empEnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empEnt.setText("View And Edit Admin Entry");
        getContentPane().add(empEnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 970, 70));

        jLabel2.setText("ItemName");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 100, -1));

        itemName.setEditable(true);
        itemName.setToolTipText("");
        itemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNameActionPerformed(evt);
            }
        });
        getContentPane().add(itemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 200, 40));

        jLabel4.setText("From");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 100, 20));
        getContentPane().add(jDateChooserFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 160, 40));

        jLabel5.setText("To");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 100, 20));
        getContentPane().add(jDateChooserTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 160, 40));

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 110, 130, -1));

        jButtonPrint.setText("Print");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 140, 100, -1));

        jButton1.setText("All Entry");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, 110, -1));

        jButtonCusotmEntry.setText("Custom entry");
        jButtonCusotmEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCusotmEntryActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCusotmEntry, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, 110, -1));

        jButtonReset.setText("Erase");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 100, -1));

        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 170, 100, -1));

        jBtnTotalCredit.setText("Total Credit");
        jBtnTotalCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTotalCreditActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnTotalCredit, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, 110, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Category", "Quantity", "Date"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1030, 400));

        jLabel1.setText("Quantity");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 70, 30));
        getContentPane().add(jTextFieldQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 100, 40));

        jButtonDeleteEntry.setText("Delete entry");
        jButtonDeleteEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteEntryActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonDeleteEntry, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 140, 130, -1));

        jButtonChangeQuantity.setText("Change quantity");
        jButtonChangeQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeQuantityActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonChangeQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 170, 130, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
            String sql = "SELECT * FROM adminentry";
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
        String selecteditem = (itemName.getSelectedIndex() != -1) ? itemName.getSelectedItem().toString() : "";
        java.util.Date fromDate = jDateChooserFrom.getDate();
        java.util.Date toDate = jDateChooserTo.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedFromDate = (fromDate != null) ? formatter.format(fromDate) : null;
        String formattedToDate = (toDate != null) ? formatter.format(toDate) : null;
        StringBuilder query = new StringBuilder("SELECT * FROM adminentry WHERE 1=1");
        if (!selecteditem.isEmpty()) {
            query.append(" AND itemName = ?");
        }
        if (formattedFromDate != null) {
            query.append(" AND entryDate >= ?");
        }
        if (formattedToDate != null) {
            query.append(" AND entryDate <= ?");
        }
        Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
        PreparedStatement pstmt = conn.prepareStatement(query.toString());
        int paramIndex = 1;
        if (!selecteditem.isEmpty()) {
            pstmt.setString(paramIndex++, selecteditem);
        }
        if (formattedFromDate != null) {
            pstmt.setString(paramIndex++, formattedFromDate);
        }
        if (formattedToDate != null) {
            pstmt.setString(paramIndex++, formattedToDate);
        }
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


      // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCusotmEntryActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        // TODO add your handling code here:
       jDateChooserFrom.setDate(null);
        jDateChooserTo.setDate(null);
        itemName.setSelectedIndex(-1);
        jTextFieldQuantity.setText("");
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jBtnTotalCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTotalCreditActionPerformed
                                               
   try {
        String selecteditem = (itemName.getSelectedIndex() != -1) ? itemName.getSelectedItem().toString() : "";
        java.util.Date fromDate = jDateChooserFrom.getDate();
        java.util.Date toDate = jDateChooserTo.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedFromDate = (fromDate != null) ? formatter.format(fromDate) : null;
        String formattedToDate = (toDate != null) ? formatter.format(toDate) : null;
        StringBuilder query = new StringBuilder("SELECT itemName, SUM(quantity) as totalQuantity FROM adminentry WHERE 1=1");
        if (!selecteditem.isEmpty()) {
            query.append(" AND itemName = ?");
        }
        if (formattedFromDate != null) {
            query.append(" AND entryDate >= ?");
        }
        if (formattedToDate != null) {
            query.append(" AND entryDate <= ?");
        }
        query.append(" GROUP BY itemName");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
        PreparedStatement pstmt = conn.prepareStatement(query.toString());
        int paramIndex = 1;
        if (!selecteditem.isEmpty()) {
            pstmt.setString(paramIndex++, selecteditem);
        }
        if (formattedFromDate != null) {
            pstmt.setString(paramIndex++, formattedFromDate);
        }
        if (formattedToDate != null) {
            pstmt.setString(paramIndex++, formattedToDate);
        }
        ResultSet rs = pstmt.executeQuery();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        while (rs.next()) {
            String itemName = rs.getString("itemName");
            int totalQuantity = rs.getInt("totalQuantity");
            model.addRow(new Object[]{itemName, totalQuantity});
        }
        rs.close();
        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
}


    }//GEN-LAST:event_jBtnTotalCreditActionPerformed

    private void itemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      new AdminMenu().setVisible(true);
        this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonDeleteEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteEntryActionPerformed
        // TODO add your handling code here:
int row = jTable1.getSelectedRow();
if (row >= 0) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    String adminNameValue = model.getValueAt(row, 0).toString(); // Assuming adminName is the first column
    String itemNameValue = model.getValueAt(row, 1).toString();  // Assuming itemName is in the second column
    String entryDate = model.getValueAt(row, 3).toString();      // Assuming date is the fourth column

    // Show confirmation dialog before deletion
    int response = JOptionPane.showConfirmDialog(null,
            "Do you want to delete the entry for admin: " + adminNameValue + 
            ", item: " + itemNameValue + " on " + entryDate + "?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

    if (response == JOptionPane.YES_OPTION) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
            String sql = "DELETE FROM adminentry WHERE adminName = ? AND itemName = ? AND entryDate = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, adminNameValue);
            pstmt.setString(2, itemNameValue);
            pstmt.setString(3, entryDate);
            pstmt.executeUpdate();

            // Remove row from the table
            model.removeRow(row);
            JOptionPane.showMessageDialog(null, "Entry deleted successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    } // If NO_OPTION is selected, no action will be taken
} else {
    JOptionPane.showMessageDialog(null, "Please select an entry to delete.");
}

    }//GEN-LAST:event_jButtonDeleteEntryActionPerformed

    private void jButtonChangeQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeQuantityActionPerformed
        int row = jTable1.getSelectedRow();
if (row >= 0) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    String adminNameValue = model.getValueAt(row, 0).toString();  // Assuming adminName is the first column
    String itemNameValue = model.getValueAt(row, 1).toString();   // Assuming itemName is the second column
    String entryDate = model.getValueAt(row, 3).toString();       // Assuming entryDate is the fourth column
    String oldQuantity = model.getValueAt(row, 2).toString();     // Assuming quantity is in the third column
    String newQuantity = jTextFieldQuantity.getText();

    // Show confirmation dialog before updating quantity
    int response = JOptionPane.showConfirmDialog(null,
            "Do you want to change the quantity from " + oldQuantity + " to " + newQuantity + "?",
            "Confirm Quantity Update",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

    if (response == JOptionPane.YES_OPTION) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:inven.db");
            String sql = "UPDATE adminentry SET quantity = ? WHERE adminName = ? AND itemName = ? AND entryDate = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newQuantity);
            pstmt.setString(2, adminNameValue);
            pstmt.setString(3, itemNameValue);
            pstmt.setString(4, entryDate);
            pstmt.executeUpdate();

            // Update table display
            model.setValueAt(newQuantity, row, 2);  // Update the quantity in the table
            JOptionPane.showMessageDialog(null, "Quantity updated successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    } // If NO_OPTION is selected, no action will be taken
} else {
    JOptionPane.showMessageDialog(null, "Please select an entry to update.");
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonChangeQuantityActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
          int row = jTable1.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    
    String itemNameValue = model.getValueAt(row, 1).toString();
    String quantityValue = model.getValueAt(row, 2).toString();
    String dateValue = model.getValueAt(row, 3).toString();

    itemName.setSelectedItem(itemNameValue);  // Assuming `empName` is a combo box
    jTextFieldQuantity.setText(quantityValue);
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
        jDateChooserFrom.setDate(sdf.parse(dateValue));  // Set date field
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Date Parse Error: " + e.getMessage());
    }
    
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(ViewAdminEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewAdminEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewAdminEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewAdminEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewAdminEntry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JComboBox<String> itemName;
    private javax.swing.JButton jBtnTotalCredit;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldQuantity;
    // End of variables declaration//GEN-END:variables
}

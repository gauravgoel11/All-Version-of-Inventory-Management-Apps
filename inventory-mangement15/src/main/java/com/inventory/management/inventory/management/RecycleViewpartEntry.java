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


/**
 *
 * @author gaura
 */
public class RecycleViewpartEntry extends javax.swing.JFrame {
    private Map<String, String> partMap = new HashMap<>();

    /**
     * Creates new form ViewEntry
     */
    public RecycleViewpartEntry() {
        initComponents();
        
         setExtendedState(this.MAXIMIZED_BOTH);
          setupKeyBindings();
          oneMonthEntry();
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
        new RecycleMenu().setVisible(true);
        this.dispose();
    }
private void oneMonthEntry() {
    try {
        // Connect to the database using PostgreSQL
        Connection conn = DatabaseConnection.getConnection();

        // Calculate the date one month ago from today
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.MONTH, -1);
        java.util.Date oneMonthAgo = cal.getTime();

        // Convert java.util.Date to java.sql.Date for use in the prepared statement
        java.sql.Date sqlDate = new java.sql.Date(oneMonthAgo.getTime());

        // Prepare SQL query to select entries from the last month
        String sql = "SELECT partName, quantity, entryDate FROM temp_partentry WHERE entryDate >= ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1, sqlDate);  // Use setDate instead of setString

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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        empEnt = new javax.swing.JLabel();
        jcombopartname = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(jcombopartname);
        jLabel1 = new javax.swing.JLabel();
        jTextFieldQuantity = new javax.swing.JTextField();
        jButtonRestoreEntry = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("View All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
        empEnt.setText("Recycle Production Entry");

        jcombopartname.setEditable(true);
        jcombopartname.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Quantity");

        jButtonRestoreEntry.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonRestoreEntry.setText("Restore Entry");
        jButtonRestoreEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestoreEntryActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
                            .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonRestoreEntry, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empEnt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcombopartname, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jDateChooserTo, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jTextFieldQuantity)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonRestoreEntry)))
                .addGap(18, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                                         
    try {
        // Connect to the database using PostgreSQL
        Connection conn = DatabaseConnection.getConnection(); // Ensure this method is set up for PostgreSQL

        // Calculate the date one month ago from today
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.MONTH, -1);
        java.util.Date oneMonthAgo = cal.getTime();

        // Convert java.util.Date to java.sql.Date for use in the prepared statement
        java.sql.Date sqlDate = new java.sql.Date(oneMonthAgo.getTime());

        // Prepare SQL query to select entries from the last month
        String sql = "SELECT partName, quantity, entryDate FROM temp_partentry WHERE entryDate >= ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1, sqlDate); // Use setDate instead of setString

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
            // Format the SQL Date to display as a string if necessary
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(entryDate);
            model.addRow(new Object[]{partName, quantity, formattedDate});
        }

        // Close connections
        rs.close();
        pstmt.close();
        conn.close();
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }


    }//GEN-LAST:event_jButton1ActionPerformed
private JFrame frame;
    private void jButtonRestoreEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestoreEntryActionPerformed
                                                    
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Adjust the column indices to match your table structure
        String partName = model.getValueAt(selectedRow, 0).toString(); // Assuming partName is in column 0
        int quantity = Integer.parseInt(model.getValueAt(selectedRow, 1).toString()); // Assuming quantity is in column 1
        String entryDate = model.getValueAt(selectedRow, 2).toString(); // Assuming entryDate is in column 2

        // Convert the entryDate from String to java.sql.Date
        java.sql.Date sqlEntryDate = java.sql.Date.valueOf(entryDate);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO partentry (partName, quantity, entryDate) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, partName);
                pstmt.setInt(2, quantity);
                pstmt.setDate(3, sqlEntryDate);

                // Execute the insert
                pstmt.executeUpdate();

                // Optionally, remove the row from temp_partentry if needed
                // This would require another SQL statement here

                JOptionPane.showMessageDialog(null, "Row recovered to partentry table successfully.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please select a row to recover.");
    }


    }//GEN-LAST:event_jButtonRestoreEntryActionPerformed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 new RecycleMenu().setVisible(true);
        this.dispose();        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(RecycleViewpartEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecycleViewpartEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecycleViewpartEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecycleViewpartEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecycleViewpartEntry().setVisible(true);
            }
        });
        SwingUtilities.invokeLater(() -> new AdminMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonRestoreEntry;
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

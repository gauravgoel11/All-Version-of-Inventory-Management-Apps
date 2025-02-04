/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.inventory.management.inventory.management;
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
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gaura
 */
public class UserAdminEntry1 extends javax.swing.JFrame {
    private Map<String, String> itemMap = new HashMap<>();
    private Map<String, String> empMap = new HashMap<>();

    /**
     * Creates new form AdminEntry
     */
    public UserAdminEntry1() {
        initComponents();
                totalTA.setLineWrap(true);
        totalTA.setWrapStyleWord(true); 
//        jDateChooser.setDate(new Date());
         setExtendedState(this.MAXIMIZED_BOTH);
          setupKeyBindings();
          loadItems(); // Load items into the map and combo boxes
        setupComboBoxListeners();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        jTextFieldDate.setText(currentDate);
         
         /**new code*/
 
    }
private void loadItems() { 
 try (Connection con = DatabaseConnection.getConnection()) {
        Statement st = con.createStatement();
        
        // Load items
        ResultSet rs = st.executeQuery("SELECT * FROM items");
        while (rs.next()) {
            String name = rs.getString("itemName");
            String code = rs.getString("itemCode");
            itemName.addItem(name);
            itemCode.addItem(code);
            itemMap.put(name, code); // Store the relationship in the map
        }

        // Load employee names with IDs
        rs = st.executeQuery("SELECT * FROM emp");
        while (rs.next()) {
            String empNameWithID = rs.getString("empName") + " " + rs.getString("empID");
            empName.addItem(empNameWithID);
            empMap.put(empNameWithID, empNameWithID); // Assuming empNameWithID is unique and used as a key
        }
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
        });}
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
        new UserMenu1().setVisible(true);
        this.dispose();
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
        itemName = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(itemName);
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldAdminName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldQuantity = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        totalTA = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        empName = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(empName);
        jLabel6 = new javax.swing.JLabel();
        itemCode = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(itemCode);
        jTextFieldDate = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        empEnt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        empEnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empEnt.setText("Vendor Entry");
        jPanel1.add(empEnt);
        empEnt.setBounds(0, 0, 610, 70);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 610, 10);

        itemName.setEditable(true);
        itemName.setToolTipText("");
        jPanel1.add(itemName);
        itemName.setBounds(10, 190, 200, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Item Name");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 160, 100, 20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("VENDOR NAME");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(351, 240, 150, 20);
        jPanel1.add(jTextFieldAdminName);
        jTextFieldAdminName.setBounds(350, 270, 160, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Date");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(351, 76, 100, 20);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Quantity");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(351, 160, 100, 20);

        jTextFieldQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQuantityActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldQuantity);
        jTextFieldQuantity.setBounds(350, 190, 160, 30);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(250, 310, 72, 32);

        totalTA.setColumns(20);
        totalTA.setRows(5);
        jScrollPane1.setViewportView(totalTA);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 350, 590, 130);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(520, 110, 80, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("EXISTING VENDOR NAME");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(11, 80, 200, 20);

        empName.setEditable(true);
        empName.setToolTipText("");
        empName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empNameActionPerformed(evt);
            }
        });
        jPanel1.add(empName);
        empName.setBounds(10, 110, 200, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Item Code");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(11, 240, 100, 20);

        itemCode.setEditable(true);
        itemCode.setToolTipText("");
        jPanel1.add(itemCode);
        itemCode.setBounds(10, 270, 200, 30);

        jTextFieldDate.setEditable(false);
        jTextFieldDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDateActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldDate);
        jTextFieldDate.setBounds(350, 110, 160, 30);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 150, 610, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                                       

    String adminName = jTextFieldAdminName.getText().toUpperCase(); // Convert to uppercase immediately
    String selectedItem = itemName.getSelectedItem().toString();
    String quantityText = jTextFieldQuantity.getText();
    java.util.Date selectedDate = new Date(); // Assuming you have a date chooser component

    if (adminName.isEmpty() || selectedItem.isEmpty() || quantityText.isEmpty() || selectedDate == null) {
        totalTA.setText("Please fill in all fields.");
        return;
    }

    try {
        BigDecimal quantity = new BigDecimal(quantityText); // Use int for quantity
        java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime()); // Convert java.util.Date to java.sql.Date

        try (Connection con = DatabaseConnection.getConnection()) {
            // Insert into adminentry table
            PreparedStatement pst1 = con.prepareStatement(
                "INSERT INTO adminentry (adminName, itemName, quantity, entryDate) VALUES (?, ?, ?, ?)");
            pst1.setString(1, adminName);
            pst1.setString(2, selectedItem);
             pst1.setBigDecimal(3, quantity); // Set int value
            pst1.setDate(4, sqlDate);

            // Insert into temp_adminentry table
            PreparedStatement pst2 = con.prepareStatement(
                "INSERT INTO temp_adminentry (adminName, itemName, quantity, entryDate) VALUES (?, ?, ?, ?)");
            pst2.setString(1, adminName);
            pst2.setString(2, selectedItem);
            pst2.setBigDecimal(3, quantity); // Set int value
            pst2.setDate(4, sqlDate);

            int rowsAffected1 = pst1.executeUpdate();
            int rowsAffected2 = pst2.executeUpdate();

            if (rowsAffected1 > 0 && rowsAffected2 > 0) {
                totalTA.append("\nEntry added to both tables: " + selectedItem + " : " + quantity + " on " + sqlDate);
            } else {
                totalTA.append("\nFailed to add entry to one or both tables.");
            }
        }
    } catch (NumberFormatException e) {
        totalTA.setText("Please enter a valid quantity.");
    } catch (ClassNotFoundException | SQLException e) {
        totalTA.setText("Error: " + e.getMessage());
    }
    jTextFieldQuantity.setText("");



    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
new UserMenu1().setVisible(true);
            this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldQuantityActionPerformed

    private void empNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empNameActionPerformed
        String selectedEmpName = (String) empName.getSelectedItem();
        // Set the selected item as the text of jTextFieldNewPartName
        jTextFieldAdminName.setText(selectedEmpName);        // TODO add your handling code here:
    }//GEN-LAST:event_empNameActionPerformed

    private void jTextFieldDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDateActionPerformed

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
            java.util.logging.Logger.getLogger(UserAdminEntry1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserAdminEntry1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserAdminEntry1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserAdminEntry1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserAdminEntry1().setVisible(true);
            }
        });
        SwingUtilities.invokeLater(() -> new UserMenu1().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JComboBox<String> empName;
    private javax.swing.JComboBox<String> itemCode;
    private javax.swing.JComboBox<String> itemName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldAdminName;
    private javax.swing.JTextField jTextFieldDate;
    private javax.swing.JTextField jTextFieldQuantity;
    private javax.swing.JTextArea totalTA;
    // End of variables declaration//GEN-END:variables
}

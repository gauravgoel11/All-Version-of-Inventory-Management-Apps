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
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;


/**
 *
 * @author gaura
 */
public class entry1 extends javax.swing.JFrame {
    private Map<String, String> itemMap = new HashMap<>();
    private Map<String, String> empMap = new HashMap<>();

    /**
     * Creates new form AdminEntry
     */
    public entry1() {
        initComponents();
                totalTA.setLineWrap(true);
        totalTA.setWrapStyleWord(true); 
        jDateChooser.setDate(new Date());
         setExtendedState(this.MAXIMIZED_BOTH);
          setupKeyBindings();
                            loadItems(); // Load items into the map and combo boxes
        setupComboBoxListeners();
         
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
        new AdminMenu().setVisible(true);
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
        jLabel4 = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        quan = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        totalTA = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        empName = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(empName);
        jLabel5 = new javax.swing.JLabel();
        itemCode = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(itemCode);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        empEnt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        empEnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empEnt.setText("Worker Entry");
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
        jLabel2.setText("Worker Name");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 76, 140, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Date");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(350, 76, 100, 20);
        jPanel1.add(jDateChooser);
        jDateChooser.setBounds(350, 110, 160, 30);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Quantity");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(350, 160, 100, 20);

        quan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quanActionPerformed(evt);
            }
        });
        jPanel1.add(quan);
        quan.setBounds(350, 190, 160, 30);

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

        empName.setEditable(true);
        empName.setToolTipText("");
        jPanel1.add(empName);
        empName.setBounds(10, 110, 200, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Item Code");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(11, 240, 100, 20);

        itemCode.setEditable(true);
        itemCode.setToolTipText("");
        jPanel1.add(itemCode);
        itemCode.setBounds(10, 270, 200, 30);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 150, 610, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    String emp = (String) empName.getSelectedItem();
    String item = (String) itemName.getSelectedItem();
    BigDecimal qn;

    try {
        qn = new BigDecimal(quan.getText().trim());
    } catch (NumberFormatException e) {
        totalTA.append("\nPlease enter a valid quantity.");
        return;
    }

    // Split the employee name to get empName and empID
    String[] empDetails = emp.split(" ");
    String empName = empDetails[0];
    int empID;

    try {
        empID = Integer.parseInt(empDetails[1]);
    } catch (NumberFormatException e) {
        totalTA.append("\nInvalid Employee ID format.");
        return;
    }

    // Get the selected date
    Date selectedDate = jDateChooser.getDate();
    if (selectedDate == null) {
        totalTA.append("\nPlease select a date.");
        return;
    }

    // Convert java.util.Date to java.sql.Date
    java.sql.Date sqlEntryDate = new java.sql.Date(selectedDate.getTime());

    // Insert data into the database
    try (Connection con = DatabaseConnection.getConnection()) {
        // Insert into entry table
        String query1 = "INSERT INTO entry (empName, empID, itemName, quantity, entryDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst1 = con.prepareStatement(query1)) {
            pst1.setString(1, empName);
            pst1.setInt(2, empID);
            pst1.setString(3, item);
            pst1.setBigDecimal(4, qn); // Use setBigDecimal for quantity
            pst1.setDate(5, sqlEntryDate);

            int result1 = pst1.executeUpdate();

            // Insert into temp_entry table
            String query2 = "INSERT INTO temp_entry (empName, empID, itemName, quantity, entryDate) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pst2 = con.prepareStatement(query2)) {
                pst2.setString(1, empName);
                pst2.setInt(2, empID);
                pst2.setString(3, item);
                pst2.setBigDecimal(4, qn); // Use setBigDecimal for quantity
                pst2.setDate(5, sqlEntryDate);

                int result2 = pst2.executeUpdate();

                if (result1 > 0 && result2 > 0) {
                    totalTA.append("\nEntry added to the table: " + item + " : " + qn + " on " + sqlEntryDate);
                } else {
                    totalTA.append("\nFailed to add entry to one or both tables.");
                }
            }
        }
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Error: " + e.getMessage());
        totalTA.append("\nError: " + e.getMessage());
    }
    quan.setText("");



    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
new AdminMenu().setVisible(true);
            this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void quanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quanActionPerformed

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
            java.util.logging.Logger.getLogger(entry1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(entry1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(entry1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(entry1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new entry1().setVisible(true);
            }
        });
        SwingUtilities.invokeLater(() -> new AdminMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JComboBox<String> empName;
    private javax.swing.JComboBox<String> itemCode;
    private javax.swing.JComboBox<String> itemName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField quan;
    private javax.swing.JTextArea totalTA;
    // End of variables declaration//GEN-END:variables
}

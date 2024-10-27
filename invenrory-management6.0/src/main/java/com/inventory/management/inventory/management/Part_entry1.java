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

/**
 *
 * @author gaura
 */
public class Part_entry1 extends javax.swing.JFrame {

    /**
     * Creates new form AdminEntry
     */
    public Part_entry1() {
        initComponents();
                totalTA.setLineWrap(true);
        totalTA.setWrapStyleWord(true); 
        jDateChooser.setDate(new Date());
         setExtendedState(this.MAXIMIZED_BOTH);
          setupKeyBindings();
         
         /**new code*/
 
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
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        quan = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        totalTA = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jcombopartname = new javax.swing.JComboBox<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:inven.db");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from part_items");

            Set<String> partNames = new HashSet<>();
            while (rs.next()) {
                String s = rs.getString("partName");
                partNames.add(s);
            }
            con.close();

            for (String partName : partNames) {
                jcombopartname.addItem(partName);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error is " + e.getMessage());
        }

        AutoCompleteDecorator.decorate(jcombopartname);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        empEnt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        empEnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empEnt.setText("Production Entry");
        jPanel1.add(empEnt);
        empEnt.setBounds(0, 0, 610, 70);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 610, 10);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Quantity");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 160, 100, 20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Product name");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 76, 140, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Date");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(350, 76, 100, 20);
        jPanel1.add(jDateChooser);
        jDateChooser.setBounds(350, 110, 160, 30);

        quan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quanActionPerformed(evt);
            }
        });
        jPanel1.add(quan);
        quan.setBounds(10, 190, 200, 30);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(250, 240, 72, 32);

        totalTA.setColumns(20);
        totalTA.setRows(5);
        jScrollPane1.setViewportView(totalTA);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 280, 590, 130);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(520, 110, 80, 30);

        jcombopartname.setEditable(true);
        jcombopartname.setToolTipText("");
        jPanel1.add(jcombopartname);
        jcombopartname.setBounds(10, 110, 200, 30);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 610, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    String item = (String) jcombopartname.getSelectedItem();
        int qn;
    try {
        qn = Integer.parseInt(quan.getText());
    } catch (NumberFormatException e) {
        totalTA.append("\nPlease enter a valid quantity.");
        return;
    }
    
 
    
    // Get the selected date
    Date selectedDate = jDateChooser.getDate();
        if (selectedDate == null) {
        totalTA.append("\nPlease select a date.");
        return;
    }
    
    // Format the date to "yyyy-MM-dd"
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String entryDate = formatter.format(selectedDate);

    // Insert data into the database
    try {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:inven.db");
        String query = "INSERT INTO partentry(partName, quantity, entryDate) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, item);
        pst.setInt(2, qn);
        pst.setString(3, entryDate);  // Insert the formatted date

        int result = pst.executeUpdate();
        
        if (result > 0) {
            totalTA.append("\nEntry added: " + item + " : " + qn + " on " + entryDate);
        } else {
            totalTA.append("\nFailed to add entry.");
        }

        pst.close();
        con.close();
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
            java.util.logging.Logger.getLogger(Part_entry1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Part_entry1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Part_entry1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Part_entry1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Part_entry1().setVisible(true);
            }
        });
        SwingUtilities.invokeLater(() -> new AdminMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empEnt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> jcombopartname;
    private javax.swing.JTextField quan;
    private javax.swing.JTextArea totalTA;
    // End of variables declaration//GEN-END:variables
}

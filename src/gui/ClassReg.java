/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import raven.toast.Notifications;

/**
 *
 * @author Chathusha Mendis
 */
public class ClassReg extends javax.swing.JFrame {

    /**
     * Creates new form ClassReg
     */
    public ClassReg() {
        initComponents();
        loadClassTable();
        loadSubjects();
        loadTeachers();
    }

    private void clearForm() {
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextField1.setText("");
    }

    private void filterClassTable(String searchText) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows
        try {
            ResultSet rs = MySQL.executeSearch(
                    "SELECT Class.ClassNo, Subject.Name AS SubjectName, Teacher.Name AS TeacherName, Class.timeSlot "
                    + "FROM Class "
                    + "JOIN Subject ON Class.subNo = Subject.Subno "
                    + "JOIN Teacher ON Class.Tno = Teacher.Tno "
                    + "WHERE Subject.Name LIKE '%" + searchText + "%' OR Teacher.Name LIKE '%" + searchText + "%'"
            );
            while (rs.next()) {
                int classNo = rs.getInt("ClassNo");
                String subjectName = rs.getString("SubjectName");
                String teacherName = rs.getString("TeacherName");
                String timeSlot = rs.getString("timeSlot");
                model.addRow(new Object[]{classNo, subjectName, teacherName, timeSlot});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadClassDetails(int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int classNo = (int) model.getValueAt(selectedRow, 0);
        String subjectName = (String) model.getValueAt(selectedRow, 1);
        String teacherName = (String) model.getValueAt(selectedRow, 2);
        String timeSlot = (String) model.getValueAt(selectedRow, 3);

        jComboBox1.setSelectedItem(subjectName);
        jComboBox2.setSelectedItem(teacherName);
        jTextField1.setText(timeSlot);
    }

    private void loadClassTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows
        try {
            ResultSet rs = MySQL.executeSearch(
                    "SELECT Class.ClassNo, Subject.Name AS SubjectName, Teacher.Name AS TeacherName, Class.timeSlot "
                    + "FROM Class "
                    + "JOIN Subject ON Class.subNo = Subject.Subno "
                    + "JOIN Teacher ON Class.Tno = Teacher.Tno"
            );
            while (rs.next()) {
                int classNo = rs.getInt("ClassNo");
                String subjectName = rs.getString("SubjectName");
                String teacherName = rs.getString("TeacherName");
                String timeSlot = rs.getString("timeSlot");
                model.addRow(new Object[]{classNo, subjectName, teacherName, timeSlot});
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error loading class table.");
        }
    }

    private void loadSubjects() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Select subject");
        try {
            ResultSet rs = MySQL.executeSearch("SELECT Name FROM Subject");
            while (rs.next()) {
                String name = rs.getString("Name");
                model.addElement(name);
            }
            jComboBox1.setModel(model);
            AutoCompleteDecorator.decorate(jComboBox1);
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error loading subjects.");
        }
    }

    private void loadTeachers() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Select teacher");
        try {
            ResultSet rs = MySQL.executeSearch("SELECT Name FROM Teacher");
            while (rs.next()) {
                String name = rs.getString("Name");
                model.addElement(name);
            }
            jComboBox2.setModel(model);
            AutoCompleteDecorator.decorate(jComboBox2);
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error loading teachers.");
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        addClassBtn = new javax.swing.JButton();
        updateClassBtn = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        clearBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Class registration");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class No", "Subject Name", "Teacher Name", "Time slot"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("Subject Name");

        jLabel4.setText("Teacher Name");

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        addClassBtn.setText("Add Class");
        addClassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClassBtnActionPerformed(evt);
            }
        });

        updateClassBtn.setText("Update Class");
        updateClassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateClassBtnActionPerformed(evt);
            }
        });

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jLabel11.setText("Search by subject or teacher name");

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        jLabel12.setText("Double click on a row to select it");

        jButton6.setText("Print Report");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel6.setText("Time slot");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(backBtn))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox2, 0, 156, Short.MAX_VALUE)
                                    .addComponent(jTextField1)))
                            .addComponent(clearBtn)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(addClassBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateClassBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jScrollPane1))
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(122, 122, 122)
                        .addComponent(addClassBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateClassBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backBtn)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // table mouse clicked
        if (evt.getClickCount() == 2) { // Double-click detected
            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow != -1) {
                loadClassDetails(selectedRow);
                addClassBtn.setEnabled(false);
                updateClassBtn.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // Back button

        this.dispose();
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
    }//GEN-LAST:event_backBtnActionPerformed

    private void addClassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClassBtnActionPerformed
        String subject = (String) jComboBox1.getSelectedItem();
        String teacher = (String) jComboBox2.getSelectedItem();
        String timeSlot = jTextField1.getText();

        if (subject.equals("Select subject") || teacher.equals("Select teacher") || timeSlot.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please fill all the fields.");
            return;
        }

        try {
            ResultSet rs = MySQL.executeSearch("SELECT Subno FROM Subject WHERE Name = '" + subject + "'");
            rs.next();
            int subNo = rs.getInt("Subno");

            rs = MySQL.executeSearch("SELECT Tno FROM Teacher WHERE Name = '" + teacher + "'");
            rs.next();
            int tNo = rs.getInt("Tno");

            MySQL.executeIUD("INSERT INTO Class (subNo, Tno, timeSlot) VALUES (" + subNo + ", " + tNo + ", '" + timeSlot + "')");
            loadClassTable();
            clearForm();
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Class added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error adding class.");
        }
    }//GEN-LAST:event_addClassBtnActionPerformed

    private void updateClassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateClassBtnActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please select a class to update.");
            return;
        }

        int classNo = (int) jTable1.getValueAt(selectedRow, 0);
        String subject = (String) jComboBox1.getSelectedItem();
        String teacher = (String) jComboBox2.getSelectedItem();
        String timeSlot = jTextField1.getText();

        if (subject.equals("Select subject") || teacher.equals("Select teacher") || timeSlot.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please fill all the fields.");
            return;
        }

        try {
            ResultSet rs = MySQL.executeSearch("SELECT Subno FROM Subject WHERE Name = '" + subject + "'");
            rs.next();
            int subNo = rs.getInt("Subno");

            rs = MySQL.executeSearch("SELECT Tno FROM Teacher WHERE Name = '" + teacher + "'");
            rs.next();
            int tNo = rs.getInt("Tno");

            MySQL.executeIUD("UPDATE Class SET subNo = " + subNo + ", Tno = " + tNo + ", timeSlot = '" + timeSlot + "' WHERE ClassNo = " + classNo);
            loadClassTable();
            clearForm();
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Class updated successfully!");
            addClassBtn.setEnabled(true);
            updateClassBtn.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error updating class.");
        }
    }//GEN-LAST:event_updateClassBtnActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // search bar key release

        String searchText = jTextField5.getText().trim().toLowerCase();
        filterClassTable(searchText);
    }//GEN-LAST:event_jTextField5KeyReleased

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        jTable1.clearSelection();
        clearForm();
        addClassBtn.setEnabled(true);
        updateClassBtn.setEnabled(false);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Print report button

        //        HashMap<String, Object> parameters = new HashMap<>();
        //        parameters.put("Generator", UserSession.getInstance().getUsername());
        //
        //        try {
        //            // Load the .jasper file
        //            InputStream jasperStream = getClass().getResourceAsStream("/reports/Students.jasper");
        //            if (jasperStream == null) {
        //                throw new JRException("Jasper file not found.");
        //            }
        //
        //            // Create a data source from the table model
        //            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
        //
        //            // Fill the report
        //            JasperPrint jasperPrint = JasperFillManager.fillReport("src/reports/Students.jasper", parameters, dataSource);
        //
        //            // View the report
        //            JasperViewer.viewReport(jasperPrint, false);
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error printing report.");
        //        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(ClassReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClassReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClassReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClassReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClassReg().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClassBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton updateClassBtn;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import raven.toast.Notifications;

/**
 *
 * @author Chathusha Mendis
 */
public class TeacherMgmt extends javax.swing.JFrame {

    /**
     * Creates new form TeacherMgmt
     */
    public TeacherMgmt() {
        initComponents();
        loadTeacherTable();
        loadSubjects();
        updateTeacherBtn.setEnabled(false);
        deleteTeacherBtn.setEnabled(false);
    }

    private void loadSubjects() {
        try {
            ResultSet subjectResult = MySQL.executeSearch("SELECT Name FROM Subject");
            DefaultListModel<String> listModel = new DefaultListModel<>();

            while (subjectResult.next()) {
                listModel.addElement(subjectResult.getString("Name"));
            }

            jList1.setModel(listModel);
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error loading subjects.");
        }
    }

    private void loadTeacherTable() {
        try {
            String query = "SELECT t.Tno, t.Name, t.Address, GROUP_CONCAT(s.Name SEPARATOR ', ') AS Subjects "
                    + "FROM Teacher t "
                    + "LEFT JOIN TeacherSubject ts ON t.Tno = ts.Tno "
                    + "LEFT JOIN Subject s ON ts.Subno = s.Subno "
                    + "GROUP BY t.Tno, t.Name, t.Address";
            ResultSet teacherResult = MySQL.executeSearch(query);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear existing data

            while (teacherResult.next()) {
                Object[] row = new Object[4];
                row[0] = teacherResult.getInt("Tno");
                row[1] = teacherResult.getString("Name");
                row[2] = teacherResult.getString("Address");
                row[3] = teacherResult.getString("Subjects");
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error loading teachers.");
        }
    }

    private void clearForm() {
        jTextField2.setText("");
        jTextField1.setText("");
        jList1.clearSelection();
    }

    private void loadTeacherDetails(int selectedRow) {
        try {
            int teacherId = (int) jTable1.getValueAt(selectedRow, 0);

            // Load teacher details
            String query = "SELECT Name, Address FROM Teacher WHERE Tno = ?";
            PreparedStatement teacherStatement = MySQL.connection.prepareStatement(query);
            teacherStatement.setInt(1, teacherId);
            ResultSet teacherResult = teacherStatement.executeQuery();

            if (teacherResult.next()) {
                jTextField2.setText(teacherResult.getString("Name"));
                jTextField1.setText(teacherResult.getString("Address"));
            }

            // Load teacher subjects
            String subjectQuery = "SELECT s.Name FROM Subject s "
                    + "JOIN TeacherSubject ts ON s.Subno = ts.Subno "
                    + "WHERE ts.Tno = ?";
            PreparedStatement subjectStatement = MySQL.connection.prepareStatement(subjectQuery);
            subjectStatement.setInt(1, teacherId);
            ResultSet subjectResult = subjectStatement.executeQuery();

            DefaultListModel<String> listModel = (DefaultListModel<String>) jList1.getModel();
            java.util.List<Integer> selectedIndicesList = new java.util.ArrayList<>();

            while (subjectResult.next()) {
                String subjectName = subjectResult.getString("Name");
                for (int i = 0; i < listModel.getSize(); i++) {
                    if (listModel.getElementAt(i).equals(subjectName)) {
                        selectedIndicesList.add(i);
                        break;
                    }
                }
            }

            int[] selectedIndices = selectedIndicesList.stream().mapToInt(i -> i).toArray();
            jList1.setSelectedIndices(selectedIndices);
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error loading teacher details.");
        }
    }

    private void filterTeacherTable(String searchText) {
        try {
            String query = "SELECT t.Tno, t.Name, t.Address, GROUP_CONCAT(s.Name SEPARATOR ', ') AS Subjects "
                    + "FROM Teacher t "
                    + "LEFT JOIN TeacherSubject ts ON t.Tno = ts.Tno "
                    + "LEFT JOIN Subject s ON ts.Subno = s.Subno "
                    + "WHERE LOWER(t.Name) LIKE ? "
                    + "GROUP BY t.Tno, t.Name, t.Address";
            PreparedStatement preparedStatement = MySQL.connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchText + "%");
            ResultSet teacherResult = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear existing data

            while (teacherResult.next()) {
                Object[] row = new Object[4];
                row[0] = teacherResult.getInt("Tno");
                row[1] = teacherResult.getString("Name");
                row[2] = teacherResult.getString("Address");
                row[3] = teacherResult.getString("Subjects");
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error filtering teachers.");
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
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        addTeacherBtn = new javax.swing.JButton();
        updateTeacherBtn = new javax.swing.JButton();
        deleteTeacherBtn = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        clearBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Teacher management");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher No", "Teacher Name", "Address", "Subjects"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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

        jLabel3.setText("Teacher Name");

        jLabel4.setText("Address");

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        addTeacherBtn.setText("Add Teacher");
        addTeacherBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTeacherBtnActionPerformed(evt);
            }
        });

        updateTeacherBtn.setText("Update Teacher");
        updateTeacherBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTeacherBtnActionPerformed(evt);
            }
        });

        deleteTeacherBtn.setText("Delete Teacher");
        deleteTeacherBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTeacherBtnActionPerformed(evt);
            }
        });

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jLabel11.setText("Search by teacher name");

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

        jLabel6.setText("Subjects");

        jScrollPane2.setViewportView(jList1);

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
                            .addComponent(clearBtn)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(addTeacherBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateTeacherBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteTeacherBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
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
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addTeacherBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateTeacherBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteTeacherBtn)
                        .addGap(13, 13, 13)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
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
                loadTeacherDetails(selectedRow);
                addTeacherBtn.setEnabled(false);
                updateTeacherBtn.setEnabled(true);
                deleteTeacherBtn.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // Back button

        this.dispose();
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
    }//GEN-LAST:event_backBtnActionPerformed

    private void addTeacherBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTeacherBtnActionPerformed
        try {
            String teacherName = jTextField2.getText();
            String teacherAddress = jTextField1.getText();
            java.util.List<String> selectedSubjects = jList1.getSelectedValuesList();

            // Validation checks
            if (teacherName.isEmpty() || teacherAddress.isEmpty() || selectedSubjects.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please fill all the fields and select at least one subject.");
                return;
            }

            // Insert the teacher into the Teacher table
            String insertTeacherQuery = "INSERT INTO Teacher (Name, Address) VALUES (?, ?)";
            PreparedStatement teacherStatement = MySQL.connection.prepareStatement(insertTeacherQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            teacherStatement.setString(1, teacherName);
            teacherStatement.setString(2, teacherAddress);
            teacherStatement.executeUpdate();

            // Get the generated teacher ID
            ResultSet generatedKeys = teacherStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int teacherId = generatedKeys.getInt(1);

                // Insert the selected subjects into the TeacherSubject table
                String insertTeacherSubjectQuery = "INSERT INTO TeacherSubject (Tno, Subno) VALUES (?, ?)";
                PreparedStatement teacherSubjectStatement = MySQL.connection.prepareStatement(insertTeacherSubjectQuery);

                for (String subjectName : selectedSubjects) {
                    // Get the subject ID from the Subject table
                    String getSubjectIdQuery = "SELECT Subno FROM Subject WHERE Name = ?";
                    PreparedStatement subjectStatement = MySQL.connection.prepareStatement(getSubjectIdQuery);
                    subjectStatement.setString(1, subjectName);
                    ResultSet subjectResult = subjectStatement.executeQuery();

                    if (subjectResult.next()) {
                        int subjectId = subjectResult.getInt("Subno");
                        teacherSubjectStatement.setInt(1, teacherId);
                        teacherSubjectStatement.setInt(2, subjectId);
                        teacherSubjectStatement.addBatch();
                    }
                }

                teacherSubjectStatement.executeBatch();
            }

            // Refresh the teacher table
            loadTeacherTable();

            // Clear the form
            clearForm();

            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Teacher added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error adding teacher.");
        }
    }//GEN-LAST:event_addTeacherBtnActionPerformed

    private void updateTeacherBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateTeacherBtnActionPerformed
        try {
            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow == -1) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please select a teacher to update.");
                return;
            }

            int teacherId = (int) jTable1.getValueAt(selectedRow, 0);
            String teacherName = jTextField2.getText();
            String teacherAddress = jTextField1.getText();
            java.util.List<String> selectedSubjects = jList1.getSelectedValuesList();

            // Validation checks
            if (teacherName.isEmpty() || teacherAddress.isEmpty() || selectedSubjects.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please fill all the fields and select at least one subject.");
                return;
            }

            // Update the teacher in the Teacher table
            String updateTeacherQuery = "UPDATE Teacher SET Name = ?, Address = ? WHERE Tno = ?";
            PreparedStatement teacherStatement = MySQL.connection.prepareStatement(updateTeacherQuery);
            teacherStatement.setString(1, teacherName);
            teacherStatement.setString(2, teacherAddress);
            teacherStatement.setInt(3, teacherId);
            teacherStatement.executeUpdate();

            // Delete existing subjects for the teacher in the TeacherSubject table
            String deleteTeacherSubjectsQuery = "DELETE FROM TeacherSubject WHERE Tno = ?";
            PreparedStatement deleteTeacherSubjectsStatement = MySQL.connection.prepareStatement(deleteTeacherSubjectsQuery);
            deleteTeacherSubjectsStatement.setInt(1, teacherId);
            deleteTeacherSubjectsStatement.executeUpdate();

            // Insert the selected subjects into the TeacherSubject table
            String insertTeacherSubjectQuery = "INSERT INTO TeacherSubject (Tno, Subno) VALUES (?, ?)";
            PreparedStatement teacherSubjectStatement = MySQL.connection.prepareStatement(insertTeacherSubjectQuery);

            for (String subjectName : selectedSubjects) {
                // Get the subject ID from the Subject table
                String getSubjectIdQuery = "SELECT Subno FROM Subject WHERE Name = ?";
                PreparedStatement subjectStatement = MySQL.connection.prepareStatement(getSubjectIdQuery);
                subjectStatement.setString(1, subjectName);
                ResultSet subjectResult = subjectStatement.executeQuery();

                if (subjectResult.next()) {
                    int subjectId = subjectResult.getInt("Subno");
                    teacherSubjectStatement.setInt(1, teacherId);
                    teacherSubjectStatement.setInt(2, subjectId);
                    teacherSubjectStatement.addBatch();
                }
            }

            teacherSubjectStatement.executeBatch();

            // Refresh the teacher table
            loadTeacherTable();

            // Clear the form
            clearForm();
            
            addTeacherBtn.setEnabled(true);
            updateTeacherBtn.setEnabled(false);
            deleteTeacherBtn.setEnabled(false);

            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Teacher updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error updating teacher.");
        }
    }//GEN-LAST:event_updateTeacherBtnActionPerformed

    private void deleteTeacherBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTeacherBtnActionPerformed
        try {
            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow == -1) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please select a teacher to delete.");
                return;
            }

            int teacherId = (int) jTable1.getValueAt(selectedRow, 0);

            // Delete the teacher from the Teacher table
            String deleteTeacherQuery = "DELETE FROM Teacher WHERE Tno = ?";
            PreparedStatement teacherStatement = MySQL.connection.prepareStatement(deleteTeacherQuery);
            teacherStatement.setInt(1, teacherId);
            teacherStatement.executeUpdate();

            // Delete the associated subjects from the TeacherSubject table
            String deleteTeacherSubjectsQuery = "DELETE FROM TeacherSubject WHERE Tno = ?";
            PreparedStatement deleteTeacherSubjectsStatement = MySQL.connection.prepareStatement(deleteTeacherSubjectsQuery);
            deleteTeacherSubjectsStatement.setInt(1, teacherId);
            deleteTeacherSubjectsStatement.executeUpdate();

            // Refresh the teacher table
            loadTeacherTable();

            // Clear the form
            clearForm();
            
            addTeacherBtn.setEnabled(true);
            updateTeacherBtn.setEnabled(false);
            deleteTeacherBtn.setEnabled(false);

            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Teacher deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error deleting teacher.");
        }
    }//GEN-LAST:event_deleteTeacherBtnActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // search bar key release

        String searchText = jTextField5.getText().trim().toLowerCase();
        filterTeacherTable(searchText);
    }//GEN-LAST:event_jTextField5KeyReleased

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        jTable1.clearSelection();
        clearForm();
        addTeacherBtn.setEnabled(true);
        updateTeacherBtn.setEnabled(false);
        deleteTeacherBtn.setEnabled(false);
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
            java.util.logging.Logger.getLogger(TeacherMgmt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherMgmt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherMgmt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherMgmt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherMgmt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTeacherBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteTeacherBtn;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton updateTeacherBtn;
    // End of variables declaration//GEN-END:variables
}

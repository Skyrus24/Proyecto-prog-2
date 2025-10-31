package agendamiento_clinico.medicos;

import agendamiento_clinico.Grilla;
import java.sql.*;
import agendamiento_clinico.BaseDatos;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FiltrarMedicos extends javax.swing.JDialog {
    BaseDatos bd = new BaseDatos();
    Grilla grd = new Grilla();
    private int medicoSeleccionadoId = -1;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FiltrarMedicos.class.getName());
    
    public FiltrarMedicos(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        cargarEspecialidades();
        cargarMedicos(null);
        cboEspecialidades.setSelectedItem(null);
        
        cboEspecialidades.addActionListener(e -> {
            String especialidad = (String) cboEspecialidades.getSelectedItem();
            cargarMedicos(especialidad);
        });
    }

    public FiltrarMedicos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarEspecialidades(); 
        cargarMedicos(null); // carga todos inicialmente
        cboEspecialidades.setSelectedItem(null);
        
        cboEspecialidades.addActionListener(e -> {
            String especialidad = (String) cboEspecialidades.getSelectedItem();
            cargarMedicos(especialidad);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboEspecialidades = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdMedicos = new javax.swing.JTable();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(null);

        jLabel1.setText("Especialidad: ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 20, 80, 16);

        cboEspecialidades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cboEspecialidades);
        cboEspecialidades.setBounds(120, 20, 520, 20);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        grdMedicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(grdMedicos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167))
        );

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(46, 46, 46)
                .addComponent(btnCancelar)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        int fila = grdMedicos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un médico de la lista.");
            return;
        }

        medicoSeleccionadoId = (int) grdMedicos.getValueAt(fila, 0); // columna ID
        dispose(); // cerrar el diálogo
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    
    public int getMedicoSeleccionadoId() {
        return medicoSeleccionadoId;
    }
    
     
    private void cargarEspecialidades(){
        cboEspecialidades.removeAllItems();
        try (Connection conexion = bd.miConexion();
             Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery("SELECT nombre_especialidad FROM especialidades ORDER BY nombre_especialidad")) {

            //Cargar mientras existan especialidades
            while (rs.next()) {
                cboEspecialidades.addItem(rs.getString("nombre_especialidad"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar especialidades: " + e.getMessage());
        }
    }
    
    private void cargarMedicos(String especialidadSeleccionada) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{
            "ID", "Nombre", "Apellidos", "Especialidad", "Email", "Teléfono", "Licencia"
        });

        String sql = 
            "SELECT m.id_medico, m.nombre, m.apellidos, e.nombre_especialidad, " +
            "m.email, m.telefono, m.numero_licencia " +
            "FROM medicos m " +
            "INNER JOIN especialidades e ON m.id_especialidad = e.id_especialidad";

        // agregar condición si se seleccionó una especialidad específica
        if (especialidadSeleccionada != null && !"Todas".equalsIgnoreCase(especialidadSeleccionada)) {
            sql += " WHERE e.nombre_especialidad = ?";
        }

        try (Connection conexion = bd.miConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            if (especialidadSeleccionada != null && !"Todas".equalsIgnoreCase(especialidadSeleccionada)) {
                ps.setString(1, especialidadSeleccionada);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_medico"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("nombre_especialidad"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("numero_licencia")
                };
                modelo.addRow(fila);
            }

            grdMedicos.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar médicos: " + e.getMessage());
        }
    }
    
    
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FiltrarMedicos dialog = new FiltrarMedicos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboEspecialidades;
    private javax.swing.JTable grdMedicos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

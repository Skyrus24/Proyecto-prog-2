package agendamiento_clinico.Gestiones;

import agendamiento_clinico.BaseDatos;
import agendamiento_clinico.Grilla;
import javax.swing.JOptionPane;

public class FrmConsultorios extends javax.swing.JDialog {

    private BaseDatos bd = new BaseDatos();
    private char opc = 'z';
    private Grilla grd = new Grilla();

    public FrmConsultorios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        if (!this.bd.hayConexion()) {
            JOptionPane.showMessageDialog(null, "Error de conexión con la base de datos");
        }
        this.habilitarCampos(false);
        this.habilitarBotones(true);
        this.actualizarGrilla();
        this.setLocationRelativeTo(null);
    }

    private void habilitarCampos(boolean estado) {
        txtNombre.setEnabled(estado);
        txtUbicacion.setEnabled(estado);
        txtCapacidad.setEnabled(estado);
    }

    private void habilitarBotones(boolean estado) {
        cmdAgregar.setEnabled(estado);
        cmdEliminar.setEnabled(estado);
        cmdModificar.setEnabled(estado);
        cmdGuardar.setEnabled(!estado);
    }

    private void limpiarCampos() {
        txtNombre.setText(null);
        txtUbicacion.setText(null);
        txtCapacidad.setText(null);
    }

    private void actualizarGrilla() {
        String campos[] = {"id_consultorio", "nombre", "ubicacion", "capacidad"};
        this.grd.cargarGrilla(grdConsultorios, "consultorios", campos);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCapacidad = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        cmdAgregar = new javax.swing.JButton();
        cmdModificar = new javax.swing.JButton();
        cmdEliminar = new javax.swing.JButton();
        cmdGuardar = new javax.swing.JButton();
        cmdCancelar = new javax.swing.JButton();
        txtUbicacion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdConsultorios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Cambria", 3, 16)); // NOI18N
        jLabel1.setText("Buscar:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 570, 60, 30);

        jLabel2.setFont(new java.awt.Font("Cambria", 3, 16)); // NOI18N
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 30, 60, 30);

        jLabel3.setFont(new java.awt.Font("Cambria", 3, 16)); // NOI18N
        jLabel3.setText("Unicación");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, 100, 80, 30);

        jLabel4.setFont(new java.awt.Font("Cambria", 3, 16)); // NOI18N
        jLabel4.setText("Capacidad");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, 170, 80, 30);

        txtCapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapacidadActionPerformed(evt);
            }
        });
        jPanel1.add(txtCapacidad);
        txtCapacidad.setBounds(160, 170, 110, 30);

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombre);
        txtNombre.setBounds(160, 30, 390, 30);

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar);
        txtBuscar.setBounds(90, 570, 470, 30);

        cmdAgregar.setText("Agregar");
        cmdAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAgregar);
        cmdAgregar.setBounds(30, 230, 80, 27);

        cmdModificar.setText("Modificar");
        cmdModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdModificarActionPerformed(evt);
            }
        });
        jPanel1.add(cmdModificar);
        cmdModificar.setBounds(130, 230, 85, 27);

        cmdEliminar.setText("Eliminar");
        cmdEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(cmdEliminar);
        cmdEliminar.setBounds(230, 230, 77, 27);

        cmdGuardar.setText("Guardar");
        cmdGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(cmdGuardar);
        cmdGuardar.setBounds(320, 230, 76, 27);

        cmdCancelar.setText("Cancelar");
        cmdCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(cmdCancelar);
        cmdCancelar.setBounds(410, 230, 90, 27);

        txtUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUbicacionActionPerformed(evt);
            }
        });
        jPanel1.add(txtUbicacion);
        txtUbicacion.setBounds(160, 100, 390, 30);

        grdConsultorios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Ubicación", "Capacidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(grdConsultorios);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(16, 276, 550, 280);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCapacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapacidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapacidadActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUbicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacionActionPerformed

    private void cmdModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdModificarActionPerformed
        if (grdConsultorios.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un consultorio de la lista para modificar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        this.txtNombre.setText(this.grdConsultorios.getValueAt(this.grdConsultorios.getSelectedRow(), 1).toString());
        this.txtUbicacion.setText(this.grdConsultorios.getValueAt(this.grdConsultorios.getSelectedRow(), 2).toString());
        this.txtCapacidad.setText(this.grdConsultorios.getValueAt(this.grdConsultorios.getSelectedRow(), 3).toString());
        this.opc = 'M';
        this.habilitarBotones(false);
        this.habilitarCampos(true);
        this.txtNombre.requestFocus();
    }//GEN-LAST:event_cmdModificarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        this.grd.filtrarGrilla(grdConsultorios, this.txtBuscar.getText(), 1);
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void cmdCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelarActionPerformed
        this.limpiarCampos();
        this.habilitarCampos(false);
        this.habilitarBotones(true);
        
    }//GEN-LAST:event_cmdCancelarActionPerformed

    private void cmdEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEliminarActionPerformed
        if (grdConsultorios.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un consultorio de la lista para eliminar.", 
                "Aviso", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idConsultorio = this.grdConsultorios.getValueAt(
                this.grdConsultorios.getSelectedRow(), 0).toString();

        bd.borrarRegistro("consultorios", "id_consultorio=" + idConsultorio);
        this.actualizarGrilla();
    }//GEN-LAST:event_cmdEliminarActionPerformed

    private void cmdGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGuardarActionPerformed
        if (opc == 'N') {
            String valores = "'" + txtNombre.getText() + "', '" + txtUbicacion.getText() + "', " + txtCapacidad.getText();
            bd.insertarRegistro("consultorios(nombre, ubicacion, capacidad)", valores);
        } else {
            String idConsultorio = grdConsultorios.getValueAt(grdConsultorios.getSelectedRow(), 0).toString();
            String campos = "nombre='" + txtNombre.getText() + "', ubicacion='" + txtUbicacion.getText() + "', capacidad=" + txtCapacidad.getText();
            bd.actualizarRegistro("consultorios", campos, "id_consultorio=" + idConsultorio);
        }
        this.opc = 'z';
        this.limpiarCampos();
        this.habilitarCampos(false);
        this.habilitarBotones(true);
        this.actualizarGrilla();
    }//GEN-LAST:event_cmdGuardarActionPerformed

    private void cmdAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAgregarActionPerformed
        this.opc = 'N';
        this.habilitarCampos(true);
        this.habilitarBotones(false);
        this.txtNombre.requestFocus();
    }//GEN-LAST:event_cmdAgregarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmConsultorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmConsultorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmConsultorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmConsultorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmConsultorios dialog = new FrmConsultorios(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton cmdAgregar;
    private javax.swing.JButton cmdCancelar;
    private javax.swing.JButton cmdEliminar;
    private javax.swing.JButton cmdGuardar;
    private javax.swing.JButton cmdModificar;
    private javax.swing.JTable grdConsultorios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}

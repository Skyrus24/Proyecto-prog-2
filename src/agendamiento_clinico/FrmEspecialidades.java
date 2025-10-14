/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package agendamiento_clinico;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import clases.Especialidad;
/**
 *
 * @author Usuario
 */
public class FrmEspecialidades extends javax.swing.JDialog {

    private final BaseDatos bd = new BaseDatos();
    private final Grilla grd = new Grilla();
    private char opc = 'Z';

    public FrmEspecialidades(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        if (!bd.hayConexion()) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
            System.exit(0);
        }
        
        this.setLocationRelativeTo(null);
        
        String[] columnas = {"Código", "Especialidad", "Descripción"};
        int[] anchos = {80, 200, 350};
        grd.configurarmodelo(grdEspecialidades, columnas, anchos);
        
        this.actualizarGrilla();
        this.habilitarBotones(true);
        this.habilitarCampos(false);
    }

    private void habilitarCampos(boolean estado) {
        this.txtCodigo.setEnabled(estado);
        this.txtEspecialidad.setEnabled(estado);
        this.txtaDescripcion.setEnabled(estado);
    }

    private void habilitarBotones(boolean navegando) {
        this.btnAgregar.setEnabled(navegando);
        this.btnActualizar.setEnabled(navegando);
        this.btnBorrar.setEnabled(navegando);
        
        this.btnGuardar.setEnabled(!navegando);
        this.btnCancelar.setEnabled(!navegando);
    }

    private void limpiarCampos() {
        this.txtCodigo.setText("");
        this.txtEspecialidad.setText("");
        this.txtaDescripcion.setText("");
    }
    
    private void actualizarGrilla() {
        String campos[] = {"id_especialidad", "nombre_especialidad", "descripcion"};
        grd.cargarGrilla(grdEspecialidades, "especialidades", campos);
    }


    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaDescripcion = new javax.swing.JTextArea();
        txtEspecialidad = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        grdEspecialidades = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel6.setText("Código:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 230, 227, 30);

        txtaDescripcion.setColumns(20);
        txtaDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtaDescripcion);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 450, 306, 106);

        txtEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEspecialidadActionPerformed(evt);
            }
        });
        txtEspecialidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEspecialidadKeyPressed(evt);
            }
        });
        jPanel1.add(txtEspecialidad);
        txtEspecialidad.setBounds(10, 360, 306, 32);

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });
        jPanel1.add(txtCodigo);
        txtCodigo.setBounds(10, 270, 306, 32);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("Nombre de la Especialidad:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 320, 260, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setText("Descripcion:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 400, 108, 30);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar);
        btnGuardar.setBounds(360, 570, 90, 30);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar);
        btnActualizar.setBounds(600, 570, 100, 30);

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBorrar);
        btnBorrar.setBounds(710, 570, 90, 30);

        grdEspecialidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Especialidad", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grdEspecialidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdEspecialidadesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(grdEspecialidades);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(350, 150, 456, 406);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setText("Buscar:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(350, 80, 65, 30);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar);
        txtBuscar.setBounds(350, 120, 456, 26);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(190, 570, 90, 30);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar);
        btnAgregar.setBounds(40, 570, 100, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setText("Especialidades");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(70, 60, 180, 80);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEspecialidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecialidadActionPerformed

    private void txtEspecialidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspecialidadKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtaDescripcion.requestFocus();
        }
    }//GEN-LAST:event_txtEspecialidadKeyPressed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEspecialidad.requestFocus();
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        grd.filtrarGrilla(grdEspecialidades, this.txtBuscar.getText(), 1);
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        String codigo = this.txtCodigo.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro de la grilla para eliminar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (bd.borrarRegistro("especialidades", "id_especialidad = '" + codigo + "'")) {
            this.actualizarGrilla();
            this.limpiarCampos();
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (this.txtCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro de la grilla para actualizar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        this.opc = 'A';
        this.habilitarBotones(false);
        this.habilitarCampos(true);
        this.txtCodigo.setEnabled(false);
        this.txtEspecialidad.requestFocus();                                           
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void grdEspecialidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdEspecialidadesMouseClicked
        int filaSeleccionada = grdEspecialidades.getSelectedRow();
        if (filaSeleccionada >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) grdEspecialidades.getModel();
            
            this.txtCodigo.setText(modelo.getValueAt(filaSeleccionada, 0).toString());
            this.txtEspecialidad.setText(modelo.getValueAt(filaSeleccionada, 1).toString());
            Object desc = modelo.getValueAt(filaSeleccionada, 2);
            this.txtaDescripcion.setText(desc != null ? desc.toString() : "");
        }
    }//GEN-LAST:event_grdEspecialidadesMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String codigoStr = this.txtCodigo.getText().trim();
        String nombre = this.txtEspecialidad.getText().trim();
        String descripcion = this.txtaDescripcion.getText().trim();

        if (codigoStr.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar el Código y el Nombre de la Especialidad.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código debe ser un número entero válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Especialidad esp = new Especialidad(codigo, nombre, descripcion);

        boolean exito = false;
        if (this.opc == 'N') {
            String campos = "id_especialidad, nombre_especialidad, descripcion";
            String valores = "'" + esp.getId_especialidad() + "', '" + esp.getNombre_especialidad() + "', '" + esp.getDescripcion() + "'";
            exito = bd.insertarRegistro("especialidades", campos, valores);
            
        } else if (this.opc == 'A') {
            String campos = "nombre_especialidad='" + esp.getNombre_especialidad() + "', descripcion='" + esp.getDescripcion() + "'";
            String criterio = "id_especialidad='" + esp.getId_especialidad() + "'";
            exito = bd.actualizarRegistro("especialidades", campos, criterio);
        }

        if (exito) {
            JOptionPane.showMessageDialog(this, "Operación guardada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.actualizarGrilla();
            this.habilitarBotones(true);
            this.habilitarCampos(false);
            this.limpiarCampos();
            this.opc = 'Z';
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limpiarCampos();
        this.habilitarBotones(true);
        this.habilitarCampos(false);
        this.opc = 'Z';
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        this.opc = 'N';
        this.limpiarCampos();
        this.habilitarBotones(false);
        this.habilitarCampos(true);
        this.txtCodigo.requestFocus();
        
    }//GEN-LAST:event_btnAgregarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmEspecialidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEspecialidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEspecialidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEspecialidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmEspecialidades dialog = new FrmEspecialidades(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTable grdEspecialidades;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEspecialidad;
    private javax.swing.JTextArea txtaDescripcion;
    // End of variables declaration//GEN-END:variables
}

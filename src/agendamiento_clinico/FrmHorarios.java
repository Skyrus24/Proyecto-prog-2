
package agendamiento_clinico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmHorarios extends javax.swing.JDialog {
    private BaseDatos bd=new BaseDatos();
    private char opc='z';
    private Grilla grilla=new Grilla();
    private int idHorarioActualizar = 0;

    public FrmHorarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        String[] columnas = {"Día", "Hora Inicio", "Hora Fin", "Válido Desde", "Válido Hasta"};
        int[] anchos = {80, 100, 100, 120, 120};
        grilla.configurarmodelo(grdHorarios, columnas, anchos);

        if(bd.hayConexion()){
            bd.cargarCombo(cboMedicos, "id_medico, CONCAT(nombre, ' ', apellidos)", "medicos");
            this.habilitar(false);
        }else{
            JOptionPane.showMessageDialog(null, "Error de Conexión con la Base de Datos");
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboMedicos = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHoraInicio = new javax.swing.JFormattedTextField();
        txtFechaFin = new javax.swing.JFormattedTextField();
        txtFechaInicio = new javax.swing.JFormattedTextField();
        txtHoraFin = new javax.swing.JFormattedTextField();
        cboDiaSemana = new javax.swing.JComboBox<>();
        cmdQuitar = new javax.swing.JButton();
        cmdInsertar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdHorarios = new javax.swing.JTable();
        cmdGuardar = new javax.swing.JButton();
        cmdCancelar = new javax.swing.JButton();
        cmdEliminar = new javax.swing.JButton();
        cmdModificar = new javax.swing.JButton();
        cmdNuevo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Seleccion del Médico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 3, 14))); // NOI18N
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel1.setText("Médico:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(20, 41, 55, 25);

        jPanel2.add(cboMedicos);
        cboMedicos.setBounds(87, 40, 935, 26);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Detalles del Horario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 3, 14))); // NOI18N
        jPanel3.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel2.setText("Día de la Semana:");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(33, 38, 140, 25);

        jLabel3.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel3.setText("Hora Inicio (HH:MM):");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(397, 38, 150, 25);

        jLabel4.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel4.setText("Fecha Inicio (AAAA-MM-DD):");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(33, 82, 190, 25);

        jLabel5.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel5.setText("Fecha Fin (AAAA-MM-DD):");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(397, 82, 180, 25);

        jLabel6.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel6.setText("Hora Fin (HH:MM):");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(725, 38, 140, 25);

        try {
            txtHoraInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHoraInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtHoraInicio);
        txtHoraInicio.setBounds(577, 37, 130, 26);

        try {
            txtFechaFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFechaFin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtFechaFin);
        txtFechaFin.setBounds(577, 81, 130, 26);

        try {
            txtFechaInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFechaInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtFechaInicio);
        txtFechaInicio.setBounds(222, 81, 130, 26);
        txtFechaInicio.getAccessibleContext().setAccessibleName("");

        try {
            txtHoraFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHoraFin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtHoraFin);
        txtHoraFin.setBounds(893, 37, 130, 26);

        cboDiaSemana.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" }));
        jPanel3.add(cboDiaSemana);
        cboDiaSemana.setBounds(222, 37, 130, 26);

        cmdQuitar.setText("Quitar Horario");
        cmdQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdQuitarActionPerformed(evt);
            }
        });
        jPanel3.add(cmdQuitar);
        cmdQuitar.setBounds(779, 78, 120, 30);

        cmdInsertar.setText("Agregar Horario");
        cmdInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdInsertarActionPerformed(evt);
            }
        });
        jPanel3.add(cmdInsertar);
        cmdInsertar.setBounds(902, 78, 130, 30);

        grdHorarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Día", "Hora Inicio", "Hora Fin", "Válido Desde", "Válido Hasta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(grdHorarios);
        if (grdHorarios.getColumnModel().getColumnCount() > 0) {
            grdHorarios.getColumnModel().getColumn(0).setHeaderValue("Día");
            grdHorarios.getColumnModel().getColumn(1).setHeaderValue("Hora Inicio");
            grdHorarios.getColumnModel().getColumn(2).setHeaderValue("Hora Fin");
            grdHorarios.getColumnModel().getColumn(3).setHeaderValue("Válido Desde");
            grdHorarios.getColumnModel().getColumn(4).setHeaderValue("Válido Hasta");
        }

        cmdGuardar.setText("Guardar Cambios");
        cmdGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGuardarActionPerformed(evt);
            }
        });

        cmdCancelar.setText("Cancelar");
        cmdCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelarActionPerformed(evt);
            }
        });

        cmdEliminar.setText("Eliminar");
        cmdEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEliminarActionPerformed(evt);
            }
        });

        cmdModificar.setText("Modificar");
        cmdModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdModificarActionPerformed(evt);
            }
        });

        cmdNuevo.setText("Nuevo");
        cmdNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1059, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1059, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1059, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(cmdModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(cmdEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(cmdCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(cmdGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdQuitarActionPerformed
        if (opc == 'M') {
             JOptionPane.showMessageDialog(this, "En modo modificación no se puede quitar de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
             return;
        }
        if (this.grdHorarios.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla para quitarla.");
        } else {
            DefaultTableModel modelo = (DefaultTableModel) this.grdHorarios.getModel();
            modelo.removeRow(this.grdHorarios.getSelectedRow());
        }
    }//GEN-LAST:event_cmdQuitarActionPerformed

    private void cmdNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNuevoActionPerformed
        this.opc='N';
        this.habilitar(true);
        this.limpiar();
        this.cboMedicos.requestFocus();
    }//GEN-LAST:event_cmdNuevoActionPerformed

    private void cmdModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdModificarActionPerformed
        FrmConsultaHorarios consulta = new FrmConsultaHorarios(null, true);
        consulta.setVisible(true);

        int idParaModificar = consulta.obtenerIdSeleccionado();

        if (idParaModificar > 0) {
            this.opc = 'M';
            this.idHorarioActualizar = idParaModificar;
            
            try {
                ResultSet rs = bd.consultarRegistros("SELECT * FROM horarios WHERE id_horario = " + idParaModificar);
                if (rs.first()) {
                    // Seleccionar el médico en el combo
                    int idMedico = rs.getInt("id_medico");
                    for (int i = 0; i < cboMedicos.getItemCount(); i++) {
                        DatosCombo item = (DatosCombo) cboMedicos.getItemAt(i);
                        if (item.getCodigo() == idMedico) {
                            cboMedicos.setSelectedIndex(i);
                            break;
                        }
                    }
                    
                    // Seleccionar el día de la semana
                    cboDiaSemana.setSelectedIndex(rs.getInt("dia_semana") - 1);
                    
                    // Llenar los campos de texto
                    txtHoraInicio.setText(rs.getString("hora_inicio"));
                    txtHoraFin.setText(rs.getString("hora_fin"));
                    txtFechaInicio.setText(rs.getString("fecha_inicio_validez"));
                    txtFechaFin.setText(rs.getString("fecha_fin_validez"));
                    
                    habilitar(true);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cargar los datos del horario: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_cmdModificarActionPerformed

    private void cmdInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdInsertarActionPerformed
        if (opc == 'M') {
            JOptionPane.showMessageDialog(this, "En modo modificación no se puede agregar a la tabla.\nModifique los datos y guarde los cambios.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtHoraInicio.getText().trim().replace(":", "").isEmpty() || txtHoraFin.getText().trim().replace(":", "").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar la hora de inicio y fin.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String fechaInicioStr = txtFechaInicio.getText().trim();
        String fechaFinStr = txtFechaFin.getText().trim();

        // Validar formato de fecha
        if (fechaInicioStr.length() < 10 || fechaFinStr.length() < 10) {
            JOptionPane.showMessageDialog(this, "Debe ingresar las fechas en formato AAAA-MM-DD.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalDate fechaInicio, fechaFin, hoy = LocalDate.now();
        try {
            fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ISO_LOCAL_DATE);
            fechaFin = LocalDate.parse(fechaFinStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "La fecha ingresada no es válida. Verifique mes y día.\nFormato: AAAA-MM-DD", "Error de Fecha", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 🔹 Validaciones de rango de fechas
        if (fechaInicio.isAfter(fechaFin)) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser posterior a la fecha de fin.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (fechaInicio.isBefore(hoy) || fechaFin.isBefore(hoy)) {
            JOptionPane.showMessageDialog(this, "Las fechas no pueden ser anteriores al día actual.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (fechaInicio.plusYears(1).isBefore(fechaFin)) {
            JOptionPane.showMessageDialog(this, "El rango de fechas no puede superar un año.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String dia = this.cboDiaSemana.getSelectedItem().toString();
        String horaInicio = this.txtHoraInicio.getText();
        String horaFin = this.txtHoraFin.getText();

        // 🔹 Validar orden lógico de horas
        LocalTime hInicio, hFin;
        try {
            hInicio = LocalTime.parse(horaInicio);
            hFin = LocalTime.parse(horaFin);
            if (!hFin.isAfter(hInicio)) {
                JOptionPane.showMessageDialog(this, "La hora de fin debe ser posterior a la hora de inicio.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Formato de hora no válido. Use HH:MM", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 🔹 Validar duplicado exacto (mismo día, mismo rango)
        for (int i = 0; i < grdHorarios.getRowCount(); i++) {
            if (grdHorarios.getValueAt(i, 0).toString().equals(dia) &&
                grdHorarios.getValueAt(i, 1).toString().equals(horaInicio) &&
                grdHorarios.getValueAt(i, 2).toString().equals(horaFin)) {
                JOptionPane.showMessageDialog(grdHorarios, "Este horario ya fue agregado para el médico.", "Horario Duplicado", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // 🔹 Validar solapamiento de horarios en el mismo día
        for (int i = 0; i < grdHorarios.getRowCount(); i++) {
            String diaExistente = grdHorarios.getValueAt(i, 0).toString();
            if (!diaExistente.equals(dia)) continue; // Solo comparar si es el mismo día
            LocalTime inicioExistente = LocalTime.parse(grdHorarios.getValueAt(i, 1).toString());
            LocalTime finExistente = LocalTime.parse(grdHorarios.getValueAt(i, 2).toString());
            // Verificamos si los rangos se superponen
            boolean solapan = 
                (hInicio.isBefore(finExistente) && hFin.isAfter(inicioExistente)) ||
                hInicio.equals(inicioExistente) || hFin.equals(finExistente);
            if (solapan) {
                JOptionPane.showMessageDialog(this,
                    String.format("El nuevo horario (%s-%s) se solapa con un horario existente (%s-%s) del mismo día (%s).",
                        horaInicio, horaFin, inicioExistente, finExistente, dia),
                    "Conflicto de Horarios", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // ✅ Si pasa todas las validaciones, agregar a la tabla
        DefaultTableModel dm = (DefaultTableModel) grdHorarios.getModel();
        Object[] filaAgregar = {dia, horaInicio, horaFin, fechaInicioStr, fechaFinStr};
        dm.addRow(filaAgregar);
    }//GEN-LAST:event_cmdInsertarActionPerformed

    private void cmdGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGuardarActionPerformed
        if (cboMedicos.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un médico.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (opc == 'N') { 
            if (grdHorarios.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Debe agregar al menos un horario en la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            guardarNuevosHorarios();
        } else if (opc == 'M') {
            actualizarHorario();
        }
        this.limpiar();
        this.habilitar(false);
        this.opc = 'z';
        this.idHorarioActualizar = 0;
    }//GEN-LAST:event_cmdGuardarActionPerformed

    private void cmdCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelarActionPerformed
        this.limpiar();
        this.habilitar(false);
        this.opc = 'z';
        this.idHorarioActualizar = 0;
    }//GEN-LAST:event_cmdCancelarActionPerformed

    private void cmdEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEliminarActionPerformed
        FrmConsultaHorarios consulta = new FrmConsultaHorarios(null, true);
        consulta.setVisible(true);
        int idParaBorrar = consulta.obtenerIdSeleccionado();
        if (idParaBorrar > 0) {
                if (bd.borrarRegistro("horarios", "id_horario = " + idParaBorrar)) {
                    JOptionPane.showMessageDialog(this, "Horario eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_cmdEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmHorarios dialog = new FrmHorarios(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cboDiaSemana;
    private javax.swing.JComboBox<Object> cboMedicos;
    private javax.swing.JButton cmdCancelar;
    private javax.swing.JButton cmdEliminar;
    private javax.swing.JButton cmdGuardar;
    private javax.swing.JButton cmdInsertar;
    private javax.swing.JButton cmdModificar;
    private javax.swing.JButton cmdNuevo;
    private javax.swing.JButton cmdQuitar;
    private javax.swing.JTable grdHorarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField txtFechaFin;
    private javax.swing.JFormattedTextField txtFechaInicio;
    private javax.swing.JFormattedTextField txtHoraFin;
    private javax.swing.JFormattedTextField txtHoraInicio;
    // End of variables declaration//GEN-END:variables

    private void habilitar(boolean estado){
        this.cboMedicos.setEnabled(estado);
        this.cboDiaSemana.setEnabled(estado);
        this.txtHoraInicio.setEnabled(estado);
        this.txtHoraFin.setEnabled(estado);
        this.txtFechaInicio.setEnabled(estado);
        this.txtFechaFin.setEnabled(estado);
        
        this.cmdInsertar.setEnabled(estado && opc != 'M');
        this.cmdQuitar.setEnabled(estado && opc != 'M');
        this.grdHorarios.setEnabled(estado && opc != 'M');

        this.cmdGuardar.setEnabled(estado);
        this.cmdCancelar.setEnabled(estado);

        this.cmdNuevo.setEnabled(!estado);
        this.cmdModificar.setEnabled(!estado);
        this.cmdEliminar.setEnabled(!estado);
    }



    private void limpiar(){
        this.txtHoraInicio.setText("");
        this.txtHoraFin.setText("");
        this.txtFechaInicio.setText("");
        this.txtFechaFin.setText("");
        this.cboMedicos.setSelectedIndex(-1);
        this.cboDiaSemana.setSelectedIndex(0);

        DefaultTableModel modelo = (DefaultTableModel) this.grdHorarios.getModel();
        while(modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
    
    private void actualizarHorario() {
        DatosCombo cboMedicoSeleccionado = (DatosCombo) this.cboMedicos.getSelectedItem();
        int idMedico = cboMedicoSeleccionado.getCodigo();
        String diaTexto = cboDiaSemana.getSelectedItem().toString();
        int diaNumero = getNumeroDia(diaTexto);
        
        String campos = String.format("id_medico=%d, dia_semana=%d, hora_inicio='%s', hora_fin='%s', fecha_inicio_validez='%s', fecha_fin_validez='%s'",
            idMedico,
            diaNumero,
            txtHoraInicio.getText(),
            txtHoraFin.getText(),
            txtFechaInicio.getText(),
            txtFechaFin.getText()
        );
        
        String criterio = "id_horario = " + this.idHorarioActualizar;
        
        if (bd.actualizarRegistro("horarios", campos, criterio)) {
            JOptionPane.showMessageDialog(this, "Horario actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void guardarNuevosHorarios() {
        DatosCombo cboMedicoSeleccionado = (DatosCombo) this.cboMedicos.getSelectedItem();
        int idMedico = cboMedicoSeleccionado.getCodigo();

        // Usamos una sola variable booleana, como en tu ejemplo
        boolean inserta = true; 

        // Definimos los campos una sola vez fuera del bucle
        String campos = "id_medico, dia_semana, hora_inicio, hora_fin, fecha_inicio_validez, fecha_fin_validez";

        // Recorremos la grilla para insertar cada horario
        for (int i = 0; i < this.grdHorarios.getRowCount(); i++) {
            String diaTexto = this.grdHorarios.getValueAt(i, 0).toString();
            int diaNumero = getNumeroDia(diaTexto);
            String horaInicio = this.grdHorarios.getValueAt(i, 1).toString();
            String horaFin = this.grdHorarios.getValueAt(i, 2).toString();
            String fechaInicio = this.grdHorarios.getValueAt(i, 3).toString();
            String fechaFin = this.grdHorarios.getValueAt(i, 4).toString();

            String valores = String.format("%d, %d, '%s', '%s', '%s', '%s'",
                    idMedico, diaNumero, horaInicio, horaFin, fechaInicio, fechaFin);

            // Intentamos insertar el registro
            inserta = bd.insertarRegistro("horarios", campos, valores);

            // Si la inserción falla (inserta == false), mostramos un error y salimos del bucle
            if (!inserta) {
                JOptionPane.showMessageDialog(cmdGuardar, "Error al intentar guardar el horario de: " + diaTexto + " a las " + horaInicio);
                break; // Detenemos el proceso
            }
        }

        // Si 'inserta' sigue siendo true, significa que todas las inserciones fueron exitosas
        if (inserta) {
            JOptionPane.showMessageDialog(this, "¡Todos los horarios han sido guardados correctamente!", "Proceso Exitoso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private int getNumeroDia(String diaTexto) {
        switch (diaTexto) {
            case "Lunes": 
                return 1;
            case "Martes": 
                return 2;
            case "Miércoles": 
                return 3;
            case "Jueves": 
                return 4;
            case "Viernes": 
                return 5;
            case "Sábado": 
                return 6;
            case "Domingo": 
                return 7;
            default: 
                return 0;
        }
    }
}

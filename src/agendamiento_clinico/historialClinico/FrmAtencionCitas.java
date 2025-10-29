package agendamiento_clinico.historialClinico;

import agendamiento_clinico.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class FrmAtencionCitas extends javax.swing.JDialog {

    // Variables de la clase
    private final BaseDatos bd = new BaseDatos();
    private final int idCita;
    private int idPaciente; // Guardaremos el ID del paciente para el historial
    private int idMedico;   // Guardaremos el ID del médico para el historial
    private final int idHistorial; // 0 si es nuevo, >0 si es para modificar
    
    // Referencia al formulario padre para poder refrescarlo
    private final FrmConsulta formPadre;

    public FrmAtencionCitas(java.awt.Frame parent, boolean modal, int idCita, FrmConsulta formPadre) {
        super(parent, modal);
        this.idCita = idCita;
        this.idHistorial = 0; // Se establece en 0 para indicar que es NUEVO
        this.formPadre = formPadre;
        
        initComponents();
        this.setLocationRelativeTo(parent);
        this.setTitle("Registro de Atención Clínica - Cita ID: " + idCita);
        this.txtFechaCita.setEnabled(false);
        this.txtMedico.setEnabled(false);
        this.txtPaciente.setEnabled(false);
        
        if (bd.hayConexion()) {
            cargarDatosCita(); // Carga los datos de la cita (paciente, médico)
        } else {
             JOptionPane.showMessageDialog(this, "Error de Conexión con la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
             this.dispose();
        }
    }

    /**
     * CONSTRUCTOR 2: Para MODIFICAR un historial existente desde FrmConsultaHistorial.
     * @param parent El Frame padre
     * @param modal Si la ventana es modal
     * @param idHistorial El ID del historial a modificar
     */
    public FrmAtencionCitas(java.awt.Frame parent, boolean modal, int idHistorial) {
        super(parent, modal);
        this.idCita = 0; // No se basa en una cita nueva
        this.idHistorial = idHistorial;
        this.formPadre = null; // No hay formulario de citas que refrescar

        initComponents();
        this.setLocationRelativeTo(parent);
        this.setTitle("Modificar Registro de Atención - Historial ID: " + idHistorial);
        this.txtFechaCita.setVisible(false);
        this.label.setVisible(false);
        this.txtMedico.setEnabled(false);
        this.txtPaciente.setEnabled(false);

        // Cambiamos el texto del botón para que el usuario sepa que está editando
        cmdGuardar.setText("Guardar Cambios");

        if (bd.hayConexion()) {
            cargarDatosHistorial(); // Carga los datos del historial existente
        } else {
             JOptionPane.showMessageDialog(this, "Error de Conexión con la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
             this.dispose();
        }
    }
    
    /**
     * Carga los datos de la cita desde la BD y los muestra en los campos bloqueados.
     */
    private void cargarDatosCita() {
        String sql = "SELECT c.id_paciente, c.id_medico, " +
                     "CONCAT(p.nombre, ' ', p.apellidos) AS nombre_paciente, " +
                     "CONCAT(m.nombre, ' ', m.apellidos) AS nombre_medico, " +
                     "c.fecha_hora_inicio " +
                     "FROM citas c " +
                     "JOIN pacientes p ON c.id_paciente = p.id_paciente " +
                     "JOIN medicos m ON c.id_medico = m.id_medico " +
                     "WHERE c.id_cita = " + this.idCita;
        
        try {
            ResultSet rs = bd.consultarRegistros(sql);
            if (rs.next()) {
                // Guardamos los IDs para usarlos al guardar el historial
                this.idPaciente = rs.getInt("id_paciente");
                this.idMedico = rs.getInt("id_medico");
                
                // Mostramos la información en los campos de texto
                txtPaciente.setText(rs.getString("nombre_paciente"));
                txtMedico.setText(rs.getString("nombre_medico"));
                txtFechaCita.setText(rs.getString("fecha_hora_inicio"));
                
                // Bloqueamos los campos para que no se puedan editar
                txtPaciente.setEditable(false);
                txtMedico.setEditable(false);
                txtFechaCita.setEditable(false);
                
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron los datos de la cita seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos de la cita: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    private void cargarDatosHistorial() {
        String sql = "SELECT h.*, " +
                     "CONCAT(p.nombre, ' ', p.apellidos) AS nombre_paciente, " +
                     "CONCAT(m.nombre, ' ', m.apellidos) AS nombre_medico " +
                     "FROM historial_clinico h " +
                     "JOIN pacientes p ON h.id_paciente = p.id_paciente " +
                     "JOIN medicos m ON h.id_medico = m.id_medico " +
                     "WHERE h.id_historial = " + this.idHistorial;

        try {
            ResultSet rs = bd.consultarRegistros(sql);
            if (rs.next()) {
                // Guardamos los IDs para usarlos al actualizar
                this.idPaciente = rs.getInt("id_paciente");
                this.idMedico = rs.getInt("id_medico");

                // Mostramos la información
                txtPaciente.setText(rs.getString("nombre_paciente"));
                txtMedico.setText(rs.getString("nombre_medico"));

                // Aquí necesitaríamos mostrar la fecha de la cita original, o podríamos ocultar el campo
                txtFechaCita.setText("N/A - Modo Edición"); 

                // Bloqueamos los campos que no deben cambiar
                txtPaciente.setEditable(false);
                txtMedico.setEditable(false);
                txtFechaCita.setEditable(false);

                // Rellenamos las áreas de texto con los datos guardados
                txaAntecedentes.setText(rs.getString("antecedentes"));
                txaDiagnostico.setText(rs.getString("diagnostico"));
                txaTratamiento.setText(rs.getString("tratamiento"));
                txaResultadosExamenes.setText(rs.getString("resultados_examenes"));
                txaNotasEvolucion.setText(rs.getString("notas_evolucion"));
                txaEnfermedades.setText(rs.getString("enfermedades"));

            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron los datos del historial.", "Error", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos del historial: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMedico = new javax.swing.JTextField();
        txtPaciente = new javax.swing.JTextField();
        txtFechaCita = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaTratamiento = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaAntecedentes = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaDiagnostico = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txaResultadosExamenes = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txaNotasEvolucion = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        txaEnfermedades = new javax.swing.JTextArea();
        cmdGuardar = new javax.swing.JButton();
        cmdCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Detalles de la Atención", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 18))); // NOI18N
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabel1.setText("Médico:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(580, 40, 70, 24);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabel2.setText("Enfermedades:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(740, 340, 120, 24);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabel3.setText("Paciente:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 40, 80, 24);

        label.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        label.setText("Fecha de Cita:");
        jPanel1.add(label);
        label.setBounds(10, 90, 120, 24);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabel5.setText("Antecedentes:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 150, 120, 24);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabel6.setText("Diagnóstico: (*)");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(380, 150, 130, 24);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabel7.setText("Tratamiento: (*)");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(740, 150, 130, 24);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabel8.setText("Resultados Exámenes:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(10, 340, 180, 24);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabel9.setText("Notas de Evolución:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(380, 340, 160, 24);

        txtMedico.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jPanel1.add(txtMedico);
        txtMedico.setBounds(650, 40, 420, 32);

        txtPaciente.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jPanel1.add(txtPaciente);
        txtPaciente.setBounds(140, 40, 420, 32);

        txtFechaCita.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jPanel1.add(txtFechaCita);
        txtFechaCita.setBounds(140, 90, 420, 32);

        txaTratamiento.setColumns(20);
        txaTratamiento.setRows(5);
        jScrollPane1.setViewportView(txaTratamiento);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(740, 180, 330, 150);

        txaAntecedentes.setColumns(20);
        txaAntecedentes.setRows(5);
        jScrollPane2.setViewportView(txaAntecedentes);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(10, 180, 340, 150);

        txaDiagnostico.setColumns(20);
        txaDiagnostico.setRows(5);
        jScrollPane3.setViewportView(txaDiagnostico);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(380, 180, 330, 150);

        txaResultadosExamenes.setColumns(20);
        txaResultadosExamenes.setRows(5);
        jScrollPane4.setViewportView(txaResultadosExamenes);

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(10, 370, 340, 150);

        txaNotasEvolucion.setColumns(20);
        txaNotasEvolucion.setRows(5);
        jScrollPane5.setViewportView(txaNotasEvolucion);

        jPanel1.add(jScrollPane5);
        jScrollPane5.setBounds(380, 370, 330, 150);

        txaEnfermedades.setColumns(20);
        txaEnfermedades.setRows(5);
        jScrollPane6.setViewportView(txaEnfermedades);

        jPanel1.add(jScrollPane6);
        jScrollPane6.setBounds(740, 370, 330, 150);

        cmdGuardar.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        cmdGuardar.setText("Guardar y Finalizar Atención");
        cmdGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGuardarActionPerformed(evt);
            }
        });

        cmdCancelar.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        cmdCancelar.setText("Cancelar");
        cmdCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmdGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(cmdCancelar)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1087, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdGuardar)
                    .addComponent(cmdCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGuardarActionPerformed
    // 1. Validar campos
        if (txaDiagnostico.getText().trim().isEmpty() || txaTratamiento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar los campos obligatorios (*):\nDiagnóstico y Tratamiento.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Preparar los datos
        String antecedentes = txaAntecedentes.getText().replace("'", "''");
        String diagnostico = txaDiagnostico.getText().replace("'", "''");
        String enfermedades = txaEnfermedades.getText().replace("'", "''");
        String tratamiento = txaTratamiento.getText().replace("'", "''");
        String resultadosExamenes = txaResultadosExamenes.getText().replace("'", "''");
        String notasEvolucion = txaNotasEvolucion.getText().replace("'", "''");

        boolean exito = false;

        // AQUÍ ESTÁ LA LÓGICA CLAVE
        if (idHistorial == 0) { // MODO NUEVO: Insertar
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaRegistro = formatoFecha.format(new Date());

            String columnas = "id_cita, id_paciente, id_medico, fecha_registro, antecedentes, diagnostico, enfermedades, tratamiento, resultados_examenes, notas_evolucion";
            String valores = String.format("%d, %d, %d, '%s', '%s', '%s', '%s', '%s', '%s', '%s'",
                this.idCita, this.idPaciente, this.idMedico, fechaRegistro,
                antecedentes, diagnostico, enfermedades, tratamiento, resultadosExamenes, notasEvolucion);

            exito = bd.insertarRegistro("historial_clinico", columnas, valores);

            if (exito) {
                // Si el historial se guardó, actualizar el estado de la cita
                String camposUpdateCita = "estado_cita = 'Atendida'";
                String criterioUpdateCita = "id_cita = " + this.idCita;
                bd.actualizarRegistro("citas", camposUpdateCita, criterioUpdateCita);
            }

        } else { // MODO MODIFICAR: Actualizar
            String camposUpdate = String.format(
                "antecedentes='%s', diagnostico='%s', enfermedades='%s', tratamiento='%s', " +
                "resultados_examenes='%s', notas_evolucion='%s'",
                antecedentes, diagnostico, enfermedades, tratamiento, resultadosExamenes, notasEvolucion
            );
            String criterio = "id_historial = " + this.idHistorial;
            exito = bd.actualizarRegistro("historial_clinico", camposUpdate, criterio);
        }

        // 3. Mostrar resultado y cerrar
        if (exito) {
            JOptionPane.showMessageDialog(this, "Operación realizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Refrescar la tabla del formulario padre si existe
            if (this.formPadre != null) {
                this.formPadre.cargarCitasProgramadas();
            }
            // Aquí llamaríamos a refrescar el form de gestión de historiales si lo pasamos como parámetro

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cmdGuardarActionPerformed

    private void cmdCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdCancelarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCancelar;
    private javax.swing.JButton cmdGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel label;
    private javax.swing.JTextArea txaAntecedentes;
    private javax.swing.JTextArea txaDiagnostico;
    private javax.swing.JTextArea txaEnfermedades;
    private javax.swing.JTextArea txaNotasEvolucion;
    private javax.swing.JTextArea txaResultadosExamenes;
    private javax.swing.JTextArea txaTratamiento;
    private javax.swing.JTextField txtFechaCita;
    private javax.swing.JTextField txtMedico;
    private javax.swing.JTextField txtPaciente;
    // End of variables declaration//GEN-END:variables
}

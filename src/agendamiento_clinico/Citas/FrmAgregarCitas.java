package agendamiento_clinico.Citas;

import agendamiento_clinico.BaseDatos;
import agendamiento_clinico.DatosCombo;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;


public class FrmAgregarCitas extends javax.swing.JDialog {
    BaseDatos bd = new BaseDatos();
    private java.util.List<String> listaPacientes;
    private java.util.List<String> listaMedicos;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmAgregarCitas.class.getName());
    public FrmAgregarCitas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarFiltroPacientes();
        inicializarFiltroMedicos();
        cboPacientes.setSelectedItem(null);
        cboMedicos.setSelectedItem(null);
        
        //Listener de cboMedicos par aactualizar
        cboMedicos.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object medicoSeleccionado = cboMedicos.getSelectedItem();
            if (medicoSeleccionado != null && !medicoSeleccionado.toString().isEmpty()) {
                cargarHorariosMedico(medicoSeleccionado.toString());
                }
            }
        });
        
        ActionListener recargarHorarios = e -> {
        Object medicoSeleccionado = cboMedicos.getSelectedItem();
        if (medicoSeleccionado != null && !medicoSeleccionado.toString().isEmpty()) {
            cargarHorariosMedico(medicoSeleccionado.toString());
        }
    };

        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        cboPacientes = new javax.swing.JComboBox<>();
        cboMedicos = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboInicioHora = new javax.swing.JComboBox<>();
        cboFinHora = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        cboEstado = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cboPacientes.setEditable(true);
        cboPacientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboPacientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cboPacientesKeyReleased(evt);
            }
        });

        cboMedicos.setEditable(true);
        cboMedicos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMedicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cboMedicosKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel7.setText("Fecha");

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel6.setText("Horario Fin");

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel5.setText("Horario Inicio");

        cboInicioHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboInicioHoraActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel1.setText("Cita N°");

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel2.setText("Paciente");

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel4.setText("Medico");

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel3.setText("Motivo");

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Programada", "Atendido", "Pendiente", "Cancelado", "No asistió", "Confirmada", " " }));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel8.setText("Estado");

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primera vez", "Seguimiento", "Urgencias", "Control" }));

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel9.setText("Tipo");

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane1.setViewportView(txtObservaciones);

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel10.setText("Observaciones");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(txtMotivo)
                    .addComponent(cboMedicos, 0, 362, Short.MAX_VALUE)
                    .addComponent(cboPacientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboInicioHora, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboFinHora, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(cboInicioHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboFinHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(cboPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(0, 35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboMedicosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboMedicosKeyReleased
        String texto = cboMedicos.getEditor().getItem().toString().trim();
        inicializarFiltroMedicos();
    }//GEN-LAST:event_cboMedicosKeyReleased

    private void cboPacientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboPacientesKeyReleased
        String texto = cboPacientes.getEditor().getItem().toString().trim();
        inicializarFiltroPacientes();
    }//GEN-LAST:event_cboPacientesKeyReleased

    private void cboInicioHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboInicioHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboInicioHoraActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try (Connection conexion = bd.miConexion()) {
            // 🔹 Validar campos obligatorios
            if (cboPacientes.getSelectedItem() == null || cboMedicos.getSelectedItem() == null ||
                dcFecha.getDate() == null || cboInicioHora.getSelectedItem() == null || cboFinHora.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos obligatorios.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String paciente = cboPacientes.getSelectedItem().toString();
            String medico = cboMedicos.getSelectedItem().toString();
            String motivo = txtMotivo.getText().trim();
            String estado = cboEstado.getSelectedItem().toString();
            String tipo = cboTipo.getSelectedItem().toString();
            String observaciones = txtObservaciones.getText().trim();

            // 🔹 Convertimos la fecha y las horas seleccionadas
            java.time.LocalDate fecha = dcFecha.getDate()
                    .toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();

            String horaInicioStr = cboInicioHora.getSelectedItem().toString();
            String horaFinStr = cboFinHora.getSelectedItem().toString();

            java.time.LocalDateTime fechaHoraInicio = java.time.LocalDateTime.parse(fecha + "T" + horaInicioStr);
            java.time.LocalDateTime fechaHoraFin = java.time.LocalDateTime.parse(fecha + "T" + horaFinStr);

            // 🔹 Validar que la hora de fin sea posterior
            if (!fechaHoraFin.isAfter(fechaHoraInicio)) {
                JOptionPane.showMessageDialog(this, "La hora de fin debe ser posterior a la hora de inicio.", "Error de horario", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 🔹 Buscar el id del médico
            int idMedico = -1;
            try (PreparedStatement ps = conexion.prepareStatement("SELECT id_medico FROM medicos WHERE CONCAT(nombre, ' ', apellidos) = ?")) {
                ps.setString(1, medico);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) idMedico = rs.getInt("id_medico");
                rs.close();
            }

            if (idMedico == -1) {
                JOptionPane.showMessageDialog(this, "No se encontró el médico seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 🔹 Verificar que la cita esté dentro del horario laboral del médico
            int diaSemana = fecha.getDayOfWeek().getValue(); // 1 = Lunes, ..., 7 = Domingo
            boolean dentroDeHorario = false;
            try (PreparedStatement psHorario = conexion.prepareStatement(
                    "SELECT hora_inicio, hora_fin FROM horarios " +
                    "WHERE id_medico = ? AND dia_semana = ? AND fecha_inicio_validez <= ? AND fecha_fin_validez >= ?")) {
                psHorario.setInt(1, idMedico);
                psHorario.setInt(2, diaSemana);
                psHorario.setDate(3, java.sql.Date.valueOf(fecha));
                psHorario.setDate(4, java.sql.Date.valueOf(fecha));

                ResultSet rsHorario = psHorario.executeQuery();
                while (rsHorario.next()) {
                    java.time.LocalTime inicioPermitido = java.time.LocalTime.parse(rsHorario.getString("hora_inicio"));
                    java.time.LocalTime finPermitido = java.time.LocalTime.parse(rsHorario.getString("hora_fin"));
                    if (!fechaHoraInicio.toLocalTime().isBefore(inicioPermitido) &&
                        !fechaHoraFin.toLocalTime().isAfter(finPermitido)) {
                        dentroDeHorario = true;
                        break;
                    }
                }
                rsHorario.close();
            }

            if (!dentroDeHorario) {
                JOptionPane.showMessageDialog(this, "La cita está fuera del horario laboral del médico para ese día.", "Horario inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 🔹 Verificar solapamiento con otras citas del mismo médico
            try (PreparedStatement psCita = conexion.prepareStatement(
                    "SELECT COUNT(*) FROM citas WHERE id_medico = ? " +
                    "AND ((? < fecha_hora_fin) AND (? > fecha_hora_inicio))")) {
                psCita.setInt(1, idMedico);
                psCita.setTimestamp(2, java.sql.Timestamp.valueOf(fechaHoraFin));
                psCita.setTimestamp(3, java.sql.Timestamp.valueOf(fechaHoraInicio));
                ResultSet rsCita = psCita.executeQuery();
                if (rsCita.next() && rsCita.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "El médico ya tiene una cita asignada en este horario.", "Conflicto de horario", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                rsCita.close();
            }

            // 🔹 Obtener id_paciente
            int idPaciente = -1;
            try (PreparedStatement ps = conexion.prepareStatement("SELECT id_paciente FROM pacientes WHERE CONCAT(nombre, ' ', apellidos) = ?")) {
                ps.setString(1, paciente);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) idPaciente = rs.getInt("id_paciente");
                rs.close();
            }

            if (idPaciente == -1) {
                JOptionPane.showMessageDialog(this, "No se encontró el paciente seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 🔹 Insertar la cita (usando las columnas correctas)
            String sqlInsert = "INSERT INTO citas (id_paciente, id_medico, fecha_hora_inicio, fecha_hora_fin, motivo_consulta, estado_cita, tipo_cita, observaciones) " +
                               "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement psInsert = conexion.prepareStatement(sqlInsert)) {
                psInsert.setInt(1, idPaciente);
                psInsert.setInt(2, idMedico);
                psInsert.setTimestamp(3, java.sql.Timestamp.valueOf(fechaHoraInicio));
                psInsert.setTimestamp(4, java.sql.Timestamp.valueOf(fechaHoraFin));
                psInsert.setString(5, motivo);
                psInsert.setString(6, estado);
                psInsert.setString(7, tipo);
                psInsert.setString(8, observaciones);
                psInsert.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Cita registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la cita: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    
    private void inicializarFiltroPacientes() {
        listaPacientes = new ArrayList<>();
        // 🔹 Obtener los nombres de pacientes desde la base de datos
        try {
            Connection conexion = bd.miConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre, apellidos FROM pacientes"); // <-- Ajusta el nombre de tu tabla/columna si difiere
            while (rs.next()) {
                listaPacientes.add(rs.getString("nombre")+" " + rs.getString("apellidos"));
            }
            rs.close();
            st.close();
            conexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar pacientes: " + e.getMessage());
        }
        // 🔹 Cargar al combo existente
        cboPacientes.removeAllItems();
        for (String p : listaPacientes) {
            cboPacientes.addItem(p);
        }
        // 🔹 Activar filtrado dinámico
        cboPacientes.setEditable(true);
        JTextField editor = (JTextField) cboPacientes.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = editor.getText();
                cboPacientes.hidePopup();
                cboPacientes.removeAllItems();
                String textoNormalizado = normalizarTexto(texto);
                for (String paciente : listaPacientes) {
                    if (normalizarTexto(paciente).contains(textoNormalizado)) {
                        cboPacientes.addItem(paciente);
                    }
                }
                editor.setText(texto);
                cboPacientes.showPopup();
            }
        });
    }
    
    private void inicializarFiltroMedicos() {
        listaMedicos = new ArrayList<>();
        // 🔹 Obtener los nombres de pacientes desde la base de datos
        try {
            Connection conexion = bd.miConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre, apellidos FROM medicos"); // <-- Ajusta el nombre de tu tabla/columna si difiere
            while (rs.next()) {
                listaMedicos.add(rs.getString("nombre")+" " + rs.getString("apellidos"));
            }
            rs.close();
            st.close();
            conexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar medicos: " + e.getMessage());
        }
        // 🔹 Cargar al combo existente
        cboMedicos.removeAllItems();
        for (String p : listaMedicos) {
            cboMedicos.addItem(p);
        }
        // 🔹 Activar filtrado dinámico
        cboMedicos.setEditable(true);
        JTextField editor = (JTextField) cboMedicos.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = editor.getText();
                cboMedicos.hidePopup();
                cboMedicos.removeAllItems();
                String textoNormalizado = normalizarTexto(texto);
                for (String medico : listaMedicos) {
                    if (normalizarTexto(medico).contains(textoNormalizado)) {
                        cboMedicos.addItem(medico);
                    }
                }
                editor.setText(texto);
                cboMedicos.showPopup();
            }
        });
    }

    private List<String> generarIntervalos(String horaInicio, String horaFin) {
        List<String> intervalos = new ArrayList<>();
        try {
            java.time.LocalTime inicio = java.time.LocalTime.parse(horaInicio);
            java.time.LocalTime fin = java.time.LocalTime.parse(horaFin);
            while (!inicio.isAfter(fin)) {
                intervalos.add(inicio.toString());
                inicio = inicio.plusMinutes(30); // intervalos de 30 minutos
            }
        } catch (Exception e) {
            System.out.println("Error generando intervalos: " + e.getMessage());
        }
        return intervalos;
    }
    
    private Set<String> obtenerHorasOcupadas(int idMedico, String fechaSeleccionada) {
        Set<String> horasOcupadas = new HashSet<>();
        try (Connection conexion = bd.miConexion()) {
            PreparedStatement ps = conexion.prepareStatement(
                "SELECT DATE(fecha_hora_inicio) AS fecha, " +
                "TIME(fecha_hora_inicio) AS hora_inicio, " +
                "TIME(fecha_hora_fin) AS hora_fin " +
                "FROM citas WHERE id_medico = ? AND DATE(fecha_hora_inicio) = ?"
            );
            ps.setInt(1, idMedico);
            ps.setString(2, fechaSeleccionada); // formato yyyy-MM-dd

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String hInicio = rs.getString("hora_inicio");
                String hFin = rs.getString("hora_fin");

                // Generamos los intervalos ocupados entre esas dos horas
                List<String> intervalosOcupados = generarIntervalos(hInicio, hFin);
                horasOcupadas.addAll(intervalosOcupados);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error obteniendo horas ocupadas: " + e.getMessage());
        }
        return horasOcupadas;
    }


    private void cargarHorariosMedico(String nombreMedicoSeleccionado) {
        cboInicioHora.removeAllItems();
        cboFinHora.removeAllItems();

        String fechaSeleccionada = obtenerFechaSeleccionada();
        if (fechaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha válida antes de cargar horarios.");
            return;
        }

        try (Connection conexion = bd.miConexion()) {
            // Buscar el id del médico
            PreparedStatement psMedico = conexion.prepareStatement(
                "SELECT id_medico FROM medicos WHERE CONCAT(nombre, ' ', apellidos) = ?"
            );
            psMedico.setString(1, nombreMedicoSeleccionado);
            ResultSet rsMedico = psMedico.executeQuery();

            if (rsMedico.next()) {
                int idMedico = rsMedico.getInt("id_medico");

                // Obtenemos el día de la semana (1=Lunes, ..., 7=Domingo)
                java.time.LocalDate fecha = java.time.LocalDate.parse(fechaSeleccionada);
                int diaSemana = fecha.getDayOfWeek().getValue();

                // Consulta horarios válidos del médico para ese día
                PreparedStatement psHorarios = conexion.prepareStatement(
                    "SELECT hora_inicio, hora_fin FROM horarios " +
                    "WHERE id_medico = ? AND dia_semana = ? " +
                    "AND fecha_inicio_validez <= ? AND fecha_fin_validez >= ?"
                );
                psHorarios.setInt(1, idMedico);
                psHorarios.setInt(2, diaSemana);
                psHorarios.setString(3, fechaSeleccionada);
                psHorarios.setString(4, fechaSeleccionada);

                ResultSet rsHorarios = psHorarios.executeQuery();
                List<String> horariosDisponibles = new ArrayList<>();

                while (rsHorarios.next()) {
                    horariosDisponibles.addAll(
                        generarIntervalos(rsHorarios.getString("hora_inicio"), rsHorarios.getString("hora_fin"))
                    );
                }

                rsHorarios.close();
                psHorarios.close();

                // 🔹 Obtener los horarios ya ocupados
                Set<String> horasOcupadas = obtenerHorasOcupadas(idMedico, fechaSeleccionada);

                // 🔹 Filtramos solo los disponibles
                List<String> horariosFinales = new ArrayList<>();
                for (String hora : horariosDisponibles) {
                    if (!horasOcupadas.contains(hora)) {
                        horariosFinales.add(hora);
                    }
                }

                if (horariosFinales.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        "El médico no tiene horarios disponibles para esa fecha.",
                        "Sin disponibilidad", JOptionPane.WARNING_MESSAGE);
                } else {
                    for (String hora : horariosFinales) {
                        cboInicioHora.addItem(hora);
                        cboFinHora.addItem(hora);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el médico seleccionado.");
            }

            rsMedico.close();
            psMedico.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar horarios del médico: " + e.getMessage());
        }
    }

    private String obtenerFechaSeleccionada() {
        if (dcFecha.getDate() == null) {
            return null;
        }
        java.time.LocalDate fecha = dcFecha.getDate()
            .toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate();
        return fecha.toString();
    }
    
    // 🔹 Normaliza un texto quitando acentos y convirtiendo a minúsculas
    private String normalizarTexto(String texto) {
        if (texto == null) return "";
        String sinAcentos = java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", ""); // elimina acentos
        return sinAcentos.toLowerCase();
    }
    
    private void limpiarCampos() {
        txtCodigo.setText("");
        txtMotivo.setText("");
        txtObservaciones.setText("");
        cboPacientes.setSelectedIndex(-1);
        cboMedicos.setSelectedIndex(-1);
        cboInicioHora.removeAllItems();
        cboFinHora.removeAllItems();
        dcFecha.setDate(null);
        cboEstado.setSelectedIndex(0);
        cboTipo.setSelectedIndex(0);
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
                FrmAgregarCitas dialog = new FrmAgregarCitas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboFinHora;
    private javax.swing.JComboBox<String> cboInicioHora;
    private javax.swing.JComboBox<String> cboMedicos;
    private javax.swing.JComboBox<String> cboPacientes;
    private javax.swing.JComboBox<String> cboTipo;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables
}

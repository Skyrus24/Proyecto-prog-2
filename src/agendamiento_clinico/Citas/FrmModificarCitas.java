package agendamiento_clinico.Citas;

import agendamiento_clinico.BaseDatos;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class FrmModificarCitas extends javax.swing.JDialog {
    private final BaseDatos bd = new BaseDatos();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmModificarCitas.class.getName());
    private int idCitaSeleccionada = -1;
    private java.util.List<String> listaPacientes;
    private java.util.List<String> listaMedicos;

    public FrmModificarCitas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarConsultorios();
        inicializarFiltroPacientes();
        inicializarFiltroMedicos();
        cboFinHora.setEnabled(false);
        cboPacientes.setSelectedItem(null);
        cboMedicos.setSelectedItem(null);

        cboMedicos.addActionListener(e -> {Object medicoSeleccionado = cboMedicos.getSelectedItem();
            if (medicoSeleccionado != null && !medicoSeleccionado.toString().isEmpty()) {
                cargarHorariosMedico(medicoSeleccionado.toString());
            }
        });

        dcFecha.getDateEditor().addPropertyChangeListener("date", evt -> {
            Object medicoSeleccionado = cboMedicos.getSelectedItem();
            if (medicoSeleccionado != null && !medicoSeleccionado.toString().isEmpty()) {
                cargarHorariosMedico(medicoSeleccionado.toString());
            }
        });

        cboInicioHora.addActionListener(e -> {
            if (cboInicioHora.getSelectedItem() != null) {
                try {
                    java.time.LocalTime inicio = java.time.LocalTime.parse(cboInicioHora.getSelectedItem().toString());
                    cboFinHora.removeAllItems();
                    cboFinHora.addItem(inicio.plusMinutes(30).toString());
                } catch (Exception ignored) {}
            }
        });
        } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
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
        cboConsultorios = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();

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
        jLabel1.setText("Consultorio");

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel2.setText("Paciente");

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel4.setText("Medico");

        dcFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dcFechaFocusGained(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel3.setText("Motivo");

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Programada", "Atendida", "Cancelada", "No asistió", "Confirmada", " " }));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel8.setText("Estado");

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primera Vez", "Seguimiento", "Urgencia", "Control" }));

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel9.setText("Tipo");

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane1.setViewportView(txtObservaciones);

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel10.setText("Observaciones");

        cboConsultorios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                    .addComponent(cboMedicos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboPacientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboConsultorios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
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
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(cboInicioHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboFinHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel7)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(36, 36, 36))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cboConsultorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 63, Short.MAX_VALUE))
        );

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
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
         if (idCitaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe buscar y seleccionar una cita antes de modificarla.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conexion = bd.miConexion()) {

            if (cboConsultorios.getSelectedItem() == null || dcFecha.getDate() == null || cboInicioHora.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos obligatorios.",
                        "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String motivo = txtMotivo.getText().trim();
            String estado = cboEstado.getSelectedItem().toString();
            String tipo = cboTipo.getSelectedItem().toString();
            String observaciones = txtObservaciones.getText().trim();
            String consultorioSeleccionado = cboConsultorios.getSelectedItem().toString();

            java.time.LocalDate fecha = dcFecha.getDate()
                    .toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();

            java.time.LocalTime horaInicio = java.time.LocalTime.parse(cboInicioHora.getSelectedItem().toString());
            java.time.LocalDateTime fechaHoraInicio = java.time.LocalDateTime.of(fecha, horaInicio);
            java.time.LocalDateTime fechaHoraFin = fechaHoraInicio.plusMinutes(30);

            int idConsultorio = obtenerIdPorNombre(conexion, "consultorios", "id_consultorio", consultorioSeleccionado);
            if (idConsultorio == -1) {
                JOptionPane.showMessageDialog(this, "Error al obtener el consultorio seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = """
                UPDATE citas
                SET id_consultorio = ?,
                    fecha_hora_inicio = ?,
                    fecha_hora_fin = ?,
                    motivo_consulta = ?,
                    estado_cita = ?,
                    tipo_cita = ?,
                    observaciones = ?
                WHERE id_cita = ?
            """;

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idConsultorio);
            ps.setTimestamp(2, Timestamp.valueOf(fechaHoraInicio));
            ps.setTimestamp(3, Timestamp.valueOf(fechaHoraFin));
            ps.setString(4, motivo);
            ps.setString(5, estado);
            ps.setString(6, tipo);
            ps.setString(7, observaciones);
            ps.setInt(8, idCitaSeleccionada);

            int filas = ps.executeUpdate();
            ps.close();

            if (filas > 0) {
                JOptionPane.showMessageDialog(this, "Cita modificada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                idCitaSeleccionada = -1;
                cboPacientes.setEnabled(true);
                cboMedicos.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo modificar la cita.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la cita: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void dcFechaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dcFechaFocusGained

    }//GEN-LAST:event_dcFechaFocusGained
    
    private void cargarDatosCita(int idCita) {
        try (Connection cn = bd.miConexion()) {
            String sql = """
                SELECT c.id_cita, CONCAT(p.nombre, ' ', p.apellidos) AS paciente,
                       CONCAT(m.nombre, ' ', m.apellidos) AS medico,
                       con.nombre AS consultorio, con.ubicacion,
                       c.motivo_consulta, c.estado_cita, c.tipo_cita,
                       c.observaciones, DATE(c.fecha_hora_inicio) AS fecha,
                       TIME(c.fecha_hora_inicio) AS hora_inicio,
                       TIME(c.fecha_hora_fin) AS hora_fin
                FROM citas c
                INNER JOIN pacientes p ON c.id_paciente = p.id_paciente
                INNER JOIN medicos m ON c.id_medico = m.id_medico
                INNER JOIN consultorios con ON c.id_consultorio = con.id_consultorio
                WHERE c.id_cita = ?
            """;

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idCita);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idCitaSeleccionada = rs.getInt("id_cita");
                cboPacientes.setEnabled(false);
                cboMedicos.setEnabled(false);

                cboPacientes.removeAllItems();
                cboPacientes.addItem(rs.getString("paciente"));

                cboMedicos.removeAllItems();
                cboMedicos.addItem(rs.getString("medico"));

                cboConsultorios.setSelectedItem(rs.getString("consultorio") + " - " + rs.getString("ubicacion"));
                txtMotivo.setText(rs.getString("motivo_consulta"));
                txtObservaciones.setText(rs.getString("observaciones"));
                cboEstado.setSelectedItem(rs.getString("estado_cita"));
                cboTipo.setSelectedItem(rs.getString("tipo_cita"));
                dcFecha.setDate(rs.getDate("fecha"));

                cboInicioHora.removeAllItems();
                cboFinHora.removeAllItems();
                cboInicioHora.addItem(rs.getString("hora_inicio"));
                cboFinHora.addItem(rs.getString("hora_fin"));
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la cita seleccionada.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de la cita: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        FrmBuscar buscador = new FrmBuscar(null, true);
        buscador.setVisible(true);

        int idCita = buscador.getCitaSeleccionada();
        if (idCita > 0) {
            cargarDatosCita(idCita);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void inicializarFiltroEntidad(String tabla, JComboBox<String> comboBox,List<String> lista,String mensajeError) {
        lista.clear();
        try (Connection conexion = bd.miConexion();
             Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery("SELECT nombre, apellidos FROM " + tabla)) {
            while (rs.next()) {
                lista.add(rs.getString("nombre") + " " + rs.getString("apellidos"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, mensajeError + ": " + e.getMessage());
        }

        // 🔹 Cargar datos en el combo
        comboBox.removeAllItems();
        for (String item : lista) comboBox.addItem(item);

        // 🔹 Activar filtrado dinámico
        comboBox.setEditable(true);
        JTextField editor = (JTextField) comboBox.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = editor.getText().trim();
                comboBox.hidePopup();
                comboBox.removeAllItems();
                String textoNormalizado = normalizarTexto(texto);

                for (String valor : lista) {
                    if (normalizarTexto(valor).contains(textoNormalizado)) {
                        comboBox.addItem(valor);
                    }
                }

                editor.setText(texto);
                comboBox.showPopup();
            }
        });
    }
   
    private void inicializarFiltroPacientes() {
        if (listaPacientes == null) listaPacientes = new ArrayList<>();
        inicializarFiltroEntidad("pacientes", cboPacientes, listaPacientes, "Error al cargar pacientes");
    }
    
    private void inicializarFiltroMedicos() {
        if (listaMedicos == null) listaMedicos = new ArrayList<>();
        inicializarFiltroEntidad("medicos", cboMedicos, listaMedicos, "Error al cargar médicos");
    }

    private void cargarConsultorios() {
        cboConsultorios.removeAllItems();
        try (Connection conexion = bd.miConexion();
             Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery("SELECT nombre, ubicacion FROM consultorios")) {
            while (rs.next()) cboConsultorios.addItem(rs.getString("nombre") + " - " + rs.getString("ubicacion"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar consultorios: " + e.getMessage());
        }
    }

    private List<String> generarIntervalos(String horaInicio, String horaFin) {
        List<String> intervalos = new ArrayList<>();
        try {
            java.time.LocalTime inicio = java.time.LocalTime.parse(horaInicio);
            java.time.LocalTime fin = java.time.LocalTime.parse(horaFin);
            while (inicio.isBefore(fin)) {
                intervalos.add(inicio.toString());
                inicio = inicio.plusMinutes(30);
            }
        } catch (Exception ignored) {}
        return intervalos;
    }

     private Set<String> obtenerHorasOcupadas(int idMedico, String fechaSeleccionada) {
        Set<String> horasOcupadas = new HashSet<>();
        try (Connection conexion = bd.miConexion()) {
            PreparedStatement ps = conexion.prepareStatement("""
                SELECT TIME(fecha_hora_inicio) AS hora_inicio, TIME(fecha_hora_fin) AS hora_fin
                FROM citas WHERE id_medico = ? AND DATE(fecha_hora_inicio) = ?
            """);
            ps.setInt(1, idMedico);
            ps.setString(2, fechaSeleccionada);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) horasOcupadas.addAll(generarIntervalos(rs.getString("hora_inicio"), rs.getString("hora_fin")));
        } catch (Exception ignored) {}
        return horasOcupadas;
    }

    private void cargarHorariosMedico(String nombreMedicoSeleccionado) {
        cboInicioHora.removeAllItems();
        cboFinHora.removeAllItems();
        String fechaSeleccionada = obtenerFechaSeleccionada();
        if (fechaSeleccionada == null) return;

        try (Connection conexion = bd.miConexion()) {
            PreparedStatement psMedico = conexion.prepareStatement("SELECT id_medico FROM medicos WHERE CONCAT(nombre, ' ', apellidos) = ?");
            psMedico.setString(1, nombreMedicoSeleccionado);
            ResultSet rsMedico = psMedico.executeQuery();

            if (rsMedico.next()) {
                int idMedico = rsMedico.getInt("id_medico");
                java.time.LocalDate fecha = java.time.LocalDate.parse(fechaSeleccionada);
                int diaSemana = fecha.getDayOfWeek().getValue();

                PreparedStatement psHorarios = conexion.prepareStatement("""
                    SELECT hora_inicio, hora_fin FROM horarios
                    WHERE id_medico = ? AND dia_semana = ?
                    AND fecha_inicio_validez <= ? AND fecha_fin_validez >= ?
                """);
                psHorarios.setInt(1, idMedico);
                psHorarios.setInt(2, diaSemana);
                psHorarios.setString(3, fechaSeleccionada);
                psHorarios.setString(4, fechaSeleccionada);

                ResultSet rsHorarios = psHorarios.executeQuery();
                List<String> horariosDisponibles = new ArrayList<>();
                while (rsHorarios.next()) {
                    horariosDisponibles.addAll(generarIntervalos(rsHorarios.getString("hora_inicio"), rsHorarios.getString("hora_fin")));
                }
                rsHorarios.close();
                psHorarios.close();

                Set<String> horasOcupadas = obtenerHorasOcupadas(idMedico, fechaSeleccionada);
                horariosDisponibles.removeAll(horasOcupadas);
                horariosDisponibles.forEach(cboInicioHora::addItem);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar horarios del médico: " + e.getMessage());
        }
    }

     private String obtenerFechaSeleccionada() {
        if (dcFecha.getDate() == null) return null;
        return dcFecha.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().toString();
    }
    
    // 🔹 Normaliza un texto quitando acentos y convirtiendo a minúsculas
    private String normalizarTexto(String texto) {
        return java.text.Normalizer.normalize(texto == null ? "" : texto, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }
    
    private int obtenerIdPorNombre(Connection conexion, String tabla, String columnaId, Object seleccionado) {
        int id = -1;
        String nombreCompleto = seleccionado.toString();
        String campoNombre = switch (tabla) {
            case "medicos", "pacientes" -> "CONCAT(nombre, ' ', apellidos)";
            case "consultorios" -> "CONCAT(nombre, ' - ', ubicacion)";
            default -> "";
        };
        if (campoNombre.isEmpty()) return -1;

        try (PreparedStatement ps = conexion.prepareStatement("SELECT " + columnaId + " FROM " + tabla + " WHERE " + campoNombre + " = ?")) {
            ps.setString(1, nombreCompleto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) id = rs.getInt(columnaId);
        } catch (SQLException ignored) {}
        return id;
    }

    private void limpiarCampos() {
        txtMotivo.setText("");
        txtObservaciones.setText("");
        cboPacientes.setSelectedIndex(-1);
        cboMedicos.setSelectedIndex(-1);
        cboConsultorios.setSelectedIndex(-1);
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
                FrmModificarCitas dialog = new FrmModificarCitas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cboConsultorios;
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
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables
}


import agendamiento_clinico.Citas.*;
import agendamiento_clinico.BaseDatos;
import agendamiento_clinico.DatosCombo;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.time.LocalDate;
import java.time.LocalTime;


public class test extends javax.swing.JDialog {
    private final BaseDatos bd = new BaseDatos();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmAgregarCitas.class.getName());
    
    private List<String> listaPacientes = new ArrayList<>();
    private List<String> listaMedicos = new ArrayList<>();

    public test(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarConsultorios();
        configurarCombos();
        configurarEventos();
    }

    
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
                    .addComponent(cboMedicos, 0, 362, Short.MAX_VALUE)
                    .addComponent(cboPacientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboConsultorios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(cboInicioHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboFinHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cboConsultorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))))
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
    }//GEN-LAST:event_cboMedicosKeyReleased

    private void cboPacientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboPacientesKeyReleased
        String texto = cboPacientes.getEditor().getItem().toString().trim();
    }//GEN-LAST:event_cboPacientesKeyReleased

    private void cboInicioHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboInicioHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboInicioHoraActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try (Connection conexion = bd.miConexion()) {
            // Validaciones
            if (cboPacientes.getSelectedItem() == null || cboMedicos.getSelectedItem() == null ||
                cboConsultorios.getSelectedItem() == null ||
                dcFecha.getDate() == null || cboInicioHora.getSelectedItem() == null || cboFinHora.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos obligatorios.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String paciente = cboPacientes.getSelectedItem().toString();
            String medico = cboMedicos.getSelectedItem().toString();
            Object consultorio = cboConsultorios.getSelectedItem();
            String motivo = txtMotivo.getText().trim();
            String estado = cboEstado.getSelectedItem().toString();
            String tipo = cboTipo.getSelectedItem().toString();
            String observaciones = txtObservaciones.getText().trim();

            // Convertir fechas y horas
            java.time.LocalDate fecha = dcFecha.getDate()
                    .toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();

            String horaInicioStr = cboInicioHora.getSelectedItem().toString();
            String horaFinStr = cboFinHora.getSelectedItem().toString();

            java.time.LocalDateTime fechaHoraInicio = java.time.LocalDateTime.parse(fecha + "T" + horaInicioStr);
            java.time.LocalDateTime fechaHoraFin = java.time.LocalDateTime.parse(fecha + "T" + horaFinStr);

            if (!fechaHoraFin.isAfter(fechaHoraInicio)) {
                JOptionPane.showMessageDialog(this, "La hora de fin debe ser posterior a la hora de inicio.", "Error de horario", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Buscar IDs de médico, paciente y consultorio
            int idMedico = obtenerIdPorNombre(conexion, "medicos", "id_medico", medico);
            int idPaciente = obtenerIdPorNombre(conexion, "pacientes", "id_paciente", paciente);
            int idConsultorio = obtenerIdPorNombre(conexion, "consultorios", "id_consultorio", consultorio);

            if (idMedico == -1 || idPaciente == -1 || idConsultorio == -1) {
                JOptionPane.showMessageDialog(this, "Error al obtener IDs de los registros seleccionados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            // Insertar usando tu método genérico
            String tabla = "citas";
            String campos = "id_paciente, id_medico, id_consultorio, fecha_hora_inicio, fecha_hora_fin, motivo_consulta, estado_cita, tipo_cita, observaciones";

            String valores = String.format(
                "%d, %d, %d, '%s', '%s', '%s', '%s', '%s', '%s'",
                idPaciente, idMedico, idConsultorio,
                java.sql.Timestamp.valueOf(fechaHoraInicio),
                java.sql.Timestamp.valueOf(fechaHoraFin),
                motivo.replace("'", "''"),
                estado,
                tipo,
                observaciones.replace("'", "''")
            );

            boolean exito = bd.insertarRegistro(tabla, campos, valores);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Cita registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la cita.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la cita: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    
    private void configurarCombos() {
        cargarConsultorios();
        cargarLista("pacientes", listaPacientes, cboPacientes);
        cargarLista("medicos", listaMedicos, cboMedicos);

        cboPacientes.setSelectedItem(null);
        cboMedicos.setSelectedItem(null);
    }

    private void configurarEventos() {
        cboMedicos.addActionListener(e -> {
            Object medico = cboMedicos.getSelectedItem();
            if (medico != null && !medico.toString().isEmpty()) {
                cargarHorariosMedico(medico.toString());
            }
        });
    }
    
    private void cargarLista(String tipo, List<String> lista, JComboBox<String> combo) {
        lista.clear();
        String tabla = tipo.equals("pacientes") ? "pacientes" : "medicos";
        try (Connection cn = bd.miConexion();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT nombre, apellidos FROM " + tabla)) {

            while (rs.next()) {
                lista.add(rs.getString("nombre") + " " + rs.getString("apellidos"));
            }

            combo.removeAllItems();
            lista.forEach(combo::addItem);
            activarFiltro(combo, lista);

        } catch (SQLException e) {
            mostrarError("Error al cargar " + tipo + ": " + e.getMessage());
        }
    }
    
    private void activarFiltro(JComboBox<String> combo, List<String> lista) {
        combo.setEditable(true);
        JTextField editor = (JTextField) combo.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = normalizarTexto(editor.getText());
                combo.hidePopup();
                combo.removeAllItems();
                lista.stream()
                     .filter(item -> normalizarTexto(item).contains(texto))
                     .forEach(combo::addItem);
                editor.setText(editor.getText());
                combo.showPopup();
            }
        });
    }
    
    private void cargarConsultorios() {
        cboConsultorios.removeAllItems();
        String sql = "SELECT id_consultorio, nombre, ubicacion FROM consultorios";
        try (Connection cn = bd.miConexion();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String textoCombo = rs.getInt("id_consultorio") + " - " +
                                    rs.getString("nombre") + " - " +
                                    rs.getString("ubicacion");
                cboConsultorios.addItem(textoCombo);
            }

        } catch (SQLException e) {
            mostrarError("Error al cargar consultorios: " + e.getMessage());
        }
    }
    
    private Set<String> obtenerHorasOcupadas(Connection cn, int idMedico, String fecha) throws SQLException {
        Set<String> ocupadas = new HashSet<>();
        String sql = """
            SELECT TIME(fecha_hora_inicio) AS inicio, TIME(fecha_hora_fin) AS fin
            FROM citas WHERE id_medico = ? AND DATE(fecha_hora_inicio) = ?
            """;
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idMedico);
            ps.setString(2, fecha);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ocupadas.addAll(generarIntervalos(rs.getString("inicio"), rs.getString("fin")));
            }
        }
        return ocupadas;
    }
    
    private void cargarHorariosMedico(String nombreMedico) {
        cboInicioHora.removeAllItems();
        cboFinHora.removeAllItems();
        String fecha = obtenerFechaSeleccionada();
        if (fecha == null) {
            mostrarError("Seleccione una fecha válida antes de cargar horarios.");
            return;
        }

        try (Connection cn = bd.miConexion()) {
            int idMedico = obtenerIdPorNombre(cn, "medicos", "id_medico", nombreMedico);
            if (idMedico == -1) {
                mostrarError("No se encontró el médico seleccionado.");
                return;
            }

            int diaSemana = LocalDate.parse(fecha).getDayOfWeek().getValue();
            List<String> horarios = obtenerHorariosDisponibles(cn, idMedico, diaSemana, fecha);
            Set<String> ocupados = obtenerHorasOcupadas(cn, idMedico, fecha);

            horarios.removeAll(ocupados);
            if (horarios.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sin horarios disponibles para esa fecha.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                horarios.forEach(h -> { cboInicioHora.addItem(h); cboFinHora.addItem(h); });
            }

        } catch (SQLException e) {
            mostrarError("Error al cargar horarios: " + e.getMessage());
        }
    }
    
    private List<String> obtenerHorariosDisponibles(Connection cn, int idMedico, int diaSemana, String fecha) throws SQLException {
        List<String> horarios = new ArrayList<>();
        String sql = """
            SELECT hora_inicio, hora_fin FROM horarios 
            WHERE id_medico = ? AND dia_semana = ? 
            AND (fecha_inicio_validez IS NULL OR fecha_inicio_validez <= ?)
            AND (fecha_fin_validez IS NULL OR fecha_fin_validez >= ?)
            """;
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idMedico);
            ps.setInt(2, diaSemana);
            ps.setString(3, fecha);
            ps.setString(4, fecha);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                horarios.addAll(generarIntervalos(rs.getString("hora_inicio"), rs.getString("hora_fin")));
            }
        }
        return horarios;
    }

    private List<String> generarIntervalos(String inicio, String fin) {
        List<String> intervalos = new ArrayList<>();
        LocalTime hora = LocalTime.parse(inicio);
        LocalTime limite = LocalTime.parse(fin);
        while (!hora.isAfter(limite)) {
            intervalos.add(hora.toString());
            hora = hora.plusMinutes(30);
        }
        return intervalos;
    }
    
    private int obtenerIdPorNombre(Connection cn, String tabla, String columnaId, String valor) throws SQLException {
        String campo = switch (tabla) {
            case "medicos", "pacientes" -> "CONCAT(nombre, ' ', apellidos)";
            case "consultorios" -> "nombre";
            default -> throw new IllegalArgumentException("Tabla no soportada: " + tabla);
        };

        String sql = "SELECT " + columnaId + " FROM " + tabla + " WHERE " + campo + " = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, valor);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(columnaId) : -1;
        }
    }

    private String obtenerFechaSeleccionada() {
        if (dcFecha.getDate() == null) return null;
        return dcFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
    }

    private String normalizarTexto(String t) {
        return java.text.Normalizer.normalize(t, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }

    private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
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
                test dialog = new test(new javax.swing.JFrame(), true);
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

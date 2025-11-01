package agendamiento_clinico.cita;

import agendamiento_clinico.BaseDatos;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FrmModificarCitas extends javax.swing.JDialog {

    private final BaseDatos bd = new BaseDatos();
    private int idCitaSeleccionada = -1;

    public FrmModificarCitas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configurarFormulario();
    }
    
    private void configurarFormulario() {
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(248, 249, 250));
        
        cargarConsultorios();
        bloqueoInicio(); // Tu método original para deshabilitar campos
        
        cboInicioHora.addActionListener(e -> {
            if (cboInicioHora.getSelectedItem() != null) {
                try {
                    java.time.LocalTime inicio = java.time.LocalTime.parse(cboInicioHora.getSelectedItem().toString());
                    txtFinHora.setText(inicio.plusMinutes(30).toString());
                } catch (Exception ignored) {
                    txtFinHora.setText("");
                }
            } else {
                 txtFinHora.setText("");
            }
        });
    }

    // TUS MÉTODOS ORIGINALES SE MANTIENEN INTTACTOS
    private void cargarDatosCita(int idCita) {
        try (Connection cn = bd.miConexion()) {
            String sql = "SELECT c.id_cita, CONCAT(p.nombre, ' ', p.apellidos) AS paciente, " +
                         "CONCAT(m.nombre, ' ', m.apellidos) AS medico, con.nombre AS consultorio, con.ubicacion, " +
                         "c.motivo_consulta, c.estado_cita, c.tipo_cita, c.observaciones, " +
                         "DATE(c.fecha_hora_inicio) AS fecha, TIME(c.fecha_hora_inicio) AS hora_inicio, " +
                         "TIME(c.fecha_hora_fin) AS hora_fin " +
                         "FROM citas c " +
                         "JOIN pacientes p ON c.id_paciente = p.id_paciente " +
                         "JOIN medicos m ON c.id_medico = m.id_medico " +
                         "JOIN consultorios con ON c.id_consultorio = con.id_consultorio " +
                         "WHERE c.id_cita = ?";

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, idCita);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        idCitaSeleccionada = rs.getInt("id_cita");

                        // Habilitar campos editables
                        cboConsultorios.setEnabled(true);
                        txtMotivo.setEnabled(true);
                        txtObservaciones.setEnabled(true);
                        cboTipo.setEnabled(true);
                        cboEstado.setEnabled(true);
                        btnGuardar.setEnabled(true);

                        // Cargar y bloquear campos no editables
                        cboPacientes.removeAllItems();
                        cboPacientes.addItem(rs.getString("paciente"));
                        cboMedicos.removeAllItems();
                        cboMedicos.addItem(rs.getString("medico"));
                        cboInicioHora.removeAllItems();
                        cboInicioHora.addItem(rs.getString("hora_inicio"));
                        txtFinHora.setText(rs.getString("hora_fin"));
                        dcFecha.setDate(rs.getDate("fecha"));

                        // Cargar datos en campos editables
                        String consultorioTexto = rs.getString("consultorio") + " - " + rs.getString("ubicacion");
                        cboConsultorios.setSelectedItem(consultorioTexto);
                        txtMotivo.setText(rs.getString("motivo_consulta"));
                        txtObservaciones.setText(rs.getString("observaciones"));
                        cboEstado.setSelectedItem(rs.getString("estado_cita"));
                        cboTipo.setSelectedItem(rs.getString("tipo_cita"));
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró la cita seleccionada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        limpiarYBloquearTodo();
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de la cita: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarConsultorios() {
        cboConsultorios.removeAllItems();
        try (ResultSet rs = bd.consultarRegistros("SELECT nombre, ubicacion FROM consultorios ORDER BY nombre")) {
            if (rs == null) return;
            while (rs.next()) {
                cboConsultorios.addItem(rs.getString("nombre") + " - " + rs.getString("ubicacion"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar consultorios: " + e.getMessage());
        }
    }

    private int obtenerIdPorNombre(Connection conexion, String tabla, String columnaId, Object seleccionado) {
        if (seleccionado == null) return -1;
        int id = -1;
        String nombreCompleto = seleccionado.toString();
        String campoNombre = switch (tabla) {
            case "consultorios" -> "CONCAT(nombre, ' - ', ubicacion)";
            default -> "nombre";
        };

        String sql = "SELECT " + columnaId + " FROM " + tabla + " WHERE " + campoNombre + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreCompleto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) id = rs.getInt(columnaId);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo ID: " + e.getMessage());
        }
        return id;
    }

    private void limpiarYBloquearTodo() {
        txtMotivo.setText("");
        txtObservaciones.setText("");
        cboPacientes.removeAllItems();
        cboMedicos.removeAllItems();
        cboConsultorios.setSelectedIndex(-1);
        cboInicioHora.removeAllItems();
        txtFinHora.setText("");
        dcFecha.setDate(null);
        cboEstado.setSelectedIndex(0);
        cboTipo.setSelectedIndex(0);
        
        bloqueoInicio();
    }

    private void bloqueoInicio() {
        dcFecha.setEnabled(false);
        cboPacientes.setEnabled(false);
        cboMedicos.setEnabled(false);
        cboConsultorios.setEnabled(false);
        cboInicioHora.setEnabled(false);
        txtMotivo.setEnabled(false);
        txtObservaciones.setEnabled(false);
        cboTipo.setEnabled(false);
        cboEstado.setEnabled(false);
        btnGuardar.setEnabled(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        panelBusqueda = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        panelDatosCita = new javax.swing.JPanel();
        lblPaciente = new javax.swing.JLabel();
        cboPacientes = new javax.swing.JComboBox<>();
        lblMedico = new javax.swing.JLabel();
        cboMedicos = new javax.swing.JComboBox<>();
        panelHorario = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        lblInicioHora = new javax.swing.JLabel();
        cboInicioHora = new javax.swing.JComboBox<>();
        lblFinHora = new javax.swing.JLabel();
        txtFinHora = new javax.swing.JTextField();
        panelDetalles = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        lblObservaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        lblConsultorio = new javax.swing.JLabel();
        cboConsultorios = new javax.swing.JComboBox<>();
        lblMotivo = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        panelBotones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(248, 249, 250));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(52, 58, 64));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Modificar Cita Agendada");

        panelBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        panelBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 226, 230)), "Paso 1: Buscar Cita", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(73, 80, 87))); // NOI18N

        btnBuscar.setBackground(new java.awt.Color(13, 110, 253));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar Cita para Modificar");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Haga clic en el bot\u00f3n para encontrar la cita que desea editar.");

        javax.swing.GroupLayout panelBusquedaLayout = new javax.swing.GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(panelBusquedaLayout);
        panelBusquedaLayout.setHorizontalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        panelBusquedaLayout.setVerticalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        panelDatosCita.setBackground(new java.awt.Color(255, 255, 255));
        panelDatosCita.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 226, 230)), "Datos (No Editables)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(73, 80, 87))); // NOI18N

        lblPaciente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPaciente.setText("Paciente:");

        cboPacientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMedico.setText("Médico:");

        cboMedicos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelDatosCitaLayout = new javax.swing.GroupLayout(panelDatosCita);
        panelDatosCita.setLayout(panelDatosCitaLayout);
        panelDatosCitaLayout.setHorizontalGroup(
            panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosCitaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPaciente)
                    .addComponent(cboPacientes, 0, 427, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMedico)
                    .addComponent(cboMedicos, 0, 427, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelDatosCitaLayout.setVerticalGroup(
            panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosCitaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPaciente)
                    .addComponent(lblMedico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelHorario.setBackground(new java.awt.Color(255, 255, 255));
        panelHorario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 226, 230)), "Horario (No Editable)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(73, 80, 87))); // NOI18N

        lblFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFecha.setText("Fecha de la Cita:");

        dcFecha.setDateFormatString("dd/MM/yyyy");
        dcFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblInicioHora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblInicioHora.setText("Hora de Inicio:");

        cboInicioHora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblFinHora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFinHora.setText("Hora de Fin:");

        txtFinHora.setEditable(false);
        txtFinHora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtFinHora.setFocusable(false);

        javax.swing.GroupLayout panelHorarioLayout = new javax.swing.GroupLayout(panelHorario);
        panelHorario.setLayout(panelHorarioLayout);
        panelHorarioLayout.setHorizontalGroup(
            panelHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHorarioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha)
                    .addComponent(dcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addGroup(panelHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInicioHora)
                    .addComponent(cboInicioHora, 0, 275, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addGroup(panelHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFinHora)
                    .addComponent(txtFinHora, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelHorarioLayout.setVerticalGroup(
            panelHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHorarioLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFecha)
                    .addComponent(lblInicioHora)
                    .addComponent(lblFinHora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboInicioHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFinHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelDetalles.setBackground(new java.awt.Color(255, 255, 255));
        panelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 226, 230)), "Paso 2: Modificar Detalles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(73, 80, 87))); // NOI18N

        lblTipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTipo.setText("Tipo de Cita:");

        cboTipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primera Vez", "Seguimiento", "Urgencia", "Control" }));

        lblEstado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEstado.setText("Estado de Cita:");

        cboEstado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Programada", "Confirmada", "Cancelada", "No Asistió" }));

        lblObservaciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblObservaciones.setText("Observaciones:");

        txtObservaciones.setColumns(20);
        txtObservaciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtObservaciones.setRows(5);
        jScrollPane1.setViewportView(txtObservaciones);

        lblConsultorio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblConsultorio.setText("Consultorio:");

        cboConsultorios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblMotivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMotivo.setText("Motivo de la Consulta:");

        txtMotivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelDetallesLayout = new javax.swing.GroupLayout(panelDetalles);
        panelDetalles.setLayout(panelDetallesLayout);
        panelDetallesLayout.setHorizontalGroup(
            panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetallesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetallesLayout.createSequentialGroup()
                        .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConsultorio)
                            .addComponent(cboConsultorios, 0, 427, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMotivo)
                            .addComponent(txtMotivo, 0, 427, Short.MAX_VALUE)))
                    .addGroup(panelDetallesLayout.createSequentialGroup()
                        .addComponent(lblObservaciones)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelDetallesLayout.createSequentialGroup()
                        .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipo)
                            .addComponent(cboTipo, 0, 427, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEstado)
                            .addComponent(cboEstado, 0, 427, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        panelDetallesLayout.setVerticalGroup(
            panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetallesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConsultorio)
                    .addComponent(lblMotivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboConsultorios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(lblEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblObservaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelBotones.setBackground(new java.awt.Color(248, 249, 250));

        btnGuardar.setBackground(new java.awt.Color(25, 135, 84));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Cambios");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(108, 117, 125));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDatosCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(panelBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelDatosCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        FrmBuscar buscador = new FrmBuscar(null, true);
        buscador.setVisible(true);
        
        int idCita = buscador.getcitaSeleccionada();
        
        if (idCita > 0) {
            cargarDatosCita(idCita);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (idCitaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe buscar y seleccionar una cita antes de modificarla.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conexion = bd.miConexion()) {
            if (cboConsultorios.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "El campo Consultorio es obligatorio.", "Campo Incompleto", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String motivo = txtMotivo.getText().trim();
            String estado = cboEstado.getSelectedItem().toString();
            String tipo = cboTipo.getSelectedItem().toString();
            String observaciones = txtObservaciones.getText().trim();
            String consultorioSeleccionado = cboConsultorios.getSelectedItem().toString();
            
            int idConsultorio = obtenerIdPorNombre(conexion, "consultorios", "id_consultorio", consultorioSeleccionado);
            if (idConsultorio == -1) {
                JOptionPane.showMessageDialog(this, "Error al obtener el ID del consultorio seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "UPDATE citas SET id_consultorio = ?, motivo_consulta = ?, estado_cita = ?, tipo_cita = ?, observaciones = ? WHERE id_cita = ?";

            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setInt(1, idConsultorio);
                ps.setString(2, motivo);
                ps.setString(3, estado);
                ps.setString(4, tipo);
                ps.setString(5, observaciones);
                ps.setInt(6, idCitaSeleccionada);
                
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Cita modificada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarYBloquearTodo();
                    idCitaSeleccionada = -1;
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo modificar la cita. Verifique los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la cita: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cboConsultorios;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboInicioHora;
    private javax.swing.JComboBox<String> cboMedicos;
    private javax.swing.JComboBox<String> cboPacientes;
    private javax.swing.JComboBox<String> cboTipo;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblConsultorio;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFinHora;
    private javax.swing.JLabel lblInicioHora;
    private javax.swing.JLabel lblMedico;
    private javax.swing.JLabel lblMotivo;
    private javax.swing.JLabel lblObservaciones;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JPanel panelDatosCita;
    private javax.swing.JPanel panelDetalles;
    private javax.swing.JPanel panelHorario;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtFinHora;
    private javax.swing.JTextField txtMotivo;
    // End of variables declaration//GEN-END:variables
}
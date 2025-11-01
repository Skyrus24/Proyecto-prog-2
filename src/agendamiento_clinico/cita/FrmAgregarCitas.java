package agendamiento_clinico.cita;

import agendamiento_clinico.BaseDatos;
import agendamiento_clinico.medicos.FiltrarMedicos;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmAgregarCitas extends javax.swing.JDialog {

    BaseDatos bd = new BaseDatos();
    private java.util.List<String> listaPacientes;
    private java.util.List<String> listaMedicos;
    
    public FrmAgregarCitas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configurarFormulario();
    }
    
    private void configurarFormulario(){
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(248, 249, 250));
        
        cargarConsultorios();
        inicializarFiltroPacientes();
        inicializarFiltroMedicos();
        
        cboEstado.setEnabled(false);
        cboEstado.setSelectedItem("Confirmada");
        
        limpiarCampos();

        //Listener de cboMedicos para actualizar
        cboMedicos.addActionListener(e -> {
            Object medicoSeleccionado = cboMedicos.getSelectedItem();
            if (medicoSeleccionado != null && !medicoSeleccionado.toString().isEmpty()) {
                cargarHorariosMedico(medicoSeleccionado.toString());
            }
        });
        
        // Listener para cargar horarios automáticamente al seleccionar una fecha
        dcFecha.getDateEditor().addPropertyChangeListener("date", evt -> {
            Object medicoSeleccionado = cboMedicos.getSelectedItem();
            if (medicoSeleccionado != null && !medicoSeleccionado.toString().isEmpty()) {
                cargarHorariosMedico(medicoSeleccionado.toString());
            }
        });
        
        // Listener: calcula hora fin automáticamente al elegir hora de inicio
        cboInicioHora.addActionListener(e -> {
            if (cboInicioHora.getSelectedItem() != null) {
                try {
                    String horaInicioStr = cboInicioHora.getSelectedItem().toString();
                    java.time.LocalTime horaInicio = java.time.LocalTime.parse(horaInicioStr);
                    java.time.LocalTime horaFin = horaInicio.plusMinutes(30); // cita de 30 minutos
                    txtFinHora.setText(horaFin.toString());
                } catch (Exception ex) {
                    txtFinHora.setText("");
                }
            } else {
                 txtFinHora.setText("");
            }
        });
    }
    
    private void limpiarCampos() {
        txtMotivo.setText("");
        txtObservaciones.setText("");
        cboPacientes.setSelectedItem(null);
        cboMedicos.setSelectedItem(null);
        cboConsultorios.setSelectedItem(null);
        cboInicioHora.removeAllItems();
        txtFinHora.setText("");
        dcFecha.setDate(null);
        cboEstado.setSelectedItem("Confirmada");
        cboTipo.setSelectedIndex(0);
    }
    
   
    private void inicializarFiltroEntidad(String tabla, javax.swing.JComboBox<String> comboBox,List<String> lista,String mensajeError) {
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

        comboBox.removeAllItems();
        for (String item : lista) comboBox.addItem(item);

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
             ResultSet rs = st.executeQuery("SELECT id_consultorio, nombre, ubicacion FROM consultorios")) {
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String ubicacion = rs.getString("ubicacion");
                String textoCombo = nombre + " - " + ubicacion;
                cboConsultorios.addItem(textoCombo);
            }
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
        } catch (Exception e) {
            System.out.println("Error generando intervalos: " + e.getMessage());
        }
        return intervalos;
    }

    private Set<String> obtenerHorasOcupadas(int idMedico, String fechaSeleccionada) {
        Set<String> horasOcupadas = new HashSet<>();
        String sql = "SELECT TIME(fecha_hora_inicio) AS hora_inicio, TIME(fecha_hora_fin) AS hora_fin " +
                     "FROM citas WHERE id_medico = ? AND DATE(fecha_hora_inicio) = ? AND estado_cita != 'Cancelada'";
        try (Connection conexion = bd.miConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idMedico);
            ps.setString(2, fechaSeleccionada);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                horasOcupadas.addAll(generarIntervalos(rs.getString("hora_inicio"), rs.getString("hora_fin")));
            }
        } catch (Exception e) {
            System.out.println("Error obteniendo horas ocupadas: " + e.getMessage());
        }
        return horasOcupadas;
    }

    private void cargarHorariosMedico(String nombreMedicoSeleccionado) {
        cboInicioHora.removeAllItems();
        
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

                PreparedStatement psHorarios = conexion.prepareStatement("SELECT hora_inicio, hora_fin FROM horarios WHERE id_medico = ? AND dia_semana = ?");
                psHorarios.setInt(1, idMedico);
                psHorarios.setInt(2, diaSemana);

                ResultSet rsHorarios = psHorarios.executeQuery();
                List<String> horariosDisponibles = new ArrayList<>();
                while (rsHorarios.next()) {
                    horariosDisponibles.addAll(generarIntervalos(rsHorarios.getString("hora_inicio"), rsHorarios.getString("hora_fin")));
                }

                Set<String> horasOcupadas = obtenerHorasOcupadas(idMedico, fechaSeleccionada);
                horariosDisponibles.removeIf(horasOcupadas::contains);
                
                if (horariosDisponibles.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El médico no tiene horarios disponibles para esa fecha.", "Sin disponibilidad", JOptionPane.WARNING_MESSAGE);
                } else {
                    horariosDisponibles.forEach(cboInicioHora::addItem);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar horarios del médico: " + e.getMessage());
        }
    }

    private String obtenerFechaSeleccionada() {
        if (dcFecha.getDate() == null) return null;
        java.time.LocalDate fecha = dcFecha.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        return fecha.toString();
    }
    
    private String normalizarTexto(String texto) {
        if (texto == null) return "";
        String sinAcentos = java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return sinAcentos.toLowerCase();
    }
    
    private int obtenerIdPorNombre(Connection conexion, String tabla, String columnaId, Object seleccionado) {
        if (seleccionado == null) return -1;
        int id = -1;
        String nombreCompleto = seleccionado.toString();
        String campoNombre = switch (tabla) {
            case "medicos", "pacientes" -> "CONCAT(nombre, ' ', apellidos)";
            case "consultorios" -> "CONCAT(nombre, ' - ', ubicacion)";
            default -> "";
        };
        if (campoNombre.isEmpty()) return -1;

        String sql = "SELECT " + columnaId + " FROM " + tabla + " WHERE " + campoNombre + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreCompleto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) id = rs.getInt(columnaId);
        } catch (SQLException e) {
            System.out.println("Error obteniendo ID de " + tabla + ": " + e.getMessage());
        }
        return id;
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
        panelDatosCita = new javax.swing.JPanel();
        lblPaciente = new javax.swing.JLabel();
        cboPacientes = new javax.swing.JComboBox<>();
        lblMedico = new javax.swing.JLabel();
        cboMedicos = new javax.swing.JComboBox<>();
        btnBuscarMedico = new javax.swing.JButton();
        lblConsultorio = new javax.swing.JLabel();
        cboConsultorios = new javax.swing.JComboBox<>();
        lblMotivo = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
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
        panelBotones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelPrincipal.setBackground(new java.awt.Color(248, 249, 250));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(52, 58, 64));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Agendamiento de Cita");

        panelDatosCita.setBackground(new java.awt.Color(255, 255, 255));
        panelDatosCita.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 226, 230)), "Datos Principales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(73, 80, 87))); // NOI18N

        lblPaciente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPaciente.setText("Paciente:");

        cboPacientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMedico.setText("Médico:");

        cboMedicos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnBuscarMedico.setText("...");
        btnBuscarMedico.setToolTipText("Buscar Médico por Especialidad");
        btnBuscarMedico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMedicoActionPerformed(evt);
            }
        });

        lblConsultorio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblConsultorio.setText("Consultorio:");

        cboConsultorios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblMotivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMotivo.setText("Motivo de la Consulta:");

        txtMotivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelDatosCitaLayout = new javax.swing.GroupLayout(panelDatosCita);
        panelDatosCita.setLayout(panelDatosCitaLayout);
        panelDatosCitaLayout.setHorizontalGroup(
            panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosCitaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosCitaLayout.createSequentialGroup()
                        .addComponent(lblMotivo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosCitaLayout.createSequentialGroup()
                        .addGroup(panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMotivo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDatosCitaLayout.createSequentialGroup()
                                .addGroup(panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPaciente)
                                    .addComponent(cboPacientes, 0, 250, Short.MAX_VALUE))
                                .addGap(30, 30, 30)
                                .addGroup(panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMedico)
                                    .addGroup(panelDatosCitaLayout.createSequentialGroup()
                                        .addComponent(cboMedicos, 0, 250, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30)
                                .addGroup(panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblConsultorio)
                                    .addComponent(cboConsultorios, 0, 250, Short.MAX_VALUE))))
                        .addGap(20, 20, 20))))
        );
        panelDatosCitaLayout.setVerticalGroup(
            panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosCitaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPaciente)
                    .addComponent(lblMedico)
                    .addComponent(lblConsultorio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosCitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboConsultorios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMotivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelHorario.setBackground(new java.awt.Color(255, 255, 255));
        panelHorario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 226, 230)), "Fecha y Hora", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(73, 80, 87))); // NOI18N

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
        panelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 226, 230)), "Detalles Adicionales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(73, 80, 87))); // NOI18N

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

        javax.swing.GroupLayout panelDetallesLayout = new javax.swing.GroupLayout(panelDetalles);
        panelDetalles.setLayout(panelDetallesLayout);
        panelDetallesLayout.setHorizontalGroup(
            panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetallesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetallesLayout.createSequentialGroup()
                        .addComponent(lblObservaciones)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetallesLayout.createSequentialGroup()
                        .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDetallesLayout.createSequentialGroup()
                                .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTipo)
                                    .addComponent(cboTipo, 0, 416, Short.MAX_VALUE))
                                .addGap(42, 42, 42)
                                .addGroup(panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEstado)
                                    .addComponent(cboEstado, 0, 413, Short.MAX_VALUE))))
                        .addGap(20, 20, 20))))
        );
        panelDetallesLayout.setVerticalGroup(
            panelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetallesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
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

        btnGuardar.setBackground(new java.awt.Color(40, 167, 69));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Cita");
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
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnBuscarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMedicoActionPerformed
        FiltrarMedicos frm = new FiltrarMedicos(this, true);
        frm.setVisible(true);

        int idSeleccionado = frm.getMedicoSeleccionadoId();

        if (idSeleccionado > 0) {
            String nombreMedico = "";
            String sql = "SELECT CONCAT(nombre, ' ', apellidos) AS nombre_completo FROM medicos WHERE id_medico = ?";

            try (Connection con = bd.miConexion();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idSeleccionado);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        nombreMedico = rs.getString("nombre_completo");
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al buscar el médico: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!nombreMedico.isEmpty()) {
                cboMedicos.setSelectedItem(nombreMedico);
            }
        }
    }//GEN-LAST:event_btnBuscarMedicoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try (Connection conexion = bd.miConexion()) {
            if (cboPacientes.getSelectedItem() == null || cboMedicos.getSelectedItem() == null ||
                cboConsultorios.getSelectedItem() == null ||
                dcFecha.getDate() == null || cboInicioHora.getSelectedItem() == null) {
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

            java.time.LocalDate fecha = dcFecha.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            String horaInicioStr = cboInicioHora.getSelectedItem().toString();
            java.time.LocalTime horaInicio = java.time.LocalTime.parse(horaInicioStr);
            java.time.LocalTime horaFin = horaInicio.plusMinutes(30);
            String horaFinStr = horaFin.toString();

            java.time.LocalDateTime fechaHoraInicio = java.time.LocalDateTime.of(fecha, horaInicio);
            java.time.LocalDateTime fechaHoraFin = java.time.LocalDateTime.of(fecha, horaFin);

            if (!fechaHoraFin.isAfter(fechaHoraInicio)) {
                JOptionPane.showMessageDialog(this, "La hora de fin debe ser posterior a la hora de inicio.", "Error de horario", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idMedico = obtenerIdPorNombre(conexion, "medicos", "id_medico", medico);
            int idPaciente = obtenerIdPorNombre(conexion, "pacientes", "id_paciente", paciente);
            int idConsultorio = obtenerIdPorNombre(conexion, "consultorios", "id_consultorio", consultorio);

            if (idMedico == -1 || idPaciente == -1 || idConsultorio == -1) {
                JOptionPane.showMessageDialog(this, "No se pudo encontrar el paciente, médico o consultorio seleccionado. Verifique los datos.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String tabla = "citas";
            String campos = "id_paciente, id_medico, id_consultorio, fecha_hora_inicio, fecha_hora_fin, motivo_consulta, estado_cita, tipo_cita, observaciones";
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaInicioSQL = sdf.format(java.util.Date.from(fechaHoraInicio.atZone(java.time.ZoneId.systemDefault()).toInstant()));
            String fechaFinSQL = sdf.format(java.util.Date.from(fechaHoraFin.atZone(java.time.ZoneId.systemDefault()).toInstant()));

            String valores = String.format("%d, %d, %d, '%s', '%s', '%s', '%s', '%s', '%s'",
                idPaciente, idMedico, idConsultorio,
                fechaInicioSQL, fechaFinSQL,
                motivo.replace("'", "''"), estado, tipo, observaciones.replace("'", "''")
            );

            if (bd.insertarRegistro(tabla, campos, valores)) {
                JOptionPane.showMessageDialog(this, "Cita registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la cita: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAgregarCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
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
    private javax.swing.JButton btnBuscarMedico;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cboConsultorios;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboInicioHora;
    private javax.swing.JComboBox<String> cboMedicos;
    private javax.swing.JComboBox<String> cboPacientes;
    private javax.swing.JComboBox<String> cboTipo;
    private com.toedter.calendar.JDateChooser dcFecha;
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
    private javax.swing.JPanel panelDatosCita;
    private javax.swing.JPanel panelDetalles;
    private javax.swing.JPanel panelHorario;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTextField txtFinHora;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables
}
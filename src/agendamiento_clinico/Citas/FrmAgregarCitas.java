import agendamiento_clinico.BaseDatos;
import agendamiento_clinico.DatosCombo;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;


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

        cboDia.addActionListener(recargarHorarios); // día
        cboMes.addActionListener(recargarHorarios); // mes
        cboAnho.addActionListener(recargarHorarios); // año

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboPacientes = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboInicioHora = new javax.swing.JComboBox<>();
        cboFinHora = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cboDia = new javax.swing.JComboBox<>();
        cboMes = new javax.swing.JComboBox<>();
        cboAnho = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cboMedicos = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnGuardar.setText("Guardar");

        btnCancelar.setText("Cancelar");

        jLabel1.setText("Cita N°");

        jLabel2.setText("Paciente");

        cboPacientes.setEditable(true);
        cboPacientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboPacientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cboPacientesKeyReleased(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setText("Sintomas");

        jLabel5.setText("Horario Inicio");

        jLabel6.setText("Horario Fin");

        jLabel7.setText("Fecha");

        cboDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cboMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", " " }));

        cboAnho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "20243", "2044", "2045", "2046", "2047", "2048", "2049", " " }));

        jLabel4.setText("Medico");

        cboMedicos.setEditable(true);
        cboMedicos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMedicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cboMedicosKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(367, 367, 367))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cboFinHora, 0, 98, Short.MAX_VALUE)
                        .addComponent(cboInicioHora, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cboDia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboAnho, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cboInicioHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cboFinHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboAnho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(cboMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboPacientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboPacientesKeyReleased
        String texto = cboPacientes.getEditor().getItem().toString().trim();
        inicializarFiltroPacientes();
    }//GEN-LAST:event_cboPacientesKeyReleased

    private void cboMedicosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboMedicosKeyReleased
        String texto = cboMedicos.getEditor().getItem().toString().trim();
        inicializarFiltroMedicos();
    }//GEN-LAST:event_cboMedicosKeyReleased
    
    private String obtenerFechaSeleccionada() {
    try {
        int dia = Integer.parseInt(cboDia.getSelectedItem().toString());
        int mes = cboMes.getSelectedIndex() + 1; // Enero = 0, por eso sumamos 1
        int anho = Integer.parseInt(cboAnho.getSelectedItem().toString());
        return String.format("%04d-%02d-%02d", anho, mes, dia);
    } catch (Exception e) {
        return null;
    }
}

    
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

                for (String paciente : listaPacientes) {
                    if (paciente.toLowerCase().contains(texto.toLowerCase())) {
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

                for (String medico : listaMedicos) {
                    if (medico.toLowerCase().contains(texto.toLowerCase())) {
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
            "SELECT hora_inicio, hora_fin FROM citas WHERE id_medico = ? AND fecha = ?"
        );
        ps.setInt(1, idMedico);
        ps.setString(2, fechaSeleccionada);
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
    private javax.swing.JComboBox<String> cboAnho;
    private javax.swing.JComboBox<String> cboDia;
    private javax.swing.JComboBox<String> cboFinHora;
    private javax.swing.JComboBox<String> cboInicioHora;
    private javax.swing.JComboBox<String> cboMedicos;
    private javax.swing.JComboBox<String> cboMes;
    private javax.swing.JComboBox<String> cboPacientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}

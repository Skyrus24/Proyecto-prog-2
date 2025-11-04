package agendamiento_clinico.medicos;

import agendamiento_clinico.BaseDatos;
import agendamiento_clinico.DatosCombo;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class FrmAgregarMedicos extends javax.swing.JDialog {

    private final BaseDatos bd = new BaseDatos();
    private final char opc;
    private final int idMedicoModificar;

    public FrmAgregarMedicos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.opc = 'N';
        this.idMedicoModificar = 0;
        initComponents();
        configurarFormulario("Agregar Nuevo Médico");
    }

    public FrmAgregarMedicos(java.awt.Frame parent, boolean modal, int idMedico) {
        super(parent, modal);
        this.opc = 'M';
        this.idMedicoModificar = idMedico;
        initComponents();
        configurarFormulario("Modificar Datos del Médico");
        cargarDatosMedico();
    }
    
    
    private void configurarFormulario(String titulo) {
        this.setTitle(titulo);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(248, 249, 250));
        
        if (!bd.hayConexion()) {
            JOptionPane.showMessageDialog(this, "Error de conexión con la base de datos.", "Error Crítico", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        bd.cargarCombo(cboEspecialidad, "id_especialidad, nombre_especialidad", "especialidades");

        if (opc == 'M') {
            cmdNuevo.setVisible(false);
            habilitarCampos(true);
            habilitarBotones(false);
        } else {
            habilitarCampos(false);
            habilitarBotones(true);
        }
    }
    
    private void cargarDatosMedico() {
        try (ResultSet rs = bd.consultarRegistros("SELECT * FROM medicos WHERE id_medico=" + this.idMedicoModificar)) {
            if (rs != null && rs.next()) {
                txtNombre.setText(rs.getString("nombre"));
                txtApellido.setText(rs.getString("apellidos"));
                txtCedula.setText(rs.getString("cedula"));
                txtNacionalidad.setText(rs.getString("nacionalidad"));
                txtRegistroProfesional.setText(rs.getString("registro_profesional"));
                txtTel.setText(rs.getString("telefono"));
                txtEmail.setText(rs.getString("email"));
                txtDireccion.setText(rs.getString("direccion"));
                cboEstadoRegistro.setSelectedItem(rs.getString("estado_registro"));
                
                bd.seleccionarItemCombo(cboEspecialidad, rs.getInt("id_especialidad"));
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    String fechaNac = rs.getString("fecha_nacimiento");
                    if (fechaNac != null) dcFechaNacimiento.setDate(sdf.parse(fechaNac));
                    
                    String fechaReg = rs.getString("fecha_registro_profesional");
                    if (fechaReg != null) dcFechaRegistro.setDate(sdf.parse(fechaReg));
                    
                    String fechaVen = rs.getString("fecha_vencimiento_registro");
                    if (fechaVen != null) dcFechaVencimiento.setDate(sdf.parse(fechaVen));
                } catch (ParseException | NullPointerException e) {
                    // No hacer nada si la fecha es nula o inválida en la BD
                }
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos del médico: " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void habilitarCampos(boolean estado) {
        txtNombre.setEnabled(estado);
        txtApellido.setEnabled(estado);
        txtCedula.setEnabled(estado);
        dcFechaNacimiento.setEnabled(estado);
        txtNacionalidad.setEnabled(estado);
        cboEspecialidad.setEnabled(estado);
        txtRegistroProfesional.setEnabled(estado);
        dcFechaRegistro.setEnabled(estado);
        dcFechaVencimiento.setEnabled(estado);
        cboEstadoRegistro.setEnabled(estado);
        txtTel.setEnabled(estado);
        txtEmail.setEnabled(estado);
        txtDireccion.setEnabled(estado);
    }

    private void habilitarBotones(boolean estado) {
        cmdNuevo.setEnabled(estado);
        cmdGuardar.setEnabled(!estado);
        cmdCancelar.setEnabled(!estado);
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        dcFechaNacimiento.setDate(null);
        txtNacionalidad.setText("Paraguaya");
        if (cboEspecialidad.getItemCount() > 0) cboEspecialidad.setSelectedIndex(0);
        txtRegistroProfesional.setText("");
        dcFechaRegistro.setDate(null);
        dcFechaVencimiento.setDate(null);
        if (cboEstadoRegistro.getItemCount() > 0) cboEstadoRegistro.setSelectedIndex(0);
        txtTel.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
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
        panelDatosPersonales = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        lblFechaNacimiento = new javax.swing.JLabel();
        dcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        lblNacionalidad = new javax.swing.JLabel();
        txtNacionalidad = new javax.swing.JTextField();
        panelDatosProfesionales = new javax.swing.JPanel();
        lblEspecialidad = new javax.swing.JLabel();
        cboEspecialidad = new javax.swing.JComboBox<>();
        lblRegistroProfesional = new javax.swing.JLabel();
        txtRegistroProfesional = new javax.swing.JTextField();
        lblFechaRegistro = new javax.swing.JLabel();
        dcFechaRegistro = new com.toedter.calendar.JDateChooser();
        lblFechaVencimiento = new javax.swing.JLabel();
        dcFechaVencimiento = new com.toedter.calendar.JDateChooser();
        lblEstadoRegistro = new javax.swing.JLabel();
        cboEstadoRegistro = new javax.swing.JComboBox<>();
        panelContacto = new javax.swing.JPanel();
        lblTel = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        panelBotones = new javax.swing.JPanel();
        cmdNuevo = new javax.swing.JButton();
        cmdGuardar = new javax.swing.JButton();
        cmdCancelar = new javax.swing.JButton();
        cmdEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        panelPrincipal.setBackground(new java.awt.Color(248, 249, 250));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(52, 58, 64));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Registro de Médicos");

        panelDatosPersonales.setBackground(new java.awt.Color(255, 255, 255));
        panelDatosPersonales.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 226, 230)), "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(73, 80, 87))); // NOI18N

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblApellido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblApellido.setText("Apellido:");

        txtApellido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblCedula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCedula.setText("Cédula:");

        txtCedula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblFechaNacimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFechaNacimiento.setText("Fecha de Nacimiento:");

        dcFechaNacimiento.setDateFormatString("dd/MM/yyyy");
        dcFechaNacimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblNacionalidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNacionalidad.setText("Nacionalidad:");

        txtNacionalidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelDatosPersonalesLayout = new javax.swing.GroupLayout(panelDatosPersonales);
        panelDatosPersonales.setLayout(panelDatosPersonalesLayout);
        panelDatosPersonalesLayout.setHorizontalGroup(
            panelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosPersonalesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(lblCedula)
                    .addComponent(txtCedula))
                .addGap(30, 30, 30)
                .addGroup(panelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellido)
                    .addGroup(panelDatosPersonalesLayout.createSequentialGroup()
                        .addGroup(panelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblApellido)
                            .addComponent(lblFechaNacimiento))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(dcFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(panelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNacionalidad)
                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelDatosPersonalesLayout.setVerticalGroup(
            panelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosPersonalesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblApellido)
                    .addComponent(lblNacionalidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCedula)
                    .addComponent(lblFechaNacimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCedula)
                    .addComponent(dcFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelDatosProfesionales.setBackground(new java.awt.Color(255, 255, 255));
        panelDatosProfesionales.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 226, 230)), "Datos Profesionales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(73, 80, 87))); // NOI18N

        lblEspecialidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEspecialidad.setText("Especialidad:");

        cboEspecialidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblRegistroProfesional.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblRegistroProfesional.setText("Registro Profesional:");

        txtRegistroProfesional.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblFechaRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFechaRegistro.setText("Fecha de Registro:");

        dcFechaRegistro.setDateFormatString("dd/MM/yyyy");
        dcFechaRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblFechaVencimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFechaVencimiento.setText("Fecha de Vencimiento:");

        dcFechaVencimiento.setDateFormatString("dd/MM/yyyy");
        dcFechaVencimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblEstadoRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEstadoRegistro.setText("Estado del Registro:");

        cboEstadoRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboEstadoRegistro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo", "Suspendido", "En trámite" }));

        javax.swing.GroupLayout panelDatosProfesionalesLayout = new javax.swing.GroupLayout(panelDatosProfesionales);
        panelDatosProfesionales.setLayout(panelDatosProfesionalesLayout);
        panelDatosProfesionalesLayout.setHorizontalGroup(
            panelDatosProfesionalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosProfesionalesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDatosProfesionalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEspecialidad)
                    .addComponent(lblFechaRegistro)
                    .addComponent(dcFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelDatosProfesionalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRegistroProfesional, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(lblRegistroProfesional)
                    .addComponent(lblFechaVencimiento)
                    .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(panelDatosProfesionalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstadoRegistro)
                    .addComponent(cboEstadoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDatosProfesionalesLayout.setVerticalGroup(
            panelDatosProfesionalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosProfesionalesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelDatosProfesionalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEspecialidad)
                    .addComponent(lblRegistroProfesional)
                    .addComponent(lblEstadoRegistro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosProfesionalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRegistroProfesional, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEstadoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatosProfesionalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaRegistro)
                    .addComponent(lblFechaVencimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosProfesionalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dcFechaRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(dcFechaVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelContacto.setBackground(new java.awt.Color(255, 255, 255));
        panelContacto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 226, 230)), "Información de Contacto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(73, 80, 87))); // NOI18N

        lblTel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTel.setText("Teléfono:");

        txtTel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmail.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDireccion.setText("Dirección:");

        txtDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelContactoLayout = new javax.swing.GroupLayout(panelContacto);
        panelContacto.setLayout(panelContactoLayout);
        panelContactoLayout.setHorizontalGroup(
            panelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContactoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion)
                    .addGroup(panelContactoLayout.createSequentialGroup()
                        .addGroup(panelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTel))
                        .addGap(30, 30, 30)
                        .addGroup(panelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail)
                            .addComponent(txtEmail))))
                .addGap(20, 20, 20))
        );
        panelContactoLayout.setVerticalGroup(
            panelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContactoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTel)
                    .addComponent(lblEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblDireccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelBotones.setBackground(new java.awt.Color(248, 249, 250));

        cmdNuevo.setBackground(new java.awt.Color(23, 162, 184));
        cmdNuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdNuevo.setForeground(new java.awt.Color(255, 255, 255));
        cmdNuevo.setText("Nuevo");
        cmdNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNuevoActionPerformed(evt);
            }
        });

        cmdGuardar.setBackground(new java.awt.Color(40, 167, 69));
        cmdGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdGuardar.setForeground(new java.awt.Color(255, 255, 255));
        cmdGuardar.setText("Guardar");
        cmdGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGuardarActionPerformed(evt);
            }
        });

        cmdCancelar.setBackground(new java.awt.Color(108, 117, 125));
        cmdCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdCancelar.setForeground(new java.awt.Color(255, 255, 255));
        cmdCancelar.setText("Cancelar");
        cmdCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelarActionPerformed(evt);
            }
        });

        cmdEliminar.setBackground(new java.awt.Color(220, 53, 69));
        cmdEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdEliminar.setForeground(new java.awt.Color(255, 255, 255));
        cmdEliminar.setText("Salir");
        cmdEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addComponent(cmdNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cmdNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmdGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmdCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmdEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
                    .addComponent(panelDatosPersonales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDatosProfesionales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelContacto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(20, 20, 20)
                .addComponent(panelDatosPersonales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelDatosProfesionales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
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

    private void cmdNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNuevoActionPerformed
        limpiarCampos();
        habilitarCampos(true);
        habilitarBotones(false);
        txtNombre.requestFocus();
    }//GEN-LAST:event_cmdNuevoActionPerformed

    private void cmdGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGuardarActionPerformed
        if(txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty() || txtRegistroProfesional.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Los campos Nombre, Apellido y Registro Profesional son obligatorios.", "Campos Requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaNacimiento = (dcFechaNacimiento.getDate() != null) ? "'" + sdf.format(dcFechaNacimiento.getDate()) + "'" : "NULL";
        String fechaRegistro = (dcFechaRegistro.getDate() != null) ? "'" + sdf.format(dcFechaRegistro.getDate()) + "'" : "NULL";
        String fechaVencimiento = (dcFechaVencimiento.getDate() != null) ? "'" + sdf.format(dcFechaVencimiento.getDate()) + "'" : "NULL";

        Object itemSeleccionado = this.cboEspecialidad.getSelectedItem();
        if (!(itemSeleccionado instanceof DatosCombo)) {
             JOptionPane.showMessageDialog(this, "Debe seleccionar una especialidad válida.", "Dato Inválido", JOptionPane.WARNING_MESSAGE);
             return;
        }
        int codEspecialidad = ((DatosCombo) itemSeleccionado).getCodigo();

        if (opc == 'N') {
            String campos = "nombre, apellidos, fecha_nacimiento, nacionalidad, cedula, id_especialidad, email, telefono, direccion, registro_profesional, fecha_registro_profesional, fecha_vencimiento_registro, estado_registro";
            String valores = String.format("'%s', '%s', %s, '%s', '%s', %d, '%s', '%s', '%s', '%s', %s, %s, '%s'",
                txtNombre.getText().trim(), txtApellido.getText().trim(), fechaNacimiento, txtNacionalidad.getText().trim(),
                txtCedula.getText().trim(), codEspecialidad, txtEmail.getText().trim(), txtTel.getText().trim(), txtDireccion.getText().trim(),
                txtRegistroProfesional.getText().trim(), fechaRegistro, fechaVencimiento, cboEstadoRegistro.getSelectedItem().toString()
            );

            if (bd.insertarRegistro("medicos(" + campos + ")", valores)) {
                 JOptionPane.showMessageDialog(this, "Médico agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                 limpiarCampos();
                 habilitarCampos(false);
                 habilitarBotones(true);
            }
        } else {
             String actualizaciones = String.format(
                "nombre='%s', apellidos='%s', fecha_nacimiento=%s, nacionalidad='%s', cedula='%s', id_especialidad=%d, " +
                "email='%s', telefono='%s', direccion='%s', registro_profesional='%s', fecha_registro_profesional=%s, " +
                "fecha_vencimiento_registro=%s, estado_registro='%s'",
                txtNombre.getText().trim(), txtApellido.getText().trim(), fechaNacimiento, txtNacionalidad.getText().trim(),
                txtCedula.getText().trim(), codEspecialidad, txtEmail.getText().trim(), txtTel.getText().trim(), txtDireccion.getText().trim(),
                txtRegistroProfesional.getText().trim(), fechaRegistro, fechaVencimiento, cboEstadoRegistro.getSelectedItem().toString()
            );
            
            if (bd.actualizarRegistro("medicos", actualizaciones, "id_medico=" + this.idMedicoModificar)) {
                JOptionPane.showMessageDialog(this, "Datos del médico actualizados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        }
    }//GEN-LAST:event_cmdGuardarActionPerformed

    private void cmdCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelarActionPerformed
        if(opc == 'N') {
            limpiarCampos();
            habilitarCampos(false);
            habilitarBotones(true);
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_cmdCancelarActionPerformed

    private void cmdEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEliminarActionPerformed
        this.dispose();
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
            java.util.logging.Logger.getLogger(FrmAgregarMedicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarMedicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarMedicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarMedicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAgregarMedicos dialog = new FrmAgregarMedicos(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<Object> cboEspecialidad;
    private javax.swing.JComboBox<String> cboEstadoRegistro;
    private javax.swing.JButton cmdCancelar;
    private javax.swing.JButton cmdEliminar;
    private javax.swing.JButton cmdGuardar;
    private javax.swing.JButton cmdNuevo;
    private com.toedter.calendar.JDateChooser dcFechaNacimiento;
    private com.toedter.calendar.JDateChooser dcFechaRegistro;
    private com.toedter.calendar.JDateChooser dcFechaVencimiento;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEspecialidad;
    private javax.swing.JLabel lblEstadoRegistro;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblFechaRegistro;
    private javax.swing.JLabel lblFechaVencimiento;
    private javax.swing.JLabel lblNacionalidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRegistroProfesional;
    private javax.swing.JLabel lblTel;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelContacto;
    private javax.swing.JPanel panelDatosPersonales;
    private javax.swing.JPanel panelDatosProfesionales;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRegistroProfesional;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
package agendamiento_clinico;

// Importa todas las clases de tus formularios (FrmAgregarPaciente, FrmLogin, etc.)
import agendamiento_clinico.cita.*;
import agendamiento_clinico.especialidades.FrmEspecialidades;
import agendamiento_clinico.gestione.*;
import agendamiento_clinico.historialClinico.*;
import agendamiento_clinico.horario.*;
import agendamiento_clinico.medicos.*;
import agendamiento_clinico.pacientes.*;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class Main extends javax.swing.JFrame {

    private String rolUsuario;
    private JPanel panelContenido;
    private CardLayout cardLayout;

    // Componentes que podríamos necesitar ocultar según el rol
    private JButton btnMedicos;
    private JPanel panelSubMenuPacientes;
    private JPanel panelSubMenuMedicos;
    private JPanel panelSubMenuCitas;
    private JPanel panelSubMenuHistorial;
    private JPanel panelSubMenuHorarios;
    private JPanel panelSubMenuConfiguracion;
    private JButton btnAgregarConsulta;
    private JButton btnGestionarHistorial;
    private JButton btnConsultorios;
    private JButton btnMedicamentos;
    private JButton btnRecetas;
    private JButton btnGestionarHorarios;
    // MODIFICADO: Añadidas variables para los botones del submenú de Médicos
    private JButton btnAgregarMedico;
    private JButton btnGestionarMedicos;

    // Paleta de colores
    private final Color COLOR_SIDEBAR = new Color(45, 52, 71);
    private final Color COLOR_SIDEBAR_TEXT = new Color(220, 220, 220);
    private final Color COLOR_SIDEBAR_HOVER = new Color(65, 75, 100);
    private final Color COLOR_CONTENT_BACKGROUND = new Color(248, 249, 250);
    private final Color COLOR_SUBMENU_BACKGROUND = Color.WHITE;
    private final Color COLOR_SUBMENU_TEXT = new Color(80, 80, 80);
    private final Color COLOR_SUBMENU_HOVER = new Color(220, 235, 255);

    /**
     * Constructor principal
     * @param rol Rol del usuario que inicia sesión (Ej: "Administrador")
     */
    public Main(String rol) {
        this.rolUsuario = rol;
        
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Falló la inicialización de FlatLaf.");
        }
        
        initComponentsManual();
        configurarSegunRol();
    }

    /**
     * Este método construye toda la interfaz gráfica desde cero, sin diseñador.
     */
    private void initComponentsManual() {
        setTitle("Sistema de Gestión Clínica - " + rolUsuario);
        setMinimumSize(new Dimension(1200, 800));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel panelSidebar = new JPanel();
        panelSidebar.setLayout(new MigLayout("wrap, fillx, insets 10 0 10 0", "[grow]"));
        panelSidebar.setBackground(COLOR_SIDEBAR);
        panelSidebar.setPreferredSize(new Dimension(280, 0));

        JLabel lblTituloApp = new JLabel("Clínica San Jorge");
        lblTituloApp.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTituloApp.setForeground(Color.WHITE);
        lblTituloApp.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloApp.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 10));
        panelSidebar.add(lblTituloApp, "align center");

        // --- 1. Pacientes ---
        JButton btnPacientes = crearBotonMenu("Pacientes");
        panelSidebar.add(btnPacientes, "h 50!, growx");
        panelSubMenuPacientes = crearPanelSubMenu(new String[]{"Agregar Paciente", "Gestionar Pacientes", "Listar Pacientes"});
        panelSidebar.add(panelSubMenuPacientes, "growx, hidemode 3");
        btnPacientes.addActionListener(e -> toggleSubMenu(panelSubMenuPacientes));
        
        // --- 2. Médicos ---
        // MODIFICADO: Se construye el submenú de Médicos manualmente para tener control sobre sus botones
        btnMedicos = crearBotonMenu("Médicos");
        panelSidebar.add(btnMedicos, "h 50!, growx");
        panelSubMenuMedicos = new JPanel(new MigLayout("wrap, fillx, insets 0", "[grow]"));
        panelSubMenuMedicos.setBackground(COLOR_SUBMENU_BACKGROUND);
        btnAgregarMedico = crearBotonSubMenu("Agregar Médico");
        btnGestionarMedicos = crearBotonSubMenu("Gestionar Médicos");
        panelSubMenuMedicos.add(btnAgregarMedico, "h 40!, growx");
        panelSubMenuMedicos.add(btnGestionarMedicos, "h 40!, growx");
        panelSubMenuMedicos.add(crearBotonSubMenu("Listar Médicos"), "h 40!, growx");
        panelSubMenuMedicos.setVisible(false);
        panelSidebar.add(panelSubMenuMedicos, "growx, hidemode 3");
        btnMedicos.addActionListener(e -> toggleSubMenu(panelSubMenuMedicos));

        // --- 3. Citas ---
        JButton btnCitas = crearBotonMenu("Citas");
        panelSidebar.add(btnCitas, "h 50!, growx");
        panelSubMenuCitas = crearPanelSubMenu(new String[]{"Agendar Cita", "Modificar Cita", "Cancelar Cita", "Listar Citas"});
        panelSidebar.add(panelSubMenuCitas, "growx, hidemode 3");
        btnCitas.addActionListener(e -> toggleSubMenu(panelSubMenuCitas));

        // --- 4. Historial Clínico ---
        JButton btnHistorial = crearBotonMenu("Historial Clínico");
        panelSidebar.add(btnHistorial, "h 50!, growx");
        panelSubMenuHistorial = new JPanel(new MigLayout("wrap, fillx, insets 0", "[grow]"));
        panelSubMenuHistorial.setBackground(COLOR_SUBMENU_BACKGROUND);
        btnAgregarConsulta = crearBotonSubMenu("Agregar Consulta");
        btnGestionarHistorial = crearBotonSubMenu("Gestionar Historial");
        panelSubMenuHistorial.add(btnAgregarConsulta, "h 40!, growx");
        panelSubMenuHistorial.add(btnGestionarHistorial, "h 40!, growx");
        panelSubMenuHistorial.add(crearBotonSubMenu("Visualizar Historial"), "h 40!, growx");
        panelSubMenuHistorial.setVisible(false);
        panelSidebar.add(panelSubMenuHistorial, "growx, hidemode 3");
        btnHistorial.addActionListener(e -> toggleSubMenu(panelSubMenuHistorial));

        // --- 5. Horarios ---
        JButton btnHorarios = crearBotonMenu("Horarios");
        panelSidebar.add(btnHorarios, "h 50!, growx");
        panelSubMenuHorarios = new JPanel(new MigLayout("wrap, fillx, insets 0", "[grow]"));
        panelSubMenuHorarios.setBackground(COLOR_SUBMENU_BACKGROUND);
        btnGestionarHorarios = crearBotonSubMenu("Gestionar Horarios");
        panelSubMenuHorarios.add(btnGestionarHorarios, "h 40!, growx");
        panelSubMenuHorarios.add(crearBotonSubMenu("Visualizar Horarios"), "h 40!, growx");
        panelSubMenuHorarios.setVisible(false);
        panelSidebar.add(panelSubMenuHorarios, "growx, hidemode 3");
        btnHorarios.addActionListener(e -> toggleSubMenu(panelSubMenuHorarios));
        
        panelSidebar.add(new JSeparator(), "growx, gaptop 15, gapbottom 15");
        
        // --- 6. Configuración ---
        JButton btnConfiguracion = crearBotonMenu("Configuración");
        panelSidebar.add(btnConfiguracion, "h 50!, growx");
        panelSubMenuConfiguracion = new JPanel(new MigLayout("wrap, fillx, insets 0", "[grow]"));
        panelSubMenuConfiguracion.setBackground(COLOR_SUBMENU_BACKGROUND);
        btnConsultorios = crearBotonSubMenu("Consultorios");
        btnMedicamentos = crearBotonSubMenu("Medicamentos");
        btnRecetas = crearBotonSubMenu("Recetas");
        panelSubMenuConfiguracion.add(crearBotonSubMenu("Especialidades"), "h 40!, growx");
        panelSubMenuConfiguracion.add(btnConsultorios, "h 40!, growx");
        panelSubMenuConfiguracion.add(btnMedicamentos, "h 40!, growx");
        panelSubMenuConfiguracion.add(btnRecetas, "h 40!, growx");
        panelSubMenuConfiguracion.setVisible(false);
        panelSidebar.add(panelSubMenuConfiguracion, "growx, hidemode 3");
        btnConfiguracion.addActionListener(e -> toggleSubMenu(panelSubMenuConfiguracion));
        
        panelSidebar.add(new JLabel(), "pushy"); 
        
        JButton btnCambiarSesion = new JButton("Cambiar Sesión");
        personalizarBotonAccion(btnCambiarSesion);
        panelSidebar.add(btnCambiarSesion, "growx, h 45!, gaptop 10, gapleft 10, gapright 10");
        
        JButton btnSalir = new JButton("Salir del Sistema");
        personalizarBotonSalida(btnSalir);
        panelSidebar.add(btnSalir, "growx, h 45!, gaptop 5, gapleft 10, gapright 10");

        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        panelContenido.setBackground(COLOR_CONTENT_BACKGROUND);
        panelContenido.add(crearPanelBienvenida(), "BIENVENIDA");

        contentPane.add(panelSidebar, BorderLayout.WEST);
        contentPane.add(panelContenido, BorderLayout.CENTER);
        
        cardLayout.show(panelContenido, "BIENVENIDA");
    }
    
    /**
     * Oculta o elimina componentes del menú según el rol del usuario.
     */
    private void configurarSegunRol() {
        JPanel sidebar = (JPanel) btnMedicos.getParent();

        switch (rolUsuario) {
            case "Medico":
                // MODIFICADO: Ahora el médico puede ver el menú de médicos, pero solo para listar.
                removerComponenteSubMenu(panelSubMenuMedicos, btnAgregarMedico);
                removerComponenteSubMenu(panelSubMenuMedicos, btnGestionarMedicos);
                
                // Se mantienen las otras restricciones
                removerComponenteSubMenu(panelSubMenuConfiguracion, btnConsultorios);
                removerComponenteSubMenu(panelSubMenuHorarios, btnGestionarHorarios);
                break;

            case "Recepcionista":
                // Esta regla no cambia: la recepcionista no ve nada del menú de médicos.
                removerComponenteMenu(btnMedicos, panelSubMenuMedicos);
                
                removerComponenteSubMenu(panelSubMenuHistorial, btnAgregarConsulta);
                removerComponenteSubMenu(panelSubMenuHistorial, btnGestionarHistorial);
                removerComponenteSubMenu(panelSubMenuConfiguracion, btnMedicamentos);
                removerComponenteSubMenu(panelSubMenuConfiguracion, btnRecetas);
                break;
                
            default:
                return; 
        }

        sidebar.revalidate();
        sidebar.repaint();
    }
    
    private JPanel crearPanelSubMenu(String[] opciones) {
        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 0", "[grow]"));
        panel.setBackground(COLOR_SUBMENU_BACKGROUND);
        for (String opcion : opciones) {
            panel.add(crearBotonSubMenu(opcion), "h 40!, growx");
        }
        panel.setVisible(false);
        return panel;
    }
    
    // (El resto de los métodos se mantienen exactamente igual)
    
    private JButton crearBotonSubMenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        boton.setForeground(COLOR_SUBMENU_TEXT);
        boton.setBackground(COLOR_SUBMENU_BACKGROUND);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFocusPainted(false);
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { boton.setBackground(COLOR_SUBMENU_HOVER); }
            @Override
            public void mouseExited(MouseEvent e) { boton.setBackground(COLOR_SUBMENU_BACKGROUND); }
        });
        boton.addActionListener(e -> abrirFormularioCorrespondiente(texto));
        return boton;
    }
    
    private void toggleSubMenu(JPanel subMenu) {
        subMenu.setVisible(!subMenu.isVisible());
        subMenu.getParent().revalidate();
        subMenu.getParent().repaint();
    }
    
    private JPanel crearPanelBienvenida() {
        JPanel panel = new JPanel(new MigLayout("fill, align center center"));
        panel.setOpaque(false);
        JLabel titulo = new JLabel("Bienvenido al Sistema de Gestión Clínica");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        titulo.setForeground(COLOR_SIDEBAR);
        JLabel subtitulo = new JLabel("Seleccione una opción del menú lateral para comenzar a trabajar.");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitulo.setForeground(Color.GRAY);
        JLabel rolLabel = new JLabel("Ha iniciado sesión como: " + rolUsuario);
        rolLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        rolLabel.setForeground(new Color(139, 92, 246));
        panel.add(titulo, "wrap, align center, gapbottom 10");
        panel.add(subtitulo, "wrap, align center, gapbottom 30");
        panel.add(rolLabel, "wrap, align center");
        return panel;
    }

    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        boton.setForeground(COLOR_SIDEBAR_TEXT);
        boton.setBackground(COLOR_SIDEBAR);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFocusPainted(false);
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { boton.setBackground(COLOR_SIDEBAR_HOVER); }
            @Override
            public void mouseExited(MouseEvent e) { boton.setBackground(COLOR_SIDEBAR); }
        });
        return boton;
    }
    
    private void personalizarBotonAccion(JButton boton) {
        boton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        boton.setBackground(new Color(108, 117, 125));
        boton.setForeground(Color.WHITE);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.addActionListener(e -> {
            new FrmLogin().setVisible(true); 
            this.dispose();
        });
    }

    private void personalizarBotonSalida(JButton boton) {
        boton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        boton.setBackground(new Color(220, 53, 69));
        boton.setForeground(Color.WHITE);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.addActionListener(e -> System.exit(0));
    }
    
    private void removerComponenteMenu(JComponent... components) {
        for (JComponent comp : components) {
            if (comp != null) {
                comp.setVisible(false);
                comp.getParent().remove(comp);
            }
        }
    }

    private void removerComponenteSubMenu(JPanel subMenuPanel, JComponent componentToRemove) {
        if (subMenuPanel == null || componentToRemove == null) return;
        componentToRemove.setVisible(false);
        subMenuPanel.remove(componentToRemove);
    }
    
    private void abrirFormularioCorrespondiente(String seleccion) {
        if (seleccion == null) return;
        switch(seleccion) {
            case "Agregar Paciente": new FrmAgregarPaciente(this, true).setVisible(true); break;
            case "Gestionar Pacientes": new FrmGestionarPacientes(this, true).setVisible(true); break;
            case "Listar Pacientes": new FrmListarPacientes(this, true).setVisible(true); break;
            case "Agregar Médico": new FrmAgregarMedicos(this, true).setVisible(true); break;
            case "Gestionar Médicos": new FrmGestionarMedicos(this, true).setVisible(true); break;
            case "Listar Médicos": new FrmListarMedicos(this, true).setVisible(true); break;
            case "Agendar Cita": new FrmAgregarCitas(this, true).setVisible(true); break;
            case "Modificar Cita": new FrmModificarCitas(this, true).setVisible(true); break;
            case "Cancelar Cita": new FrmEliminarCitas(this, true).setVisible(true); break;
            case "Listar Citas": new FrmListar(this, true).setVisible(true); break;
            case "Agregar Consulta": new FrmConsulta(this, true).setVisible(true); break;
            case "Gestionar Historial": new FrmGestionHistorial(this, true).setVisible(true); break;
            case "Visualizar Historial": new FrmVisualisarHistorial(this, true).setVisible(true); break;
            case "Gestionar Horarios": new FrmHorarios(this, true).setVisible(true); break;
            case "Visualizar Horarios": new FrmVisualizarHorarios(this, true).setVisible(true); break;
            case "Especialidades": new FrmEspecialidades(this, true).setVisible(true); break;
            case "Consultorios": new FrmConsultorios(this, true).setVisible(true); break;
            case "Medicamentos": new FrmMedicamentos(this, true).setVisible(true); break;
            case "Recetas": new FrmRecetas(this, true).setVisible(true); break;
        }
    }

    private void initComponents() {}

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Main("Medico").setVisible(true);
        });
    }
}
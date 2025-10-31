package agendamiento_clinico;

import agendamiento_clinico.especialidades.*;
import agendamiento_clinico.cita.*;
import agendamiento_clinico.gestione.*;
import agendamiento_clinico.horario.*;
import agendamiento_clinico.medicos.*;
import agendamiento_clinico.pacientes.*;
import agendamiento_clinico.historialClinico.*;

import com.formdev.flatlaf.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuBarUI;


public class Main extends javax.swing.JFrame {

    private String rolUsuario;

    public Main() {
        this("Administrador");
    }
    
    public Main(String rol) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Falló la inicialización de FlatLaf.");
        }
        
        initComponents();
        
        this.rolUsuario = rol;
        
        aplicarEstilosYLayout(); 
        
        configurarSegunRol(); 
    }
    
    /**
     * Este método centraliza todas las personalizaciones visuales y de layout.
     * Se ejecuta DESPUÉS de initComponents(), por lo que puede modificar los
     * componentes que NetBeans ya ha creado.
     */
    private void aplicarEstilosYLayout() {
        setTitle("Sistema de Gestión Clínica - Bienvenido, " + rolUsuario);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(850, 650));

        // Personaliza el menú usando nuestra clase de ayuda.
        MenuCustomizer.customize(jMenuBar1);
        
        // --- Lógica para el Layout Responsivo ---
        
        // 1. Creamos un nuevo panel principal que SÍ es responsivo.
        JPanel panelContenedor = new JPanel(new BorderLayout());
        
        // 2. Creamos el panel de bienvenida (puedes poner aquí una imagen, etc.)
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel welcomeLabel = new JLabel("Bienvenido al Sistema de Gestión");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        welcomeLabel.setForeground(new Color(40, 60, 80)); // Texto oscuro
        welcomePanel.add(welcomeLabel, gbc);
        
        JLabel roleLabel = new JLabel("Rol de Usuario: " + this.rolUsuario);
        roleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        roleLabel.setForeground(new Color(100, 120, 140)); // Texto gris
        welcomePanel.add(roleLabel, gbc);

        // 3. Añadimos el panel de bienvenida al centro del contenedor.
        panelContenedor.add(welcomePanel, BorderLayout.CENTER);
        
        // 4. Creamos un panel inferior para el botón. FlowLayout(FlowLayout.RIGHT)
        // empujará cualquier cosa que contenga hacia la derecha.
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10)); // Alineado a la derecha
        
        // 5. ¡IMPORTANTE! Tomamos el botón 'cmdSalir' que NetBeans ya creó en
        // initComponents() y lo AÑADIMOS a nuestro nuevo panel inferior.
        // No necesitamos crearlo de nuevo.
        personalizarBotonSalir(); // Le damos un estilo más bonito
        bottomPanel.add(cmdSalir);
        
        // 6. Añadimos el panel del botón al sur (abajo) del contenedor.
        panelContenedor.add(bottomPanel, BorderLayout.SOUTH);
        
        // 7. Finalmente, reemplazamos el panel por defecto del JFrame con el nuestro.
        this.setContentPane(panelContenedor);
    }
    
    /**
     * Da estilo al botón salir para que combine con el nuevo diseño.
     */
    private void personalizarBotonSalir() {
        cmdSalir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cmdSalir.setBackground(new Color(220, 53, 69));
        cmdSalir.setForeground(Color.WHITE);
        cmdSalir.setFocusPainted(false);
        cmdSalir.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
    }

    private void configurarSegunRol() {
        switch (rolUsuario) {
            case "Medico":
                menuMedico.setVisible(false);
                menuConsultorio.setVisible(false);
                break;
            case "Recepcionista":
                menuMedico.setVisible(false);
                menuMedicamento.setVisible(false);
                itemGestionarHistorial.setVisible(false);
                itemAgregarhisto.setVisible(false);
                menuRecetas.setVisible(false);
                break;
            default:
                break;
        }
    }
    
    /**
     * Clase estática para personalizar el menú con una estética de clínica.
     */
    private static class MenuCustomizer {
        // << NUEVA PALETA DE COLORES "CLÍNICA" >>
        private static final Color MENU_BACKGROUND = new Color(245, 248, 251); // Blanco hueso
        private static final Color MENU_FOREGROUND = new Color(50, 70, 90);    // Azul oscuro/gris para texto
        private static final Color ITEM_HOVER_BACKGROUND = new Color(0, 123, 255); // Azul brillante (Bootstrap primary)
        private static final Color ITEM_HOVER_FOREGROUND = Color.WHITE;
        private static final Font MENU_FONT = new Font("Segoe UI", Font.BOLD, 14);
        private static final Font MENU_ITEM_FONT = new Font("Segoe UI", Font.PLAIN, 13);

        public static void customize(JMenuBar menuBar) {
            menuBar.setUI(new BasicMenuBarUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    g.setColor(MENU_BACKGROUND);
                    g.fillRect(0, 0, c.getWidth(), c.getHeight());
                }
            });
            menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 220, 230)));
            
            for (int i = 0; i < menuBar.getMenuCount(); i++) {
                JMenu menu = menuBar.getMenu(i);
                styleMenu(menu);
                for (Component comp : menu.getMenuComponents()) {
                    if (comp instanceof JMenuItem) {
                        styleMenuItem((JMenuItem) comp);
                    }
                }
            }
        }
        
        private static void styleMenu(JMenu menu) {
            menu.setFont(MENU_FONT);
            menu.setForeground(MENU_FOREGROUND);
            menu.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            menu.getPopupMenu().setBackground(Color.WHITE);
            menu.getPopupMenu().setBorder(BorderFactory.createLineBorder(new Color(200, 210, 220), 1));
        }
        
        private static void styleMenuItem(JMenuItem item) {
            item.setFont(MENU_ITEM_FONT);
            item.setForeground(MENU_FOREGROUND);
            item.setBackground(Color.WHITE); // Fondo normal blanco
            item.setOpaque(true);
            item.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            
            item.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    item.setBackground(ITEM_HOVER_BACKGROUND);
                    item.setForeground(ITEM_HOVER_FOREGROUND);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    item.setBackground(Color.WHITE);
                    item.setForeground(MENU_FOREGROUND);
                }
            });
        }
        
        /**
         * Método de ayuda para cargar íconos de forma segura.
         * @param path La ruta del ícono desde la carpeta 'src'.
         * @return Un ImageIcon o null si no se encuentra.
         */
        public static ImageIcon createImageIcon(String path) {
            java.net.URL imgURL = Main.class.getResource(path);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                System.err.println("No se pudo encontrar el archivo: " + path);
                return null;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        cmdSalir = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMedico = new javax.swing.JMenu();
        itemAgregarMedico = new javax.swing.JMenuItem();
        itemModificarMedico = new javax.swing.JMenuItem();
        itemListarMedico = new javax.swing.JMenuItem();
        menuPacientes = new javax.swing.JMenu();
        itemAgregarPaciente = new javax.swing.JMenuItem();
        itemPacientesGestionar = new javax.swing.JMenuItem();
        itemPacientesListar = new javax.swing.JMenuItem();
        menuCitas = new javax.swing.JMenu();
        itemAgregarCitas = new javax.swing.JMenuItem();
        itemCitasEliminar = new javax.swing.JMenuItem();
        itemCitasModificar = new javax.swing.JMenuItem();
        itemCitasListar = new javax.swing.JMenuItem();
        menuHistorial = new javax.swing.JMenu();
        itemAgregarhisto = new javax.swing.JMenuItem();
        itemGestionarHistorial = new javax.swing.JMenuItem();
        itemVisualizarHisto = new javax.swing.JMenuItem();
        menuEspecialidad = new javax.swing.JMenu();
        itemEspecialidades = new javax.swing.JMenuItem();
        menuHorario = new javax.swing.JMenu();
        itemHorario = new javax.swing.JMenuItem();
        itemVisualizar = new javax.swing.JMenuItem();
        menuConsultorio = new javax.swing.JMenu();
        itemConsultorio = new javax.swing.JMenuItem();
        menuMedicamento = new javax.swing.JMenu();
        itemMedicamento = new javax.swing.JMenuItem();
        menuRecetas = new javax.swing.JMenu();
        itemReceta = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem9.setText("jMenuItem9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        cmdSalir.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        cmdSalir.setText("Salir");
        cmdSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSalirActionPerformed(evt);
            }
        });
        jPanel1.add(cmdSalir);
        cmdSalir.setBounds(510, 490, 97, 39);

        menuMedico.setText("Medico");

        itemAgregarMedico.setText("Agregar ");
        itemAgregarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarMedicoActionPerformed(evt);
            }
        });
        menuMedico.add(itemAgregarMedico);

        itemModificarMedico.setText("Modificar/Eliminar");
        itemModificarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemModificarMedicoActionPerformed(evt);
            }
        });
        menuMedico.add(itemModificarMedico);

        itemListarMedico.setText("Listar");
        itemListarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListarMedicoActionPerformed(evt);
            }
        });
        menuMedico.add(itemListarMedico);

        jMenuBar1.add(menuMedico);

        menuPacientes.setText("Pacientes");

        itemAgregarPaciente.setText("Agregar");
        itemAgregarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarPacienteActionPerformed(evt);
            }
        });
        menuPacientes.add(itemAgregarPaciente);

        itemPacientesGestionar.setText("Gestionar");
        itemPacientesGestionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPacientesGestionarActionPerformed(evt);
            }
        });
        menuPacientes.add(itemPacientesGestionar);

        itemPacientesListar.setText("Listar");
        itemPacientesListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPacientesListarActionPerformed(evt);
            }
        });
        menuPacientes.add(itemPacientesListar);

        jMenuBar1.add(menuPacientes);

        menuCitas.setText("Citas");

        itemAgregarCitas.setText("Agregar");
        itemAgregarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarCitasActionPerformed(evt);
            }
        });
        menuCitas.add(itemAgregarCitas);

        itemCitasEliminar.setText("Eliminar");
        itemCitasEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCitasEliminarActionPerformed(evt);
            }
        });
        menuCitas.add(itemCitasEliminar);

        itemCitasModificar.setText("Modificar");
        itemCitasModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCitasModificarActionPerformed(evt);
            }
        });
        menuCitas.add(itemCitasModificar);

        itemCitasListar.setText("Listar");
        itemCitasListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCitasListarActionPerformed(evt);
            }
        });
        menuCitas.add(itemCitasListar);

        jMenuBar1.add(menuCitas);

        menuHistorial.setText("Historial");

        itemAgregarhisto.setText("Agregar Historial");
        itemAgregarhisto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarhistoActionPerformed(evt);
            }
        });
        menuHistorial.add(itemAgregarhisto);

        itemGestionarHistorial.setText("Modificar/Eliminar");
        itemGestionarHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGestionarHistorialActionPerformed(evt);
            }
        });
        menuHistorial.add(itemGestionarHistorial);

        itemVisualizarHisto.setText("Visualizar Historial");
        itemVisualizarHisto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVisualizarHistoActionPerformed(evt);
            }
        });
        menuHistorial.add(itemVisualizarHisto);

        jMenuBar1.add(menuHistorial);

        menuEspecialidad.setText("Especialidades");

        itemEspecialidades.setText("Gestion Especialidades");
        itemEspecialidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEspecialidadesActionPerformed(evt);
            }
        });
        menuEspecialidad.add(itemEspecialidades);

        jMenuBar1.add(menuEspecialidad);

        menuHorario.setText("Horarios");

        itemHorario.setText("Gestionar Horario");
        itemHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHorarioActionPerformed(evt);
            }
        });
        menuHorario.add(itemHorario);

        itemVisualizar.setText("Visualizar");
        itemVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVisualizarActionPerformed(evt);
            }
        });
        menuHorario.add(itemVisualizar);

        jMenuBar1.add(menuHorario);

        menuConsultorio.setText("Consultorio");

        itemConsultorio.setText("Gestionar Consultorio");
        itemConsultorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConsultorioActionPerformed(evt);
            }
        });
        menuConsultorio.add(itemConsultorio);

        jMenuBar1.add(menuConsultorio);

        menuMedicamento.setText("Medicamentos");

        itemMedicamento.setText("Gestionar Medicamentos");
        itemMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMedicamentoActionPerformed(evt);
            }
        });
        menuMedicamento.add(itemMedicamento);

        jMenuBar1.add(menuMedicamento);

        menuRecetas.setText("Recetas");

        itemReceta.setText("Gestionar Receta");
        itemReceta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRecetaActionPerformed(evt);
            }
        });
        menuRecetas.add(itemReceta);

        jMenuBar1.add(menuRecetas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cmdSalirActionPerformed

    private void itemVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVisualizarActionPerformed
        FrmVisualizarHorarios frm = new FrmVisualizarHorarios(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemVisualizarActionPerformed

    private void itemHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemHorarioActionPerformed
        FrmHorarios frm = new FrmHorarios(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemHorarioActionPerformed

    private void itemEspecialidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEspecialidadesActionPerformed
        FrmEspecialidades frm = new FrmEspecialidades(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemEspecialidadesActionPerformed

    private void itemGestionarHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGestionarHistorialActionPerformed
        FrmGestionHistorial frm = new FrmGestionHistorial(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemGestionarHistorialActionPerformed

    private void itemCitasListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCitasListarActionPerformed
        FrmListar frm = new FrmListar(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemCitasListarActionPerformed

    private void itemCitasModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCitasModificarActionPerformed
        FrmModificarCitas frm = new FrmModificarCitas(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemCitasModificarActionPerformed

    private void itemCitasEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCitasEliminarActionPerformed
        FrmEliminarCitas frm = new FrmEliminarCitas(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemCitasEliminarActionPerformed

    private void itemAgregarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarCitasActionPerformed
        FrmAgregarCitas frm = new FrmAgregarCitas(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemAgregarCitasActionPerformed

    private void itemPacientesListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPacientesListarActionPerformed
        FrmListarPacientes frm = new FrmListarPacientes(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemPacientesListarActionPerformed

    private void itemPacientesGestionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPacientesGestionarActionPerformed
        FrmGestionarPacientes frm = new FrmGestionarPacientes(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemPacientesGestionarActionPerformed

    private void itemAgregarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarPacienteActionPerformed
        FrmAgregarPaciente frm = new FrmAgregarPaciente(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemAgregarPacienteActionPerformed

    private void itemListarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListarMedicoActionPerformed
        FrmListarMedicos frm = new FrmListarMedicos(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemListarMedicoActionPerformed

    private void itemModificarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemModificarMedicoActionPerformed
        FrmModificarMedico frm = new FrmModificarMedico(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemModificarMedicoActionPerformed

    private void itemAgregarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarMedicoActionPerformed
        FrmAgregarMedico frm = new FrmAgregarMedico(this,true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemAgregarMedicoActionPerformed

    private void itemAgregarhistoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarhistoActionPerformed
        FrmConsulta frm = new FrmConsulta(this,true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemAgregarhistoActionPerformed

    private void itemConsultorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConsultorioActionPerformed
        FrmConsultorios frm = new FrmConsultorios(this,true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemConsultorioActionPerformed

    private void itemMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMedicamentoActionPerformed
        FrmMedicamentos frm = new FrmMedicamentos(this,true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemMedicamentoActionPerformed

    private void itemRecetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRecetaActionPerformed
        FrmRecetas frm = new FrmRecetas(this,true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemRecetaActionPerformed

    private void itemVisualizarHistoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVisualizarHistoActionPerformed
        FrmVisualisarHistorial frm = new FrmVisualisarHistorial(this,true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemVisualizarHistoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSalir;
    private javax.swing.JMenuItem itemAgregarCitas;
    private javax.swing.JMenuItem itemAgregarMedico;
    private javax.swing.JMenuItem itemAgregarPaciente;
    private javax.swing.JMenuItem itemAgregarhisto;
    private javax.swing.JMenuItem itemCitasEliminar;
    private javax.swing.JMenuItem itemCitasListar;
    private javax.swing.JMenuItem itemCitasModificar;
    private javax.swing.JMenuItem itemConsultorio;
    private javax.swing.JMenuItem itemEspecialidades;
    private javax.swing.JMenuItem itemGestionarHistorial;
    private javax.swing.JMenuItem itemHorario;
    private javax.swing.JMenuItem itemListarMedico;
    private javax.swing.JMenuItem itemMedicamento;
    private javax.swing.JMenuItem itemModificarMedico;
    private javax.swing.JMenuItem itemPacientesGestionar;
    private javax.swing.JMenuItem itemPacientesListar;
    private javax.swing.JMenuItem itemReceta;
    private javax.swing.JMenuItem itemVisualizar;
    private javax.swing.JMenuItem itemVisualizarHisto;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu menuCitas;
    private javax.swing.JMenu menuConsultorio;
    private javax.swing.JMenu menuEspecialidad;
    private javax.swing.JMenu menuHistorial;
    private javax.swing.JMenu menuHorario;
    private javax.swing.JMenu menuMedicamento;
    private javax.swing.JMenu menuMedico;
    private javax.swing.JMenu menuPacientes;
    private javax.swing.JMenu menuRecetas;
    // End of variables declaration//GEN-END:variables
}

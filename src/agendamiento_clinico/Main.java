package agendamiento_clinico;

import agendamiento_clinico.especialidades.*;
import agendamiento_clinico.cita.*;
import agendamiento_clinico.gestione.*;
import agendamiento_clinico.horario.*;
import agendamiento_clinico.medicos.*;
import agendamiento_clinico.pacientes.*;
import agendamiento_clinico.historialClinico.*;

public class Main extends javax.swing.JFrame {
    
    // 1. Añade una variable para guardar el rol del usuario
    private String rolUsuario;

    // 2. Modifica tu constructor actual y crea uno nuevo
    public Main() { // Este constructor ya no se usará, pero es bueno dejarlo
        initComponents();
        this.setLocationRelativeTo(null);
    }

    // ¡ESTE ES EL NUEVO CONSTRUCTOR IMPORTANTE!
    public Main(String rol) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.rolUsuario = rol; // Guardamos el rol
        configurarSegunRol(); // Llamamos al método que habilita/deshabilita cosas
    }

    // 3. Crea el método que contiene toda la lógica de los permisos
    private void configurarSegunRol() {
        // Por defecto, un administrador puede ver todo, así que no hacemos nada para él.
        switch (rolUsuario) {
            case "Medico":
                // Un médico no debería poder gestionar otros médicos ni consultorios.
                menuMedico.setVisible(false); // Oculta el menú "Medico" completo
                menuConsultorio.setVisible(false);
                break;

            case "Recepcionista":
                // Un recepcionista no debería ver  gestionar médicos/medicamentos.
                menuMedico.setVisible(false);      // Oculta el menú "Medico"
                menuMedicamento.setVisible(false); // Oculta solo el item de "Gestionar Medicamentos"
                itemGestionarHistorial.setVisible(false); // Oculta solo el item de "Gestionar Historial"
                itemAgregarhisto.setVisible(false);// Oculta solo el item de "Agregar Historial"
                menuRecetas.setVisible(false); // Oculta solo el item de "Menu Recetas"
                break;
            
            default: // Si el rol es Administrador o cualquier otro, no se oculta nada.
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        cmdSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
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
        itemGestionarHistorial = new javax.swing.JMenuItem();
        itemAgregarhisto = new javax.swing.JMenuItem();
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
        cmdSalir.setBounds(500, 490, 97, 39);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icono/iconos/Screenshot 2025-10-28 190651.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 610, 540);

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

        itemGestionarHistorial.setText("Modificar/Eliminar");
        itemGestionarHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGestionarHistorialActionPerformed(evt);
            }
        });
        menuHistorial.add(itemGestionarHistorial);

        itemAgregarhisto.setText("Agregar Historial");
        itemAgregarhisto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarhistoActionPerformed(evt);
            }
        });
        menuHistorial.add(itemAgregarhisto);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
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
    private javax.swing.JLabel jLabel2;
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

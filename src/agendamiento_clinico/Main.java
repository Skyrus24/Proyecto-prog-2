package agendamiento_clinico;
import agendamiento_clinico.cita.FrmListar;
import agendamiento_clinico.cita.FrmEliminarCitas;
import agendamiento_clinico.cita.FrmModificarCitas;
import agendamiento_clinico.cita.FrmAgregarCitas;
import agendamiento_clinico.gestione.FrmRecetas;
import agendamiento_clinico.gestione.FrmMedicamentos;
import agendamiento_clinico.gestione.FrmConsultorios;
import agendamiento_clinico.horario.FrmVisualizarHorarios;
import agendamiento_clinico.horario.FrmHorarios;
import agendamiento_clinico.medicos.FrmModificarMedico;
import agendamiento_clinico.pacientes.*;
import agendamiento_clinico.historialClinico.*;
import agendamiento_clinico.medicos.FrmAgregarMedico;
import agendamiento_clinico.medicos.FrmListarMedicos;

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
                itemMedico.setVisible(false); // Oculta el menú "Medico" completo
                jMenu1.setVisible(false);     // Oculta el menú "Gestiones" completo
                break;

            case "Recepcionista":
                // Un recepcionista no debería ver historiales clínicos ni gestionar médicos/medicamentos.
                itemHistorial.setVisible(false);   // Oculta el menú "Historial"
                itemMedico.setVisible(false);      // Oculta el menú "Medico"
                itemMedicamento.setVisible(false); // Oculta solo el item de "Gestionar Medicamentos"
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
        cmdSalir = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        itemMedico = new javax.swing.JMenu();
        itemAgregarMedico = new javax.swing.JMenuItem();
        itemModificarMedico = new javax.swing.JMenuItem();
        itemListarMedico = new javax.swing.JMenuItem();
        itemPacientes = new javax.swing.JMenu();
        itemAgregarPaciente = new javax.swing.JMenuItem();
        itemPacientesGestionar = new javax.swing.JMenuItem();
        itemPacientesListar = new javax.swing.JMenuItem();
        itemCitas = new javax.swing.JMenu();
        itemAgregarCitas = new javax.swing.JMenuItem();
        itemCitasEliminar = new javax.swing.JMenuItem();
        itemCitasModificar = new javax.swing.JMenuItem();
        itemCitasListar = new javax.swing.JMenuItem();
        itemHistorial = new javax.swing.JMenu();
        itemGestionarHistorial = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        itemConsultorio = new javax.swing.JMenuItem();
        itemMedicamento = new javax.swing.JMenuItem();
        itemReceta = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemEspecialidades = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        itemHorario = new javax.swing.JMenuItem();
        itemVisualizar = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem9.setText("jMenuItem9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmdSalir.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        cmdSalir.setText("Salir");
        cmdSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSalirActionPerformed(evt);
            }
        });

        itemMedico.setText("Medico");

        itemAgregarMedico.setText("Agregar ");
        itemAgregarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarMedicoActionPerformed(evt);
            }
        });
        itemMedico.add(itemAgregarMedico);

        itemModificarMedico.setText("Modificar/Eliminar");
        itemModificarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemModificarMedicoActionPerformed(evt);
            }
        });
        itemMedico.add(itemModificarMedico);

        itemListarMedico.setText("Listar");
        itemListarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListarMedicoActionPerformed(evt);
            }
        });
        itemMedico.add(itemListarMedico);

        jMenuBar1.add(itemMedico);

        itemPacientes.setText("Pacientes");

        itemAgregarPaciente.setText("Agregar");
        itemAgregarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarPacienteActionPerformed(evt);
            }
        });
        itemPacientes.add(itemAgregarPaciente);

        itemPacientesGestionar.setText("Gestionar");
        itemPacientesGestionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPacientesGestionarActionPerformed(evt);
            }
        });
        itemPacientes.add(itemPacientesGestionar);

        itemPacientesListar.setText("Listar");
        itemPacientesListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPacientesListarActionPerformed(evt);
            }
        });
        itemPacientes.add(itemPacientesListar);

        jMenuBar1.add(itemPacientes);

        itemCitas.setText("Citas");

        itemAgregarCitas.setText("Agregar");
        itemAgregarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarCitasActionPerformed(evt);
            }
        });
        itemCitas.add(itemAgregarCitas);

        itemCitasEliminar.setText("Eliminar");
        itemCitasEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCitasEliminarActionPerformed(evt);
            }
        });
        itemCitas.add(itemCitasEliminar);

        itemCitasModificar.setText("Modificar");
        itemCitasModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCitasModificarActionPerformed(evt);
            }
        });
        itemCitas.add(itemCitasModificar);

        itemCitasListar.setText("Listar");
        itemCitasListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCitasListarActionPerformed(evt);
            }
        });
        itemCitas.add(itemCitasListar);

        jMenuBar1.add(itemCitas);

        itemHistorial.setText("Historial");

        itemGestionarHistorial.setText("Gestionar Historiales");
        itemGestionarHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGestionarHistorialActionPerformed(evt);
            }
        });
        itemHistorial.add(itemGestionarHistorial);

        jMenuBar1.add(itemHistorial);

        jMenu1.setText("Gestiones");

        itemConsultorio.setText("Gestionar Consultorio");
        itemConsultorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConsultorioActionPerformed(evt);
            }
        });
        jMenu1.add(itemConsultorio);

        itemMedicamento.setText("Gestionar Medicamentos");
        itemMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMedicamentoActionPerformed(evt);
            }
        });
        jMenu1.add(itemMedicamento);

        itemReceta.setText("Gestionar Receta");
        itemReceta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRecetaActionPerformed(evt);
            }
        });
        jMenu1.add(itemReceta);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Especialidades");

        itemEspecialidades.setText("Gestion Especialidades");
        itemEspecialidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEspecialidadesActionPerformed(evt);
            }
        });
        jMenu2.add(itemEspecialidades);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Horarios");

        itemHorario.setText("Gestionar Horario");
        itemHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHorarioActionPerformed(evt);
            }
        });
        jMenu3.add(itemHorario);

        itemVisualizar.setText("Visualizar");
        itemVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVisualizarActionPerformed(evt);
            }
        });
        jMenu3.add(itemVisualizar);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(459, Short.MAX_VALUE)
                .addComponent(cmdSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(255, Short.MAX_VALUE)
                .addComponent(cmdSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemAgregarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarPacienteActionPerformed
        FrmAgregarPaciente frm = new FrmAgregarPaciente(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemAgregarPacienteActionPerformed

    private void itemCitasEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCitasEliminarActionPerformed
        FrmEliminarCitas frm = new FrmEliminarCitas(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemCitasEliminarActionPerformed

    private void itemModificarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemModificarMedicoActionPerformed
        FrmModificarMedico frm = new FrmModificarMedico(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemModificarMedicoActionPerformed

    private void itemConsultorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConsultorioActionPerformed
        FrmConsultorios frm = new FrmConsultorios(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemConsultorioActionPerformed

    private void itemAgregarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarMedicoActionPerformed
        FrmAgregarMedico frm = new FrmAgregarMedico(this,true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemAgregarMedicoActionPerformed

    private void itemPacientesListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPacientesListarActionPerformed
        FrmListarPacientes frm = new FrmListarPacientes(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemPacientesListarActionPerformed

    private void itemAgregarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarCitasActionPerformed
        FrmAgregarCitas frm = new FrmAgregarCitas(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemAgregarCitasActionPerformed

    private void itemCitasModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCitasModificarActionPerformed
        FrmModificarCitas frm = new FrmModificarCitas(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemCitasModificarActionPerformed

    private void itemCitasListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCitasListarActionPerformed
        FrmListar frm = new FrmListar(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemCitasListarActionPerformed

    private void itemMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMedicamentoActionPerformed
        FrmMedicamentos frm = new FrmMedicamentos(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemMedicamentoActionPerformed

    private void itemRecetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRecetaActionPerformed
        FrmRecetas frm = new FrmRecetas(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemRecetaActionPerformed

    private void itemGestionarHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGestionarHistorialActionPerformed
        FrmGestionarHistorial frm = new FrmGestionarHistorial(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemGestionarHistorialActionPerformed

    private void itemEspecialidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEspecialidadesActionPerformed
        FrmEspecialidades frm = new FrmEspecialidades(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemEspecialidadesActionPerformed

    private void itemHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemHorarioActionPerformed
        FrmHorarios frm = new FrmHorarios(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemHorarioActionPerformed

    private void itemVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVisualizarActionPerformed
        FrmVisualizarHorarios frm = new FrmVisualizarHorarios(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemVisualizarActionPerformed

    private void itemPacientesGestionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPacientesGestionarActionPerformed
        FrmGestionarPacientes frm = new FrmGestionarPacientes(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemPacientesGestionarActionPerformed

    private void cmdSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cmdSalirActionPerformed

    private void itemListarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListarMedicoActionPerformed
        FrmListarMedicos frm = new FrmListarMedicos(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_itemListarMedicoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSalir;
    private javax.swing.JMenuItem itemAgregarCitas;
    private javax.swing.JMenuItem itemAgregarMedico;
    private javax.swing.JMenuItem itemAgregarPaciente;
    private javax.swing.JMenu itemCitas;
    private javax.swing.JMenuItem itemCitasEliminar;
    private javax.swing.JMenuItem itemCitasListar;
    private javax.swing.JMenuItem itemCitasModificar;
    private javax.swing.JMenuItem itemConsultorio;
    private javax.swing.JMenuItem itemEspecialidades;
    private javax.swing.JMenuItem itemGestionarHistorial;
    private javax.swing.JMenu itemHistorial;
    private javax.swing.JMenuItem itemHorario;
    private javax.swing.JMenuItem itemListarMedico;
    private javax.swing.JMenuItem itemMedicamento;
    private javax.swing.JMenu itemMedico;
    private javax.swing.JMenuItem itemModificarMedico;
    private javax.swing.JMenu itemPacientes;
    private javax.swing.JMenuItem itemPacientesGestionar;
    private javax.swing.JMenuItem itemPacientesListar;
    private javax.swing.JMenuItem itemReceta;
    private javax.swing.JMenuItem itemVisualizar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}

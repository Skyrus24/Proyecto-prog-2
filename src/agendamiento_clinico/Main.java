package agendamiento_clinico;
import agendamiento_clinico.Gestiones.*;
import agendamiento_clinico.pacientes.*;
import agendamiento_clinico.Citas.*;
import agendamiento_clinico.Horarios.*;
import agendamiento_clinico.Medico.*;
import agendamiento_clinico.historialClinico.*;

public class Main extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());

    public Main() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
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

        jMenuItem1.setText("jMenuItem1");

        jMenuItem9.setText("jMenuItem9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
        FrmAgregarPaciente frm = new FrmAgregarPaciente(this,true);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemAgregarCitas;
    private javax.swing.JMenuItem itemAgregarMedico;
    private javax.swing.JMenuItem itemAgregarPaciente;
    private javax.swing.JMenu itemCitas;
    private javax.swing.JMenuItem itemCitasEliminar;
    private javax.swing.JMenuItem itemCitasListar;
    private javax.swing.JMenuItem itemCitasModificar;
    private javax.swing.JMenuItem itemConsultorio;
    private javax.swing.JMenuItem itemGestionarHistorial;
    private javax.swing.JMenu itemHistorial;
    private javax.swing.JMenuItem itemListarMedico;
    private javax.swing.JMenuItem itemMedicamento;
    private javax.swing.JMenu itemMedico;
    private javax.swing.JMenuItem itemModificarMedico;
    private javax.swing.JMenu itemPacientes;
    private javax.swing.JMenuItem itemPacientesGestionar;
    private javax.swing.JMenuItem itemPacientesListar;
    private javax.swing.JMenuItem itemReceta;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}

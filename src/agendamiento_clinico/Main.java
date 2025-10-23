package agendamiento_clinico;

import agendamiento_clinico.pacientes.FrmAgregarPaciente;

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
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        itemPacientes = new javax.swing.JMenu();
        itemAgregarEspeci = new javax.swing.JMenuItem();
        itemPacientesModificar = new javax.swing.JMenuItem();
        itemPacientesEliminar = new javax.swing.JMenuItem();
        itemPacientesListar = new javax.swing.JMenuItem();
        itemCitas = new javax.swing.JMenu();
        itemAgregarCitas = new javax.swing.JMenuItem();
        itemCitasEliminar = new javax.swing.JMenuItem();
        itemCitasModificar = new javax.swing.JMenuItem();
        itemCitasListar = new javax.swing.JMenuItem();
        itemHistorial = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        itemConsultorio = new javax.swing.JMenuItem();
        itemMedicamento = new javax.swing.JMenuItem();
        itemReceta = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem9.setText("jMenuItem9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        itemMedico.setText("Medico");

        jMenuItem2.setText("Agregar Medico");
        itemMedico.add(jMenuItem2);

        jMenuItem3.setText("Modificar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        itemMedico.add(jMenuItem3);

        jMenuItem4.setText("Eliminar");
        itemMedico.add(jMenuItem4);

        jMenuItem5.setText("Listar");
        itemMedico.add(jMenuItem5);

        jMenuBar1.add(itemMedico);

        itemPacientes.setText("Pacientes");

        itemAgregarEspeci.setText("Agregar");
        itemAgregarEspeci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarEspeciActionPerformed(evt);
            }
        });
        itemPacientes.add(itemAgregarEspeci);

        itemPacientesModificar.setText("Modificar");
        itemPacientes.add(itemPacientesModificar);

        itemPacientesEliminar.setText("Eliminar");
        itemPacientes.add(itemPacientesEliminar);

        itemPacientesListar.setText("Listar");
        itemPacientes.add(itemPacientesListar);

        jMenuBar1.add(itemPacientes);

        itemCitas.setText("Citas");

        itemAgregarCitas.setText("Agregar");
        itemCitas.add(itemAgregarCitas);

        itemCitasEliminar.setText("Eliminar");
        itemCitasEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCitasEliminarActionPerformed(evt);
            }
        });
        itemCitas.add(itemCitasEliminar);

        itemCitasModificar.setText("Modificar");
        itemCitas.add(itemCitasModificar);

        itemCitasListar.setText("Listar");
        itemCitas.add(itemCitasListar);

        jMenuBar1.add(itemCitas);

        itemHistorial.setText("Historial");
        jMenuBar1.add(itemHistorial);

        jMenu1.setText("Gestiones");

        itemConsultorio.setText("Gestionar Consultorio");
        jMenu1.add(itemConsultorio);

        itemMedicamento.setText("Gestionar Medicamentos");
        jMenu1.add(itemMedicamento);

        itemReceta.setText("Gestionar Receta");
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

    private void itemAgregarEspeciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarEspeciActionPerformed

    }//GEN-LAST:event_itemAgregarEspeciActionPerformed

    private void itemCitasEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCitasEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemCitasEliminarActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
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
    private javax.swing.JMenuItem itemAgregarEspeci;
    private javax.swing.JMenu itemCitas;
    private javax.swing.JMenuItem itemCitasEliminar;
    private javax.swing.JMenuItem itemCitasListar;
    private javax.swing.JMenuItem itemCitasModificar;
    private javax.swing.JMenuItem itemConsultorio;
    private javax.swing.JMenu itemHistorial;
    private javax.swing.JMenuItem itemMedicamento;
    private javax.swing.JMenu itemMedico;
    private javax.swing.JMenu itemPacientes;
    private javax.swing.JMenuItem itemPacientesEliminar;
    private javax.swing.JMenuItem itemPacientesListar;
    private javax.swing.JMenuItem itemPacientesModificar;
    private javax.swing.JMenuItem itemReceta;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}

package agendamiento_clinico;

import agendamiento_clinico.FrmHistorialClinico;
import agendamiento_clinico.FrmMedicos;

public class Main extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());
    
    // Variable para guardar el rol del usuario que inició sesión
    private String rolUsuario;

    public Main(String rol) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.rolUsuario = rol;
        this.setTitle("Clínica - Usuario: " + this.rolUsuario); // Muestra el rol en el título

        configurarPermisos();
    }
    
    /**
     * Este método habilita o deshabilita las opciones del menú
     * basándose en el rol del usuario que ha iniciado sesión.
     */
    private void configurarPermisos() {
        itemMedico.setEnabled(false);
        itemHistorial.setEnabled(false); 
        
        switch (rolUsuario) {
            case "Administrador":
                // El administrador tiene acceso a todo
                itemMedico.setEnabled(true);
                itemHistorial.setEnabled(true);
                break;
            case "Recepcionista":
                // El recepcionista puede gestionar médicos pero no ver historiales
                itemMedico.setEnabled(true);
                break;
            case "Medico":
                // El médico puede ver historiales clínicos pero no gestionar otros médicos
                itemHistorial.setEnabled(true);
                break;
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        vacio = new javax.swing.JMenu();
        itemMedico = new javax.swing.JMenuItem();
        itemHistorial = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        vacio.setText("Agendar");

        itemMedico.setText("Medico");
        itemMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMedicoActionPerformed(evt);
            }
        });
        vacio.add(itemMedico);

        itemHistorial.setText("Historial Clinico");
        itemHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHistorialActionPerformed(evt);
            }
        });
        vacio.add(itemHistorial);

        jMenuBar1.add(vacio);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

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

    private void itemMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMedicoActionPerformed
        FrmMedicos medico = new FrmMedicos(this, false);
        medico.setVisible(true);
    }//GEN-LAST:event_itemMedicoActionPerformed

    private void itemHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemHistorialActionPerformed
        FrmHistorialClinico historial = new FrmHistorialClinico(this, false);
        historial.setVisible(true);
    }//GEN-LAST:event_itemHistorialActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FrmLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemHistorial;
    private javax.swing.JMenuItem itemMedico;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu vacio;
    // End of variables declaration//GEN-END:variables
}

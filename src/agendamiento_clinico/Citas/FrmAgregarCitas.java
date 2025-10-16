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
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmAgregarCitas.class.getName());
    public FrmAgregarCitas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarFiltroPacientes();
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboPacientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboPacientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboPacientesKeyReleased
        String texto = cboPacientes.getEditor().getItem().toString().trim();
        inicializarFiltroPacientes();
        
    }//GEN-LAST:event_cboPacientesKeyReleased
    
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
    private javax.swing.JComboBox<String> cboPacientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}

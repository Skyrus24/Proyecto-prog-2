package agendamiento_clinico.Horarios;

import agendamiento_clinico.BaseDatos;
import agendamiento_clinico.Grilla;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class FrmVisualizarHorarios extends javax.swing.JDialog {
    private BaseDatos bd = new BaseDatos();
    private Grilla grilla = new Grilla();

    public FrmVisualizarHorarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        cargarTodosLosHorarios();
    }
    
    private void cargarTodosLosHorarios() {
        DefaultTableModel modelo = (DefaultTableModel) grdHorarios.getModel();
        
        String sql = "SELECT h.id_horario, CONCAT(m.nombre, ' ', m.apellidos) AS medico, " +
                     "h.dia_semana, h.hora_inicio, h.hora_fin, h.fecha_inicio_validez, h.fecha_fin_validez " +
                     "FROM horarios h JOIN medicos m ON h.id_medico = m.id_medico " +
                     "ORDER BY medico, h.dia_semana";

        try (ResultSet rs = bd.consultarRegistros(sql)) {
            if (rs == null) return;
            
            LocalDate hoy = LocalDate.now();
            DateTimeFormatter mesFormatter = DateTimeFormatter.ofPattern("MM");

            while (rs.next()) {
                Date sqlFechaInicio = rs.getDate("fecha_inicio_validez");
                Date sqlFechaFin = rs.getDate("fecha_fin_validez");
                
                LocalDate fechaInicio = null;
                LocalDate fechaFin = null;
                
                if (sqlFechaInicio != null) {
                    fechaInicio = sqlFechaInicio.toLocalDate();
                }
                if (sqlFechaFin != null) {
                    fechaFin = sqlFechaFin.toLocalDate();
                }
                
                String estado = "Indefinido";
                String mesInicio = "N/A";
                
                if (fechaInicio != null && fechaFin != null) {
                    if ((hoy.isAfter(fechaInicio) || hoy.isEqual(fechaInicio)) && (hoy.isBefore(fechaFin) || hoy.isEqual(fechaFin))) {
                        estado = "Activo";
                    } else {
                        estado = "Inactivo";
                    }
                    mesInicio = fechaInicio.format(mesFormatter);
                }
                
                String diaTexto;
                switch(rs.getInt("dia_semana")){
                    case 1: diaTexto = "Lunes"; break;
                    case 2: diaTexto = "Martes"; break;
                    case 3: diaTexto = "Miércoles"; break;
                    case 4: diaTexto = "Jueves"; break;
                    case 5: diaTexto = "Viernes"; break;
                    case 6: diaTexto = "Sábado"; break;
                    case 7: diaTexto = "Domingo"; break;
                    default: diaTexto = "N/A";
                }
                
                Object[] fila = {
                    rs.getInt("id_horario"),
                    rs.getString("medico"),
                    diaTexto,
                    rs.getString("hora_inicio"),
                    rs.getString("hora_fin"),
                    fechaInicio,
                    fechaFin,
                    estado,
                    mesInicio
                };
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los horarios: " + e.getMessage());
        }
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboCriterio = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        cmdCerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdHorarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Filtro de Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel1.setText("Buscar:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(290, 40, 50, 30);

        cboCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre del Médico", "Mes de Inicio (MM)", "Estado (Activo/Inactivo)" }));
        jPanel1.add(cboCriterio);
        cboCriterio.setBounds(110, 40, 160, 26);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setText("Filtrar por:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 40, 80, 30);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar);
        txtBuscar.setBounds(350, 40, 370, 26);

        cmdCerrar.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        cmdCerrar.setText("Cerrar");
        cmdCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCerrarActionPerformed(evt);
            }
        });

        grdHorarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Médico", "Día", "Hora Inicio", "Hora Fin", "Válido Desde", "Válido Hasta", "Estado", "Mes Inicio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(grdHorarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdCerrarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        int criterioIndex = cboCriterio.getSelectedIndex();
        int columnaParaFiltrar;
        
        switch(criterioIndex) {
            case 0:
                columnaParaFiltrar = 1; 
                break;
            case 1:
                columnaParaFiltrar = 8;
                break;
            case 2:
                columnaParaFiltrar = 7;
                break;
            default:
                columnaParaFiltrar = 1;
        }
        
        grilla.filtrarGrilla(grdHorarios, this.txtBuscar.getText(), columnaParaFiltrar);
    }//GEN-LAST:event_txtBuscarKeyReleased

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmVisualizarHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVisualizarHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVisualizarHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVisualizarHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmVisualizarHorarios dialog = new FrmVisualizarHorarios(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cboCriterio;
    private javax.swing.JButton cmdCerrar;
    private javax.swing.JTable grdHorarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}

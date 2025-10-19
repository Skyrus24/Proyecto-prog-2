package agendamiento_clinico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class BaseDatos {
    private String host = "localhost";
    private String baseDatos = "clinica";
    private String usuBD = "root";
    private String clave = "1234";
    private Connection conexion = null;


    private Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                String url = "jdbc:mysql://" + this.host + "/" + this.baseDatos + "?characterEncoding=utf8";
                conexion = DriverManager.getConnection(url, this.usuBD, this.clave);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
                throw e;
            }
        }
        return conexion;
    }

    // --- MÉTODOS DE ESCRITURA CON COMMIT MANUAL ---

    public boolean insertarRegistro(String tabla, String campos, String valores) {
        String sql = "INSERT INTO " + tabla + " (" + campos + ") VALUES (" + valores + ")";
        return ejecutarSentenciaDeEscritura(sql, "insertar");
    }

    public boolean actualizarRegistro(String tabla, String campos, String criterio) {
        String sql = "UPDATE " + tabla + " SET " + campos + " WHERE " + criterio;
        return ejecutarSentenciaDeEscritura(sql, "actualizar");
    }

    public boolean borrarRegistro(String tabla, String condicion) {
        // La confirmación se hace en FrmHorarios, aquí solo borramos
        String sql = "DELETE FROM " + tabla + " WHERE " + condicion;
        return ejecutarSentenciaDeEscritura(sql, "borrar");
    }

    // MÉTODO CENTRALIZADO PARA INSERTAR, ACTUALIZAR Y BORRAR
    private boolean ejecutarSentenciaDeEscritura(String sql, String operacion) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConexion();
            conn.setAutoCommit(false); // 1. Desactivamos el auto-guardado para controlar nosotros
            stmt = conn.createStatement();
            
            stmt.executeUpdate(sql);
            
            conn.commit(); // 2. SI LLEGA HASTA AQUÍ, CONFIRMAMOS Y GUARDAMOS PERMANENTEMENTE
            return true;
            
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(); // 3. Si hubo un error, DESHACEMOS todo
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error crítico al intentar deshacer la operación: " + ex.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Ocurrió un error al " + operacion + ":\n" + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true); // 4. Dejamos la conexión en su estado normal
                }
            } catch (SQLException ex) {
                // Error al cerrar recursos
            }
        }
    }

    // --- MÉTODOS DE LECTURA (NO NECESITAN TRANSACCIONES) ---
    
    public ResultSet consultarRegistros(String sql) {
        try {
            Statement s = getConexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return s.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error en la consulta: " + e.getMessage(), "Atención", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }

    public void cargarCombo(JComboBox combo, String campos, String tabla) {
        String sql = "SELECT " + campos + " FROM " + tabla;
        try (Statement sentencia = getConexion().createStatement();
             ResultSet rsC = sentencia.executeQuery(sql)) {

            combo.removeAllItems();
            ArrayList<DatosCombo> camposCombo = new ArrayList<>();
            while (rsC.next()) {
                camposCombo.add(new DatosCombo(rsC.getInt(1), rsC.getString(2)));
            }
            for (DatosCombo nombre : camposCombo) {
                combo.addItem(nombre);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llenar combo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean hayConexion(){
        try {
            return getConexion() != null && !getConexion().isClosed();
        } catch (SQLException e){
            return false;
        }
    }
    
}
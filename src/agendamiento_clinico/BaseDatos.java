package agendamiento_clinico;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class BaseDatos {
    private String host = "localhost";
    private String baseDatos="clinica";
    private String usuBD = "root";
    private String clave = "1234";
    private Connection conexion = null;
    private static Statement sentencia;
    
    public BaseDatos(){
        this.hayConexion();
    }
    
    public boolean hayConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(
                    "jdbc:mysql://" + this.host + ":3306/" + this.baseDatos + "?characterEncoding=utf8",
                    this.usuBD,
                    this.clave
                );
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
            return false;
        }
    }

    public boolean borrarRegistro(String tabla, String condicion){
        try {
            Statement s = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            JOptionPane optionPane=new JOptionPane();
            Object[] opciones={"Si","No"};

            int ret=optionPane.showOptionDialog(null,"Esta seguro de ELIMINAR el REGISTRO? ","Pregunta",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
            if(ret==JOptionPane.YES_OPTION){
                s.executeUpdate("delete from "+tabla+" where "+condicion);
                s.close();
            }else{
                s.close();
                return false;
            }
        } catch (SQLException e) {
            if (e.getErrorCode()==1451){
                JOptionPane.showMessageDialog(null, "El registro esta relacionado con otros registros\nno podrá borrarlo." ,
                        "Atencion", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Ocurrio Un error" + e.getMessage(), "Atencion",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return false;
        }
        return true;
    }

    public boolean borrarRegistroSinPreguntar(String tabla, String condicion){
        try {
            Statement s = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            s.executeUpdate("delete from "+tabla+" where "+condicion);
            s.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha eliminado el registro seleccionado\nPuede estar usándose en otra tabla", "Atencion",
                        JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean insertarRegistro(String tabla, String campos, String valores){
        try {
            Statement s = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            s.executeUpdate("insert into "+tabla+" ("+campos+") values ("+valores+")");
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio Un error al insertar \n"+ e.getMessage() , "Atencion",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean insertarRegistro(String tabla, String valores){
        try {
            Statement s = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            s.executeUpdate("insert into "+tabla+" values ("+valores+")");
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio Un error al insertar \n"+ e.getMessage() , "Atencion",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    
    public boolean actualizarRegistro(String tabla, String campos, String criterio){
        try {
            Statement s = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            s.executeUpdate("update "+tabla+" set "+campos+" where " +criterio);
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio Un error\n"+e.getMessage() , "Atencion",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    
    public ResultSet consultarRegistros(String sql) {
        ResultSet rs = null;
        try {
            hayConexion(); // Garantizar que la conexión esté activa
            Statement s = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio Un error"+ e.getMessage() , "Atencion",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }
    
    public void cargarCombo(JComboBox combo, String campos, String tabla){
         ResultSet rsC;
         try{
             hayConexion();
             sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             rsC = sentencia.executeQuery("select " + campos + " from " + tabla);
             ArrayList<DatosCombo> camposCombo;
             camposCombo = new ArrayList();
             while (rsC.next()) {
                 camposCombo.add(new DatosCombo(rsC.getInt(1), rsC.getString(2)));
             }
             for (DatosCombo nombre: camposCombo){
                 combo.addItem(nombre);
             }
         }catch(Exception e) {
             JOptionPane.showMessageDialog(null, "Error al llenar combo\n" + e.getMessage()  , "Llenar Combo - "  + combo.getName(), JOptionPane.ERROR_MESSAGE);
         }
     }
    
    public void seleccionarItemCombo(JComboBox<Object> combo, int idSeleccionar) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i) instanceof DatosCombo) {
                DatosCombo item = (DatosCombo) combo.getItemAt(i);
                if (item.getCodigo() == idSeleccionar) {
                    combo.setSelectedIndex(i);
                    return;
                }
            }
        }
    }
    
    public void cierraConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
    
    public Connection miConexion() {
        try {
            hayConexion();
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return conexion;
    }
}
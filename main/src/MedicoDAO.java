import ola.BaseDatos;
import clase.Medico;
import clase.Especialidad;

public class MedicoDAO {
    private BaseDatos bd = new BaseDatos();

    public boolean insertar(Medico medico) {
        String campos = "id_medico, nombre, apellidos, id_especialidad, email, telefono, numero_licencia";
        String valores = String.format(
            "%d, '%s', '%s', %d, '%s', '%s', '%s'",
            medico.getId_medico(),
            medico.getNombre(),
            medico.getApellidos(),
            medico.getEspecialidad().getId_especialidad(),
            medico.getEmail(),
            medico.getTelefono(),
            medico.getNumero_licencia()
        );
        return bd.insertarRegistro("medicos", campos, valores);
    }
}

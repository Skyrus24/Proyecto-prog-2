package clase;

import java.util.ArrayList;
import java.util.List;
public class Medico {
    private int id_medico;
    private String nombre;
    private String apellidos;
    private Especialidad especialidad;
    private String email;
    private String telefono;
    private String numero_licencia;
    private List<Horario> horarios; 

    public Medico(int id_medico, String nombre, String apellidos, Especialidad especialidad, String email, String telefono, String numero_licencia) {
        this.id_medico = id_medico;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.email = email;
        this.telefono = telefono;
        this.numero_licencia = numero_licencia;
        this.horarios = new ArrayList<>(); // Inicializar la lista
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumero_licencia() {
        return numero_licencia;
    }

    public void setNumero_licencia(String numero_licencia) {
        this.numero_licencia = numero_licencia;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
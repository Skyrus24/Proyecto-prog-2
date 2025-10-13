package clases;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Paciente {
    private int id_paciente;
    private String nombre;
    private String apellidos;
    private LocalDate fecha_nacimiento;
    private Genero genero;
    private String numero_documento;
    private String tipo_documento;
    private String direccion;
    private String telefono;
    private String email;
    private String informacion_adicional;
    private LocalDateTime fecha_registro;

    public Paciente(int id_paciente, String nombre, String apellidos, LocalDate fecha_nacimiento, Genero genero, String numero_documento, String tipo_documento, String direccion, String telefono, String email, String informacion_adicional, LocalDateTime fecha_registro) {
        this.id_paciente = id_paciente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.numero_documento = numero_documento;
        this.tipo_documento = tipo_documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.informacion_adicional = informacion_adicional;
        this.fecha_registro = fecha_registro;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
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

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInformacion_adicional() {
        return informacion_adicional;
    }

    public void setInformacion_adicional(String informacion_adicional) {
        this.informacion_adicional = informacion_adicional;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}


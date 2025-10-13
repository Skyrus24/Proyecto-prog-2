package clases;

public class Usuario {
    private int id_usuario;
    private String nombre_usuario;
    private String contrasena;
    private Rol rol;
    private Medico medico_asociado;

    public Usuario(int id_usuario, String nombre_usuario, String contrasena, Rol rol, Medico medico_asociado) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.medico_asociado = medico_asociado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Medico getMedico_asociado() {
        return medico_asociado;
    }

    public void setMedico_asociado(Medico medico_asociado) {
        this.medico_asociado = medico_asociado;
    }
}
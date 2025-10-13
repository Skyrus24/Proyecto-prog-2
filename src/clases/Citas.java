package clases;

import clases.Usuario;
import java.time.LocalDateTime;

public class Citas {
    private int id_cita;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fecha_hora_inicio;
    private LocalDateTime fecha_hora_fin;
    private String motivo_consulta;
    private EstadoCita estado_cita;
    private TipoCita tipo_cita;
    private String observaciones;
    private Usuario usuario_creador;
    private LocalDateTime fecha_creacion;

    public Citas(int id_cita, Paciente paciente, Medico medico, LocalDateTime fecha_hora_inicio, LocalDateTime fecha_hora_fin, String motivo_consulta, EstadoCita estado_cita, TipoCita tipo_cita, String observaciones, Usuario usuario_creador, LocalDateTime fecha_creacion) {
        this.id_cita = id_cita;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha_hora_inicio = fecha_hora_inicio;
        this.fecha_hora_fin = fecha_hora_fin;
        this.motivo_consulta = motivo_consulta;
        this.estado_cita = estado_cita;
        this.tipo_cita = tipo_cita;
        this.observaciones = observaciones;
        this.usuario_creador = usuario_creador;
        this.fecha_creacion = fecha_creacion;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getFecha_hora_inicio() {
        return fecha_hora_inicio;
    }

    public void setFecha_hora_inicio(LocalDateTime fecha_hora_inicio) {
        this.fecha_hora_inicio = fecha_hora_inicio;
    }

    public LocalDateTime getFecha_hora_fin() {
        return fecha_hora_fin;
    }

    public void setFecha_hora_fin(LocalDateTime fecha_hora_fin) {
        this.fecha_hora_fin = fecha_hora_fin;
    }

    public String getMotivo_consulta() {
        return motivo_consulta;
    }

    public void setMotivo_consulta(String motivo_consulta) {
        this.motivo_consulta = motivo_consulta;
    }

    public EstadoCita getEstado_cita() {
        return estado_cita;
    }

    public void setEstado_cita(EstadoCita estado_cita) {
        this.estado_cita = estado_cita;
    }

    public TipoCita getTipo_cita() {
        return tipo_cita;
    }

    public void setTipo_cita(TipoCita tipo_cita) {
        this.tipo_cita = tipo_cita;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuario getUsuario_creador() {
        return usuario_creador;
    }

    public void setUsuario_creador(Usuario usuario_creador) {
        this.usuario_creador = usuario_creador;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}

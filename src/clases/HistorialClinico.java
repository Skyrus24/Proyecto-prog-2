package clases;

import java.time.LocalDateTime;

public class HistorialClinico {
    
    private int id_historial;
    private Citas cita; // El historial se genera a partir de una cita
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fecha_registro;
    private String antecedentes;
    private String diagnostico;
    private String tratamiento;
    private String resultados_examenes;
    private String notas_evolucion;

    // Constructor
    public HistorialClinico(int id_historial, Citas cita, Paciente paciente, Medico medico, LocalDateTime fecha_registro, String antecedentes, String diagnostico, String tratamiento, String resultados_examenes, String notas_evolucion) {
        this.id_historial = id_historial;
        this.cita = cita;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha_registro = fecha_registro;
        this.antecedentes = antecedentes;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.resultados_examenes = resultados_examenes;
        this.notas_evolucion = notas_evolucion;
    }

    public int getId_historial() {
        return id_historial;
    }

    public void setId_historial(int id_historial) {
        this.id_historial = id_historial;
    }

    public Citas getCita() {
        return cita;
    }

    public void setCita(Citas cita) {
        this.cita = cita;
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

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getResultados_examenes() {
        return resultados_examenes;
    }

    public void setResultados_examenes(String resultados_examenes) {
        this.resultados_examenes = resultados_examenes;
    }

    public String getNotas_evolucion() {
        return notas_evolucion;
    }

    public void setNotas_evolucion(String notas_evolucion) {
        this.notas_evolucion = notas_evolucion;
    }
}
package clases;

import java.time.LocalDate;
import java.time.LocalTime;

public class Horario {
    
    private int id_horario;
    private Medico medico; // Relación con el médico al que pertenece el horario
    private int dia_semana; // Lunes=1, Martes=2, ..., Domingo=7
    private LocalTime hora_inicio;
    private LocalTime hora_fin;
    private LocalDate fecha_inicio_validez; 
    private LocalDate fecha_fin_validez;    

    // Constructor
    public Horario(int id_horario, Medico medico, int dia_semana, LocalTime hora_inicio, LocalTime hora_fin, LocalDate fecha_inicio_validez, LocalDate fecha_fin_validez) {
        this.id_horario = id_horario;
        this.medico = medico;
        this.dia_semana = dia_semana;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.fecha_inicio_validez = fecha_inicio_validez;
        this.fecha_fin_validez = fecha_fin_validez;
    }

    // --- Getters y Setters ---

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public int getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(int dia_semana) {
        this.dia_semana = dia_semana;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public LocalDate getFecha_inicio_validez() {
        return fecha_inicio_validez;
    }

    public void setFecha_inicio_validez(LocalDate fecha_inicio_validez) {
        this.fecha_inicio_validez = fecha_inicio_validez;
    }

    public LocalDate getFecha_fin_validez() {
        return fecha_fin_validez;
    }

    public void setFecha_fin_validez(LocalDate fecha_fin_validez) {
        this.fecha_fin_validez = fecha_fin_validez;
    }
}
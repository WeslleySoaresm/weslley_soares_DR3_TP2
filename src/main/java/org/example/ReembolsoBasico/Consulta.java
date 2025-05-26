package org.example.ReembolsoBasico;

import java.time.LocalDateTime;

public class Consulta {
    private LocalDateTime dataHora;
    private double valor;
    private Paciente paciente;
    private Medico medico;

    public Consulta(LocalDateTime dataHora, double valor, Paciente paciente, Medico medico) {
        this.dataHora = dataHora;
        this.valor = valor;
        this.paciente = paciente;
        this.medico = medico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public double getValor() {
        return valor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }
}

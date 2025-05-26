package org.example.ReembolsoBasico;

//Interface de historico de consultas.

import java.util.List;

public interface HistoricoConsultas {
    void resgistrarConsulta(Paciente paciente, double valorConsulta);
    List<Double> obterConsultasDoPaciente(Paciente paciente);

}

package org.example.ReembolsoBasico;

public interface PlanoSaude {




    double getPercentualDeCobertura(Paciente paciente);
    double valorReembolsavel(Consulta consulta);

}

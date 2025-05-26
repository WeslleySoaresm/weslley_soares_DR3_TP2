package org.example.ReembolsoBasico;

import java.util.*;

public class HistoricoConsultasFake implements HistoricoConsultas {

    //Chave Paciente e valor Lista de consulta
    private Map<Paciente, List<Double>> consultaPorPaciente = new HashMap<>();




    @Override
    public void resgistrarConsulta(Paciente paciente, double valorConsulta) {
        consultaPorPaciente
                .computeIfAbsent(paciente, p -> new ArrayList<>())
                .add(valorConsulta);
    }

    @Override
    public List<Double> obterConsultasDoPaciente(Paciente paciente){
     return consultaPorPaciente.getOrDefault(paciente, Collections.emptyList());
 }


}


/*
 * Um Fake é um dublê de teste com uma implementação real simples,
 * ideal para testes automatizados sem depender de banco de dados.
 *
 * Neste exemplo, a classe HistoricoConsultasFake permite registrar e consultar
 * valores de consultas diretamente em memória, usando um Map para simular o armazenamento.
 *
 * Isso nos ajuda a testar funcionalidades que dependem de histórico,
 * sem a complexidade e lentidão de um banco de dados real.
 */

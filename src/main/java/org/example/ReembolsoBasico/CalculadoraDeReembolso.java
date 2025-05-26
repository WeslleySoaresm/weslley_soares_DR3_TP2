package org.example.ReembolsoBasico;

public class CalculadoraDeReembolso {
   private PlanoSaude plano;
   private Auditoria auditoria;
   private AutorizadorReembolso autorizador;
    public CalculadoraDeReembolso(PlanoSaude plano, Auditoria auditoria, AutorizadorReembolso autorizador) {
        this.plano = plano;
        this.auditoria = auditoria;
        this.autorizador = autorizador;
    }

    public double calcularReembolso(double valorConsulta, double percentualCobertura, Paciente paciente) {
        if (!autorizador.autorizar(paciente, valorConsulta)) {
            throw new RuntimeException("Consulta não autorizada para reembolso.");
        }
        double valorReembolso = valorConsulta * percentualCobertura;

        // Aplica o teto de R$150
        if (valorReembolso > 150.0) {
            valorReembolso = 150.0;
        }

        auditoria.registrarReembolso(paciente, valorConsulta, valorReembolso);
        return valorReembolso;
       //auditoria.registrarConsulta(paciente, valorConsulta); // <- chamada que será verificada

        //return valorConsulta * percentualCobertura;
    }


}


//FASE GREEN DO TDD
// Aqui está a logica do cálculo para fazer o teste rodar.
//Agregamos um novo paremetro paciente, ainda não utilizado.



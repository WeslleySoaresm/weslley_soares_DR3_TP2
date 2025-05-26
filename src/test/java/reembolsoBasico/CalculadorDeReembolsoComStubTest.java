package reembolsoBasico;

import org.example.ReembolsoBasico.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculadorDeReembolsoComStubTest {
    // Stub de plano com 50% de cobertura
    private static class PlanoSaudeStub50 implements PlanoSaude {
        @Override
        public double valorReembolsavel(Consulta consulta) {
            return 300.0;
        }

        @Override
        public double getPercentualDeCobertura(Paciente paciente) {
            return 0.5; // ou 0.8, dependendo do stub
        }
    }

    // Stub de plano com 80% de cobertura
    private static class PlanoSaudeStub80 implements PlanoSaude {
        @Override
        public double getPercentualDeCobertura(Paciente paciente) {
            return 0.8;
        }

        @Override
        public double valorReembolsavel(Consulta consulta) {
            return 300.0;
        }
    }

    // Stub de auditoria que não faz nada
    private static class AuditoriaStub implements Auditoria {

        public void registrarConsulta(Paciente paciente, double valorConsulta) {
            // implementação vazia para o teste
        }

        @Override
        public void registrarReembolso(Paciente paciente, double valorConsulta, double valorReembolso) {
            // implementação vazia para o teste
        }

    }

    @Test
    public void deveCalcularReembolsoComPlanoDe50PorCento() {
        PlanoSaude planoStub = new PlanoSaudeStub50();
        Auditoria auditoriaStub = new AuditoriaStub();

        AutorizadorReembolso autorizadorMock = mock(AutorizadorReembolso.class);


        CalculadoraDeReembolso calculadora = new CalculadoraDeReembolso(planoStub, auditoriaStub, autorizadorMock);

        Paciente paciente = new Paciente("Weslley", "123.456.789-00");
        double valorConsulta = 200.0;
        double percentualCobertura = planoStub.getPercentualDeCobertura(paciente);
        double resultado = calculadora.calcularReembolso(valorConsulta, percentualCobertura, paciente);

        assertEquals(100.0, resultado, 0.01);
    }

    @Test
    public void deveCalcularReembolsoComPlanoDe80PorCento() {
        PlanoSaude planoStub = new PlanoSaudeStub80();
        Auditoria auditoriaStub = new AuditoriaStub();
        AutorizadorReembolso autorizadorMock = mock(AutorizadorReembolso.class);
        CalculadoraDeReembolso calculadora = new CalculadoraDeReembolso(planoStub, auditoriaStub, autorizadorMock);

        Paciente paciente = new Paciente("Janaina", "123.456.789-00");
        double valorConsulta = 200.0;
        double percentualCobertura = planoStub.getPercentualDeCobertura(paciente);
        double resultado = calculadora.calcularReembolso(valorConsulta, percentualCobertura, paciente);


        assertEquals(200.0, resultado, 0.01);


}
    @Test
    void deveAutorizarReembolsoComMock() {
        // Mock
        AutorizadorReembolso autorizadorMock = mock(AutorizadorReembolso.class);
        when(autorizadorMock.estaAutorizado(any())).thenReturn(true);
        when(autorizadorMock.autorizar(any(), anyDouble())).thenReturn(true);


    }
}

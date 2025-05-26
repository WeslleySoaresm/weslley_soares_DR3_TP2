package reembolsoBasico;

import org.example.ReembolsoBasico.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class aplicadorDeTetoTest
{
    @Test
    public void deveAplicarTetoDeReembolsoDe150Reais() {
        Paciente paciente = new Paciente("Carlos", "123.456.789-00");

        // Stub que retornaria normalmente 80% de 250 = R$200
        PlanoSaude plano = mock(PlanoSaude.class);
        when(plano.getPercentualDeCobertura(paciente)).thenReturn(0.8);

        Auditoria auditoria = mock(Auditoria.class);
        AutorizadorReembolso autorizador = mock(AutorizadorReembolso.class);
        when(autorizador.autorizar(paciente, 250.0)).thenReturn(true);

        CalculadoraDeReembolso calculadora = new CalculadoraDeReembolso(plano, auditoria, autorizador);

        double reembolso = calculadora.calcularReembolso(250.0, 0.8, paciente);

        // Esperado: o teto de R$150 se aplica (80% de 250 = 200, mas limitado a 150)
        assertComMargem(150.0, reembolso);







    }



    private void assertComMargem(double v, double reembolso) {
        double margemDeErro = 0.01;
        assertEquals(v, reembolso, margemDeErro);
    }


}

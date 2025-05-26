package reembolsoBasico;

import org.example.ReembolsoBasico.*;
import org.junit.jupiter.api.Test;
import org.example.ReembolsoBasico.helpers.ConsultaHelper;

import java.time.LocalDateTime;
import org.example.ReembolsoBasico.AutorizadorReembolso;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AplicadorDeTetoComStubEMockValidacaoCompletoTest {

    @Test

    void deveAplicarTetoEAutorizarReembolso() {

        AutorizadorReembolso autorizadorMock = mock(AutorizadorReembolso.class);
        when(autorizadorMock.estaAutorizado(any(Reembolso.class))).thenReturn(true);

        // Stub para PlanoSaude
        PlanoSaude planoStub = new PlanoSaude() {
            public double valorReembolsavel(Consulta consulta) {
                return 300.0;
            }

            // Só implemente este método se realmente existir na interface
            @Override
            public double getPercentualDeCobertura(Paciente paciente) {
                return 1.0;
            }
        };

        // Mock para AutorizadorReembolso
        AutorizadorReembolso autorizadorMock2= mock(AutorizadorReembolso.class);
        when(autorizadorMock.estaAutorizado(any(Reembolso.class))).thenReturn(true);

        // Helper para criar uma consulta

        Consulta consulta = ConsultaHelper.criarConsultaSimples();

        // Aplicador de Teto com limite de R$150
        aplicadorDeTeto aplicador = new aplicadorDeTeto(150.0, autorizadorMock);

        // Ação
        Reembolso reembolso = aplicador.aplicar(consulta, planoStub);

        // Verificações
        assertEquals(150.0, reembolso.valor()); // valor limitado ao teto
        assertEquals(consulta, reembolso.consulta());

        // Verifica se o autorizador foi chamado com o reembolso gerado
        verify(autorizadorMock).estaAutorizado(reembolso);


    }
}

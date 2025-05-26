package reembolsoBasico;

import org.example.ReembolsoBasico.*;
import org.junit.jupiter.api.Test;
import org.example.ReembolsoBasico.AutorizadorReembolso;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CalcularvalorReembolsadoComBaseEmUmValorFixoPercentualDeCoberturaTest {
    private PlanoSaude plano;// CLASS -> (1)****
    double valorConsulta = 200.0;
    double percentualCobertura = 0.7; //70%A
    double ValorEsperado = 140.0;

    AutorizadorReembolso autorizadorMock = mock(AutorizadorReembolso.class); //mock


    public double calcularReembolso(double valorConsulta, double percentualCobertura, Paciente dummyPaciente){
        return valorConsulta * percentualCobertura;
    }



    public double calcularReembolso(double valorconsulta, Paciente paciente){
        double percentual = plano.getPercentualDeCobertura(paciente);
        return valorconsulta * percentual;
    }
    //Refatorado em Helpe
    //Refatorado com margem de erro.
    @Test
    public void deveCalcularOReembolsoComBasePercentual() {
        AutorizadorReembolso autorizadorMock = mock(AutorizadorReembolso.class);
        when(autorizadorMock.estaAutorizado(any())).thenReturn(true);

        Paciente dummyPaciente = new Paciente("Paciente Teste", "123.456.789-00");
        CalculadoraDeReembolso calculadora = criarCalculadoraComMocks(dummyPaciente, true, 0.7);

        double valorReembolso = calculadora.calcularReembolso(200.0, 0.7, dummyPaciente);

        assertComMargem(140.0, valorReembolso);

    }


    //Refatorado em Helpe
    @Test
    public void deveLancarExcecaoQuandoConsultaNaoForAutorizada() {
        Paciente paciente = new Paciente("João", "123.456.789-00");
        CalculadoraDeReembolso calculadora = criarCalculadoraComMocks(paciente, false, 0.7);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            calculadora.calcularReembolso(200.0, 0.7, paciente);
        });

        assertEquals("Consulta não autorizada para reembolso.", thrown.getMessage());
    }

    //Refatorado em Helper
    @Test
    public void deveCalcularReembolsoQuandoConsultaForAutorizada() {
        Paciente paciente = new Paciente("Maria", "123.456.789-00");
        CalculadoraDeReembolso calculadora = criarCalculadoraComMocks(paciente, true, 0.7);

        double reembolso = calculadora.calcularReembolso(200.0, 0.7, paciente);

        assertComMargem(140.0, reembolso);

    }


    // Método Helper
    private CalculadoraDeReembolso criarCalculadoraComMocks(Paciente paciente, boolean autorizado, double percentualCobertura) {
        PlanoSaude plano = mock(PlanoSaude.class);
        when(plano.getPercentualDeCobertura(paciente)).thenReturn(percentualCobertura);

        Auditoria auditoria = mock(Auditoria.class);
        AutorizadorReembolso autorizador = mock(AutorizadorReembolso.class);
        when(autorizador.autorizar(paciente, 200.0)).thenReturn(autorizado);

        return new CalculadoraDeReembolso(plano, auditoria, autorizador);
    }
//Margem de erro, onde está essa função para padronizar todas as comparações de numeros decimaism com margem de ero,
// evitandoa repetição de 0.01 em todos os testes aplicado, deixando o codigo mais limpo de facil de manter
    private void assertComMargem(double esperado, double atual) {
        double margemDeErro = 0.01;
        assertEquals(esperado, atual, margemDeErro);
    }

    @Test
    void deveAutorizarReembolsoComMock() {
        // Mock
        AutorizadorReembolso autorizadorMock = mock(AutorizadorReembolso.class);
        when(autorizadorMock.estaAutorizado(any())).thenReturn(true);
        when(autorizadorMock.autorizar(any(), anyDouble())).thenReturn(true);


    }
    }






//Fase RED DO TDD
// SOBRE O TESTE - TODO TESTE AJUDA A DETECTAR ERROS RAPIDAMENTE E GARANTEM MELHOR FUNCINALIDADE E SEM RISCO DE QUEBRAR FAZENDO ASSIM REGREDIR;
//TESTE PARA CALCULAR O REEMBOLSO DO CLIENTE, ONDE A CONSULTA ESTÁ NO VALOR DE 200 E ELA TEM 70% DA COBERTURA
// ONDE ELA PAGA SÓ 30% DO VALOR, SENDO ASSIM CRIAMOS UMA CLASS -> (1)***, CONTENDO O TESTE (deveCalcularOReembolsoComBasePercentual)
//ONDE INVOCAMOS A CLASS DE CALCULADORADEREEMBOLSO CRIANDO UMA NOVA VARIAVEL CALCULADORA ATRIBUINDO UMA NOVA INTSACIA DA CLASS CALCULADORADEREEMBOLSO();
//CRIAMOS O VALORCONSULTA, PERCENTUALCOBERTURA, VALORESPERADO COM O TIPO DOUBLE, O QUE É DOUBLE? UM TIPO DE DADO PRIMITIVO PARA AMARZENAR NUMERO DE PONTO FLUTUANTE (NÚMEROS DECIMAIS)
//acrecentamos um paciente ficticio para testar o codigo.
// Utilizamos um objeto do tipo Paciente como "Dummy" neste teste. Isso significa que ele é passado apenas para satisfazer a assinatura do método, mas ainda não influencia a lógica de cálculo do reembolso.
// * É uma prática comum quando antecipamos que um objeto terá papel importante no futuro.
//Refatoramos os três teste para implementar o helpe, onde encurtamos o código e deixamos mais legivel, agora temos helper reutilizável para criar obkjetos mackados com menas repetiçao.
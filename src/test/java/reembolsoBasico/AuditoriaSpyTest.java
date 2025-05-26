package reembolsoBasico;

import org.example.ReembolsoBasico.*;
import org.junit.jupiter.api.Test;
import org.example.ReembolsoBasico.Paciente;

import static org.junit.jupiter.api.Assertions.*;

public class AuditoriaSpyTest {

    @Test
    public void deveChamarMetodoDeAuditoriaAoCalcularReembolso() {
// Stub de plano com 70%
        PlanoSaude planoStub = new PlanoSaude() {
            @Override
            public double valorReembolsavel(Consulta consulta) {
                return 200.0;
            }

            @Override
            public double getPercentualDeCobertura(Paciente paciente) {
                return 0.5;
            }
        }; // <-- FECHA AQUI

        AuditoriaSpy auditoriaSpy = new AuditoriaSpy();
        AutorizadorReembolso autorizador = new AutorizadorReembolso() {
            @Override
            public boolean autorizar(Paciente paciente, double valorConsulta) {
                return true;
            }

            @Override
            public boolean estaAutorizado(Reembolso reembolso) {
                return true;
            }
        };


        CalculadoraDeReembolso calculadora = new CalculadoraDeReembolso(planoStub, auditoriaSpy, autorizador);

        Paciente paciente = new Paciente("Carlos", "123.456.789-00");
        double percentualCobertura = planoStub.getPercentualDeCobertura(paciente);
        calculadora.calcularReembolso(200.0, percentualCobertura, paciente);

        // Verifica se o método foi chamado
        assertTrue(auditoriaSpy.foiChamado(), "Esperava que o método registrarConsulta fosse chamado.");
    }


    @Test
    public void deveCalcularReembolsoComPlanoDe80PorCento() {

            PlanoSaude planoStub = new PlanoSaude() {
                @Override
                public double valorReembolsavel(Consulta consulta) {
                    return 250.0;
                }

                @Override
                public double getPercentualDeCobertura(Paciente paciente) {
                    return 0.8;
                }
            };

            Auditoria auditoriaStub = new Auditoria() {
                @Override
                public void registrarReembolso(Paciente paciente, double valorConsulta, double valorReembolso) {
                    // Stub sem lógica
                }
            };

            AutorizadorReembolso autorizador = new AutorizadorReembolso() {
                @Override
                public boolean autorizar(Paciente paciente, double valorConsulta) {
                    return true;
                }

                @Override
                public boolean estaAutorizado(Reembolso reembolso) {
                    return true;
                }
            };

            CalculadoraDeReembolso calculadora = new CalculadoraDeReembolso(planoStub, auditoriaStub, autorizador);

            Paciente paciente = new Paciente("Janaina", "123.456.789-00");
            double valorConsulta = 250.0;
            double percentualCobertura = 0.8;

            double resultado = calculadora.calcularReembolso(valorConsulta, percentualCobertura, paciente);

            assertEquals(200.0, resultado, 0.01);
        }

    }



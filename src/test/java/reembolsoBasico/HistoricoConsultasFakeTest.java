package reembolsoBasico;

import org.example.ReembolsoBasico.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoricoConsultasFakeTest {

    @Test
    public void deveRegistrarEObterConsultasDeUmPaciente() {
        HistoricoConsultas historico = new HistoricoConsultasFake();
        Paciente paciente = new Paciente("João", "123.456.789-00");

        historico.resgistrarConsulta(paciente, 200.0);
        historico.resgistrarConsulta(paciente, 180.0);

        List<Double> consultas = historico.obterConsultasDoPaciente(paciente);

        assertEquals(2, consultas.size());
        assertEquals(200.0, consultas.get(0));
        assertEquals(180.0, consultas.get(1));
    }
}

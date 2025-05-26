package org.example.ReembolsoBasico.helpers;

import org.example.ReembolsoBasico.Paciente;
import org.example.ReembolsoBasico.Consulta;
import org.example.ReembolsoBasico.Medico;


import java.time.LocalDateTime;


    public class ConsultaHelper {
        public static Consulta criarConsultaSimples() {
            Paciente paciente = new Paciente("João da Silva", "123.456.789-00");
            Medico medico = new Medico("Dra. Ana", "CRM12345", "Cardiologia");
            return new Consulta(LocalDateTime.now(), 300.0, paciente, medico);
        }
    }

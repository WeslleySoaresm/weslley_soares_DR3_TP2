package org.example.ReembolsoBasico;

public interface AutorizadorReembolso {

    boolean autorizar (Paciente paciente, double valorConsulta);

    boolean estaAutorizado(Reembolso reembolso);

}

package org.example.ReembolsoBasico;

public class Reembolso {
    private final double valor;
    private final Consulta consulta;

    public Reembolso(double valor, Consulta consulta) {
        this.valor = valor;
        this.consulta = consulta;
    }

    public double valor() {
        return valor;
    }

    public Consulta consulta() {
        return consulta;
    }
}

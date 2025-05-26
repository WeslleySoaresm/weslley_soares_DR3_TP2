package org.example.ReembolsoBasico;

public class aplicadorDeTeto {

    private final double teto;
    private final AutorizadorReembolso autorizador;

    public aplicadorDeTeto(double teto, AutorizadorReembolso autorizador) {
        this.teto = teto;
        this.autorizador = autorizador;
    }

    public Reembolso aplicar(Consulta consulta, PlanoSaude plano) {
        double valorCalculado = plano.valorReembolsavel(consulta);
        double valorFinal = Math.min(valorCalculado, teto);

        Reembolso reembolso = new Reembolso(valorFinal, consulta);

        if (autorizador.estaAutorizado(reembolso)) {
            return reembolso;
        } else {
            throw new RuntimeException("Reembolso não autorizado");
        }
    }
}

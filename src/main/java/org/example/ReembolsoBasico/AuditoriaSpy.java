package org.example.ReembolsoBasico;

public class AuditoriaSpy implements  Auditoria{
    private boolean metodoChamado = false;


    public void registrarConsulta(Paciente paciente, double valorConsulta) {
        metodoChamado = true;
        // Em produção poderia registrar em log, enviar evento etc.

    }

    @Override
    public void registrarReembolso(Paciente paciente, double valorConsulta, double valorReembolso) {

    }


    public boolean foiChamado() {
        return metodoChamado;
    }
}

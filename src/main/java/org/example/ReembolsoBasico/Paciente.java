package org.example.ReembolsoBasico;


import java.util.Objects;

// Class dummmy - sem lógica por enquanto.
public class Paciente {

    private String nome;
    private String cpf;



    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
    private String name;

    public Paciente(String name, String s){
        this.name = name; //recebe o nome
        this.nome = nome;
        this.cpf = cpf;

    }

    public String getName(){
        return name;
    }

    //Comparação em coleção (map)
    @Override
    public  boolean equals(Object x){
        if (this == x) return true;
        if (!(x instanceof Paciente)) return false;
        Paciente paciente = (Paciente) x;
        return Objects.equals(name, paciente.name);

    }
    @Override
    public int hashCode(){
        return Objects.hash(name);
    }

}


/*
 * Utilizamos um objeto do tipo Paciente como "Dummy" neste teste.
 * Isso significa que ele é passado apenas para satisfazer a assinatura do método,
 * mas ainda não influencia a lógica de cálculo do reembolso.
 *
 * É uma prática comum quando antecipamos que um objeto terá papel importante no futuro.
 */

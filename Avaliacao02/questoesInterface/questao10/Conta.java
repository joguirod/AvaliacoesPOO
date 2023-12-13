package Avaliacao02.questoesInterface.questao10;

public class Conta {
    private String nome;
    private double saldo;

    public Conta(String nome, double saldo){
        this.nome = nome;
        this.saldo = saldo;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public double getSaldo(){
        return saldo;
    }

    public void setSaldo(double Saldo){
        this.saldo = saldo;
    }
}

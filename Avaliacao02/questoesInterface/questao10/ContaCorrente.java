package Avaliacao02.questoesInterface.questao10;

public class ContaCorrente extends Conta implements ITributavel{

    public ContaCorrente(String nome, double saldo) {
        super(nome, saldo);
    }

    public double calcularTributos() {
        return this.getSaldo() * 0.1;
    }
}

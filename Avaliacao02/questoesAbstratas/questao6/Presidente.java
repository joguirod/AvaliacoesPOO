package Avaliacao02.questoesAbstratas.questao6;

public class Presidente extends Funcionario{
    public Presidente(double salario) {
        super(salario);
    }

    @Override
    double getBonificacao() {
        return this.getSalario() + 1000;
    }
}

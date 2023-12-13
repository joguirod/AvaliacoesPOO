package Avaliacao02.questoesAbstratas.questao6;

public class Diretor extends Gerente{
    public Diretor(double salario, String login, String senha) {
        super(salario, login, senha);
    }

    @Override
    double getBonificacao() {
        return this.getSalario()*0.6;
    }
}

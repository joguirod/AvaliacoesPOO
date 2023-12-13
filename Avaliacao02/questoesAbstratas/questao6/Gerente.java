package Avaliacao02.questoesAbstratas.questao6;

public class Gerente extends Funcionario{
    private String login;
    private String senha;

    public Gerente(double salario, String login, String senha) {
        super(salario);
        this.login = login;
        this.senha = senha;
    }

    @Override
    double getBonificacao() {
        return this.getSalario() * 0.4;
    }
}

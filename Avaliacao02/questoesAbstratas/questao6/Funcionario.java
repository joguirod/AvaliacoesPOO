package Avaliacao02.questoesAbstratas.questao6;

abstract class Funcionario {
    private double salario;

    Funcionario(double salario){
        this.salario = salario;
    }

    abstract double getBonificacao();

    public double getSalario(){
        return salario;
    }
}

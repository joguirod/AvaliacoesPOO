package Avaliacao02.questoesInterface.questao11;

import Avaliacao02.questoesInterface.questao10.ContaCorrente;
import Avaliacao02.questoesInterface.questao10.SeguroDeVida;

public class Teste {
    public static void main(String[] args) {
        ContaCorrente conta0 = new ContaCorrente("Fernandinho", 12450);
        ContaCorrente conta1 = new ContaCorrente("Rogerinho", 20000);
        ContaCorrente conta2 = new ContaCorrente("Otilinho", 31000);
        ContaCorrente conta3 = new ContaCorrente("VitorHuguinho", 120);

        SeguroDeVida seguro0 = new SeguroDeVida();
        SeguroDeVida seguro1 = new SeguroDeVida();
        SeguroDeVida seguro2 = new SeguroDeVida();
        SeguroDeVida seguro3 = new SeguroDeVida();

        AuditoriaInterna auditoriaInterna = new AuditoriaInterna();

        auditoriaInterna.adicionar(conta0);
        auditoriaInterna.adicionar(conta1);
        auditoriaInterna.adicionar(conta2);
        auditoriaInterna.adicionar(conta3);

        auditoriaInterna.adicionar(seguro0);
        auditoriaInterna.adicionar(seguro1);
        auditoriaInterna.adicionar(seguro2);
        auditoriaInterna.adicionar(seguro3);

        double totalTributos = auditoriaInterna.calcularTributos();
        System.out.println("O total de tributos é: R$ " + totalTributos);
    }
}

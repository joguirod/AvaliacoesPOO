package Avaliacao02.questoesAbstratas;

public class Questao3 {
    /*Se uma classe herda de uma classe abstrata, mas não implementa todos os métodos abstratos definidos pela classe abstrata,
 logo essa classe concreta também deve ser marcada como abstrata.
 Se a classe concreta não for marcada como abstrata e não implementar todos os métodos abstratos,
 o compilador dará um erro.


 EXEMPLO

    abstract class ClasseAbstrata {
        abstract void metodoAbstrato();
    }

    class ClasseConcreta extends ClasseAbstrata {
        // ErroR: ClasseConccreta deve ser delarada abstrata ou implementar o métodoAbstrato
    }

/*------------------------------------------------------------------------------------------------------------
A ClasseConcreta não implementou o método abstrato metodoAbstrato,
resultando em um erro de compilação.
Para corrigir isso, você pode marcar a ClasseConcreta como abstrata ou fornecer a implementação para o método abstrato:
--------------------------------------------------------------------------------------------------------------
*/
}

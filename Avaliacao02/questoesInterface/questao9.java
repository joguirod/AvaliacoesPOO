package Avaliacao02.questoesInterface;

import Avaliacao02.questoesInterface.questao8.Quadrado;
import Avaliacao02.questoesInterface.questao8.Retangulo;
import Avaliacao02.questoesInterface.questao8.Triangulo;

public class questao9 {
    public static void main(String[] args) {
        Quadrado quadrado = new Quadrado(4);
        Retangulo retangulo = new Retangulo(2, 8);
        Triangulo triangulo = new Triangulo(3, 4, 3, 4, 5);
        Quadrado quadrado1 = new Quadrado(2);
        Retangulo retangulo2 = new Retangulo(5, 2);

        int resultadoComparacao1 = quadrado.comparar(retangulo);
        int resultadoComparacao2 = retangulo.comparar(triangulo);
        int resultadoComparacao3 = quadrado1.comparar(retangulo2);

        System.out.println("Resultado da comparação 1: " + resultadoComparacao1);
        System.out.println("Resultado da comparação 2: " + resultadoComparacao2);
        System.out.println("Resultado da comparação 3: " + resultadoComparacao3);
    }
}

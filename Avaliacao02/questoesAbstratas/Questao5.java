package Avaliacao02.questoesAbstratas;

import Avaliacao02.questoesAbstratas.questao4.FiguraGeometrica;
import Avaliacao02.questoesAbstratas.questao4.Quadrado;
import Avaliacao02.questoesAbstratas.questao4.Retangulo;
import Avaliacao02.questoesAbstratas.questao4.Triangulo;

public class Questao5 {
    /*
    Ao criar um array de um tipo abstrato ou de uma interface em Java, você está basicamente criando um recipiente
    que pode armazenar referências para objetos de classes concretas que seguem essa abstração. Essa flexibilidade
    acontece porque, em tempo de execução, o programa pode reconhecer o tipo real dos objetos armazenados no array,
    permitindo que você trabalhe com instâncias específicas de classes concretas, mesmo que o array seja declarado
    com um tipo mais genérico.
     */

    public static void main(String[] args) {
        FiguraGeometrica[] figuras = new FiguraGeometrica[3];

        // Você não pode criar uma instância direta da classe abstrata, mas pode criar instâncias das subclasses
        figuras[0] = new Quadrado(2);
        figuras[1] = new Triangulo(3, 3, 3, 3, 3);
        figuras[2] = new Retangulo(2, 4);

        // Agora você pode chamar métodos nas instâncias usando o array
        for (FiguraGeometrica figura : figuras) {
            figura.calcularPerimetro();
            System.out.println("Área: " + figura.calcularArea());
            System.out.println("Perímetro: " + figura.calcularPerimetro());
        }
    }
}

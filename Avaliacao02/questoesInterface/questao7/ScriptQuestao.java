package Avaliacao02.questoesInterface.questao7;

public class ScriptQuestao {
    public static void main(String[] args) {
        IFiguraGeometrica[] figuras = new IFiguraGeometrica[3];

        figuras[0] = new Quadrado(5);
        figuras[1] = new Triangulo(3, 4, 5, 6, 7);
        figuras[2] = new Retangulo(4, 6);

        for (IFiguraGeometrica figura : figuras) {
            System.out.println("Área: " + figura.calcularArea());
            System.out.println("Perimetro: " + figura.calcularPerimetro());
            System.out.println("--------------");
        }
    }
}

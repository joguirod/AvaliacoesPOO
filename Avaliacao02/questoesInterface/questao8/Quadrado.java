package Avaliacao02.questoesInterface.questao8;

public class Quadrado implements IFiguraGeometrica, IComparavel {
    private double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }

    @Override
    public double calcularPerimetro() {
        return 4 * lado;
    }


    // Adição do método comparar
    @Override
    public int comparar(IFiguraGeometrica figuraGeometrica) {
        if(this.calcularArea() < figuraGeometrica.calcularArea()){
            return -1;
        }
        if(this.calcularArea() == figuraGeometrica.calcularArea()){
            return 0;
        }
        return 1;
    }
}

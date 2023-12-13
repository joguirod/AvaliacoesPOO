package Avaliacao02.questoesInterface.questao8;

public class Retangulo implements IFiguraGeometrica, IComparavel {
    private double base;
    private double altura;

    public Retangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (base + altura);
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

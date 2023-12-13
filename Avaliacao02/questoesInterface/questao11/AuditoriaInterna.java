package Avaliacao02.questoesInterface.questao11;

import Avaliacao02.questoesInterface.questao10.ITributavel;

import java.util.ArrayList;
import java.util.List;

public class AuditoriaInterna {
    List<ITributavel> tributaveis = new ArrayList<ITributavel>();

    public void adicionar(ITributavel tributavel){
        tributaveis.add(tributavel);
    }

    public double calcularTributos(){
        double total = 0;
        for (ITributavel tributavel:
             tributaveis) {
            total += tributavel.calcularTributos();
        }
        return total;
    }
}

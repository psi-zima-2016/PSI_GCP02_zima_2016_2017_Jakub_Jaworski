import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.nnet.Perceptron;
import org.neuroph.nnet.learning.PerceptronLearning;

import java.util.Arrays;

/**
 * Created by Jakub on 2017-01-19.
 */
public class Zagadnienie1 {

    Dane dane;
    Perceptron perceptron;
    PerceptronLearning uczeniePerceptronu;

    double pomiarPrzed, pomiarPo,czasUczenia;

public Zagadnienie1(Dane dane) {

    this.dane = dane;
    perceptron = new Perceptron(28, 1);
    uczeniePerceptronu = new PerceptronLearning();

    System.out.println("Perceptron:\n");

    uczeniePerceptronu.setLearningRate(0.1);

    //uczenie perceptronu

    uczeniePerceptronu.addListener(new LearningEventListener() {
        public void handleLearningEvent(LearningEvent learningEvent) {
            PerceptronLearning tempUczenie = (PerceptronLearning) learningEvent.getSource();

            System.out.println("epoka: " + tempUczenie.getCurrentIteration()
                    + " - błąd MSE: " + tempUczenie.getTotalNetworkError());
        }
    });

    perceptron.setLearningRule(uczeniePerceptronu);

    pomiarPrzed = System.currentTimeMillis();
    perceptron.learn(dane.daneUczace);
    pomiarPo = System.currentTimeMillis();
    czasUczenia = pomiarPo - pomiarPrzed;

    System.out.println("Czas uczenia: " + czasUczenia+ " milisekund\n");

    //zadanie zbioru walidującego



    if(dane.daneWejsciowe.isEmpty()) {

        for (DataSetRow rekord : dane.daneWalidujace.getRows()) {
            perceptron.setInput(rekord.getInput());
            perceptron.calculate();

            double[] wyjscie = perceptron.getOutput();

            dane.wyswietlMacierz(rekord.getInput(), wyjscie);
        }
    }
    else
    {
        for (DataSetRow rekord : dane.daneWejsciowe.getRows()) {
            perceptron.setInput(rekord.getInput());
            perceptron.calculate();

            double[] wyjscie = perceptron.getOutput();

            dane.wyswietlMacierz(rekord.getInput(), wyjscie);
        }
    }



}

}

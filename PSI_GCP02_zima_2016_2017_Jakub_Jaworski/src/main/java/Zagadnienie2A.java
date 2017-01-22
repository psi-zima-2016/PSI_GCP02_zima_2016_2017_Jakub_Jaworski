import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;

/**
 * Created by Jakub on 2017-01-21.
 */
public class Zagadnienie2A {

    /* Osoby programujące, ale nieimplementujące algorytmów:
  - zapoznać się z:
    - działaniem neuronu, warstwy i sieci
    - regułą delta
    - algorytmem wstecznej propagacji
  - wybrać podproblem z danych i przeprowadzić uczenie z:
    - regułą delta (ADALINE i MADALINE lub neuron nieliniowy (można zbudować sieci jednowarstwowe))
    - algorytm wstecznej propagacji (bez bezwładności)*/


    MultiLayerPerceptron siecWielowarstwowa;
    BackPropagation wstecznaPropagacja;


    Dane dane;

    double pomiarPrzed, pomiarPo,czasUczenia;

    public Zagadnienie2A(Dane dane)
    {
        this.dane=dane;

        System.out.println("Siec wielowarstwowa:\n");

        siecWielowarstwowa = new MultiLayerPerceptron(28,15,1);

        wstecznaPropagacja = siecWielowarstwowa.getLearningRule();

        wstecznaPropagacja.addListener(new LearningEventListener() {
            public void handleLearningEvent(LearningEvent learningEvent) {
                BackPropagation tempUczenie = (BackPropagation) learningEvent.getSource();
                System.out.println("epoka: " + tempUczenie.getCurrentIteration()
                        + " - błąd MSE: " + tempUczenie.getTotalNetworkError());
            }
        });

        wstecznaPropagacja.setLearningRate(0.1);
        wstecznaPropagacja.setMaxIterations(100);

        pomiarPrzed = System.currentTimeMillis();
        siecWielowarstwowa.learn(dane.daneUczace);
        pomiarPo = System.currentTimeMillis();
        czasUczenia = pomiarPo - pomiarPrzed;

        System.out.println("Czas uczenia: " + czasUczenia+ " milisekund\n");



       /* for(DataSetRow rekord: dane.daneWalidujace.getRows())
        {
            siecWielowarstwowa.setInput(rekord.getInput());
            siecWielowarstwowa.calculate();

            double [] wyjscie = siecWielowarstwowa.getOutput();
            dane.wyswietlMacierz(rekord.getInput(),wyjscie);
        }*/

        if(dane.daneWejsciowe.isEmpty()) {

            for (DataSetRow rekord : dane.daneWalidujace.getRows()) {
                siecWielowarstwowa.setInput(rekord.getInput());
                siecWielowarstwowa.calculate();

                double[] wyjscie = siecWielowarstwowa.getOutput();

                dane.wyswietlMacierz(rekord.getInput(), wyjscie);
            }
        }
        else
        {
            for (DataSetRow rekord : dane.daneWejsciowe.getRows()) {
                siecWielowarstwowa.setInput(rekord.getInput());
                siecWielowarstwowa.calculate();

                double[] wyjscie = siecWielowarstwowa.getOutput();

                dane.wyswietlMacierz(rekord.getInput(), wyjscie);
            }
        }
    }






}

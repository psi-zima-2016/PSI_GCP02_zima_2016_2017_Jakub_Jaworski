import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.transfer.Sigmoid;
import org.neuroph.nnet.Adaline;
import org.neuroph.nnet.learning.SigmoidDeltaRule;

/**
 * Created by Jakub on 2017-01-21.
 */
public class Zagadnienie2B {

    SigmoidDeltaRule regulaDelta;
    Adaline adaline;

    Dane dane;

    double pomiarPrzed, pomiarPo,czasUczenia;

    public Zagadnienie2B(Dane dane)
    {
       this.dane = dane;

        System.out.println("Adaline (reguła Delta):\n");

        adaline = new Adaline(28);
        regulaDelta = new SigmoidDeltaRule();

        regulaDelta.addListener(new LearningEventListener() {
            public void handleLearningEvent(LearningEvent learningEvent) {
                SigmoidDeltaRule tempUczenie = (SigmoidDeltaRule) learningEvent.getSource();
                System.out.println("epoka: " + tempUczenie.getCurrentIteration()
                + " - błąd MSE: " + tempUczenie.getTotalNetworkError());
            }
        });

        adaline.setLearningRule(regulaDelta);

        pomiarPrzed = System.currentTimeMillis();
        adaline.learn(dane.daneUczace);
        pomiarPo = System.currentTimeMillis();
        czasUczenia = pomiarPo - pomiarPrzed;

        System.out.println("Czas uczenia: " + czasUczenia+ " milisekund\n");

        /*for(DataSetRow rekord : dane.daneWalidujace.getRows())
        {
            adaline.setInput(rekord.getInput());
            adaline.calculate();

            double[] wyjscie = adaline.getOutput();
            dane.wyswietlMacierz(rekord.getInput(),wyjscie);
        }*/

        if(dane.daneWejsciowe.isEmpty()) {

            for (DataSetRow rekord : dane.daneWalidujace.getRows()) {
                adaline.setInput(rekord.getInput());
                adaline.calculate();

                double[] wyjscie = adaline.getOutput();

                dane.wyswietlMacierz(rekord.getInput(), wyjscie);
            }
        }
        else
        {
            for (DataSetRow rekord : dane.daneWejsciowe.getRows()) {
                adaline.setInput(rekord.getInput());
                adaline.calculate();

                double[] wyjscie = adaline.getOutput();

                dane.wyswietlMacierz(rekord.getInput(), wyjscie);
            }
        }
    }




}

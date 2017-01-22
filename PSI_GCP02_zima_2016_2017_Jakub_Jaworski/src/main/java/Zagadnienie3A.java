import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.nnet.SupervisedHebbianNetwork;
import org.neuroph.nnet.learning.SupervisedHebbianLearning;
import org.neuroph.nnet.learning.UnsupervisedHebbianLearning;

/**
 * Created by Jakub on 2017-01-21.
 */
public class Zagadnienie3A {

    SupervisedHebbianNetwork  siecHebbaZNauczycielem;
    SupervisedHebbianLearning hebbZNauczycielem;

    Dane dane;

    double pomiarPrzed, pomiarPo,czasUczenia;

    public Zagadnienie3A(Dane dane)
    {
        this.dane=dane;

        System.out.println("Sieć Hebb'a z nauczycielem:\n");

        siecHebbaZNauczycielem = new SupervisedHebbianNetwork(28,1);

        hebbZNauczycielem = new SupervisedHebbianLearning();

        hebbZNauczycielem.addListener(new LearningEventListener() {
            public void handleLearningEvent(LearningEvent learningEvent) {
                SupervisedHebbianLearning tempUczenie = (SupervisedHebbianLearning) learningEvent.getSource();
                System.out.println("epoka: " + tempUczenie.getCurrentIteration()
                        + " - błąd MSE: " + tempUczenie.getTotalNetworkError());
            }
        });

        siecHebbaZNauczycielem.setLearningRule(hebbZNauczycielem);

        pomiarPrzed = System.currentTimeMillis();
        siecHebbaZNauczycielem.learn(dane.daneUczace);
        pomiarPo = System.currentTimeMillis();
        czasUczenia = pomiarPo - pomiarPrzed;

        System.out.println("Czas uczenia: " + czasUczenia+ " milisekund\n");



       /* for(DataSetRow rekord: dane.daneWalidujace.getRows())
        {
            siecHebbaZNauczycielem.setInput(rekord.getInput());
            siecHebbaZNauczycielem.calculate();

            double [] wyjscie = siecHebbaZNauczycielem.getOutput();
            dane.wyswietlMacierz(rekord.getInput(),wyjscie);
        }*/

        if(dane.daneWejsciowe.isEmpty()) {

            for (DataSetRow rekord : dane.daneWalidujace.getRows()) {
                siecHebbaZNauczycielem.setInput(rekord.getInput());
                siecHebbaZNauczycielem.calculate();

                double[] wyjscie = siecHebbaZNauczycielem.getOutput();

                dane.wyswietlMacierz(rekord.getInput(), wyjscie);
            }
        }
        else
        {
            for (DataSetRow rekord : dane.daneWejsciowe.getRows()) {
                siecHebbaZNauczycielem.setInput(rekord.getInput());
                siecHebbaZNauczycielem.calculate();

                double[] wyjscie = siecHebbaZNauczycielem.getOutput();

                dane.wyswietlMacierz(rekord.getInput(), wyjscie);
            }
        }
    }

}

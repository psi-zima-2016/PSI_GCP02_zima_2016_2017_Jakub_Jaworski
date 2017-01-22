import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.nnet.UnsupervisedHebbianNetwork;
import org.neuroph.nnet.learning.OjaLearning;
import org.neuroph.nnet.learning.UnsupervisedHebbianLearning;

/**
 * Created by Jakub on 2017-01-21.
 */
public class Zagadnienie3B {

    UnsupervisedHebbianNetwork siecHebbaBezNauczyciela;
    UnsupervisedHebbianLearning hebbBezNauczyciela;

    Dane dane;

    double pomiarPrzed, pomiarPo,czasUczenia;

    public Zagadnienie3B(Dane dane)
    {
        this.dane=dane;

        System.out.println("Sieć Hebb'a bez nauczyciela:\n");

        siecHebbaBezNauczyciela = new UnsupervisedHebbianNetwork(28,1);

        hebbBezNauczyciela = new OjaLearning();

        hebbBezNauczyciela.addListener(new LearningEventListener() {
            public void handleLearningEvent(LearningEvent learningEvent) {
                UnsupervisedHebbianLearning tempUczenie = (UnsupervisedHebbianLearning) learningEvent.getSource();
                System.out.println("epoka: " + tempUczenie.getCurrentIteration());
                //        + " - błąd MSE: " + tempUczenie.getTotalNetworkError());
            }
        });


        siecHebbaBezNauczyciela.setLearningRule(hebbBezNauczyciela);

        pomiarPrzed = System.currentTimeMillis();
        siecHebbaBezNauczyciela.learn(dane.daneUczace);
        pomiarPo = System.currentTimeMillis();
        czasUczenia = pomiarPo - pomiarPrzed;

        System.out.println("Czas uczenia: " + czasUczenia+ " milisekund\n");



        /*for(DataSetRow rekord: dane.daneWalidujace.getRows())
        {
            siecHebbaBezNauczyciela.setInput(rekord.getInput());
            siecHebbaBezNauczyciela.calculate();

            double [] wyjscie = siecHebbaBezNauczyciela.getOutput();
            dane.wyswietlMacierz(rekord.getInput(),wyjscie);
        }*/

        if(dane.daneWejsciowe.isEmpty()) {

            for (DataSetRow rekord : dane.daneWalidujace.getRows()) {
                siecHebbaBezNauczyciela.setInput(rekord.getInput());
                siecHebbaBezNauczyciela.calculate();

                double[] wyjscie = siecHebbaBezNauczyciela.getOutput();

                dane.wyswietlMacierz(rekord.getInput(), wyjscie);
            }
        }
        else
        {
            for (DataSetRow rekord : dane.daneWejsciowe.getRows()) {
                siecHebbaBezNauczyciela.setInput(rekord.getInput());
                siecHebbaBezNauczyciela.calculate();

                double[] wyjscie = siecHebbaBezNauczyciela.getOutput();

                dane.wyswietlMacierz(rekord.getInput(), wyjscie);
            }
        }
    }

}

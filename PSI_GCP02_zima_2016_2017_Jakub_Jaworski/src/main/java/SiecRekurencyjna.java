import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Hopfield;
import org.neuroph.nnet.learning.UnsupervisedHebbianLearning;

/**
 * Created by Jakub on 2017-01-21.
 */
public class SiecRekurencyjna {

    Hopfield siecHopfielda;

    Dane dane;

    double pomiarPrzed, pomiarPo,czasUczenia;

    public SiecRekurencyjna(Dane dane)
    {
        this.dane = dane;

        System.out.println("Rekurencyjna sieć Hopfield'a:\n");

        siecHopfielda = new Hopfield(28);

       // siecHopfielda.setLearningRule(new UnsupervisedHebbianLearning());
        pomiarPrzed = System.currentTimeMillis();
        siecHopfielda.learn(dane.daneUczace);
        pomiarPo = System.currentTimeMillis();
        czasUczenia = pomiarPo - pomiarPrzed;

        System.out.println("Czas uczenia: " + czasUczenia+ " milisekund\n");

        /*for(DataSetRow rekord : dane.daneWalidujace.getRows())
        {
            siecHopfielda.setInput(rekord.getInput());
            siecHopfielda.calculate();

            double[] wyjscie = siecHopfielda.getOutput();
            dane.wyswietlMacierz(rekord.getInput(),wyjscie);
        }*/

        if(dane.daneWejsciowe.isEmpty()) {

            for (DataSetRow rekord : dane.daneWalidujace.getRows()) {
                siecHopfielda.setInput(rekord.getInput());
                siecHopfielda.calculate();

                double[] wyjscie = siecHopfielda.getOutput();

                dane.wyswietlMacierz(rekord.getInput(), wyjscie);
            }
        }
        else
        {
            for (DataSetRow rekord : dane.daneWejsciowe.getRows()) {
                siecHopfielda.setInput(rekord.getInput());
                siecHopfielda.calculate();

                double[] wyjscie = siecHopfielda.getOutput();

                dane.wyswietlMacierz(rekord.getInput(), wyjscie);
            }
        }
    }
}

import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Kohonen;
import org.neuroph.nnet.learning.KohonenLearning;

/**
 * Created by Jakub on 2017-01-21.
 */
public class Zagadnienie4 {

    Kohonen siecKohonena;
    KohonenLearning regulaKohonena;

    Dane dane;

    double pomiarPrzed, pomiarPo,czasUczenia;

    public Zagadnienie4(Dane dane)
    {
        this.dane=dane;

        System.out.println("Sieć Kohonen'a:\n");

        siecKohonena = new Kohonen(28,1);

        regulaKohonena = new KohonenLearning();
        regulaKohonena.setIterations(10,10);

        siecKohonena.setLearningRule(regulaKohonena);

        pomiarPrzed = System.currentTimeMillis();
        siecKohonena.learn(dane.daneUczace);
        pomiarPo = System.currentTimeMillis();
        czasUczenia = pomiarPo - pomiarPrzed;

        System.out.println("Czas uczenia: " + czasUczenia+ " milisekund\n");


       /* for(DataSetRow rekord: dane.daneWalidujace.getRows())
        {
            siecKohonena.setInput(rekord.getInput());
            siecKohonena.calculate();

            double[] wyjscie = siecKohonena.getOutput();
            dane.wyswietlMacierz(rekord.getInput(),wyjscie);
        }*/

        if(dane.daneWejsciowe.isEmpty()) {

            for (DataSetRow rekord : dane.daneWalidujace.getRows()) {
                siecKohonena.setInput(rekord.getInput());
                siecKohonena.calculate();

                double[] wyjscie = siecKohonena.getOutput();

                dane.wyswietlMacierz(rekord.getInput(), wyjscie);
            }
        }
        else
        {
            for (DataSetRow rekord : dane.daneWejsciowe.getRows()) {
                siecKohonena.setInput(rekord.getInput());
                siecKohonena.calculate();

                double[] wyjscie = siecKohonena.getOutput();

                dane.wyswietlMacierz(rekord.getInput(), wyjscie);
            }
        }

    }
}

import org.neuroph.nnet.learning.OjaLearning;

import java.io.FileNotFoundException;

/**
 * Created by Jakub on 2017-01-19.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Dane dane =  new Dane();

       /* Zagadnienie1 z1 = new Zagadnienie1(dane);
        Zagadnienie2A z2A = new Zagadnienie2A(dane);
        Zagadnienie2B z2B = new Zagadnienie2B(dane);
        Zagadnienie3A z3A = new Zagadnienie3A(dane);
        Zagadnienie3B z3B = new Zagadnienie3B(dane);
        Zagadnienie4 z4 = new Zagadnienie4(dane);

       SiecRekurencyjna hopfield = new SiecRekurencyjna(dane); */


        RzutowanieObrazka rzutowanie = new RzutowanieObrazka("C:\\Users\\Jakub\\Desktop\\Pong\\PSI_GCP02_zima_2016_2017_Jakub_Jaworski\\src\\main\\resources\\osemka.jpg");

        dane.ustawDaneWejsciowe(rzutowanie.wektorWejsciowy,1);

        Zagadnienie1 z1 = new Zagadnienie1(dane);
        Zagadnienie2A z2A = new Zagadnienie2A(dane);
        Zagadnienie2B z2B = new Zagadnienie2B(dane);
        Zagadnienie3A z3A = new Zagadnienie3A(dane);
        Zagadnienie3B z3B = new Zagadnienie3B(dane);
        Zagadnienie4 z4 = new Zagadnienie4(dane);
    }

}
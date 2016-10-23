package all;

import java.util.Random;

/**
 * Created by Jakub on 2016-10-23.
 */
public class Main {


    public static void main(String[] args) {
        Random r = new Random();

        double rx = r.nextDouble();
        double ry = r.nextDouble();

        NeuralCell cell1 = new NeuralCell() {

            @Override
            public double finalizeData(double membranePotencial) {
                return (1/(1+(Math.pow(Math.E,-membranePotencial*2.0))));
            }
        };

        cell1.addInput(2);

        cell1.setInputWeight(0,rx);
        cell1.setInputWeight(1,ry);


    }
}

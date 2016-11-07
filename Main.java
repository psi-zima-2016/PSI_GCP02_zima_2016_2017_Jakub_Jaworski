package all;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Jakub on 2016-10-23.
 */
public class Main {


    public static void main(String[] args) {
        Random r = new Random();

        double r1 = r.nextDouble();
        double r2 = r.nextDouble();
        double r3 = r.nextDouble();
        double r4 = r.nextDouble();
        double r5 = r.nextDouble();
        double r6 = r.nextDouble();
        NeuralCell MCP = new NeuralCell() {

            @Override
            public double finalizeData(double membranePotencial) {
              // return (1/(1+(Math.pow(Math.E,-membranePotencial*2.0))));
              if(membranePotencial<0) return 0;
                else return 1;
               // else return -1;
            }
        };

        MCP.addInput(6);

        MCP.setInputWeight(0,r1);
        MCP.setInputWeight(1,r2);
        MCP.setInputWeight(2,r3);
        MCP.setInputWeight(3,r4);
        MCP.setInputWeight(4,r5);
        MCP.setInputWeight(5,r6);

        //ten model bedzie sie zajmowal rozpoznawaniem ułozenia
        // dwóch pasujących znaków obok siebie w buforze z 5 znakow.

        double learningData1[]={0,0,0,0,0,0};
        double learningData2[]={1,0,0,0,0,0};
        double learningData3[]={0,1,0,0,0,0};
        double learningData4[]={0,0,1,0,0,0};
        double learningData5[]={0,0,0,1,0,0};
        double learningData6[]={0,0,0,0,1,0};
        double learningData7[]={1,1,0,0,0,1};
        double learningData8[]={1,0,1,0,0,0};
        double learningData9[]={1,0,0,1,0,0};
        double learningData10[]={1,0,0,0,1,0};
        double learningData11[]={1,1,1,0,0,1};
        double learningData12[]={1,1,0,1,0,1};
        double learningData13[]={1,1,0,0,1,1};
        double learningData14[]={1,1,1,1,0,1};
        double learningData15[]={1,1,1,0,1,1};
        double learningData16[]={1,1,1,1,1,1};
        double learningData17[]={0,1,1,0,0,1};
        double learningData18[]={0,0,1,1,0,1};
        double learningData19[]={0,0,0,1,1,1};
        double learningData20[]={1,0,1,0,1,0};
        double learningData21[]={1,0,1,1,1,1};
        double learningData22[]={0,1,1,1,0,1};
        double learningData23[]={1,1,0,1,1,1};
        double learningData24[]={1,0,1,1,0,1};
        double learningData25[]={1,0,0,1,1,1};
        double learningData26[]={0,0,1,0,1,0};
        double learningData27[]={0,1,0,0,1,0};
        double learningData28[]={0,1,0,1,0,0};
        double learningData29[]={0,1,1,0,1,1};
        double learningData30[]={0,1,0,1,1,1};
        double learningData31[]={0,1,1,1,1,1};
        double learningData32[]={0,0,1,1,1,1};

        List<double[]> listaTablic = new ArrayList<>();
        listaTablic.add(learningData1);
        listaTablic.add(learningData2);
        listaTablic.add(learningData3);
        listaTablic.add(learningData4);
        listaTablic.add(learningData5);
        listaTablic.add(learningData6);
        listaTablic.add(learningData7);
        listaTablic.add(learningData8);
        listaTablic.add(learningData9);
        listaTablic.add(learningData10);
        listaTablic.add(learningData11);
        listaTablic.add(learningData12);
        listaTablic.add(learningData13);
        listaTablic.add(learningData14);
        listaTablic.add(learningData15);
        listaTablic.add(learningData16);
        listaTablic.add(learningData17);
        listaTablic.add(learningData18);
        listaTablic.add(learningData19);
        listaTablic.add(learningData20);
        listaTablic.add(learningData21);
        listaTablic.add(learningData22);
        listaTablic.add(learningData23);
        listaTablic.add(learningData24);
        listaTablic.add(learningData25);
        listaTablic.add(learningData26);
        listaTablic.add(learningData27);
        listaTablic.add(learningData28);
        listaTablic.add(learningData29);
        listaTablic.add(learningData30);
        listaTablic.add(learningData31);
        listaTablic.add(learningData32);


        for(int i=0;i<32;i++)
        {
            for(int j=0;j<32;j++)
            {

                if(i!=j && Arrays.equals(listaTablic.get(i),listaTablic.get(j))) System.out.println(i+1 + " oraz " + (j+1) + " są takie same");
                else {
                    System.out.println("Wzorce się nie powtarzają!");
                    break;
                    }
            }
        }


        double sampleArray[][] = {learningData1,learningData2,learningData3,learningData4,learningData5,
                                 learningData6,learningData7,learningData8,learningData9,learningData10,
                                learningData11,learningData12,learningData13,learningData14,learningData15,
                                 learningData16,learningData17,learningData18,learningData19,learningData20,
                                 learningData21,learningData22,learningData23,learningData24,learningData25,
                                learningData26,learningData27,learningData28,learningData29,learningData30,
                                learningData31,learningData32};
        double errorArray[] = new double[32];
        double learningFactor = 0.1;

        //uczenie

        boolean  globalError = true;
        int maxIterations=100;
        int epoch =1;

        double errorSum =0;

        while(epoch<(maxIterations+1) && globalError == true) {

            System.out.println();
            System.out.println("Epoka: " + epoch);

            double tempInputWeight;

            for (int i = 0; i < 32; i++)
            {
                    for(int j=0;j<5;j++)
                    {
                        MCP.setInputData(j,sampleArray[i][j]);
                    }
                errorArray[i]=sampleArray[i][5] - MCP.finalizeData(MCP.getMembranePotential());


                for(int j=0;j<5;j++)
                {
                    tempInputWeight=MCP.getInputWeight(j);
                    //nanoszenie korekty na synapsy.
                    MCP.setInputWeight(j, tempInputWeight + learningFactor * errorArray[i] * MCP.getInputData(j));
                }

                //korekta biasu

                    MCP.setInputWeight(5,MCP.getInputWeight(5)+ learningFactor*errorArray[i]);


                System.out.println("Błąd dla wzorca nr." + (i+1) + " " + errorArray[i]);
                System.out.println();

                //wyswietlanie wag dla poszczegolnego wzorca


               /* for(int j=0;j<5;j++)
                {
                    System.out.println("waga dla wzorca nr." + j + ": " + MCP.getInputWeight(j) );
                }
                errorSum=0;*/
            }

            for(int j=0;j<32;j++) errorSum += errorArray[j];
            if(errorSum==0){
                globalError = false;
                System.out.println("Siec nauczona!");
            }

            epoch++;
        }

    }
}

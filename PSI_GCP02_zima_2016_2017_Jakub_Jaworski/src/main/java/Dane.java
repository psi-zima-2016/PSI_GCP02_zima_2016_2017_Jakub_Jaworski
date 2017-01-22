import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Jakub on 2017-01-19.
 */
public class Dane {

    DataSet daneUczace, daneWalidujace;
    DataSet daneWejsciowe;
    File plik;
    double[][] tablicaWejscU,tablicaWyjscU,
                tablicaWejscW,tablicaWyjscW;

    double[] dobre,zle;



    public Dane() throws FileNotFoundException {

        dobre = new double[1];
        zle = new double[1];

        daneWejsciowe = new DataSet(28,1);



        inicjalizujU();
        inicjalizujW();
    }

    public void ustawDaneWejsciowe(double[] input,int oczekiwanyOutput)
    {




        dobre[0]=1;
        zle[0]=0;

        if(oczekiwanyOutput==1) daneWejsciowe.addRow(input, dobre);
        else daneWejsciowe.addRow(input,zle);



    }

    public void inicjalizujU() throws FileNotFoundException {
        daneUczace = new DataSet(28,1);

        plik = new File("C:\\Users\\Jakub\\Desktop\\Pong\\PSI_GCP02_zima_2016_2017_Jakub_Jaworski\\src\\main\\resources\\learning_data.txt");

        Scanner IN = new Scanner(plik);
        tablicaWejscU = new double[30][28];
        tablicaWyjscU = new double[30][1];




        //dodawanie danych uczących

        for(int i=0;i<30;i++)
        {
            for(int j=0;j<28;j++)
            {
                tablicaWejscU[i][j]=IN.nextInt();
            }

                //wyswietlMacierz(tablicaWejsc);

            tablicaWyjscU[i][0] =IN.nextInt();

            daneUczace.addRow(new DataSetRow (tablicaWejscU[i],tablicaWyjscU[i]));
           // System.out.println(daneUczace);
        }



    }

    public void inicjalizujW() throws FileNotFoundException {
        daneWalidujace = new DataSet(28,1);

        plik = new File("C:\\Users\\Jakub\\Desktop\\Pong\\PSI_GCP02_zima_2016_2017_Jakub_Jaworski\\src\\main\\resources\\validating_data.txt");

        Scanner IN = new Scanner(plik);
        tablicaWejscW = new double[10][28];
        tablicaWyjscW = new double[10][1];

        //dodawanie danych walidujących

        for(int i=0;i<10;i++)
        {
            for(int j=0;j<28;j++)
            {
                tablicaWejscW[i][j]=IN.nextInt();
            }
           // System.out.println("Dane walidujace \n");

            //  wyswietlMacierz(tablicaWejsc);

            tablicaWyjscW[i][0] =IN.nextInt();

            daneWalidujace.addRow(new DataSetRow(tablicaWejscW[i],tablicaWyjscW[i]));
            //System.out.println(daneWalidujace);

        }




    }



    public void wyswietlMacierz(double [] tab,double [] wyjscie)
    {
        int[] tabInt = new int[tab.length];


        for (int i=0;i<tab.length;i++)
        {
            tabInt[i] = (int)tab[i];
        }
        System.out.println("Wejscie:");

        for(int k=0;k<28;k+=4)
        {
            for(int l=k;l<k+4;l++)
            {
                System.out.print(tabInt[l] + " ");
                if(l==27) System.out.print("\tWyjście:" + wyjscie[0]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public void wyswietlMacierz(double [] tab)
    {
        int[] tabInt = new int[tab.length];


        for (int i=0;i<tab.length;i++)
        {
            tabInt[i] = (int)tab[i];
        }
        System.out.println("Wejscie:");

        for(int k=0;k<28;k+=4)
        {
            for(int l=k;l<k+4;l++)
            {
                System.out.print(tabInt[l] + " ");

            }
            System.out.println();
        }
        System.out.println();
    }
}

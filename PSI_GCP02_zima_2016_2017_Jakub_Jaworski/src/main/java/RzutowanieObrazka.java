import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Arrays;

/**
 * Created by Jakub on 2017-01-21.
 */
public class RzutowanieObrazka {

    BufferedImage obrazek;
    String sciezka;


    int[][] tablicaKolorow;

    double[][] macierzWejsciowa, macierzZamalowania;
    double[] wektorWejsciowy;

    public RzutowanieObrazka(String sciezka)
    {
        this.sciezka = sciezka;

        macierzWejsciowa = new double[4][7];
        macierzZamalowania = new double[4][7];

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<7;j++)
            {
                macierzZamalowania[i][j]=0;
            }
        }

        try {
            obrazek = wczytajObrazek(sciezka);
        } catch (IOException e) {
            System.out.println("Nie znaleziono pliku obrazka!");
        }


        tablicaKolorow = new int [obrazek.getWidth()][obrazek.getHeight()];

        for(int i=0;i<obrazek.getWidth();i++)
        {
            for(int j=0;j<obrazek.getHeight();j++)
            {
                tablicaKolorow[i][j]=-obrazek.getRGB(i,j);
            }
        }


        int szerokoscElementu = obrazek.getWidth()/4;
        int wysokoscElementu = obrazek.getHeight()/7;

        System.out.println("Szerokosc jednego elementu: " + szerokoscElementu);
        System.out.println("Wysokosc jednego elementu: " + wysokoscElementu);

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<7;j++)
            {
                for(int k=(i*szerokoscElementu);k<((i+1)*szerokoscElementu);k++)
                {
                    for(int l=(j*wysokoscElementu);l<((j+1)*wysokoscElementu);l++)
                    {
                        if(tablicaKolorow[k][l]>1) macierzZamalowania[i][j]++;
                    }
                }
            }
        }

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<7;j++)
            {
                if(macierzZamalowania[i][j]>((szerokoscElementu*wysokoscElementu)/2)) macierzWejsciowa[i][j] = 1;
                else macierzWejsciowa[i][j] = 0;
            }
        }

        wektorWejsciowy = new double[28];

        for(int i=0;i<7;i++)
        {
            for(int j=0;j<4;j++)
            {
                wektorWejsciowy[i*4 + j]= macierzWejsciowa[j][i];
            }
        }

            //System.out.println(Arrays.toString(wektorWejsciowy));

        /*for(int i=0;i<7;i++)
        {
            for(int j=0;j<4;j++)
            {
                System.out.print((int)macierzWejsciowa[j][i] +  " ");


            }
            System.out.println();
        }*/
        System.out.println();

    }



    public BufferedImage wczytajObrazek(String sciezka) throws IOException {
        BufferedImage zmiennaObrazka;

        zmiennaObrazka= ImageIO.read(new File(sciezka));

        System.out.println("Wczytano obrazek!");
        System.out.println("Rozmiary obrazka: " + zmiennaObrazka.getWidth() + "px szerokości, "
                    + zmiennaObrazka.getHeight() + "px wysokości");

        return zmiennaObrazka;




    }
}

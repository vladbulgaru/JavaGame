package Maps;

import java.awt.*;
import Tiles.Tile;
import joc.GameWindow;


import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;


public class PadIntunMap {


    GameWindow gw;
    private int width; //latimea hartii in numar de dale
    private int height; //inaltimea hartii in numar de dale

    //private int [][] tiles;//referinta catre o matrice cu codurile dalelor ce vor construi harta
    public int [][] tiles;//referinta catre o matrice cu codurile dalelor ce vor construi harta

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }



    public PadIntunMap(){


        LoadWorld();
    }

    public void Update(){



    }




     public void Draw(Graphics g) {


        for(int y = 0; y < Tile.TILE_HEIGHT; y++) {

            for(int x = 0; x < Tile.TILE_WIDTH; x++) {

                GetTile(x,y).Draw(g,x*Tile.TILE_HEIGHT, y*Tile.TILE_WIDTH);
            }
        }
    }






    public Tile GetTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.treeTile;
        }
        return t;
    }





//LoadWorld() este functie de incarcare a hartii jocului; aici se poate genera/incarca din fisier harta. Momentan este incarcata static


    /*
    private void LoadWorld() {
        ///Se stabileste latimea hartii in numar de dale.
        width = 20;

        ///Se stabileste inaltimea hartii in numar de dale
        //height = 10;

        ///Se stabileste inaltimea hartii in numar de dale
        height = 20;

        ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];



        //Se incarca matricea cu coduri
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                tiles[x][y]=padure(y,x);
            }
        }
    }

     */





    //se incarca o harta dintr-un fisier text si se stocheaza codurile de dale intr-o matrice bidimensionala
    private void LoadWorld() {
        ///Se stabileste latimea hartii in numar de dale.
        width = 20;

        ///Se stabileste inaltimea hartii in numar de dale
        height = 20;

        ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];

        try {
            InputStream is = getClass().getResourceAsStream("/PadIntun.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            //2 buclre for: pentru iteratia pe inaltimea hartii(j) si latimea hartii(i)
            //citim datele coloana cu coloana din fisier si le stocam in matricea de coduri de dale "tiles"

            for (int j = 0; j < height; j++) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int i = 0; i < width; i++) {
                    int ide = Integer.parseInt(numbers[i]);
                    tiles[i][j] = ide;
                }
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Fisierul nu a fost gasit.");
            e.printStackTrace();
        }
    }





/*
//harta incarcata static; x->linia pe care se afla codul dalei de interes; y->coloana pe care se afla codul dalei de interes
private int padure(int x ,int y)
{
    ///Definire statica a matricei de coduri de dale.



    final int map[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 0, 0, 3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 0, 2, 2, 3, 5},
            {0, 3, 3, 0, 0, 0, 3, 0, 3, 0, 3, 0, 5, 0, 3, 0, 2, 0, 5, 0},
            {0, 2, 0, 0, 3, 3, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 2, 0, 3, 0},
            {0, 3, 0, 0, 3, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3, 2, 3, 0, 5, 0},
            {0, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 0, 5, 0, 0, 5, 0, 0, 3, 0},
            {0, 0, 0, 0, 2, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 3, 2, 2, 3, 0},
            {0, 3, 3, 3, 3, 0, 3, 3, 3, 0, 3, 0, 3, 0, 5, 0, 0, 0, 3, 0},
            {0, 3, 3, 0, 3, 0, 0, 0, 3, 0, 0, 0, 5, 0, 3, 3, 3, 0, 3, 0},
            {0, 0, 3, 3, 3, 0, 3, 3, 3, 0, 0, 0, 3, 0, 3, 0, 3, 0, 5, 0},
            {0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3, 3, 5, 3, 3, 0},
            {0, 3, 3, 3, 3, 3, 3, 0, 3, 0, 3, 0, 5, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 3, 0, 0, 3, 0, 3, 0, 2, 0, 3, 2, 2, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 3, 0, 0, 3, 0, 3, 0, 3, 0, 3, 3, 3, 3, 3, 3, 3, 0},
            {0, 3, 3, 3, 3, 3, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 0, 3, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 2, 0, 3, 0, 3, 2, 3, 0, 3, 0},
            {0, 3, 0, 2, 0, 3, 3, 3, 3, 3, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0},
            {0, 3, 3, 2, 3, 3, 0, 3, 0, 0, 5, 3, 3, 3, 3, 0, 3, 3, 3, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    return map[x][y];


    }
    */

}

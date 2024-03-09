package Maps;

import Tiles.Tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Munte {
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



    public Munte(){

        LoadWorld();
    }

    public void Update(){

    }


    public void Draw(Graphics g) {
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for(int y = 0; y < Tile.TILE_HEIGHT; y++) {

            for(int x = 0; x < Tile.TILE_WIDTH; x++) {

                GetTile(x,y).Draw(g,x*Tile.TILE_HEIGHT, y*Tile.TILE_WIDTH);
            }
        }
    }


    public Tile GetTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.zapada;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.copacZapada;
        }
        return t;
    }

//LoadWorld() este functie de incarcare a hartii jocului; aici se poate genera/incarca din fisier harta. Momentan este incarcata static



    //se incarca o harta dintr-un fisier text si se stocheaza codurile de dale intr-o matrice bidimensionala
    private void LoadWorld() {
        ///Se stabileste latimea hartii in numar de dale.
        width = 20;

        ///Se stabileste inaltimea hartii in numar de dale
        height = 20;

        ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];

        try {
            InputStream is = getClass().getResourceAsStream("/munte.txt");
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
}

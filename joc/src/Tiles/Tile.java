package Tiles;


import java.awt.*;
import java.awt.image.BufferedImage;

//Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
public class Tile {

   // public boolean collision = false;

    public static boolean collisionFlag = false;

    private static final int NO_TILES = 60;
    public static Tile[] tiles = new Tile[NO_TILES];       // Vector de referinte de tipuri de dale.

    /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
    /// o singura data in memorie




    //IsSolid() Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
    public boolean isSolid(){
        return false;
    }




    public static Tile grassTile        = new GrassTile(3);     /*!< Dala de tip iarba*/
    public static Tile treeTile         = new TreeTile(0);      /*!< Dala de tip copac*/

    public static Tile flowers          = new FlowerTile(5);  /* Dala de tip floare */
    public static Tile spini            = new SpiniTile(2);  /*Dala de tip spini*/

    public static Tile poarta           = new PoartaTile(8);  /* Dala de tip poarta (la coliziune se va trece la urmatorul nivel)*/
    public static Tile poarta2          = new Poarta2Tile(9); /* Dala de tip poarta (la coliziune se va trece la urmatorul nivel)*/




    public static Tile zapada           = new ZapadaTile(4);
    public static Tile copacZapada      = new CopacZapadaTile(1);
    public static Tile groapa           = new groapaTile(7);




    public static Tile pietre           = new pietreTile(11);
    public static Tile lava             = new LavaTile(13);
    public static Tile comoara          = new ComoaraTile(12);
    public static Tile negru            = new NegruTile(10);

    public static Tile capcana1         = new Capcana1Tile(18);
    public static Tile capcana2         = new Capcana2Tile(6);
    public static Tile capcana3         = new Capcana3Tile(20);
    public static Tile apa              = new ApaTile(21);







    public static final int TILE_WIDTH  = 40;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 40;                       /*!< Inaltimea unei dale.*/



    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/






    public Tile(BufferedImage image, int idd) {
        img = image;
        id = idd;

        tiles[id] = this;
    }







    //Update() actualizeaza proprietatile dalei
    public void Update() {

    }









    //Draw() deseneaza in fereastra dala
    /*
        param g Contextul grafic in care sa se realizeze desenarea
        param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */



        public void Draw(Graphics g, int x, int y) {

        /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }






    //GetId() Returneaza id-ul dalei.
    public int GetId() {
        return id;
    }

}

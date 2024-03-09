package Items;

import Tiles.Tile;
import joc.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

import Graphics.Assets;

import static Tiles.Tile.TILE_WIDTH;


//la coliziunea cu eroul, acestea ii vor scade viata eroului
public class Projectile extends Item{



    private final int PRO_WIDTH = 32;

    public int getProWidth(){
        return PRO_WIDTH;
    }
    private final int PRO_HEIGHT = 32;

    public int getProHeight(){
        return PRO_HEIGHT;
    }

    public int xPro;
    public int yPro;


    private int speedProjectile;



    //constructorul cu parametrii
    public Projectile(GameWindow gw, int xPro, int yPro, String directieProiectil){


        super(gw);


        solidPro = new Rectangle();
        solidPro.x = 10;
        solidPro.y = 14;
        solidPro.width = 24;
        solidPro.height = 18;

        this.xPro = xPro;
        this.yPro = yPro;


        setSolidDefaultX(solidPro.x);
        setSolidDefaultY(solidPro.y);


        speedProjectile=7;

        this.directieProiectil = directieProiectil;



        Assets.Init();
        getImage();

    }



//pozitiile proiectilelor se vor reseta atunci cand vor iesi de pe ecran
    public void UpdateLeft() {

        if(directieProiectil == "left") {

            if (xPro > gw.screenWidth) {
                xPro = gw.tileSize;
            } else{
                xPro += speedProjectile;
            }
        }

    }

    public void UpdateRight(){

        if (directieProiectil == "right") {
            if (xPro < 0) {
               xPro=gw.screenWidth-gw.tileSize*2;
            } else {
                xPro -= speedProjectile;
            }
        }

    }

//desenarea proiectilului
    public void Draw(Graphics2D g2){

        BufferedImage img = proiectil;
        g2.drawImage(img, xPro, yPro, PRO_WIDTH, PRO_HEIGHT, null);

    }


    //imaginea proiectilului
    public void getImage(){

        proiectil = Assets.projectile;

    }

    public boolean isSolid(){
        return false;
    }



}

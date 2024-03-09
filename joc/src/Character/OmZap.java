package Character;

import Items.Projectile;
import Tiles.Tile;
import joc.GameWindow;
import Graphics.Assets;


import java.awt.*;
import java.awt.image.BufferedImage;


public class OmZap extends Character{


    private static final int OMZ_WIDTH=40;
    private static final int OMZ_HEIGHT=40;
    public int getOmzWidth(){return OMZ_WIDTH;}
    public int getOmzHeight(){return OMZ_HEIGHT;}







   public OmZap(GameWindow gw, int x, int y){

        super(gw);

       setX(x);
       setY(y);

       solid = new Rectangle(0,0, OMZ_WIDTH, OMZ_HEIGHT);

       //setValues();
       //setLife(50);



       Assets.Init();
       getImage();


   }


   public void getImage(){
        omzap = Assets.omZapada;
   }





    public void Update(){
/*
        // Verificăm dacă a trecut suficient timp de la ultima aruncare de proiectil
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastProjectileTime >= PROJECTILE_DELAY) {
            throwProjectile();
            lastProjectileTime = currentTime;
        }
 */


        /*
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastThrowTime >= throwInterval) {
            throwProjectile();
            lastThrowTime = currentTime;
        }

         */



    }


    /*private void throwProjectile() {
        // Creează o instanță a proiectilului și lansează-l
        Projectile projectile = new Projectile(gw, getX(), getY());

        // Actualizează poziția proiectilului
        projectile.Update();
    }

     */

    public void Draw(Graphics2D g2){
        BufferedImage img = omzap;
        g2.drawImage(img, getX(), getY(), OMZ_WIDTH, OMZ_HEIGHT, null);


    }

    public boolean isSolid(){
        return true;
    }






}

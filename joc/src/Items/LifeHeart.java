package Items;

import joc.GameWindow;

import Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;


//obiect ce va creste viata eroului cu 50 HP la coliziune
public class LifeHeart extends Item{
    private final int LIFE_WIDTH  = 32;
    private final int LIFE_HEIGHT = 32;

    public int getLifeWidth(){
        return LIFE_WIDTH;
    }
    public int getLifeHeight(){
        return LIFE_HEIGHT;
    }

    public LifeHeart(GameWindow gw, int x, int y){

        super(gw);

        setX(x);
        setY(y);

        //solid = new Rectangle(0,0,LIFE_WIDTH, LIFE_HEIGHT);
        solid = new Rectangle(12,17,26,22);

        Assets.Init();

        getImage();
    }


    public void Update(){


    }
    public void Draw(Graphics2D g2 ){



        BufferedImage img = viata;
        g2.drawImage(img, getX(), getY(), LIFE_WIDTH, LIFE_HEIGHT, null);

    }


    public void getImage(){

        viata = Assets.lifeHeart;

    }

}

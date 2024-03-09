package Items;

import Graphics.Assets;
import joc.GameWindow;
import observer.Observer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;



/*
obiect ce va creste scorul eroului la coliziune
clasa diamond actioneaza ca subiect, in timp ce clasa Hero actioneaza ca observator
 */
public class Diamond extends Item {




    private final int DMD_WIDTH = 42;

    public int getDmdWidth(){
        return DMD_WIDTH;
    }
    private final int DMD_HEIGHT = 42;

    public int getDmdHeight(){
        return DMD_HEIGHT;
    }



    //subiectul are o lista de observatori; la coliziunea cu eroul, starea diamantului se schimba prin metoda Notify()
    private static List<Observer> observers = new ArrayList<>();

    public static void addObserver(Observer observer) {
        observers.add(observer);
    }

    public static void releaseObserver(Observer observer){
        observers.remove(observer);
    }




    public Diamond(GameWindow gw, int x, int y){
        super(gw);


       setX(x);
       setY(y);



        //solid = new Rectangle(0,0,DMD_WIDTH,DMD_HEIGHT);
        solid = new Rectangle(12,17,26,22);

        //SetValues();
        Assets.Init();
        getImage();
    }


    public void Update(){


    }
    public void Draw(Graphics2D g2 ){



        BufferedImage img = diamant;
        g2.drawImage(img, getX(), getY(), DMD_WIDTH, DMD_HEIGHT, null);

    }

    public void getImage(){
        diamant = Assets.diamond;
    }





}

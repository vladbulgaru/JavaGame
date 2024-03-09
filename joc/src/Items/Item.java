package Items;

import joc.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;


//implementeaza notiunea abstracta de obiect
//clasa parinte pentru clasele de tip Item
public abstract class Item {

    GameWindow gw;

    public String directieProiectil;


    //adaugam 2 variabile care vor memora valorile initiale ale lui x si y pentru ca vom schimba pe parcurs valorile x si y
    private int solidDefaultX;
    private int solidDefaultY;


    public int getSolidDefaultX(){
        return solidDefaultX;
    }
    public int getSolidDefaultY(){
        return solidDefaultY;
    }

    public void setSolidDefaultX(int solidDefaultX){
        this.solidDefaultX = solidDefaultX;
    }
    public void setSolidDefaultY(int solidDefaultY){
        this.solidDefaultY = solidDefaultY;
    }




    //vom creea un patrat invizibil pentru coliziune
    public Rectangle solid;
    public Rectangle solidPro;



    public BufferedImage diamant;
    public BufferedImage viata;

    public BufferedImage proiectil;



    //coordonatele obiectelor de tip diamant
    private int x, y;


    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }


    public Item(GameWindow gw){
        this.gw = gw;
    }




}

package Character;

import joc.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;



//clasa parinte pentru clasa Hero si celelalte clase de tip character
//defineste notiunea abstracta de caracter
public abstract class Character {

    GameWindow gw;


    private int Life;
    public int getLife(){
        return Life;
    }

    public void setLife(int Life){
        this.Life = Life;
    }


    //Coordonatele caracterului de pe harta

    private int x,y;

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }





    //setam viteza implicita a jucatorului
    private int speed;

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }




    //imaginile eroului in miscare si cand se lupta
    public BufferedImage up1, up2, left1, left2, right1, right2, down1, down2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2,attackLeft1, attackLeft2, attackRight1, attackRight2;

    //imaginea lupului in miscare
    public BufferedImage lupUp1, lupUp2, lupDown1, lupDown2, lupLeft1, lupLeft2, lupRight1, lupRight2, lupIdle;


    //imaginea NPC-ului
    public BufferedImage pp;

    //imaginea NPC-ului
    public BufferedImage capra;

    //imaginea inamicului
    public BufferedImage omzap;








    //pentru a crea animatia caracterului(iluzia de a putea merge), vom folosi variabilele de mai jos
    public int cnt=0;
    public int imgAnim = 1;
    public String direction;



    //vom creea un patrat invizibil pentru coliziune
    public Rectangle solid;
    public Rectangle attackAreaLup;
    public Rectangle attackAreaHit = new Rectangle(0,0,0,0);


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


    public boolean collisionFlag = false;

    public boolean muscaturaLup = false;
    public boolean lovitura = false;
    public int lovituraCnt = 0;
    public int muscaturaCnt=0;






    public Character(GameWindow gw){
        this.gw = gw;
    }


}

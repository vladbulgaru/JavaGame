package Character;

import Tiles.Tile;
import joc.GameWindow;

import Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;


//monstru ce se va putea misca si ataca eroul atunci cand eroul se apropie de lup
public class Lup extends Character{

    private final int LUP_WIDTH=80;
    private final int LUP_HEIGHT=64;

    public int attackFlag=0;



    public int getLUP_WIDTH(){  return LUP_WIDTH;}
    public int getLUP_HEIGHT(){
        return LUP_HEIGHT;
    }


    public Lup(GameWindow gw){
        super(gw);

        setLife(200);

        //solid = new Rectangle(18,15, 14, 20);
        solid = new Rectangle(18,15, 42, 37);
        attackAreaLup = new Rectangle(0,0,gw.screenWidth,gw.tileSize*14);



        setSolidDefaultX(solid.x);
        setSolidDefaultY(solid.y);

        Spawn(Tile.TILE_WIDTH*9, Tile.TILE_HEIGHT * 3, 3, "idle");
        Assets.Init();
        getImage();
    }

    public void Spawn(int x, int y, int speed, String direction) {

        setX(x);
        setY(y);

        setSpeed(speed);
        this.direction = direction;


    }

    public void getImage(){
                lupIdle = Assets.lupIdle;
                lupUp1 = Assets.lupUp1;
                lupUp2 = Assets.lupUp2;
                lupDown1 = Assets.lupDown1;
                lupDown2 = Assets.lupDown2;
                lupLeft1 = Assets.lupLeft1;
                lupLeft2 = Assets.lupLeft2;
                lupRight1 = Assets.lupRight1;
                lupRight2 = Assets.lupRight2;
    }


    //deplasarea lupului; cand eroul intra intr-un patrat invizibil, lupul il va ataca pe erou
    public void Update(){



        if (attackAreaLup.contains(gw.hero.getX(), gw.hero.getY())) {

            int heroX = gw.hero.getX();
            int heroY = gw.hero.getY();

            int lupX = getX();
            int lupY = getY();

            int newX = getX();
            int newY = getY();


            if (getX() < gw.hero.getX()) {

                // Lupul se deplasează spre dreapta
                direction = "right";

            } else if (getX() > gw.hero.getX()) {

                // Lupul se deplasează spre stânga
                direction = "left";
            }

            if (getY() < gw.hero.getY()) {

                // Lupul se deplasează în jos
                direction = "down";

            } else if (getY() > gw.hero.getY()) {

                // Lupul se deplasează în sus

                direction = "up";
            }
        }
        else{
            direction = "idle";
        }






        //se verifica daca exista coliziuni
        collisionFlag = false;

        if(gw.mapID == 2){
            gw.collision.collisionTileLevel3Lup(this);
            gw.collision.HeroCollision(gw.hero);
        }





        if(!collisionFlag){

            switch (direction) {
                case "up":
                    //y -= getSpeed();
                    setY(getY()-getSpeed());

                    break;
                case "down":
                    //y += getSpeed();
                    setY(getY()+getSpeed());

                    break;
                case "left":
                    // x -= getSpeed();
                    setX(getX()-getSpeed());

                    break;
                case  "right":
                    //x += getSpeed();
                    setX(getX()+getSpeed());

                    break;
            }

        }

        cnt++;

        //imaginea lupului se schimba la fiecare 15 frame-uri
        if(cnt > 15){
            if(imgAnim == 1){
                imgAnim = 2;
            }
            else if(imgAnim == 2){
                imgAnim = 1;
            }
            cnt=0;
        }
    }


    public void Draw(Graphics2D g2){


        BufferedImage image = null;

        if(direction == "idle"){
            image = lupIdle;
        }

        if(direction == "up"){
            if(imgAnim == 1) {
                image = lupUp1;
            }
            if (imgAnim == 2) {
                image = lupUp2;
            }
        }
        if(direction == "down"){
            if(imgAnim == 1) {
                image = lupDown1;
            }
            if(imgAnim == 2){
                image = lupDown2;
            }
        }
        if(direction == "left"){
            if(imgAnim == 1){
                image=lupLeft1;
            }
            if(imgAnim == 2){
                image=lupLeft2;
            }

        }
        if(direction == "right"){
            if(imgAnim == 1) {
                image = lupRight1;
            }
            if(imgAnim == 2){
                image = lupRight2;
            }
        }


        //g2.setColor(Color.green);
        //g2.fillRect(attackArea.x,attackArea.y,attackArea.width,attackArea.height);


        g2.drawImage(image, getX(), getY(), LUP_WIDTH, LUP_HEIGHT, null);
    }

}

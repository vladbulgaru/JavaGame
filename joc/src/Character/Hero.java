package Character;

import java.awt.image.BufferedImage;


import Tiles.Tile;
import joc.GameWindow;
import joc.KeyManager;
import Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import observer.Observer;
import java.util.List;
import java.util.Objects;



//clasa hero actioneaza ca observator pentru subiectul Diamond
public class Hero extends Character implements Observer{

    private final int HERO_WIDTH=32;
    private final int HERO_HEIGHT=32;



    public int getHeroWidth(){
        return HERO_WIDTH;
    }
    public int getHeroHeight(){
        return HERO_HEIGHT;
    }





    private int heroScore = 0;

    public int getHeroScore(){
        return heroScore;
    }
    public void setHeroScore(int heroScore){
        this.heroScore = heroScore;
    }



    private boolean attacking = false;
    private int attackCounter = 0;





   //GameWindow gw;
   KeyManager keyManager;




//constructor cu parametrii
    public Hero(GameWindow gw, KeyManager keyManager){

        //apelam constructorul din Character;
        super(gw);

        //this.gw=gw;
        this.keyManager=keyManager;




        solid = new Rectangle(8,16, (HERO_WIDTH/8)*4, (HERO_HEIGHT/8)*4);



        setSolidDefaultX(solid.x);
        setSolidDefaultY(solid.y);


        attackAreaHit.width  = 36;
        attackAreaHit.height = 36;


        setLife(100);
        Spawn(Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 15, 3, "down");




        Assets.Init();
        getImage();
        getAttackImage();

    }



    //coordonatele, viteza si directia eroului atunci cand se spawneaza
    public void Spawn(int x, int y, int speed, String direction) {

            setX(x);
            setY(y);

            setSpeed(speed);
            this.direction = direction;


    }

//imaginile eroului cand merge
    public void getImage(){

            up1    = Assets.heroUp1;
            up2    = Assets.heroUp2;
            down1  = Assets.heroDown1;
            down2  = Assets.heroDown2;
            left1  = Assets.heroLeft1;
            left2  = Assets.heroLeft2;
            right1 = Assets.heroRight1;
            right2 = Assets.heroRight2;

    }


    //imaginile eroului cand ataca
    public void getAttackImage(){

        attackUp1 = Assets.heroAttackUp1;
        attackUp2 = Assets.heroAttackUp2;
        attackDown1 = Assets.heroAttackDown1;
        attackDown2 = Assets.heroAttackDown2;
        attackLeft1 = Assets.heroAttackLeft1;
        attackLeft2 = Assets.heroAttackLeft2;
        attackRight1 = Assets.heroAttackRight1;
        attackRight2 = Assets.heroAttackRight2;
    }


    //metoda pentru animatia eroului atunci cand ataca
    public void heroAttack() {
        if (!attacking) {
            attacking = true;
            attackCounter = 0;
        }

        attackCounter++;

        if (attackCounter <= 5) {
            imgAnim = 1;
        } else if (attackCounter <= 25) {
            imgAnim = 2;
        } else {
            imgAnim = 1;
            attackCounter = 0;
            attacking = false;
        }
    }


    //metoda ce notifica observatorii atunci cand starea subiectului(clasa Diamond) se schimba
    public void Notify(){

        System.out.println("starea diamantului s-a schimbat. Eroul a fost notificat prin schimbarea starii diamantului(cresterea scorului).");
        gw.hero.setHeroScore(gw.hero.getHeroScore()+1);

    }





    //aceasta metoda este apelata de 60 de ori pe secunda
    public void Update(){



        if(!keyManager.up || !keyManager.down || !keyManager.left || !keyManager.right){
            direction = "down";
        }




        if(keyManager.up || keyManager.down || keyManager.left || keyManager.right) {


            if (keyManager.up) {
                direction = "up";
                //y -= speed;

            } else if (keyManager.down) {
                direction = "down";
                //y += speed;
            } else if (keyManager.left) {
                direction = "left";
                //x-=speed;
            } else {
                direction = "right";
                //x+=speed;

            }


            if (keyManager.attack) {
                heroAttack();
            }


            //se verifica daca exista coliziuni
            collisionFlag = false;

            if (gw.mapID == 0) {
                gw.collision.collisionTileLevel1(this);
                gw.collision.PPcollision(gw.pazPadNPC);
            }


            if (gw.mapID == 1) {
                gw.collision.collisionTileLevel2(this);
                gw.collision.GoatCollision(gw.goatNPC);
                //gw.collision.OmZap1Collision(gw.omZap1);
                for (int i = 0; i < gw.omZaps.length; i++) {
                    if (gw.omZaps[i] != null) {
                        gw.collision.OmZapsCollision(gw.omZaps);
                    }
                }


            }


            if (gw.mapID == 2) {
                if(this != null)
                    gw.collision.collisionTileLevel3(this);
                if(gw.lup!=null)
                    gw.collision.LupCollision(gw.lup);

                if(gw.collision.coliziuneDiamant5(gw.diamond5)){
                    Notify();
                    gw.diamond5 = null;
                }
            }


            //daca coliziunea este falsa, eroul se poate misca
            //if(!collisionFlag && !keyManager.attack){
            if (!collisionFlag) {

                switch (direction) {
                    case "up":
                        //y -= getSpeed();
                        setY(getY() - getSpeed());

                        break;
                    case "down":
                        //y += getSpeed();
                        setY(getY() + getSpeed());

                        break;
                    case "left":
                        // x -= getSpeed();
                        setX(getX() - getSpeed());

                        break;
                    case "right":
                        //x += getSpeed();
                        setX(getX() + getSpeed());

                        break;
                }

            }



            cnt++;

            if (!attacking){
                //imaginea eroului se schimba la fiecare 15 frame-uri
                if (cnt > 15) {
                    if (imgAnim == 1) {
                        imgAnim = 2;
                    } else if (imgAnim == 2) {
                        imgAnim = 1;
                    }
                    cnt = 0;

                }
        }else {
                attacking = true;
                cnt++;
                if (cnt <= 25) {
                    imgAnim = 1;
                } else if (cnt <= 50) {
                    imgAnim = 2;

                    //salvam coordonatele suprafetei de lovire
                    int toporX = getX();
                    int toporY = getY();
                    int toporAreaWidth = solid.width;
                    int toporAreaHeight = solid.height;

                    //setam coordonatele eroului pentru suprafata de lovire

                    if(direction == "up"){
                        setY(getY()-attackAreaHit.height);
                    }
                    if(direction == "down"){
                        setY(getY()+attackAreaHit.height);
                    }
                    if(direction == "left"){
                        setX(getX()-attackAreaHit.height);
                    }
                    if(direction == "right"){
                        setX(getX()+attackAreaHit.height);
                    }


                    //schimbam marimea lui solid; attackareahit devine solid
                    solid.width = attackAreaHit.width;
                    solid.height = attackAreaHit.height;

                    //verificam coliziune

                    if(gw.collision.lupColiziuneFlag == 1 && gw.lup!=null){
                        //System.out.println("hit!");
                        //gw.lup.setLife(getLife()-2);
                        if(!lovitura){
                            gw.lup.setLife(gw.lup.getLife()-25);
                            lovitura = true;
                            if(gw.lup.getLife() <= 0){
                                System.out.println("ai invins lupul!");
                                gw.lup = null;
                            }
                        }
                    }

                    //dupa coliziune, dam datele initiale
                    setX(toporX);
                    setY(toporY);
                    solid.width = toporAreaWidth;
                    solid.height = toporAreaHeight;

                } else {
                    imgAnim = 1;
                    cnt = 0;
                    keyManager.attack = false;
                    attacking = false;
                }
            }

        }



        //muscatura lupului
        if(muscaturaLup){
            muscaturaCnt++;
            if(muscaturaCnt > 60){
                muscaturaLup = false;
                muscaturaCnt = 0;
            }
        }

        //lovitura hero
        if(lovitura){
            lovituraCnt++;
            if(lovituraCnt>60){
                lovitura=false;
                lovituraCnt = 0;
            }
        }





        if(gw.collision.coliziuneDiamant1(gw.diamond1)){
            Notify();
            gw.diamond1 = null;


        }

        if(gw.collision.coliziuneDiamant2(gw.diamond2)){
            Notify();
            gw.diamond2 = null;

        }



        if(gw.collision.coliziuneViata1(gw.lifeHeart1)){
            gw.lifeHeart1 = null;
            setLife(getLife()+50);
        }

        if(gw.mapID == 1) {

            if (gw.collision.coliziuneViata2(gw.lifeHeart2)) {
                gw.lifeHeart2 = null;
                setLife(getLife()+50);
            }



            if(gw.collision.coliziuneDiamant3(gw.diamond3)){
                Notify();
                gw.diamond3 = null;

            }

            if(gw.collision.coliziuneDiamant4(gw.diamond4)){
                Notify();
                gw.diamond4 = null;

            }


            if(gw.collision.coliziuneProiectilStanga(gw.projectilesLeft)){
                setLife(getLife()-2);
                if (gw.hero.getLife() <= 0) {
                    System.out.println("Ai pierdut!");
                    System.exit(0);
                }
            }

            if(gw.collision.coliziuneProiectilDreapta(gw.projectilesRight)){
                setLife(getLife()-2);
                if (gw.hero.getLife() <= 0) {
                    System.out.println("Ai pierdut!");
                    System.exit(0);
                }
            }


        }


    }



    //desenam imaginile eroului
    public void Draw(Graphics2D g2){

        BufferedImage image = null;

        if(Objects.equals(direction, "up")){
            if(!attacking){
                if(imgAnim == 1){image = up1;}
                else if(imgAnim == 2){image = up2;}
            }else{
                if(imgAnim == 1){image = attackUp1;}
                else if(imgAnim == 2){image = attackUp2;}
            }
        }else if(Objects.equals(direction, "down")){
            if(!attacking){
                if(imgAnim == 1){image = down1;}
                else if(imgAnim == 2){image = down2;}
            }else{
                if(imgAnim == 1){image = attackDown1;}
                else if(imgAnim == 2){image = attackDown2;}
            }
        }else if(Objects.equals(direction, "left")){
            if(!attacking){
                if(imgAnim == 1){image = left1;}
                else if(imgAnim == 2){image = left2;}
            }else{
                if(imgAnim == 1){image = attackLeft1;}
                else if(imgAnim == 2){image = attackLeft2;}
            }
        }else if(Objects.equals(direction, "right")){
            if(!attacking){
                if(imgAnim == 1){image = right1;}
                else if(imgAnim == 2){image = right2;}
            }else{
                if(imgAnim == 1){image = attackRight1;}
                else if(imgAnim == 2){image = attackRight2;}
            }
        }

        g2.drawImage(image, getX(), getY(), HERO_WIDTH, HERO_HEIGHT, null);

    }



}
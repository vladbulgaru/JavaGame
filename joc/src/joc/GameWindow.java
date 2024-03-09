package joc;

import Character.Hero;

import Character.PazPadNPC;
import Character.GoatNPC;
import Character.OmZap;
import Character.Lup;


import Items.Diamond;
import Items.Item;
import Items.LifeHeart;
import Items.Projectile;
import Maps.Munte;
import Maps.PadIntunMap;
import Maps.Pestera;
import Tiles.Tile;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




//in aceasta clasa vom instantia toate obiectele jocului
public class GameWindow extends JPanel implements Runnable {

    public final int tileSize = 40;






    //setam numarul de linii si coloane ce vor fi afisate pe ecran

    public final int col = 20;

    public final int lin = 20;



    //setam marimea ecranului jocului in pixeli




    public final int screenWidth = tileSize * col;
    public final int screenHeight = tileSize * lin;



    //public final int screenWidth = 480;
    //public final int screenHeight = 480;



    public int mapID = 0;



    public static int updateScor;
    public static int updateViata;

    public int hallOfFameFlag=0;




    //variabila statica pentru a stoca instanta singleton a clasei GameWindow
    private static GameWindow instance;





    public KeyManager keyManager = new KeyManager();
    Thread thread; //fir de executie; game clock; afiseaza pe ecran 60 de imagini pe secunda
    public Collision collision = new Collision(this);



    public Hero hero = new Hero(this, keyManager);
    public Lup lup = new Lup(this);




    public PadIntunMap padIntunMap = new PadIntunMap();
    public Munte munte = new Munte();
    public Pestera pestera = new Pestera();



    public PazPadNPC pazPadNPC = new PazPadNPC(this);
    public GoatNPC goatNPC = new GoatNPC(this);




    //vector de obiecte de tipul OmZap
    public OmZap[] omZaps = createOmZap();

    public OmZap[] createOmZap(){
        OmZap[] omZaps = new OmZap[10];
        omZaps[0] = new OmZap(this, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*4);
        omZaps[1] = new OmZap(this, Tile.TILE_WIDTH*18, Tile.TILE_HEIGHT*5);
        omZaps[2] = new OmZap(this, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*8);
        omZaps[3] = new OmZap(this, Tile.TILE_WIDTH*18, Tile.TILE_HEIGHT*7);
        omZaps[4] = new OmZap(this, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*10);
        omZaps[5] = new OmZap(this, Tile.TILE_WIDTH*18, Tile.TILE_HEIGHT*11);
        omZaps[6] = new OmZap(this, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*12);
        omZaps[7] = new OmZap(this, Tile.TILE_WIDTH*18, Tile.TILE_HEIGHT*13);
        omZaps[8] = new OmZap(this, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*15);
        omZaps[9] = new OmZap(this, Tile.TILE_WIDTH*18, Tile.TILE_HEIGHT*16);
        return omZaps;
    }


    public static void UpdateAllOmZ(OmZap[] omZaps){
        for(int i=0; i<omZaps.length; i++){
            omZaps[i].Update();
        }
    }

    public static void DrawAllOmZ(Graphics2D g2, OmZap[] omZaps) {
        for (int i = 0; i < omZaps.length; i++) {
            omZaps[i].Draw(g2);
        }
    }



    //vector de obiecte de tipul Projectile
    public Projectile[] projectilesRight = createProjectilesRight();
    public Projectile[] projectilesLeft = createProjectilesLeft();

    public Projectile[] createProjectilesRight(){

        projectilesRight = new Projectile[5];
        projectilesRight[0] = new Projectile(this, Tile.TILE_WIDTH*18, Tile.TILE_HEIGHT*5, "right");
        projectilesRight[1] = new Projectile(this, Tile.TILE_WIDTH*18, Tile.TILE_HEIGHT*7, "right");
        projectilesRight[2] = new Projectile(this, Tile.TILE_WIDTH*18, Tile.TILE_HEIGHT*11, "right");
        projectilesRight[3] = new Projectile(this, Tile.TILE_WIDTH*18, Tile.TILE_HEIGHT*13, "right");
        projectilesRight[4] = new Projectile(this, Tile.TILE_WIDTH*18, Tile.TILE_HEIGHT*16, "right");


        return projectilesRight;
    }

    public Projectile[] createProjectilesLeft(){

        projectilesLeft = new Projectile[5];
        projectilesLeft[0] = new Projectile(this, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*4, "left");
        projectilesLeft[1] = new Projectile(this, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*8, "left");
        projectilesLeft[2] = new Projectile(this, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*10, "left");
        projectilesLeft[3] = new Projectile(this, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*12, "left");
        projectilesLeft[4] = new Projectile(this, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*15, "left");

        return projectilesLeft;
    }


    public static void UpdateProjectilesRight(Projectile[] projectilesRight){

        for(int i=0; i<projectilesRight.length; i++){
            if(projectilesRight[i]!=null)
                projectilesRight[i].UpdateRight();
        }
    }

    public static void UpdateProjectilesLeft(Projectile[] projectilesLeft){
        //System.out.println(projectilesLeft[0].xPro + " ");
        for(int i=0; i<projectilesLeft.length; i++){
            if(projectilesLeft[i]!=null)
                projectilesLeft[i].UpdateLeft();
        }

    }


    public static void DrawProjectilesRight(Graphics2D g2, Projectile[] projectilesRight) {
        //System.out.println(projectilesRight[0].xPro + " ");
        for (int i = 0; i < projectilesRight.length; i++) {
            if(projectilesRight[i]!=null)
                projectilesRight[i].Draw(g2);
        }
    }

    public static void DrawProjectilesLeft(Graphics2D g2, Projectile[] projectilesLeft) {
        for (int i = 0; i < projectilesLeft.length; i++) {
            if(projectilesLeft[i]!=null)
                projectilesLeft[i].Draw(g2);
        }
    }








        public Diamond diamond1 = new Diamond(this, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT * 1);
        public Diamond diamond2 = new Diamond(this, Tile.TILE_WIDTH * 10, Tile.TILE_HEIGHT * 15);
        public Diamond diamond3 = new Diamond(this, Tile.TILE_WIDTH*18, Tile.TILE_HEIGHT*1);
        public Diamond diamond4 = new Diamond(this, Tile.TILE_WIDTH*2, Tile.TILE_HEIGHT*16);
        public Diamond diamond5 = new Diamond(this,Tile.TILE_WIDTH*13, Tile.TILE_HEIGHT*18);









    public LifeHeart lifeHeart1 = new LifeHeart(this, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*18);
    public LifeHeart lifeHeart2 = new LifeHeart(this, Tile.TILE_WIDTH*14, Tile.TILE_HEIGHT);






    long oldTime = System.nanoTime();   // Retine timpul in nanosecunde aferent frame-ului anterior
    long curentTime;                    // Retine timpul curent de executie

    /// Apelul functiilor update() & repaint() trebuie realizat la fiecare 16.7 ms
    /// sau mai bine spus de 60 ori pe secunda.

    final double framesPerSecond   = 60; // Constanta intreaga initializata cu numarul de frame-uri pe secunda.
    final double timeFrame      = 1000000000 / framesPerSecond; // Durata unui frame in nanosecunde.


    // constructor privat pentru a preveni instantierea directa a clasei GameWindow
        private GameWindow(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyManager);
        this.setFocusable(true);
    }

    //functie publica pentru a obtine instanta Singleton a clasei GameWindow
    public static synchronized GameWindow getInstance() {
        if (instance == null) {
            instance = new GameWindow();
        }
        return instance;
    }



    public void startGame(){
        thread = new Thread(this);//am instantiat thread-ul
        thread.start();//cand pornim thread, automat se apeleaza metoda run
    }






    //vom crea game loop in metoda run
    @Override
    public void run(){
        // in while vom scrie 2 metode: update(actualizeaza pozitia caracterului) si repaint(afiseaza pe ecran informatiile actualizate)
        while(thread!=null){

            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            if((curentTime-oldTime)>timeFrame){
                Update();
                repaint();
                oldTime=curentTime;
            }

        }

    }


//in functie de ID-ul hartilor vom reactualiza pozitia eroului si obiectelor in scena

    public void Update(){

            if(mapID == 0) {

                if(padIntunMap!= null)
                    padIntunMap.Update();

                if (pazPadNPC != null)
                    pazPadNPC.Update();


                if (diamond1 != null)
                    diamond1.Update();
                if (diamond2 != null)
                    diamond2.Update();


                if (lifeHeart1 != null)
                    lifeHeart1.Update();

            }


        if(mapID == 1){


            if(munte!= null)
                munte.Update();

            if (lifeHeart2 != null)
                lifeHeart2.Update();

            if(diamond3 != null)
                diamond3.Update();

            if(diamond4 != null)
                diamond4.Update();

            if(goatNPC != null)
                goatNPC.Update();



            UpdateAllOmZ(omZaps);

            UpdateProjectilesLeft(projectilesLeft);
            UpdateProjectilesRight(projectilesRight);



        }

        if(mapID == 2){


            if(pestera!=null)
                pestera.Update();

            if(lup!=null)
                lup.Update();

            if(diamond5!=null)
                diamond5.Update();


        }

        if(hero!=null)
            hero.Update();

        if(keyManager.quit){
            System.out.println("Ai inchis jocul!");
            System.exit(0);
        }


    }



    //metoda paintComponent deseneaza eroul cu pozitiile x si y actualizate, harta, npc, ...
    //in functie de ID-ul hartilor vom desena eroul si obiectele
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if(mapID == 0) {

            if(padIntunMap!= null)
                padIntunMap.Draw(g2);

            if (pazPadNPC != null)
                pazPadNPC.Draw(g2);

            if (diamond1 != null)
                diamond1.Draw(g2);
            if (diamond2 != null)
                diamond2.Draw(g2);

            if (lifeHeart1 != null)
                lifeHeart1.Draw(g2);
        }


        if(mapID == 1){
            if(munte!= null)
                munte.Draw(g2);

            if(lifeHeart2 != null)
                lifeHeart2.Draw(g2);

            if(diamond3 != null)
                diamond3.Draw(g2);

            if(diamond4 != null)
                diamond4.Draw(g2);

            if(goatNPC != null)
                goatNPC.Draw(g2);


            DrawAllOmZ(g2, omZaps);

            DrawProjectilesRight(g2, projectilesRight);
            DrawProjectilesLeft(g2, projectilesLeft);






        }

        if(mapID == 2){

            if(pestera!=null)
                pestera.Draw(g2);

            if(lup!=null)
                lup.Draw(g2);

            if(diamond5!=null)
                diamond5.Draw(g2);

        }

        if(hero!=null)
            hero.Draw(g2);

        //g.setColor(Color.BLUE);
        //g.fillRect(0, 0, getWidth(), getHeight());

        //hero.Draw(g2,hero.cameraHeroX, hero.cameraHeroY);
        //camera.draw(g2,hero);




        if(mapID == 0 && !keyManager.info){


            int x = tileSize * 4;
            int y = getHeight() - tileSize * 2;
            int width = screenWidth - (tileSize * 8);
            int height = tileSize * 2;




            Color c = new Color(90, 0, 0, 150);
            g2.setColor(c);
            g2.fillRoundRect(x, y, width, height, 50, 50);

            c = new Color(255, 255, 255);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

            x += tileSize;
            y += tileSize;
            g2.drawString("SHIFT-> GUI W->sus A->stanga S->jos D->dreapta Q->iesire joc SPACE->lovire" , x, y);




        }





        // daca tasta shift este apasata, ne va arata pe ecran informatiile eroului(viata, scor, ....)
        // si dialogurile cu NPC-urile
         if(keyManager.info && hero!=null) {





            //vom desena un panou cu informatiile eroului(viata)


            //int xViata = tileSize * 0;
            int xViata = 0;
            int yViata = tileSize / 6;
            int widthViata = screenWidth - (tileSize * 16);
            int heightViata = tileSize * 2;


            Color c = new Color(255, 0, 0, 150);
            g2.setColor(c);
            g2.fillRoundRect(xViata, yViata, widthViata, heightViata, 50, 50);

            c = new Color(255, 255, 255);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(xViata + 5, yViata + 5, widthViata - 10, heightViata - 10, 25, 25);

            xViata += tileSize;
            yViata += tileSize;
            g2.drawString("   Viata: " + hero.getLife(), xViata, yViata);


            //vom desena un panou cu informatiile eroului(scor)

            int xScore = tileSize * 4;
            int yScore = tileSize / 6;
            int widthScore = screenWidth - (tileSize * 16);
            int heightScore = tileSize * 2;


            Color d = new Color(0, 0, 255, 150);
            g2.setColor(d);
            g2.fillRoundRect(xScore, yScore, widthScore, heightScore, 35, 35);


            d = new Color(255, 255, 255);
            g2.setColor(d);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(xScore + 5, yScore + 5, widthScore - 10, heightScore - 10, 25, 25);

            xScore += tileSize;
            yScore += tileSize;
            g2.drawString("   Scor: " + hero.getHeroScore(), xScore, yScore);


            //afisam dialogul eroului cu paznicul padurii
            if (mapID == 0) {
                if (collision.dialogFlagPP == 1) {

                    int xDialog = tileSize * 8;
                    int yDialog = tileSize / 6;
                    int widthDialog = screenWidth - (tileSize * 8);
                    int heightDialog = tileSize * 2;


                    Color f = new Color(0, 0, 0, 200);
                    g2.setColor(f);
                    g2.fillRoundRect(xDialog, yDialog, widthDialog, heightDialog, 50, 50);


                    f = new Color(255, 255, 255);
                    g2.setColor(f);
                    g2.setStroke(new BasicStroke(5));
                    g2.drawRoundRect(xDialog + 5, yDialog + 5, widthDialog - 10, heightDialog - 10, 25, 25);


                    xDialog += tileSize;
                    yDialog += tileSize;
                    //g2.drawString("PP:Pana aici te-a adus harta. Daca vrei sa iesi de aici, urmareste florile...", xDialog, yDialog);
                    //g2.drawString("PP:Ultimul om care a cautat comoara nu s-a mai intors...Ia-te dupa flori...Bafta", xDialog, yDialog);
                    g2.drawString("Paznicul Padurii:Urmeaza florile daca vrei sa ajungi la comoara...", xDialog, yDialog);


                }
            }

            if (mapID == 0) {
                if (collision.poartaFlag == 1) {

                    int xNivel1 = 0;
                    int yNivel1 = screenHeight - (tileSize * 2 + tileSize / 6);
                    int widthNivel1 = tileSize * 10;
                    int heightNivel1 = tileSize * 2;


                    Color f = new Color(255, 165, 0, 150);
                    g2.setColor(f);
                    g2.fillRoundRect(xNivel1, yNivel1, widthNivel1, heightNivel1, 50, 50);


                    f = new Color(255, 255, 255);
                    g2.setColor(f);
                    g2.setStroke(new BasicStroke(5));
                    g2.drawRoundRect(xNivel1 + 5, yNivel1 + 5, widthNivel1 - 10, heightNivel1 - 10, 25, 25);


                    xNivel1 += tileSize;
                    yNivel1 += tileSize;
                    //g2.drawString("PP:Pana aici te-a adus harta. Daca vrei sa iesi de aici, urmareste florile...", xDialog, yDialog);
                    g2.drawString("Ca sa treci la nivelul 2, iti trebuie 2 diamante colectate!", xNivel1, yNivel1);

                }

            }

            if(mapID == 1){

                if (collision.dialogFlagGOAT == 1) {

                    int xDialog = tileSize * 8;
                    int yDialog = tileSize / 6;
                    int widthDialog = screenWidth - (tileSize * 8);
                    int heightDialog = tileSize * 2;


                    Color f = new Color(0, 0, 0, 200);
                    g2.setColor(f);
                    g2.fillRoundRect(xDialog, yDialog, widthDialog, heightDialog, 50, 50);


                    f = new Color(255, 255, 255);
                    g2.setColor(f);
                    g2.setStroke(new BasicStroke(5));
                    g2.drawRoundRect(xDialog + 5, yDialog + 5, widthDialog - 10, heightDialog - 10, 25, 25);


                    xDialog += tileSize;
                    yDialog += tileSize;
                    //g2.drawString("PP:Pana aici te-a adus harta. Daca vrei sa iesi de aici, urmareste florile...", xDialog, yDialog);
                    g2.drawString("Nu ataca oamenii de zapada.Intrarea in pestera este in groapa de langa stanca.", xDialog, yDialog);


                }

                if(collision.poarta2Flag == 1){
                    int xNivel2 = 0;
                    int yNivel2 = screenHeight - (tileSize * 2 + tileSize / 6);
                    int widthNivel2 = tileSize * 10;
                    int heightNivel2 = tileSize * 2;


                    Color f = new Color(255, 105, 0, 255);
                    g2.setColor(f);
                    g2.fillRoundRect(xNivel2, yNivel2, widthNivel2, heightNivel2, 50, 50);


                    f = new Color(255, 255, 255);
                    g2.setColor(f);
                    g2.setStroke(new BasicStroke(5));
                    g2.drawRoundRect(xNivel2 + 5, yNivel2 + 5, widthNivel2 - 10, heightNivel2 - 10, 25, 25);


                    xNivel2 += tileSize;
                    yNivel2 += tileSize;

                    g2.drawString("Ca sa treci la nivelul 3, iti trebuie 4 diamante colectate!", xNivel2, yNivel2);
                }

            }

            if(mapID == 2){

                int xDialog = tileSize * 8;
                int yDialog = tileSize / 6;
                int widthDialog = screenWidth - (tileSize * 16);
                int heightDialog = tileSize * 2;

                Color f = new Color(0, 0, 0, 200);
                g2.setColor(f);
                g2.fillRoundRect(xDialog, yDialog, widthDialog, heightDialog, 50, 50);


                f = new Color(255, 255, 255);
                g2.setColor(f);
                g2.setStroke(new BasicStroke(5));
                g2.drawRoundRect(xDialog + 5, yDialog + 5, widthDialog - 10, heightDialog - 10, 25, 25);


                xDialog += tileSize;
                yDialog += tileSize;
                if(lup!=null)
                    g2.drawString("Viata Lup: " + lup.getLife(), xDialog, yDialog);
                else
                    g2.drawString("Ai invins lupul!" , xDialog, yDialog);

                if (collision.comoaraFlag == 1) {


                    int xNivel3 = tileSize * 12;
                    int yNivel3 = tileSize / 6;
                    int widthNivel3 = tileSize * 8;
                    int heightNivel3 = tileSize * 2;


                    Color z = new Color(255, 165, 0, 150);
                    g2.setColor(z);
                    g2.fillRoundRect(xNivel3, yNivel3, widthNivel3, heightNivel3, 50, 50);


                    z = new Color(255, 255, 255);
                    g2.setColor(z);
                    g2.setStroke(new BasicStroke(5));
                    g2.drawRoundRect(xNivel3 + 5, yNivel3 + 5, widthNivel3 - 10, heightNivel3 - 10, 25, 25);


                    xNivel3 += tileSize;
                    yNivel3 += tileSize;

                    g2.drawString("Trebuie sa invingi lupul pentru a lua comoara!", xNivel3, yNivel3);

                }


            }

        }

        if(mapID == 3 && hallOfFameFlag==1){


            g.setColor(Color.BLACK);
            g.fillRect(0, 0, screenWidth, screenHeight);

            g.setColor(Color.green);
            Font font1 = new Font("Arial", Font.BOLD, 30);
            g.setFont(font1);
            g.drawString("Ai Furat Comoara Blestemata!", 60, 100);

            g.setColor(Color.blue);
            Font font2 = new Font("Arial", Font.ITALIC, 60);
            g.setFont(font2);
            g.drawString("Scor: " + updateScor, 60, 200);

            g.setColor(Color.red);
            Font font3 = new Font("Arial", Font.ITALIC, 60);
            g.setFont(font3);
            g.drawString("Viata: " + updateViata, 60, 300);

            g.setColor(Color.yellow);
            Font font4 = new Font("Arial", Font.BOLD, 30);
            g.setFont(font4);
            g.drawString("Apasa Q pentru a iesi" , 60, 400);


        }


        g2.dispose();



    }




}

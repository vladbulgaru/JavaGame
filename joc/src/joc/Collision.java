package joc;

import Character.Character;
import Items.Diamond;
import Items.LifeHeart;
import Character.PazPadNPC;
import Character.GoatNPC;
import Character.OmZap;
import Character.Lup;
import Character.Hero;
import Items.Projectile;
import Tiles.Tile;


//clasa unde voi implementa coliziunile dintre obiecte si tile-uri si intre obiecte si alte obiecte
public class Collision {

    GameWindow gw;
    public int dialogFlagPP = 0;
    public int dialogFlagGOAT = 0;
    public int poartaFlag = 0;
    public int poarta2Flag = 0;
    public int comoaraFlag = 0;
    public int lupColiziuneFlag=0;

    public Collision(GameWindow gw) {
        this.gw = gw;
    }


    //metode pentru coliziunea dintre erou si tile-uri
    public void collisionTileLevel1(Character character) {

       //latura din stanga a patratului solid
        int erouStangaX = character.getX() + character.solid.x;

        //latura din dreapta a patratului solid
        int erouDreaptaX = character.getX() + character.solid.x + character.solid.width;

        //latura de sus a patratului solid
        int erouSusY = character.getY() + character.solid.y;

        //latura de jos a patratului solid
        int erouJosY = character.getY() + character.solid.y + character.solid.height;


        //se calculeaza indicele coloanei din harta in care se afla latura stanga al eroului
        int erouStangaCol = erouStangaX / gw.tileSize;

        //se calculeaza indicele coloanei din harta in care se afla latura dreapta al eroului
        int erouDreaptaCol = erouDreaptaX / gw.tileSize;

        //se calculeaza indicele randului din harta in care se afla latura sus al eroului
        int erouSusRand = erouSusY / gw.tileSize;

        //se calculeaza indicele randului din harta in care se afla latura jos al eroului
        int erouJosRand = erouJosY / gw.tileSize;


        //decalaram 2 variabile pt a stoca valorile numerice ale dalelor din harta in care se afla eroul
        //daca eroul se duce in sus, va trebui sa verificam doar coltul dreapta sus si coltul stanga sus
        //daca eroul se duce in jos, va trebui sa verificam doar coltul dreapta jos si coltul stanga jos
        int tile1, tile2;



        if (character.direction == "up") {

            //se actualizeaza indicele randului din harta in care se afla eroul in f. de viteza, a.i. sa simuleze miscarea eroului in sus
            erouSusRand = (erouSusY - character.getSpeed()) / gw.tileSize;

            //se obtine valoarea numerica a dalei din harta in care se afla coltul stanga al eroului
            tile1 = gw.padIntunMap.tiles[erouStangaCol][erouSusRand];

            //se obtine valoarea numerica a dalei din harta in care se afla coltul dreapta al eroului
            tile2 = gw.padIntunMap.tiles[erouDreaptaCol][erouSusRand];


            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }


            if (Tile.tiles[tile1] == Tile.spini || Tile.tiles[tile2] == Tile.spini) {
                gw.hero.setLife(gw.hero.getLife() - 1);
                if (gw.hero.getLife() <= 0) {
                    System.out.println("Ai pierdut!");
                    System.exit(0);
                }
            }


        }
        if (character.direction == "down") {


            erouJosRand = (erouJosY + character.getSpeed()) / gw.tileSize;
            tile1 = gw.padIntunMap.tiles[erouStangaCol][erouJosRand];
            tile2 = gw.padIntunMap.tiles[erouDreaptaCol][erouJosRand];
            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }

            if (Tile.tiles[tile1] == Tile.spini || Tile.tiles[tile2] == Tile.spini) {
                gw.hero.setLife(gw.hero.getLife() - 1);
                if (gw.hero.getLife() <= 0) {
                    System.out.println("Ai pierdut!");
                    System.exit(0);
                }
            }


        }
        if (character.direction == "left") {


            erouStangaCol = (erouStangaX - character.getSpeed()) / gw.tileSize;
            tile1 = gw.padIntunMap.tiles[erouStangaCol][erouSusRand];
            tile2 = gw.padIntunMap.tiles[erouStangaCol][erouJosRand];
            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }

            if (Tile.tiles[tile1] == Tile.spini || Tile.tiles[tile2] == Tile.spini) {
                gw.hero.setLife(gw.hero.getLife() - 1);
                if (gw.hero.getLife() <= 0) {
                    System.out.println("Ai pierdut!");
                    System.exit(0);
                }
            }


        }
        if (character.direction == "right") {


            erouDreaptaCol = (erouDreaptaX + character.getSpeed()) / gw.tileSize;
            tile1 = gw.padIntunMap.tiles[erouDreaptaCol][erouSusRand];
            tile2 = gw.padIntunMap.tiles[erouDreaptaCol][erouJosRand];
            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }

            if (Tile.tiles[tile1] == Tile.spini || Tile.tiles[tile2] == Tile.spini) {
                gw.hero.setLife(gw.hero.getLife() - 1);
                if (gw.hero.getLife() <= 0) {
                    System.out.println("Ai pierdut!");
                    System.exit(0);
                }
            }


            if((Tile.tiles[tile1] == Tile.poarta || Tile.tiles[tile2] == Tile.poarta) && gw.hero.getHeroScore() != 2){
                poartaFlag = 1;
            }


            if((Tile.tiles[tile1] == Tile.poarta || Tile.tiles[tile2] == Tile.poarta) && gw.hero.getHeroScore()==2){

                DataBase.database(gw.hero);

                gw.mapID = 1;
                gw.padIntunMap = null;
                gw.pazPadNPC   = null;



                gw.hero.Spawn(Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 3, "down");
                //gw.hero.setSolidDefaultX(gw.hero.solid.x);
                //gw.hero.setSolidDefaultY(gw.hero.solid.y);
            }



        }

    }
    public void collisionTileLevel2(Character character) {


        int erouStangaX = character.getX() + character.solid.x;


        int erouDreaptaX = character.getX() + character.solid.x + character.solid.width;


        int erouSusY = character.getY() + character.solid.y;


        int erouJosY = character.getY() + character.solid.y + character.solid.height;



        int erouStangaCol = erouStangaX / gw.tileSize;


        int erouDreaptaCol = erouDreaptaX / gw.tileSize;


        int erouSusRand = erouSusY / gw.tileSize;


        int erouJosRand = erouJosY / gw.tileSize;



        int tile1, tile2;



        if (character.direction == "up") {


            erouSusRand = (erouSusY - character.getSpeed()) / gw.tileSize;


            tile1 = gw.munte.tiles[erouStangaCol][erouSusRand];


            tile2 = gw.munte.tiles[erouDreaptaCol][erouSusRand];


            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }

            if (Tile.tiles[tile1] == Tile.capcana2 || Tile.tiles[tile2] == Tile.capcana2) {

                System.out.println("Ai fost prins in capcana! Ai pierdut!");
                System.exit(0);

            }



        }
        if (character.direction == "down") {


            erouJosRand = (erouJosY + character.getSpeed()) / gw.tileSize;
            tile1 = gw.munte.tiles[erouStangaCol][erouJosRand];
            tile2 = gw.munte.tiles[erouDreaptaCol][erouJosRand];
            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }

            if (Tile.tiles[tile1] == Tile.capcana2 || Tile.tiles[tile2] == Tile.capcana2) {

                System.out.println("Ai fost prins in capcana! Ai pierdut!");
                System.exit(0);

            }



        }
        if (character.direction == "left") {


            erouStangaCol = (erouStangaX - character.getSpeed()) / gw.tileSize;
            tile1 = gw.munte.tiles[erouStangaCol][erouSusRand];
            tile2 = gw.munte.tiles[erouStangaCol][erouJosRand];
            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }

            if (Tile.tiles[tile1] == Tile.capcana2 || Tile.tiles[tile2] == Tile.capcana2) {

                System.out.println("Ai fost prins in capcana! Ai pierdut!");
                System.exit(0);

            }


            if (Tile.tiles[tile1] == Tile.groapa || Tile.tiles[tile2] == Tile.groapa) {

                System.out.println("Ai cazut in groapa! Ai pierdut!");
                System.exit(0);

            }





        }
        if (character.direction == "right") {


            erouDreaptaCol = (erouDreaptaX + character.getSpeed()) / gw.tileSize;
            tile1 = gw.munte.tiles[erouDreaptaCol][erouSusRand];
            tile2 = gw.munte.tiles[erouDreaptaCol][erouJosRand];
            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }

            if (Tile.tiles[tile1] == Tile.capcana2 || Tile.tiles[tile2] == Tile.capcana2) {

                System.out.println("Ai fost prins in capcana! Ai pierdut!");
                System.exit(0);

            }

            if((Tile.tiles[tile1] == Tile.poarta2 || Tile.tiles[tile2] == Tile.poarta2) && (gw.hero.getHeroScore() >= 2) && (gw.hero.getHeroScore() < 4)){
                poarta2Flag = 1;
            }

            if((Tile.tiles[tile1] == Tile.poarta2 || Tile.tiles[tile2] == Tile.poarta2) && gw.hero.getHeroScore()==4){

                gw.mapID     = 2;
                gw.munte     = null;
                gw.goatNPC   = null;

                DataBase.database(gw.hero);

                gw.hero.Spawn(Tile.TILE_WIDTH*2, Tile.TILE_HEIGHT*18, 3, "down");

            }

        }

    }
    public void collisionTileLevel3(Character character) {


        int erouStangaX = character.getX() + character.solid.x;


        int erouDreaptaX = character.getX() + character.solid.x + character.solid.width;


        int erouSusY = character.getY() + character.solid.y;


        int erouJosY = character.getY() + character.solid.y + character.solid.height;


        int erouStangaCol = erouStangaX / gw.tileSize;


        int erouDreaptaCol = erouDreaptaX / gw.tileSize;


        int erouSusRand = erouSusY / gw.tileSize;


        int erouJosRand = erouJosY / gw.tileSize;



        int tile1, tile2;



        if (character.direction == "up") {


            erouSusRand = (erouSusY - character.getSpeed()) / gw.tileSize;


            tile1 = gw.pestera.tiles[erouStangaCol][erouSusRand];


            tile2 = gw.pestera.tiles[erouDreaptaCol][erouSusRand];


            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }

            if (Tile.tiles[tile1] == Tile.capcana3 || Tile.tiles[tile2] == Tile.capcana3) {

                System.out.println("Ai fost prins in capcana! Ai pierdut!");
                System.exit(0);

            }

            if (Tile.tiles[tile1] == Tile.lava || Tile.tiles[tile2] == Tile.lava) {
                gw.hero.setLife(gw.hero.getLife() - 1);
                if (gw.hero.getLife() <= 0) {
                    System.out.println("Ai pierdut!");
                    System.exit(0);
                }
            }

        }
        if (character.direction == "down") {


            erouJosRand = (erouJosY + character.getSpeed()) / gw.tileSize;
            tile1 = gw.pestera.tiles[erouStangaCol][erouJosRand];
            tile2 = gw.pestera.tiles[erouDreaptaCol][erouJosRand];
            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }

            if (Tile.tiles[tile1] == Tile.capcana3 || Tile.tiles[tile2] == Tile.capcana3) {

                System.out.println("Ai fost prins in capcana! Ai pierdut!");
                System.exit(0);

            }

            if (Tile.tiles[tile1] == Tile.lava || Tile.tiles[tile2] == Tile.lava) {
                gw.hero.setLife(gw.hero.getLife() - 1);
                if (gw.hero.getLife() <= 0) {
                    System.out.println("Ai pierdut!");
                    System.exit(0);
                }
            }

        }
        if (character.direction == "left") {


            erouStangaCol = (erouStangaX - character.getSpeed()) / gw.tileSize;
            tile1 = gw.pestera.tiles[erouStangaCol][erouSusRand];
            tile2 = gw.pestera.tiles[erouStangaCol][erouJosRand];
            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }

            if(Tile.tiles[tile1] == Tile.apa || Tile.tiles[tile2] == Tile.apa){
                gw.hero.setLife(100);
            }

            if (Tile.tiles[tile1] == Tile.capcana3 || Tile.tiles[tile2] == Tile.capcana3) {

                System.out.println("Ai fost prins in capcana! Ai pierdut!");
                System.exit(0);

            }


            if (Tile.tiles[tile1] == Tile.lava || Tile.tiles[tile2] == Tile.lava) {
                gw.hero.setLife(gw.hero.getLife() - 1);
                if (gw.hero.getLife() <= 0) {
                    System.out.println("Ai pierdut!");
                    System.exit(0);
                }
            }



        }
        if (character.direction == "right") {


            erouDreaptaCol = (erouDreaptaX + character.getSpeed()) / gw.tileSize;
            tile1 = gw.pestera.tiles[erouDreaptaCol][erouSusRand];
            tile2 = gw.pestera.tiles[erouDreaptaCol][erouJosRand];
            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }

            if (Tile.tiles[tile1] == Tile.capcana3 || Tile.tiles[tile2] == Tile.capcana3) {

                System.out.println("Ai fost prins in capcana! Ai pierdut!");
                System.exit(0);

            }

            if(gw.lup!=null) {
                if ((Tile.tiles[tile1] == Tile.comoara || Tile.tiles[tile2] == Tile.comoara) && gw.lup.getLife() != 0) {

                    comoaraFlag = 1;

                }
            }
            else if ((Tile.tiles[tile1] == Tile.comoara || Tile.tiles[tile2] == Tile.comoara) ) {


                        gw.mapID = 3;

                        gw.pestera = null;

                        DataBase.database(gw.hero);

                        gw.hero = null;

                        if(gw.diamond5!=null)
                            gw.diamond5 = null;

                        System.out.println("Ai castigat!");
                        gw.hallOfFameFlag = 1;



                }



            if(Tile.tiles[tile1] == Tile.apa || Tile.tiles[tile2] == Tile.apa){
                gw.hero.setLife(100);
            }
            if (Tile.tiles[tile1] == Tile.lava || Tile.tiles[tile2] == Tile.lava) {
                gw.hero.setLife(gw.hero.getLife() - 1);
                if (gw.hero.getLife() <= 0) {
                    System.out.println("Ai pierdut!");
                    System.exit(0);
                }
            }

        }

    }

    //metoda pentru coliziunea dintre lup si tile-uri
    public void collisionTileLevel3Lup(Character character) {


        int lupStangaX = character.getX() + character.solid.x;


        int lupDreaptaX = character.getX() + character.solid.x + character.solid.width;


        int lupSusY = character.getY() + character.solid.y;


        int lupJosY = character.getY() + character.solid.y + character.solid.height;



        int lupStangaCol = lupStangaX / gw.tileSize;


        int lupDreaptaCol = lupDreaptaX / gw.tileSize;


        int lupSusRand = lupSusY / gw.tileSize;


        int lupJosRand = lupJosY / gw.tileSize;



        int tile1, tile2;



        if (character.direction == "up") {


            lupSusRand = (lupSusY - character.getSpeed()) / gw.tileSize;


            tile1 = gw.pestera.tiles[lupStangaCol][lupSusRand];


            tile2 = gw.pestera.tiles[lupDreaptaCol][lupSusRand];


            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }


        }
        if (character.direction == "down") {


            lupJosRand = (lupJosY + character.getSpeed()) / gw.tileSize;
            tile1 = gw.pestera.tiles[lupStangaCol][lupJosRand];
            tile2 = gw.pestera.tiles[lupDreaptaCol][lupJosRand];
            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }


        }
        if (character.direction == "left") {


            lupStangaCol = (lupStangaX - character.getSpeed()) / gw.tileSize;
            tile1 = gw.pestera.tiles[lupStangaCol][lupSusRand];
            tile2 = gw.pestera.tiles[lupStangaCol][lupJosRand];
            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }




        }
        if (character.direction == "right") {


            lupDreaptaCol = (lupDreaptaX + character.getSpeed()) / gw.tileSize;
            tile1 = gw.pestera.tiles[lupDreaptaCol][lupSusRand];
            tile2 = gw.pestera.tiles[lupDreaptaCol][lupJosRand];
            if (Tile.tiles[tile1].isSolid() || Tile.tiles[tile2].isSolid()) {
                character.collisionFlag = true;
            }




        }

    }

    //metoda pentru coliziunea dintre erou si obiecte
    public void OmZapsCollision(OmZap[] omZaps) {

        for (int i = 0; i < omZaps.length; i++) {
            gw.hero.solid.x = gw.hero.getX() + gw.hero.solid.x;
            gw.hero.solid.y = gw.hero.getY() + gw.hero.solid.y;

            omZaps[i].solid.x = omZaps[i].getX() + omZaps[i].solid.x;
            omZaps[i].solid.y = omZaps[i].getY() + omZaps[i].solid.y;


            //if (gw.hero.getX() < gw.omZap1.getX() + omZap1.getOmzWidth() && gw.hero.getX() + gw.hero.getHeroWidth() > omZap1.getX() && gw.hero.getY() < omZap1.getY() + omZap1.getOmzHeight() && gw.hero.getY() + gw.hero.getHeroHeight() > omZap1.getY()) {

            switch (gw.hero.direction) {
                case "up":
                    gw.hero.solid.y -= gw.hero.getSpeed();
                    if (gw.hero.solid.intersects(omZaps[i].solid)) {
                        gw.hero.collisionFlag = true;
                    }
                    break;
                case "down":
                    gw.hero.solid.y += gw.hero.getSpeed();
                    if (gw.hero.solid.intersects(omZaps[i].solid)) {
                        gw.hero.collisionFlag = true;
                    }
                    break;
                case "left":
                    gw.hero.solid.x -= gw.hero.getSpeed();
                    if (gw.hero.solid.intersects(omZaps[i].solid)) {
                        gw.hero.collisionFlag = true;
                    }
                    break;
                case "right":
                    gw.hero.solid.x += gw.hero.getSpeed();
                    if (gw.hero.solid.intersects(omZaps[i].solid)) {
                        gw.hero.collisionFlag = true;
                    }
                    break;
            }


            //}


            //vom reseta x si y cu valorile initiale
            gw.hero.solid.x = gw.hero.getSolidDefaultX();
            gw.hero.solid.y = gw.hero.getSolidDefaultY();

            omZaps[i].solid.x = omZaps[i].getSolidDefaultX();
            omZaps[i].solid.y = omZaps[i].getSolidDefaultY();
        }
    }
    public void LupCollision(Lup lup){

        if(lup!=null) {

            gw.hero.solid.x = gw.hero.getX() + gw.hero.solid.x;
            gw.hero.solid.y = gw.hero.getY() + gw.hero.solid.y;

            lup.solid.x = lup.getX() + lup.solid.x;
            lup.solid.y = lup.getY() + lup.solid.y;


            //if (gw.hero.getX() < gw.omZap1.getX() + omZap1.getOmzWidth() && gw.hero.getX() + gw.hero.getHeroWidth() > omZap1.getX() && gw.hero.getY() < omZap1.getY() + omZap1.getOmzHeight() && gw.hero.getY() + gw.hero.getHeroHeight() > omZap1.getY()) {

            switch (gw.hero.direction) {
                case "up":
                    lupColiziuneFlag = 0;
                    gw.hero.solid.y -= gw.hero.getSpeed();

                    if (gw.hero.solid.intersects(lup.solid)) {
                        gw.hero.collisionFlag = true;
                        lupColiziuneFlag = 1;

                    }
                    break;
                case "down":
                    lupColiziuneFlag = 0;
                    gw.hero.solid.y += gw.hero.getSpeed();
                    if (gw.hero.solid.intersects(lup.solid)) {
                        gw.hero.collisionFlag = true;
                        lupColiziuneFlag = 1;


                    }
                    break;
                case "left":
                    lupColiziuneFlag = 0;
                    gw.hero.solid.x -= gw.hero.getSpeed();
                    if (gw.hero.solid.intersects(lup.solid)) {
                        gw.hero.collisionFlag = true;
                        lupColiziuneFlag = 1;

                    }
                    break;
                case "right":
                    lupColiziuneFlag = 0;
                    gw.hero.solid.x += gw.hero.getSpeed();
                    if (gw.hero.solid.intersects(lup.solid)) {
                        gw.hero.collisionFlag = true;
                        lupColiziuneFlag = 1;

                    }
                    break;
            }


            //}


            //vom reseta x si y cu valorile initiale
            gw.hero.solid.x = gw.hero.getSolidDefaultX();
            gw.hero.solid.y = gw.hero.getSolidDefaultY();

            lup.solid.x = lup.getSolidDefaultX();
            lup.solid.y = lup.getSolidDefaultY();
        }

    }
    public void HeroCollision(Hero hero){

        gw.lup.solid.x = gw.lup.getX() + gw.lup.solid.x;
        gw.lup.solid.y = gw.lup.getY() + gw.lup.solid.y;

        hero.solid.x = hero.getX() + hero.solid.x;
        hero.solid.y = hero.getY() + hero.solid.y;





        switch (gw.lup.direction) {
            case "up":
                gw.lup.solid.y -= gw.lup.getSpeed();
                if (gw.lup.solid.intersects(hero.solid)) {
                    gw.lup.collisionFlag = true;
                    if(!hero.muscaturaLup){
                        hero.setLife(hero.getLife()-25);
                        hero.muscaturaLup = true;
                        if(hero.getLife() <= 0){
                            System.out.println("lupul te-a invins!");
                            System.exit(0);
                        }
                    }

                }
                break;
            case "down":
                gw.lup.solid.y += gw.lup.getSpeed();
                if (gw.lup.solid.intersects(hero.solid)) {
                    gw.lup.collisionFlag = true;
                    if(!gw.hero.muscaturaLup){
                        gw.hero.setLife(gw.hero.getLife()-25);
                        gw.hero.muscaturaLup = true;
                        if(hero.getLife() <= 0){
                            System.out.println("lupul te-a invins!");
                            System.exit(0);
                        }
                    }


                }
                break;
            case "left":
                gw.lup.solid.x -= gw.lup.getSpeed();
                if (gw.lup.solid.intersects(hero.solid)) {
                    gw.lup.collisionFlag = true;
                    if(!gw.hero.muscaturaLup){
                        gw.hero.setLife(gw.hero.getLife()-25);
                        gw.hero.muscaturaLup = true;
                        if(hero.getLife() <= 0){
                            System.out.println("lupul te-a invins!");
                            System.exit(0);
                        }
                    }

                }
                break;
            case "right":
                gw.lup.solid.x += gw.lup.getSpeed();
                if (gw.lup.solid.intersects(hero.solid)) {
                    gw.lup.collisionFlag = true;
                    if(!gw.hero.muscaturaLup){
                        gw.hero.setLife(gw.hero.getLife()-25);
                        gw.hero.muscaturaLup = true;
                        if(hero.getLife() <= 0){
                            System.out.println("lupul te-a invins!");
                            System.exit(0);
                        }
                    }


                }
                break;
        }


        //}


        //vom reseta x si y cu valorile initiale
        gw.lup.solid.x = gw.lup.getSolidDefaultX();
        gw.lup.solid.y = gw.lup.getSolidDefaultY();

        hero.solid.x = hero.getSolidDefaultX();
        hero.solid.y = hero.getSolidDefaultY();

    }
    public boolean coliziuneDiamant1(Diamond diamond1) {
        if (diamond1 != null)
            if (gw.hero.getX() < gw.diamond1.getX() + gw.diamond1.solid.width && gw.hero.getX() + gw.hero.getHeroWidth() > gw.diamond1.getX() && gw.hero.getY() < gw.diamond1.getY() + gw.diamond1.solid.height && gw.hero.getY() + gw.hero.getHeroHeight() > gw.diamond1.getY()) {
                return true;
            }
        return false;

    }
    public boolean coliziuneDiamant2(Diamond diamond2) {
        if (diamond2 != null)
            if (gw.hero.getX() < gw.diamond2.getX() + gw.diamond2.solid.width && gw.hero.getX() + gw.hero.getHeroWidth() > gw.diamond2.getX() && gw.hero.getY() < gw.diamond2.getY() + gw.diamond2.solid.height && gw.hero.getY() + gw.hero.getHeroHeight() > gw.diamond2.getY()) {
                return true;
            }
        return false;

    }
    public boolean coliziuneDiamant3(Diamond diamond3) {
        if (diamond3 != null)
            if (gw.hero.getX() < gw.diamond3.getX() + gw.diamond3.solid.width && gw.hero.getX() + gw.hero.getHeroWidth() > gw.diamond3.getX() && gw.hero.getY() < gw.diamond3.getY() + gw.diamond3.solid.height && gw.hero.getY() + gw.hero.getHeroHeight() > gw.diamond3.getY()) {
                return true;
            }
        return false;

    }
    public boolean coliziuneDiamant4(Diamond diamond4) {
        if (diamond4 != null)
            if (gw.hero.getX() < gw.diamond4.getX() + gw.diamond4.solid.width && gw.hero.getX() + gw.hero.getHeroWidth() > gw.diamond4.getX() && gw.hero.getY() < gw.diamond4.getY() + gw.diamond4.solid.height && gw.hero.getY() + gw.hero.getHeroHeight() > gw.diamond4.getY()) {
                return true;
            }
        return false;

    }
    public boolean coliziuneDiamant5(Diamond diamond5){
        if (diamond5 != null)
            if (gw.hero.getX() < diamond5.getX() + diamond5.solid.width && gw.hero.getX() + gw.hero.getHeroWidth() > diamond5.getX() && gw.hero.getY() < diamond5.getY() + diamond5.solid.height && gw.hero.getY() + gw.hero.getHeroHeight() > diamond5.getY()) {
                return true;
            }
        return false;
    }
    public boolean coliziuneViata1(LifeHeart lifeHeart1) {
        if (lifeHeart1 != null)
            if (gw.hero.getX() < gw.lifeHeart1.getX() + gw.lifeHeart1.solid.width && gw.hero.getX() + gw.hero.getHeroWidth() > gw.lifeHeart1.getX() && gw.hero.getY() < gw.lifeHeart1.getY() + gw.lifeHeart1.solid.height && gw.hero.getY() + gw.hero.getHeroHeight() > gw.lifeHeart1.getY()) {

                return true;
            }
        return false;

    }
    public boolean coliziuneViata2(LifeHeart lifeHeart2) {
        if (lifeHeart2 != null)
            if (gw.hero.getX() < gw.lifeHeart2.getX() + gw.lifeHeart2.solid.width && gw.hero.getX() + gw.hero.getHeroWidth() > gw.lifeHeart2.getX() && gw.hero.getY() < gw.lifeHeart2.getY() + gw.lifeHeart2.solid.height && gw.hero.getY() + gw.hero.getHeroHeight() > gw.lifeHeart2.getY()) {

                return true;
            }
        return false;

    }
    public boolean coliziuneProiectilStanga(Projectile[] projectilesLeft){
        for(int i = 0; i<projectilesLeft.length; i++) {
            if (projectilesLeft[i] != null)
                if (gw.hero.getX() < projectilesLeft[i].xPro + projectilesLeft[i].solidPro.width && gw.hero.getX() + gw.hero.getHeroWidth() > projectilesLeft[i].xPro && gw.hero.getY() < projectilesLeft[i].yPro + projectilesLeft[i].solidPro.height && gw.hero.getY() + gw.hero.getHeroHeight() > projectilesLeft[i].yPro){
                    return true;
                }
        }
        return false;
    }
    public boolean coliziuneProiectilDreapta(Projectile[] projectilesRight){
        for(int i = 0; i<projectilesRight.length; i++) {
            if (projectilesRight[i] != null)
                if (gw.hero.getX() < projectilesRight[i].xPro + projectilesRight[i].solidPro.width && gw.hero.getX() + gw.hero.getHeroWidth() > projectilesRight[i].xPro && gw.hero.getY() < projectilesRight[i].yPro + projectilesRight[i].solidPro.height && gw.hero.getY() + gw.hero.getHeroHeight() > projectilesRight[i].yPro){
                    return true;
                }
        }
        return false;
    }
    public void PPcollision(PazPadNPC pazPadNPC) {
        int flagUp = 0;
        if (pazPadNPC != null)
            if (gw.hero.getX() < gw.pazPadNPC.getX() + pazPadNPC.getPpWidth() && gw.hero.getX() + gw.hero.getHeroWidth() > pazPadNPC.getX() && gw.hero.getY() < pazPadNPC.getY() + pazPadNPC.getPpHeight() && gw.hero.getY() + gw.hero.getHeroHeight() > pazPadNPC.getY()) {

                if(gw.hero.direction == "up"){
                    gw.hero.solid.y -= gw.hero.getSpeed();
                    if (gw.hero.solid.intersects(gw.pazPadNPC.solid)) {
                        gw.hero.collisionFlag = true;
                        flagUp++;

                    }
                }


            }
        if(flagUp!=0){
            dialogFlagPP = 1;
        }


        //vom reseta x si y cu valorile initiale
        gw.hero.solid.x =  gw.hero.getSolidDefaultX();
        gw.hero.solid.y =  gw.hero.getSolidDefaultY();

        //gw.pazPadNPC.solid.x = gw.pazPadNPC.getSolidDefaultX();
        //gw.pazPadNPC.solid.y = gw.pazPadNPC.getSolidDefaultY();

    }
    public void GoatCollision(GoatNPC goatNPC) {
        int flagUp = 0;
        if (goatNPC != null)
            //if (gw.hero.getX() < goatNPC.getX() + goatNPC.getGoatWidth() && gw.hero.getX() + gw.hero.getHeroWidth() > goatNPC.getX() && gw.hero.getY() < goatNPC.getY() + goatNPC.getGoatHeight() && gw.hero.getY() + gw.hero.getHeroHeight() > goatNPC.getY()) {
            if (gw.hero.getX() < goatNPC.getX() + goatNPC.solid.width && gw.hero.getX() + gw.hero.getHeroWidth() > goatNPC.getX() && gw.hero.getY() < goatNPC.getY() + goatNPC.solid.height && gw.hero.getY() + gw.hero.getHeroHeight() > goatNPC.getY()) {
                if(gw.hero.direction == "up"){
                    gw.hero.solid.y -= gw.hero.getSpeed();
                    if (gw.hero.solid.intersects(goatNPC.solid)) {
                        gw.hero.collisionFlag = true;
                        flagUp++;

                    }
                }


            }
        if(flagUp!=0){
            dialogFlagGOAT = 1;
        }


        //vom reseta x si y cu valorile initiale
        gw.hero.solid.x =  gw.hero.getSolidDefaultX();
        gw.hero.solid.y =  gw.hero.getSolidDefaultY();

        //gw.pazPadNPC.solid.x = gw.pazPadNPC.getSolidDefaultX();
        //gw.pazPadNPC.solid.y = gw.pazPadNPC.getSolidDefaultY();

    }

}
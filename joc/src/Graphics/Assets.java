package Graphics;

import java.awt.image.BufferedImage;


public class Assets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage heroRight1;
    public static BufferedImage heroRight2;
    public static BufferedImage heroUp1;
    public static BufferedImage heroUp2;
    public static BufferedImage heroLeft1;
    public static BufferedImage heroLeft2;
    public static BufferedImage heroDown1;
    public static BufferedImage heroDown2;

    public static BufferedImage heroAttackUp1;
    public static BufferedImage heroAttackUp2;
    public static BufferedImage heroAttackDown1;
    public static BufferedImage heroAttackDown2;
    public static BufferedImage heroAttackLeft1;
    public static BufferedImage heroAttackLeft2;
    public static BufferedImage heroAttackRight1;
    public static BufferedImage heroAttackRight2;

    public static BufferedImage PazPad;

    public static BufferedImage tree;
    public static BufferedImage grass;
    public static BufferedImage flowers;
    public static BufferedImage spini;
    public static BufferedImage diamond;
    public static BufferedImage lifeHeart;

    public static BufferedImage zapada;
    public static BufferedImage copacZapada;
    public static BufferedImage poarta;
    public static BufferedImage goat;
    public static BufferedImage poarta2;
    public static BufferedImage groapa;
    public static BufferedImage omZapada;
    public static BufferedImage projectile;

    public static BufferedImage pietre;
    public static BufferedImage lava;
    public static BufferedImage comoara;
    public static BufferedImage negru;
    public static BufferedImage capcana1;
    public static BufferedImage capcana2;
    public static BufferedImage capcana3;
    public static BufferedImage apa;

    public static BufferedImage lupIdle;
    public static BufferedImage lupUp1;
    public static BufferedImage lupUp2;
    public static BufferedImage lupDown1;
    public static BufferedImage lupDown2;
    public static BufferedImage lupLeft1;
    public static BufferedImage lupLeft2;
    public static BufferedImage lupRight1;
    public static BufferedImage lupRight2;


     /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */

    public static void Init(){
    /// Se creeaza temporar 6 obiecte SpriteSheet initializate prin intermediul clasei ImageLoader
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.LoadImage("/textures/CharacterSprite.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.LoadImage("/textures/TileSprite.png"));
        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.LoadImage("/textures/TileSprite(2).png"));
        SpriteSheet sheet4 = new SpriteSheet(ImageLoader.LoadImage("/textures/nivel3.png"));
        SpriteSheet sheet5 = new SpriteSheet(ImageLoader.LoadImage("/textures/SpriteSheetFinal.png"));
        SpriteSheet sheet6 = new SpriteSheet(ImageLoader.LoadImage("/textures/CharacterAttack.png"));

        lupIdle   = sheet5.crop(3,3);
        lupUp1    = sheet5.crop(1,3);
        lupUp2    = sheet5.crop(2,3);
        lupDown1  = sheet5.crop(3,1);
        lupDown2  = sheet5.crop(0,2);
        lupRight1 = sheet5.crop(3,2);
        lupRight2 = sheet5.crop(0,3);
        lupLeft1  = sheet5.crop(1,2);
        lupLeft2  = sheet5.crop(2,2);

        heroRight1 = sheet1.crop(0,2);
        heroRight2 = sheet1.crop(2,1);
        heroLeft1  = sheet1.crop(1,2);
        heroLeft2  = sheet1.crop(2,2);
        heroUp1    = sheet1.crop(0,1);
        heroUp2    = sheet1.crop(1,1);
        heroDown1  = sheet1.crop(1,0);
        heroDown2  = sheet1.crop(2,0);

        heroAttackUp1    = sheet6.crop(1,1);
        heroAttackUp2    = sheet6.crop(2,1);
        heroAttackDown1  = sheet6.crop(0,2);
        heroAttackDown2  = sheet6.crop(1,2);
        heroAttackLeft1  = sheet6.crop(2,0);
        heroAttackLeft2  = sheet6.crop(0,1);
        heroAttackRight1 = sheet6.crop(0,0);
        heroAttackRight2 = sheet6.crop(1,0);


        PazPad     = sheet1.crop(0,0);

        tree       = sheet2.crop(2,0);
        grass      = sheet2.crop(0,0);
        flowers    = sheet2.crop(1,0);
        spini      = sheet2.crop(0,1);
        diamond    = sheet2.crop(2,1);
        lifeHeart  = sheet2.crop(0,2);


        zapada = sheet3.crop(0,0);

        copacZapada = sheet5.crop(2,1);

        poarta = sheet3.crop(0,2);
        goat = sheet3.crop(1,2);

        groapa = sheet5.crop(0,1);

        poarta2 = sheet3.crop(2,0);
        omZapada = sheet3.crop(1,1);
        projectile = sheet3.crop(2,1);



        pietre = sheet4.crop(1,0);
        lava = sheet4.crop(0,1);
        comoara = sheet4.crop(1,1);

        negru = sheet5.crop(0,0);

        capcana1 = sheet5.crop(2,0);
        capcana2 = sheet5.crop(1,0);
        capcana3 = sheet5.crop(3,0);
        apa = sheet5.crop(1,1);






    }

}

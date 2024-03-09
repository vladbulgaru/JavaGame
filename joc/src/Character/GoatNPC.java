package Character;

import Tiles.Tile;
import joc.GameWindow;
import Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GoatNPC extends Character{

    private static final int GOAT_WIDTH=50;
    private static final int GOAT_HEIGHT=50;

    public int getGoatWidth(){
        return GOAT_WIDTH;
    }
    public int getGoatHeight(){
        return GOAT_HEIGHT;
    }

    public GoatNPC(GameWindow gw){

        super(gw);

        solid = new Rectangle(0,0, (GOAT_WIDTH/10)*8, (GOAT_HEIGHT/10)*8);

        Spawn();//apelam constructorul
        Assets.Init();//apelam functia Init() din clasa Assets
        getImage();//apelam functia
    }

    public void Spawn(){
        setX(Tile.TILE_WIDTH*9);
        setY(0);
    }

    public void getImage(){
        capra = Assets.goat;
    }


    public void Update(){


    }
    public void Draw(Graphics2D g2){

        BufferedImage img = capra;
        g2.drawImage(img, getX(), getY(), GOAT_WIDTH, GOAT_HEIGHT, null);

    }

    public boolean isSolid(){
        return true;
    }


}
